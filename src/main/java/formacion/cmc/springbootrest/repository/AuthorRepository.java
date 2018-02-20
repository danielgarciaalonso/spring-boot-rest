package formacion.cmc.springbootrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import formacion.cmc.springbootrest.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
