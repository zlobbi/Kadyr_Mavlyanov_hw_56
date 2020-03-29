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

    @ApiPageable
    @RequestMapping("/mytasks")
    public Slice<TaskDTO> getUserTasks(@ApiIgnore Pageable pageable) {
        return taskService.tasksForAuthUser(pageable);
    }

    @PutMapping("/mytasks/add")
    public String addTaskForUser(@RequestParam String headline, @RequestParam String description,
                                 @RequestParam String date) {
        return taskService.addTaskForAuthUser(headline, description, date);
    }

    @RequestMapping("/mytasks/status/{taskId}")
    public Object changeStatus(@PathVariable("taskId") String taskId) {
        return taskService.changeStatusOfTask(taskId);
    }

    @DeleteMapping("/mytasks/dct")
    public String deleteCompletedTasks() {
        return taskService.deleteAllCompletedTasksOfAuthUser();
    }

    @ApiPageable
    @RequestMapping("/tasks")
    public Slice<TaskDTOforAdmin> getAllTasksForAdmin(@ApiIgnore Pageable pageable) {
        return taskService.tasksForAdmin(pageable);
    }

    @GetMapping
    public String getRootMessage() {
        return "Path's: /register ----------------- to register \n" +
                "        /mytasks ------------------ view auth tasks \n" +
                "        /mytasks/add -------------- to add task \n" +
                "        /mytasks/status/{taskId} -- to automatic change status \n" +
                "        /mytasks/dct -------------- to delete auth completed tasks \n" +
                "ROLE_ADMIN path: \n" +
                "        /tasks -------------------- to view all tasks in db";
    }
}
