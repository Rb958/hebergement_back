package com.lsd.logement.controller.impl;

import com.lsd.logement.entity.finance.Caisse;
import com.lsd.logement.mapper.CaisseMapper;
import com.lsd.logement.mapper.ReferenceMapper;
import com.lsd.logement.service.CaisseService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class CaisseControllerImplTest {
    //TODO: create the data Test generator class CaisseBuilder
    private static final String ENDPOINT_URL = "/caisses";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private CaisseControllerImpl caisseController;
    @MockBean
    private CaisseService caisseService;
    @MockBean
    private CaisseMapper caisseMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.caisseController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(caisseMapper.asDTOList(ArgumentMatchers.any())).thenReturn(CaisseBuilder.getListDTO());

        Mockito.when(caisseService.findAll()).thenReturn(CaisseBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(caisseMapper.asDTO(ArgumentMatchers.any())).thenReturn(CaisseBuilder.getDTO());

        Mockito.when(caisseService.findById(ArgumentMatchers.anyInteger())).thenReturn(java.util.Optional.of(CaisseBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(caisseService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(caisseService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(caisseMapper.asEntity(ArgumentMatchers.any())).thenReturn(CaisseBuilder.getEntity());
        Mockito.when(caisseService.save(ArgumentMatchers.any(Caisse.class))).thenReturn(CaisseBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(CaisseBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(caisseService, Mockito.times(1)).save(ArgumentMatchers.any(Caisse.class));
        Mockito.verifyNoMoreInteractions(caisseService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(caisseMapper.asEntity(ArgumentMatchers.any())).thenReturn(CaisseBuilder.getEntity());
        Mockito.when(caisseService.update(ArgumentMatchers.any(), ArgumentMatchers.anyInteger())).thenReturn(CaisseBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(CaisseBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(caisseService, Mockito.times(1)).update(ArgumentMatchers.any(Caisse.class), ArgumentMatchers.anyInteger());
        Mockito.verifyNoMoreInteractions(caisseService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(caisseService).deleteById(ArgumentMatchers.anyInteger());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(caisseService, Mockito.times(1)).deleteById(Mockito.anyInteger());
        Mockito.verifyNoMoreInteractions(caisseService);
    }