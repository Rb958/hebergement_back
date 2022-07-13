package com.lsd.logement.controller.impl;

import com.lsd.logement.entity.finance.TransfertCaisse;
import com.lsd.logement.entity.controller.impl.TransfertCaisseControllerImpl;
import com.lsd.logement.mapper.ReferenceMapper;
import com.lsd.logement.mapper.TransfertCaisseMapper;
import com.lsd.logement.service.TransfertCaisseService;
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
public class TransfertCaisseControllerImplTest {
    //TODO: create the data Test generator class TransfertCaisseBuilder
    private static final String ENDPOINT_URL = "/transfert-caisses";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private TransfertCaisseControllerImpl transfertcaisseController;
    @MockBean
    private TransfertCaisseService transfertcaisseService;
    @MockBean
    private TransfertCaisseMapper transfertcaisseMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.transfertcaisseController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(transfertcaisseMapper.asDTOList(ArgumentMatchers.any())).thenReturn(TransfertCaisseBuilder.getListDTO());

        Mockito.when(transfertcaisseService.findAll()).thenReturn(TransfertCaisseBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(transfertcaisseMapper.asDTO(ArgumentMatchers.any())).thenReturn(TransfertCaisseBuilder.getDTO());

        Mockito.when(transfertcaisseService.findById(ArgumentMatchers.anyInteger())).thenReturn(java.util.Optional.of(TransfertCaisseBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(transfertcaisseService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(transfertcaisseService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(transfertcaisseMapper.asEntity(ArgumentMatchers.any())).thenReturn(TransfertCaisseBuilder.getEntity());
        Mockito.when(transfertcaisseService.save(ArgumentMatchers.any(TransfertCaisse.class))).thenReturn(TransfertCaisseBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(TransfertCaisseBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(transfertcaisseService, Mockito.times(1)).save(ArgumentMatchers.any(TransfertCaisse.class));
        Mockito.verifyNoMoreInteractions(transfertcaisseService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(transfertcaisseMapper.asEntity(ArgumentMatchers.any())).thenReturn(TransfertCaisseBuilder.getEntity());
        Mockito.when(transfertcaisseService.update(ArgumentMatchers.any(), ArgumentMatchers.anyInteger())).thenReturn(TransfertCaisseBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(TransfertCaisseBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(transfertcaisseService, Mockito.times(1)).update(ArgumentMatchers.any(TransfertCaisse.class), ArgumentMatchers.anyInteger());
        Mockito.verifyNoMoreInteractions(transfertcaisseService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(transfertcaisseService).deleteById(ArgumentMatchers.anyInteger());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(transfertcaisseService, Mockito.times(1)).deleteById(Mockito.anyInteger());
        Mockito.verifyNoMoreInteractions(transfertcaisseService);
    }