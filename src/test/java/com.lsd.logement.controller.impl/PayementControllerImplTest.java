package com.lsd.logement.controller.impl;

import com.lsd.logement.entity.finance.Payement;
import com.lsd.logement.mapper.PayementMapper;
import com.lsd.logement.mapper.ReferenceMapper;
import com.lsd.logement.service.PayementService;
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
public class PayementControllerImplTest {
    //TODO: create the data Test generator class PayementBuilder
    private static final String ENDPOINT_URL = "/payements";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private PayementControllerImpl payementController;
    @MockBean
    private PayementService payementService;
    @MockBean
    private PayementMapper payementMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.payementController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(payementMapper.asDTOList(ArgumentMatchers.any())).thenReturn(PayementBuilder.getListDTO());

        Mockito.when(payementService.findAll()).thenReturn(PayementBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(payementMapper.asDTO(ArgumentMatchers.any())).thenReturn(PayementBuilder.getDTO());

        Mockito.when(payementService.findById(ArgumentMatchers.anyInteger())).thenReturn(java.util.Optional.of(PayementBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(payementService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(payementService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(payementMapper.asEntity(ArgumentMatchers.any())).thenReturn(PayementBuilder.getEntity());
        Mockito.when(payementService.save(ArgumentMatchers.any(Payement.class))).thenReturn(PayementBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(PayementBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(payementService, Mockito.times(1)).save(ArgumentMatchers.any(Payement.class));
        Mockito.verifyNoMoreInteractions(payementService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(payementMapper.asEntity(ArgumentMatchers.any())).thenReturn(PayementBuilder.getEntity());
        Mockito.when(payementService.update(ArgumentMatchers.any(), ArgumentMatchers.anyInteger())).thenReturn(PayementBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(PayementBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(payementService, Mockito.times(1)).update(ArgumentMatchers.any(Payement.class), ArgumentMatchers.anyInteger());
        Mockito.verifyNoMoreInteractions(payementService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(payementService).deleteById(ArgumentMatchers.anyInteger());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(payementService, Mockito.times(1)).deleteById(Mockito.anyInteger());
        Mockito.verifyNoMoreInteractions(payementService);
    }