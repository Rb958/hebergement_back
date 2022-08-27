package com.lsd.logement.controller.impl;

import com.lsd.logement.entity.reservation.Bail;
import com.lsd.logement.mapper.BailMapper;
import com.lsd.logement.mapper.ReferenceMapper;
import com.lsd.logement.service.BailService;
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
public class BailControllerImplTest {
    //TODO: create the data Test generator class BailBuilder
    private static final String ENDPOINT_URL = "/bails";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private BailControllerImpl bailController;
    @MockBean
    private BailService bailService;
    @MockBean
    private BailMapper bailMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.bailController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(bailMapper.asDTOList(ArgumentMatchers.any())).thenReturn(BailBuilder.getListDTO());

        Mockito.when(bailService.findAll()).thenReturn(BailBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(bailMapper.asDTO(ArgumentMatchers.any())).thenReturn(BailBuilder.getDTO());

        Mockito.when(bailService.findById(ArgumentMatchers.anyInteger())).thenReturn(java.util.Optional.of(BailBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(bailService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(bailService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(bailMapper.asEntity(ArgumentMatchers.any())).thenReturn(BailBuilder.getEntity());
        Mockito.when(bailService.save(ArgumentMatchers.any(Bail.class))).thenReturn(BailBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(BailBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(bailService, Mockito.times(1)).save(ArgumentMatchers.any(Bail.class));
        Mockito.verifyNoMoreInteractions(bailService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(bailMapper.asEntity(ArgumentMatchers.any())).thenReturn(BailBuilder.getEntity());
        Mockito.when(bailService.update(ArgumentMatchers.any(), ArgumentMatchers.anyInteger())).thenReturn(BailBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(BailBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(bailService, Mockito.times(1)).update(ArgumentMatchers.any(Bail.class), ArgumentMatchers.anyInteger());
        Mockito.verifyNoMoreInteractions(bailService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(bailService).deleteById(ArgumentMatchers.anyInteger());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(bailService, Mockito.times(1)).deleteById(Mockito.anyInteger());
        Mockito.verifyNoMoreInteractions(bailService);
    }