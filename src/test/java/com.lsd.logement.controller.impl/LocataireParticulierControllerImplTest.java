package com.lsd.logement.controller.impl;

import com.lsd.logement.entity.client.LocataireParticulier;
import com.lsd.logement.mapper.LocataireParticulierMapper;
import com.lsd.logement.mapper.ReferenceMapper;
import com.lsd.logement.service.LocataireParticulierService;
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
public class LocataireParticulierControllerImplTest {
    //TODO: create the data Test generator class LocataireParticulierBuilder
    private static final String ENDPOINT_URL = "/locataire-particuliers";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private LocataireParticulierControllerImpl locataireparticulierController;
    @MockBean
    private LocataireParticulierService locataireparticulierService;
    @MockBean
    private LocataireParticulierMapper locataireparticulierMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.locataireparticulierController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(locataireparticulierMapper.asDTOList(ArgumentMatchers.any())).thenReturn(LocataireParticulierBuilder.getListDTO());

        Mockito.when(locataireparticulierService.findAll()).thenReturn(LocataireParticulierBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(locataireparticulierMapper.asDTO(ArgumentMatchers.any())).thenReturn(LocataireParticulierBuilder.getDTO());

        Mockito.when(locataireparticulierService.findById(ArgumentMatchers.anyInteger())).thenReturn(java.util.Optional.of(LocataireParticulierBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(locataireparticulierService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(locataireparticulierService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(locataireparticulierMapper.asEntity(ArgumentMatchers.any())).thenReturn(LocataireParticulierBuilder.getEntity());
        Mockito.when(locataireparticulierService.save(ArgumentMatchers.any(LocataireParticulier.class))).thenReturn(LocataireParticulierBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(LocataireParticulierBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(locataireparticulierService, Mockito.times(1)).save(ArgumentMatchers.any(LocataireParticulier.class));
        Mockito.verifyNoMoreInteractions(locataireparticulierService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(locataireparticulierMapper.asEntity(ArgumentMatchers.any())).thenReturn(LocataireParticulierBuilder.getEntity());
        Mockito.when(locataireparticulierService.update(ArgumentMatchers.any(), ArgumentMatchers.anyInteger())).thenReturn(LocataireParticulierBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(LocataireParticulierBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(locataireparticulierService, Mockito.times(1)).update(ArgumentMatchers.any(LocataireParticulier.class), ArgumentMatchers.anyInteger());
        Mockito.verifyNoMoreInteractions(locataireparticulierService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(locataireparticulierService).deleteById(ArgumentMatchers.anyInteger());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(locataireparticulierService, Mockito.times(1)).deleteById(Mockito.anyInteger());
        Mockito.verifyNoMoreInteractions(locataireparticulierService);
    }