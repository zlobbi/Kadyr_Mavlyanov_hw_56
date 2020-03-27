package km.hw56.taskmanager.service;

import km.hw56.taskmanager.dto.TaskDTOforAdmin;
import km.hw56.taskmanager.repository.TaskRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Slice<TaskDTOforAdmin> tasksForAdmin(Pageable pageable) {
        var slice = taskRepository.findAll(pageable);
       return slice.map(TaskDTOforAdmin::from);
    }
}
