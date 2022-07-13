package com.lsd.logement.controller.impl;

import com.lsd.logement.entity.reservation.Booking;
import com.lsd.logement.mapper.BookingMapper;
import com.lsd.logement.mapper.ReferenceMapper;
import com.lsd.logement.service.BookingService;
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
public class BookingControllerImplTest {
    //TODO: create the data Test generator class BookingBuilder
    private static final String ENDPOINT_URL = "/bookings";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private BookingControllerImpl bookingController;
    @MockBean
    private BookingService bookingService;
    @MockBean
    private BookingMapper bookingMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.bookingController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(bookingMapper.asDTOList(ArgumentMatchers.any())).thenReturn(BookingBuilder.getListDTO());

        Mockito.when(bookingService.findAll()).thenReturn(BookingBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(bookingMapper.asDTO(ArgumentMatchers.any())).thenReturn(BookingBuilder.getDTO());

        Mockito.when(bookingService.findById(ArgumentMatchers.anyInteger())).thenReturn(java.util.Optional.of(BookingBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(bookingService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(bookingService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(bookingMapper.asEntity(ArgumentMatchers.any())).thenReturn(BookingBuilder.getEntity());
        Mockito.when(bookingService.save(ArgumentMatchers.any(Booking.class))).thenReturn(BookingBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(BookingBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(bookingService, Mockito.times(1)).save(ArgumentMatchers.any(Booking.class));
        Mockito.verifyNoMoreInteractions(bookingService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(bookingMapper.asEntity(ArgumentMatchers.any())).thenReturn(BookingBuilder.getEntity());
        Mockito.when(bookingService.update(ArgumentMatchers.any(), ArgumentMatchers.anyInteger())).thenReturn(BookingBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(BookingBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(bookingService, Mockito.times(1)).update(ArgumentMatchers.any(Booking.class), ArgumentMatchers.anyInteger());
        Mockito.verifyNoMoreInteractions(bookingService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(bookingService).deleteById(ArgumentMatchers.anyInteger());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(bookingService, Mockito.times(1)).deleteById(Mockito.anyInteger());
        Mockito.verifyNoMoreInteractions(bookingService);
    }