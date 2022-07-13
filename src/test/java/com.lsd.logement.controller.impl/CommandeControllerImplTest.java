package com.lsd.logement.controller.impl;

import com.lsd.logement.entity.stock.Commande;
import com.lsd.logement.mapper.CommandeMapper;
import com.lsd.logement.mapper.ReferenceMapper;
import com.lsd.logement.service.CommandeService;
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
public class CommandeControllerImplTest {
    //TODO: create the data Test generator class CommandeBuilder
    private static final String ENDPOINT_URL = "/commandes";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private CommandeControllerImpl commandeController;
    @MockBean
    private CommandeService commandeService;
    @MockBean
    private CommandeMapper commandeMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.commandeController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(commandeMapper.asDTOList(ArgumentMatchers.any())).thenReturn(CommandeBuilder.getListDTO());

        Mockito.when(commandeService.findAll()).thenReturn(CommandeBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(commandeMapper.asDTO(ArgumentMatchers.any())).thenReturn(CommandeBuilder.getDTO());

        Mockito.when(commandeService.findById(ArgumentMatchers.anyInteger())).thenReturn(java.util.Optional.of(CommandeBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(commandeService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(commandeService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(commandeMapper.asEntity(ArgumentMatchers.any())).thenReturn(CommandeBuilder.getEntity());
        Mockito.when(commandeService.save(ArgumentMatchers.any(Commande.class))).thenReturn(CommandeBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(CommandeBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(commandeService, Mockito.times(1)).save(ArgumentMatchers.any(Commande.class));
        Mockito.verifyNoMoreInteractions(commandeService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(commandeMapper.asEntity(ArgumentMatchers.any())).thenReturn(CommandeBuilder.getEntity());
        Mockito.when(commandeService.update(ArgumentMatchers.any(), ArgumentMatchers.anyInteger())).thenReturn(CommandeBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(CommandeBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(commandeService, Mockito.times(1)).update(ArgumentMatchers.any(Commande.class), ArgumentMatchers.anyInteger());
        Mockito.verifyNoMoreInteractions(commandeService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(commandeService).deleteById(ArgumentMatchers.anyInteger());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(commandeService, Mockito.times(1)).deleteById(Mockito.anyInteger());
        Mockito.verifyNoMoreInteractions(commandeService);
    }