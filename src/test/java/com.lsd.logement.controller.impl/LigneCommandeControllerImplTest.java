package com.lsd.logement.controller.impl;

import com.lsd.logement.entity.stock.LigneCommande;
import com.lsd.logement.mapper.LigneCommandeMapper;
import com.lsd.logement.mapper.ReferenceMapper;
import com.lsd.logement.service.LigneCommandeService;
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
public class LigneCommandeControllerImplTest {
    //TODO: create the data Test generator class LigneCommandeBuilder
    private static final String ENDPOINT_URL = "/ligne-commandes";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private LigneCommandeControllerImpl lignecommandeController;
    @MockBean
    private LigneCommandeService lignecommandeService;
    @MockBean
    private LigneCommandeMapper lignecommandeMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.lignecommandeController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(lignecommandeMapper.asDTOList(ArgumentMatchers.any())).thenReturn(LigneCommandeBuilder.getListDTO());

        Mockito.when(lignecommandeService.findAll()).thenReturn(LigneCommandeBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(lignecommandeMapper.asDTO(ArgumentMatchers.any())).thenReturn(LigneCommandeBuilder.getDTO());

        Mockito.when(lignecommandeService.findById(ArgumentMatchers.anyInteger())).thenReturn(java.util.Optional.of(LigneCommandeBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(lignecommandeService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(lignecommandeService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(lignecommandeMapper.asEntity(ArgumentMatchers.any())).thenReturn(LigneCommandeBuilder.getEntity());
        Mockito.when(lignecommandeService.save(ArgumentMatchers.any(LigneCommande.class))).thenReturn(LigneCommandeBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(LigneCommandeBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(lignecommandeService, Mockito.times(1)).save(ArgumentMatchers.any(LigneCommande.class));
        Mockito.verifyNoMoreInteractions(lignecommandeService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(lignecommandeMapper.asEntity(ArgumentMatchers.any())).thenReturn(LigneCommandeBuilder.getEntity());
        Mockito.when(lignecommandeService.update(ArgumentMatchers.any(), ArgumentMatchers.anyInteger())).thenReturn(LigneCommandeBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(LigneCommandeBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(lignecommandeService, Mockito.times(1)).update(ArgumentMatchers.any(LigneCommande.class), ArgumentMatchers.anyInteger());
        Mockito.verifyNoMoreInteractions(lignecommandeService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(lignecommandeService).deleteById(ArgumentMatchers.anyInteger());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(lignecommandeService, Mockito.times(1)).deleteById(Mockito.anyInteger());
        Mockito.verifyNoMoreInteractions(lignecommandeService);
    }