package formacion.cmc.springbootrest.service;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import formacion.cmc.springbootrest.domain.Author;
import formacion.cmc.springbootrest.exception.NotFoundException;
import formacion.cmc.springbootrest.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {
    
    private final AuthorRepository authorRepository;
    
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Set<Author> findAll() {
        return authorRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public Author findOne(Long id) throws NotFoundException {
        Author author = authorRepository.findOne(id);
        if (author == null) {
            throw new NotFoundException("Author " + id + " not found");  
        }
        return authorRepository.findOne(id);
    }

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void update(Long id, Author author) {
        Author original = authorRepository.findOne(id);
        if (original != null) {
            if (!StringUtils.isEmpty(author.getName())) {
                original.setName(author.getName());
            }
            if (author.getBooks()!=null) {
                original.setBooks(author.getBooks());
            }
            authorRepository.save(original);
        }
    }

    @Override
    public void delete(Long id) {
        authorRepository.delete(id);
    }

}
