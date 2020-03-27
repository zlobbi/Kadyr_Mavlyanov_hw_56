package km.hw56.taskmanager.dto;

import km.hw56.taskmanager.model.Task;
import km.hw56.taskmanager.model.TaskStatus;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class TaskDTOforAdmin {
    private String id;
    private String userEmail;
    private String headline;
    private TaskStatus status;
    private LocalDate date;

    public static TaskDTOforAdmin from(Task t) {
        return builder()
                .id(t.getId())
                .headline(t.getHeadline())
                .status(t.getStatus())
                .userEmail(t.getUser().getEmail())
                .date(t.getDate())
                .build();
    }
}
