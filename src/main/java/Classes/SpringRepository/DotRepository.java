package Classes.SpringRepository;

import Classes.DataClasses.Dot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface DotRepository extends JpaRepository<Dot, Integer> {
}
