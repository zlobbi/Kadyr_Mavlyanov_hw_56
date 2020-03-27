package km.hw56.taskmanager.repository;

import km.hw56.taskmanager.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
}
