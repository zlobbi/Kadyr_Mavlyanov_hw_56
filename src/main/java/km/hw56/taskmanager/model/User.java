package km.hw56.taskmanager.model;

import km.hw56.taskmanager.util.Generator;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "users")
public class User implements UserDetails {

    private String id = UUID.randomUUID().toString();
    private String username;
    private String email;
    private String password;
    private String notEncodedPass;

    public static User make() {
        User u = new User();
        u.setUsername(Generator.makeName());
        u.setEmail(Generator.makeEmail() + ".com");
        u.setNotEncodedPass(Generator.makePassword());
        u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
        return u;
    }

    public static User make(String username, String email, String notEncodedPass) {
        User u = new User();
        u.setUsername(username);
        u.setEmail(email);
        u.setNotEncodedPass(notEncodedPass);
        u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
        return u;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("FULL"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
