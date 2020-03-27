package km.hw56.taskmanager.controller;

import km.hw56.taskmanager.annotations.ApiPageable;
import km.hw56.taskmanager.dto.TaskDTOforAdmin;
import km.hw56.taskmanager.service.TaskService;
import km.hw56.taskmanager.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class Controller {

    private final UserService userService;
    private final TaskService taskService;

    public Controller(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @ApiPageable
    @RequestMapping("/tasks")
    public Slice<TaskDTOforAdmin> getAllTasksForAdmin(@ApiIgnore Pageable pageable) {
        return taskService.tasksForAdmin(pageable);
    }
}
