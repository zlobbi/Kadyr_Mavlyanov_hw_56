package km.hw56.taskmanager.service;

import km.hw56.taskmanager.dto.TaskDTO;
import km.hw56.taskmanager.dto.TaskDTOforAdmin;
import km.hw56.taskmanager.model.Task;
import km.hw56.taskmanager.model.TaskStatus;
import km.hw56.taskmanager.model.User;
import km.hw56.taskmanager.repository.TaskRepository;
import km.hw56.taskmanager.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Slice<TaskDTOforAdmin> tasksForAdmin(Pageable pageable) {
        var slice = taskRepository.findAll(pageable);
       return slice.map(TaskDTOforAdmin::from);
    }

    public Slice<TaskDTO> tasksForAuthUser(Pageable pageable) {
        var slice = taskRepository.findAllByUserId(getUser().getId(), pageable);
        return slice.map(TaskDTO::from);
    }

    public String deleteAllCompletedTasksOfAuthUser() {
        String msg;
        if(taskRepository.existsByUserIdAndStatus(getUser().getId(), TaskStatus.COMPLETED)) {
            taskRepository.deleteAllByUserIdAndStatus(getUser().getId(), TaskStatus.COMPLETED);
            msg = "Your completed tasks deleted";
        } else {
            msg = "You have not completed tasks";
        }
        return msg;
    }

    public String addTaskForAuthUser(String headline, String description, String date, String status) {
        Task t = Task.make(headline, description, LocalDate.parse(date), getUser(), TaskStatus.valueOf(status.toUpperCase()));
        taskRepository.save(t);
        return "Task added";
    }

    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName()).get();
    }
}
