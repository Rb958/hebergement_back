package com.lsd.logement.controller.impl;

import com.lsd.logement.entity.infra.Local;
import com.lsd.logement.mapper.LocalMapper;
import com.lsd.logement.mapper.ReferenceMapper;
import com.lsd.logement.service.LocalService;
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
public class LocalControllerImplTest {
    //TODO: create the data Test generator class LocalBuilder
    private static final String ENDPOINT_URL = "/locals";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private LocalControllerImpl localController;
    @MockBean
    private LocalService localService;
    @MockBean
    private LocalMapper localMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.localController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(localMapper.asDTOList(ArgumentMatchers.any())).thenReturn(LocalBuilder.getListDTO());

        Mockito.when(localService.findAll()).thenReturn(LocalBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(localMapper.asDTO(ArgumentMatchers.any())).thenReturn(LocalBuilder.getDTO());

        Mockito.when(localService.findById(ArgumentMatchers.anyInteger())).thenReturn(java.util.Optional.of(LocalBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(localService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(localService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(localMapper.asEntity(ArgumentMatchers.any())).thenReturn(LocalBuilder.getEntity());
        Mockito.when(localService.save(ArgumentMatchers.any(Local.class))).thenReturn(LocalBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(LocalBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(localService, Mockito.times(1)).save(ArgumentMatchers.any(Local.class));
        Mockito.verifyNoMoreInteractions(localService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(localMapper.asEntity(ArgumentMatchers.any())).thenReturn(LocalBuilder.getEntity());
        Mockito.when(localService.update(ArgumentMatchers.any(), ArgumentMatchers.anyInteger())).thenReturn(LocalBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(LocalBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(localService, Mockito.times(1)).update(ArgumentMatchers.any(Local.class), ArgumentMatchers.anyInteger());
        Mockito.verifyNoMoreInteractions(localService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(localService).deleteById(ArgumentMatchers.anyInteger());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(localService, Mockito.times(1)).deleteById(Mockito.anyInteger());
        Mockito.verifyNoMoreInteractions(localService);
    }