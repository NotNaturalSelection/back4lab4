package Classes.SpringRepository;

import Classes.DataClasses.Dot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface DotRepository extends JpaRepository<Dot, Integer> {
    List<Dot> findAllByOwner(String owner);

    List<Dot> getAllByHitTrueAndOwner(String owner);

    List<Dot> getAllByHit(boolean isHit);
}
