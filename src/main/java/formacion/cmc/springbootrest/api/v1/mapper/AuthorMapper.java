package formacion.cmc.springbootrest.api.v1.mapper;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import formacion.cmc.springbootrest.api.v1.model.AuthorDTO;
import formacion.cmc.springbootrest.api.v1.model.BookDTO;
import formacion.cmc.springbootrest.domain.Author;
import formacion.cmc.springbootrest.domain.Book;

@Component
public class AuthorMapper {

	public AuthorDTO convertAuthorToAuthorDTO(Author author) {
        return new AuthorDTO(author.getId(), author.getName(), author.getBooks().stream()
                .map(b -> new BookDTO(b.getId(), b.getTitle())).collect(Collectors.toSet()));
	}
	
    public Author convertAuthorDTOToAuthor(AuthorDTO dto) {
        return new Author(dto.getId(), dto.getName(), dto.getBooks().stream()
                .map(b -> new Book(b.getId(), b.getTitle())).collect(Collectors.toSet()));
	}
	
}
