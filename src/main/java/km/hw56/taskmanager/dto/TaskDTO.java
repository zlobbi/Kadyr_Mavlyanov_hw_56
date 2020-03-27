package km.hw56.taskmanager.dto;

import km.hw56.taskmanager.model.Task;
import km.hw56.taskmanager.model.TaskStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class TaskDTO {
    private String id;
    private String headline;
    private TaskStatus status;
    private LocalDate date;

    public static TaskDTO from(Task t) {
        return builder()
                .id(t.getId())
                .headline(t.getHeadline())
                .status(t.getStatus())
                .date(t.getDate())
                .build();
    }
}
