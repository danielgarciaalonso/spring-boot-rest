package formacion.cmc.springbootrest.api.v1.model;

import java.util.Set;

public class AuthorDTO {
	
	private Long id;

	private String name;

	private Set<BookDTO> books;
	
	public AuthorDTO() {
        super();
    }

    public AuthorDTO(Long id, String name, Set<BookDTO> books) {
		super();
		this.id = id;
		this.name = name;
		this.books = books;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<BookDTO> getBooks() {
		return books;
	}

	public void setBooks(Set<BookDTO> books) {
		this.books = books;
	}

}
