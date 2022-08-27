package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.impl.CustomUtils;
import com.lsd.logement.controller.impl.SousCaisseControllerImpl;
import com.lsd.logement.dto.SousCaisseDTO;
import com.lsd.logement.entity.SousCaisse;
import com.lsd.logement.mapper.ReferenceMapper;
import com.lsd.logement.mapper.SousCaisseMapper;
import com.lsd.logement.service.SousCaisseService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class SousCaisseControllerImplTest {
    //TODO: create the data Test generator class SousCaisseBuilder
    private static final String ENDPOINT_URL = "/sous-caisses";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private SousCaisseControllerImpl souscaisseController;
    @MockBean
    private SousCaisseService souscaisseService;
    @MockBean
    private SousCaisseMapper souscaisseMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.souscaisseController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(souscaisseMapper.asDTOList(ArgumentMatchers.any())).thenReturn(SousCaisseBuilder.getListDTO());

        Mockito.when(souscaisseService.findAll()).thenReturn(SousCaisseBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(souscaisseMapper.asDTO(ArgumentMatchers.any())).thenReturn(SousCaisseBuilder.getDTO());

        Mockito.when(souscaisseService.findById(ArgumentMatchers.anyInteger())).thenReturn(java.util.Optional.of(SousCaisseBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(souscaisseService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(souscaisseService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(souscaisseMapper.asEntity(ArgumentMatchers.any())).thenReturn(SousCaisseBuilder.getEntity());
        Mockito.when(souscaisseService.save(ArgumentMatchers.any(SousCaisse.class))).thenReturn(SousCaisseBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(SousCaisseBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(souscaisseService, Mockito.times(1)).save(ArgumentMatchers.any(SousCaisse.class));
        Mockito.verifyNoMoreInteractions(souscaisseService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(souscaisseMapper.asEntity(ArgumentMatchers.any())).thenReturn(SousCaisseBuilder.getEntity());
        Mockito.when(souscaisseService.update(ArgumentMatchers.any(), ArgumentMatchers.anyInteger())).thenReturn(SousCaisseBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(SousCaisseBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(souscaisseService, Mockito.times(1)).update(ArgumentMatchers.any(SousCaisse.class), ArgumentMatchers.anyInteger());
        Mockito.verifyNoMoreInteractions(souscaisseService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(souscaisseService).deleteById(ArgumentMatchers.anyInteger());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(souscaisseService, Mockito.times(1)).deleteById(Mockito.anyInteger());
        Mockito.verifyNoMoreInteractions(souscaisseService);
    }