package com.lsd.logement.controller.impl;

import com.lsd.logement.entity.client.LocataireSociete;
import com.lsd.logement.mapper.LocataireSocieteMapper;
import com.lsd.logement.mapper.ReferenceMapper;
import com.lsd.logement.service.LocataireSocieteService;
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
public class LocataireSocieteControllerImplTest {
    //TODO: create the data Test generator class LocataireSocieteBuilder
    private static final String ENDPOINT_URL = "/locataire-societes";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private LocataireSocieteControllerImpl locatairesocieteController;
    @MockBean
    private LocataireSocieteService locatairesocieteService;
    @MockBean
    private LocataireSocieteMapper locatairesocieteMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.locatairesocieteController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(locatairesocieteMapper.asDTOList(ArgumentMatchers.any())).thenReturn(LocataireSocieteBuilder.getListDTO());

        Mockito.when(locatairesocieteService.findAll()).thenReturn(LocataireSocieteBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(locatairesocieteMapper.asDTO(ArgumentMatchers.any())).thenReturn(LocataireSocieteBuilder.getDTO());

        Mockito.when(locatairesocieteService.findById(ArgumentMatchers.anyInteger())).thenReturn(java.util.Optional.of(LocataireSocieteBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(locatairesocieteService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(locatairesocieteService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(locatairesocieteMapper.asEntity(ArgumentMatchers.any())).thenReturn(LocataireSocieteBuilder.getEntity());
        Mockito.when(locatairesocieteService.save(ArgumentMatchers.any(LocataireSociete.class))).thenReturn(LocataireSocieteBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(LocataireSocieteBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(locatairesocieteService, Mockito.times(1)).save(ArgumentMatchers.any(LocataireSociete.class));
        Mockito.verifyNoMoreInteractions(locatairesocieteService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(locatairesocieteMapper.asEntity(ArgumentMatchers.any())).thenReturn(LocataireSocieteBuilder.getEntity());
        Mockito.when(locatairesocieteService.update(ArgumentMatchers.any(), ArgumentMatchers.anyInteger())).thenReturn(LocataireSocieteBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(LocataireSocieteBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(locatairesocieteService, Mockito.times(1)).update(ArgumentMatchers.any(LocataireSociete.class), ArgumentMatchers.anyInteger());
        Mockito.verifyNoMoreInteractions(locatairesocieteService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(locatairesocieteService).deleteById(ArgumentMatchers.anyInteger());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(locatairesocieteService, Mockito.times(1)).deleteById(Mockito.anyInteger());
        Mockito.verifyNoMoreInteractions(locatairesocieteService);
    }