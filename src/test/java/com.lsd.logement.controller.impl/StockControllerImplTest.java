package com.lsd.logement.controller.impl;

import com.lsd.logement.entity.stock.Stock;
import com.lsd.logement.mapper.ReferenceMapper;
import com.lsd.logement.mapper.StockMapper;
import com.lsd.logement.service.StockService;
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
public class StockControllerImplTest {
    //TODO: create the data Test generator class StockBuilder
    private static final String ENDPOINT_URL = "/stocks";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private StockControllerImpl stockController;
    @MockBean
    private StockService stockService;
    @MockBean
    private StockMapper stockMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.stockController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(stockMapper.asDTOList(ArgumentMatchers.any())).thenReturn(StockBuilder.getListDTO());

        Mockito.when(stockService.findAll()).thenReturn(StockBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(stockMapper.asDTO(ArgumentMatchers.any())).thenReturn(StockBuilder.getDTO());

        Mockito.when(stockService.findById(ArgumentMatchers.anyInteger())).thenReturn(java.util.Optional.of(StockBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(stockService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(stockService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(stockMapper.asEntity(ArgumentMatchers.any())).thenReturn(StockBuilder.getEntity());
        Mockito.when(stockService.save(ArgumentMatchers.any(Stock.class))).thenReturn(StockBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(StockBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(stockService, Mockito.times(1)).save(ArgumentMatchers.any(Stock.class));
        Mockito.verifyNoMoreInteractions(stockService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(stockMapper.asEntity(ArgumentMatchers.any())).thenReturn(StockBuilder.getEntity());
        Mockito.when(stockService.update(ArgumentMatchers.any(), ArgumentMatchers.anyInteger())).thenReturn(StockBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(StockBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(stockService, Mockito.times(1)).update(ArgumentMatchers.any(Stock.class), ArgumentMatchers.anyInteger());
        Mockito.verifyNoMoreInteractions(stockService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(stockService).deleteById(ArgumentMatchers.anyInteger());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(stockService, Mockito.times(1)).deleteById(Mockito.anyInteger());
        Mockito.verifyNoMoreInteractions(stockService);
    }