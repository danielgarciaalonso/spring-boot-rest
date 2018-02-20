package formacion.cmc.springbootrest.api.v1.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import formacion.cmc.springbootrest.api.v1.mapper.AuthorMapper;
import formacion.cmc.springbootrest.api.v1.model.AuthorDTO;
import formacion.cmc.springbootrest.exception.NotFoundException;
import formacion.cmc.springbootrest.service.AuthorService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = AuthorController.BASE_URL)
public class AuthorController {
    
    public static final String BASE_URL = "/api/v1/authors";
    
    private final AuthorService authorService;
    
    private final AuthorMapper authorMapper;

    public AuthorController(AuthorService authorService, AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @ApiOperation(value = "Listado de todos los autores", response = AuthorDTO.class, responseContainer = "List")
    @GetMapping
    public List<AuthorDTO> getAllAuthors(){
        return authorService.findAll().stream().map(a -> authorMapper.convertAuthorToAuthorDTO(a))
                .collect(Collectors.toList());
    }
    
    @ApiOperation(value = "Recuperar autor por id", response = AuthorDTO.class)
    @GetMapping("/{id}")
    public AuthorDTO getAuthor(@PathVariable Long id) throws NotFoundException {
        return authorMapper.convertAuthorToAuthorDTO(authorService.findOne(id));
    }
    
    @ApiOperation(value = "Crear autor")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAuthor(@RequestBody AuthorDTO dto) {
        authorService.save(authorMapper.convertAuthorDTOToAuthor(dto));
    }
    
    @ApiOperation(value = "Actualización parcial de un autor")
    @PatchMapping("/{id}")
    public void partialUpdateAuthor(@PathVariable Long id, @RequestBody AuthorDTO dto) {
        authorService.update(id, authorMapper.convertAuthorDTOToAuthor(dto));
    }
    
    @ApiOperation(value = "Eliminar autor")
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
    }

}
