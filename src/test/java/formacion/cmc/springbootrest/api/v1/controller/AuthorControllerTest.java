package formacion.cmc.springbootrest.api.v1.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import formacion.cmc.springbootrest.api.v1.mapper.AuthorMapper;
import formacion.cmc.springbootrest.api.v1.model.AuthorDTO;
import formacion.cmc.springbootrest.api.v1.model.BookDTO;
import formacion.cmc.springbootrest.domain.Author;
import formacion.cmc.springbootrest.domain.Book;
import formacion.cmc.springbootrest.service.AuthorService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=AuthorController.class)
public class AuthorControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    AuthorService authorService;
    
    @MockBean
    AuthorMapper authorMapper;
    
    @Autowired
    AuthorController authorController;

    
    @Before
    public void setUp() {
        ReflectionTestUtils.setField(authorController, "authorMapper", new AuthorMapper());
    }
    
    @Test
    public void getAllAuthorsShouldReturnAuthorDTOList() throws Exception {
        
        when(authorService.findAll()).thenReturn(authors);
        
        mockMvc.perform(get(AuthorController.BASE_URL))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].id").exists())
            .andExpect(jsonPath("$[0].name").exists())
            .andExpect(jsonPath("$[0].phone").doesNotExist())
            .andExpect(jsonPath("$[0].address").doesNotExist())
            .andExpect(jsonPath("$[0].books").exists())
            .andExpect(jsonPath("$[0].books").isArray());
    }
    
    @Test
    public void getAuthorShouldReturnAuthorById() throws Exception {
        when(authorService.findOne(any(Long.class))).thenReturn(author);
        
        mockMvc.perform(get(AuthorController.BASE_URL + "/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", equalTo(1)))
            .andExpect(jsonPath("$.name", equalTo("Author")));
    }
    

    @Test
    public void postAuthorDTOShouldCreateNewAuthorAndReturnCreated() throws Exception {
        doNothing().when(authorService).save(any(Author.class));
        
        mockMvc.perform(post(AuthorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(authorDTO)))
            .andExpect(status().isCreated());
    }
    
    // ETC..
    
    
    /**
     * DATOS DE PRUEBA
     */
    Set<Book> books = Arrays.asList(new Book(1L, "Book 1"), new Book(2L, "Book2")).stream().collect(Collectors.toSet());
    Author author = new Author(1L, "Author", "Direction", "+3495858585", books);
    Set<Author> authors = Arrays.asList(author).stream().collect(Collectors.toSet());
    AuthorDTO authorDTO = new formacion.cmc.springbootrest.api.v1.model.AuthorDTO(1L, "Nuevo Autor",
            Arrays.asList(new BookDTO(1L, "Book1")).stream().collect(Collectors.toSet()));

}
