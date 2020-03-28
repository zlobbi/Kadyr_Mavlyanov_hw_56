package km.hw56.taskmanager.repository;

import km.hw56.taskmanager.model.Task;
import km.hw56.taskmanager.model.TaskStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskRepository extends PagingAndSortingRepository<Task, String> {
    Slice<Task> findAllByUserId(String id, Pageable pageable);

    boolean existsByUserIdAndStatus(String id, TaskStatus completed);

    void deleteAllByUserIdAndStatus(String userId, TaskStatus taskStatus);
}
