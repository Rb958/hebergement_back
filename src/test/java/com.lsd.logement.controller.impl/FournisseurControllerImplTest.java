package com.lsd.logement.controller.impl;

import com.lsd.logement.entity.stock.Fournisseur;
import com.lsd.logement.mapper.FournisseurMapper;
import com.lsd.logement.mapper.ReferenceMapper;
import com.lsd.logement.service.FournisseurService;
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
public class FournisseurControllerImplTest {
    //TODO: create the data Test generator class FournisseurBuilder
    private static final String ENDPOINT_URL = "/fournisseurs";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private FournisseurControllerImpl fournisseurController;
    @MockBean
    private FournisseurService fournisseurService;
    @MockBean
    private FournisseurMapper fournisseurMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.fournisseurController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(fournisseurMapper.asDTOList(ArgumentMatchers.any())).thenReturn(FournisseurBuilder.getListDTO());

        Mockito.when(fournisseurService.findAll()).thenReturn(FournisseurBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(fournisseurMapper.asDTO(ArgumentMatchers.any())).thenReturn(FournisseurBuilder.getDTO());

        Mockito.when(fournisseurService.findById(ArgumentMatchers.anyInteger())).thenReturn(java.util.Optional.of(FournisseurBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(fournisseurService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(fournisseurService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(fournisseurMapper.asEntity(ArgumentMatchers.any())).thenReturn(FournisseurBuilder.getEntity());
        Mockito.when(fournisseurService.save(ArgumentMatchers.any(Fournisseur.class))).thenReturn(FournisseurBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(FournisseurBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(fournisseurService, Mockito.times(1)).save(ArgumentMatchers.any(Fournisseur.class));
        Mockito.verifyNoMoreInteractions(fournisseurService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(fournisseurMapper.asEntity(ArgumentMatchers.any())).thenReturn(FournisseurBuilder.getEntity());
        Mockito.when(fournisseurService.update(ArgumentMatchers.any(), ArgumentMatchers.anyInteger())).thenReturn(FournisseurBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(FournisseurBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(fournisseurService, Mockito.times(1)).update(ArgumentMatchers.any(Fournisseur.class), ArgumentMatchers.anyInteger());
        Mockito.verifyNoMoreInteractions(fournisseurService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(fournisseurService).deleteById(ArgumentMatchers.anyInteger());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(fournisseurService, Mockito.times(1)).deleteById(Mockito.anyInteger());
        Mockito.verifyNoMoreInteractions(fournisseurService);
    }