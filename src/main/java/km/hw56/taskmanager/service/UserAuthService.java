package km.hw56.taskmanager.service;

import km.hw56.taskmanager.model.User;
import km.hw56.taskmanager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthService implements UserDetailsService {

    private final UserRepository repo;

    public UserAuthService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = repo.findByUsername(s);
        if (user.isPresent())
            return user.get();

        throw new UsernameNotFoundException("User does not exit");
    }


}
