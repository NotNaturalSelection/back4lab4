package Classes.SpringRepository;

import Classes.DataClasses.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByUsername(String username);
}
