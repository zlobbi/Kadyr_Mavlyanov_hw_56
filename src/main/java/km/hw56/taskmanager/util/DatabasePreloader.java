package km.hw56.taskmanager.util;

import km.hw56.taskmanager.model.*;
import km.hw56.taskmanager.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Configuration
public class DatabasePreloader {
    private Random r = new Random();

    @Bean
    CommandLineRunner generateGibberish(UserRepository usersRepo, TaskRepository taskRepo) {
        return args -> {
            // users repo initialization
            usersRepo.deleteAll();
            taskRepo.deleteAll();

            var users = Stream.generate(User::make).limit(10).collect(toList());

            // tasks repository initialization
            int i = 0;
            List<Task> tasks = new ArrayList<>();
            while (i < 30) {
                var u = users.get(r.nextInt(users.size()));
                u.plusTask();
                var t = Task.make(u);
                tasks.add(t);
                users.add(u);
                i++;
            }

            usersRepo.saveAll(users);
            taskRepo.saveAll(tasks);

            System.out.println("\nUsers:");
            usersRepo.findAll().forEach(u -> System.out.println(u));
            System.out.println("\nTasks:");
            AtomicInteger pi = new AtomicInteger(1);
            taskRepo.findAll().forEach(pu -> System.out.println((pi.getAndIncrement()) + " " + pu.toString()));

        };
    }
}
