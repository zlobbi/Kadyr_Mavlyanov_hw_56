package km.hw56.taskmanager.repository;

import km.hw56.taskmanager.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
    Optional<User> findByUsername(String s);

    boolean existsByUsernameAndEmail(String username, String email);
}
