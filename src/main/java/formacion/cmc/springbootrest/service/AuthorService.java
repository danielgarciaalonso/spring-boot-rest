package formacion.cmc.springbootrest.service;

import java.util.Set;
import formacion.cmc.springbootrest.domain.Author;
import formacion.cmc.springbootrest.exception.NotFoundException;

public interface AuthorService {

    Set<Author> findAll();
    
    Author findOne(Long id) throws NotFoundException;
    
    void save(Author author);
    
    void update(Long id, Author author);
    
    void delete(Long id);
    
}
