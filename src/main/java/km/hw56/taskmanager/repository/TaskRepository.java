package km.hw56.taskmanager.repository;

import km.hw56.taskmanager.model.Task;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskRepository extends PagingAndSortingRepository<Task, String> {
}
