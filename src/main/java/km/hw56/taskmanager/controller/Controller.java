package km.hw56.taskmanager.controller;

import km.hw56.taskmanager.annotations.ApiPageable;
import km.hw56.taskmanager.dto.TaskDTO;
import km.hw56.taskmanager.dto.TaskDTOforAdmin;
import km.hw56.taskmanager.service.TaskService;
import km.hw56.taskmanager.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class Controller {

    private final UserService userService;
    private final TaskService taskService;

    public Controller(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @PutMapping("/register")
    public String registerUser(@RequestParam String email, @RequestParam String username, @RequestParam String password) {
        return userService.addUser(username, email, password);
    }

    @PutMapping("/tasks/add")
    public String addTaskForUser(@RequestParam String headline, @RequestParam String description,
                                 @RequestParam String date, @RequestParam String status) {

        return taskService.addTaskForAuthUser(headline, description, date, status);
    }

    @ApiPageable
    @RequestMapping("/tasks")
    public Slice<TaskDTOforAdmin> getAllTasksForAdmin(@ApiIgnore Pageable pageable) {
        return taskService.tasksForAdmin(pageable);
    }

    @ApiPageable
    @RequestMapping("/mytasks")
    public Slice<TaskDTO> getUserTasks(@ApiIgnore Pageable pageable) {
        return taskService.tasksForAuthUser(pageable);
    }

    @DeleteMapping("/dct")
    public String deleteCompletedTasks() {
        return taskService.deleteAllCompletedTasksOfAuthUser();
    }
}
