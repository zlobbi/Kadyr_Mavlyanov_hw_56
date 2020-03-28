package km.hw56.taskmanager.service;

import km.hw56.taskmanager.model.User;
import km.hw56.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String addUser(String username, String email, String password) {
        String msg = "";
        if(!userRepository.existsByUsernameAndEmail(username, email)) {
            User u = User.make(username, email, password);
            userRepository.save(u);
            msg = "User " + username + " registered, " + "role: " + u.getRole();
        } else {
            msg = "User " + username + " already exists!";
        }
        return msg;
    }
}
