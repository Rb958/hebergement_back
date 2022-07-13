package com.lsd.logement.controller.impl;

import com.lsd.logement.entity.stock.Article;
import com.lsd.logement.mapper.ArticleMapper;
import com.lsd.logement.mapper.ReferenceMapper;
import com.lsd.logement.service.ArticleService;
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
public class ArticleControllerImplTest {
    //TODO: create the data Test generator class ArticleBuilder
    private static final String ENDPOINT_URL = "/articles";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private ArticleControllerImpl articleController;
    @MockBean
    private ArticleService articleService;
    @MockBean
    private ArticleMapper articleMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.articleController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(articleMapper.asDTOList(ArgumentMatchers.any())).thenReturn(ArticleBuilder.getListDTO());

        Mockito.when(articleService.findAll()).thenReturn(ArticleBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(articleMapper.asDTO(ArgumentMatchers.any())).thenReturn(ArticleBuilder.getDTO());

        Mockito.when(articleService.findById(ArgumentMatchers.anyInteger())).thenReturn(java.util.Optional.of(ArticleBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(articleService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(articleService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(articleMapper.asEntity(ArgumentMatchers.any())).thenReturn(ArticleBuilder.getEntity());
        Mockito.when(articleService.save(ArgumentMatchers.any(Article.class))).thenReturn(ArticleBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(ArticleBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(articleService, Mockito.times(1)).save(ArgumentMatchers.any(Article.class));
        Mockito.verifyNoMoreInteractions(articleService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(articleMapper.asEntity(ArgumentMatchers.any())).thenReturn(ArticleBuilder.getEntity());
        Mockito.when(articleService.update(ArgumentMatchers.any(), ArgumentMatchers.anyInteger())).thenReturn(ArticleBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(ArticleBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(articleService, Mockito.times(1)).update(ArgumentMatchers.any(Article.class), ArgumentMatchers.anyInteger());
        Mockito.verifyNoMoreInteractions(articleService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(articleService).deleteById(ArgumentMatchers.anyInteger());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(articleService, Mockito.times(1)).deleteById(Mockito.anyInteger());
        Mockito.verifyNoMoreInteractions(articleService);
    }