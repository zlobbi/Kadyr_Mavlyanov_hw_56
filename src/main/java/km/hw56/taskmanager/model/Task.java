package km.hw56.taskmanager.model;

import km.hw56.taskmanager.util.Generator;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@Data
@Document(collection = "tasks")
public class Task {
    private static Random r = new Random();
    @Id
    private String id = UUID.randomUUID().toString();
    private String headline;
    private String description;
    private LocalDate date;
    @DBRef
    private User user;
    private TaskStatus status;

    public static Task make(User user) {
        Task t = new Task();
        t.setHeadline(Generator.makeName().toUpperCase());
        t.setDescription(Generator.makeDescription());
        t.setStatus(TaskStatus.values()[r.nextInt(3)]);
        t.setUser(user);
        if (t.getStatus() == TaskStatus.COMPLETED) {
            t.setDate(LocalDate.now().minusDays(r.nextInt(5)));
        } else {
            t.setDate(LocalDate.now().plusDays(r.nextInt(5)));
        }
        return t;
    }

    public static Task make(String headline, String description, LocalDate date,
                            User user,  TaskStatus status) {
        Task t = new Task();
        t.setHeadline(headline);
        t.setDescription(description);
        t.setDate(date);
        t.setUser(user);
        t.setStatus(status);
        return t;
    }
}
