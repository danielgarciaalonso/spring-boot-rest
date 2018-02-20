package formacion.cmc.springbootrest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import formacion.cmc.springbootrest.domain.Author;
import formacion.cmc.springbootrest.domain.Book;
import formacion.cmc.springbootrest.exception.NotFoundException;
import formacion.cmc.springbootrest.repository.AuthorRepository;

@RunWith(SpringRunner.class)
public class AuthorServiceTest {
    
    @MockBean
    AuthorRepository authorRepository;
    
    AuthorService authorService;
    
    @Before
    public void setUp() {
        authorService = new AuthorServiceImpl(authorRepository); 
    }
    
    
    @Test
    public void findAllShouldReturnAuthorSet() throws Exception {
        when(authorRepository.findAll()).thenReturn(authors);
        
        Set<Author> authorSet = authorService.findAll();
        
        assertNotNull("Null author Set", authorSet);
        assertTrue("Empty author Set", !authorSet.isEmpty());
    }
    
    @Test
    public void findOneShouldReturnAuthor() throws Exception {
        when(authorRepository.findOne(anyLong())).thenReturn(author);
        
        Author author = authorService.findOne(1L);
        
        assertNotNull("Null author", author);
        assertEquals("Author wrong id", String.valueOf(1L), String.valueOf(author.getId()));
        assertEquals("Author wrong name", "Author", author.getName());
        assertEquals("Author wrong address", "Direction", author.getAddress());
        assertEquals("Author wrong phone", "+3495858585", author.getPhone());
    }
    
    @Test(expected=NotFoundException.class)
    public void findOneCouldReturnNotFoundException() throws Exception {
        when(authorRepository.findOne(anyLong())).thenReturn(null);
        
        authorService.findOne(1L);
    }
    
    // ETC ..
    
    /**
     * DATOS DE PRUEBA
     */
    Set<Book> books = Arrays.asList(new Book(1L, "Book 1"), new Book(2L, "Book2")).stream().collect(Collectors.toSet());
    Author author = new Author(1L, "Author", "Direction", "+3495858585", books);
    List<Author> authors = Arrays.asList(author);

}
