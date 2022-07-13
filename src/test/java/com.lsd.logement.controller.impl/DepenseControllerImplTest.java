package com.lsd.logement.controller.impl;

import com.lsd.logement.entity.finance.Depense;
import com.lsd.logement.entity.controller.impl.DepenseControllerImpl;
import com.lsd.logement.mapper.DepenseMapper;
import com.lsd.logement.mapper.ReferenceMapper;
import com.lsd.logement.service.DepenseService;
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
public class DepenseControllerImplTest {
    //TODO: create the data Test generator class DepenseBuilder
    private static final String ENDPOINT_URL = "/depenses";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private DepenseControllerImpl depenseController;
    @MockBean
    private DepenseService depenseService;
    @MockBean
    private DepenseMapper depenseMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.depenseController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(depenseMapper.asDTOList(ArgumentMatchers.any())).thenReturn(DepenseBuilder.getListDTO());

        Mockito.when(depenseService.findAll()).thenReturn(DepenseBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(depenseMapper.asDTO(ArgumentMatchers.any())).thenReturn(DepenseBuilder.getDTO());

        Mockito.when(depenseService.findById(ArgumentMatchers.anyInteger())).thenReturn(java.util.Optional.of(DepenseBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(depenseService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(depenseService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(depenseMapper.asEntity(ArgumentMatchers.any())).thenReturn(DepenseBuilder.getEntity());
        Mockito.when(depenseService.save(ArgumentMatchers.any(Depense.class))).thenReturn(DepenseBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(DepenseBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(depenseService, Mockito.times(1)).save(ArgumentMatchers.any(Depense.class));
        Mockito.verifyNoMoreInteractions(depenseService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(depenseMapper.asEntity(ArgumentMatchers.any())).thenReturn(DepenseBuilder.getEntity());
        Mockito.when(depenseService.update(ArgumentMatchers.any(), ArgumentMatchers.anyInteger())).thenReturn(DepenseBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(DepenseBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(depenseService, Mockito.times(1)).update(ArgumentMatchers.any(Depense.class), ArgumentMatchers.anyInteger());
        Mockito.verifyNoMoreInteractions(depenseService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(depenseService).deleteById(ArgumentMatchers.anyInteger());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(depenseService, Mockito.times(1)).deleteById(Mockito.anyInteger());
        Mockito.verifyNoMoreInteractions(depenseService);
    }