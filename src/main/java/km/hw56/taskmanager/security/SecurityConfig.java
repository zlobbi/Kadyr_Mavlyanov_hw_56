package km.hw56.taskmanager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(
//            AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password(encoder().encode("password"))
//                .authorities("FULL")
//                .and()
//                .withUser("guest")
//                .password(encoder().encode("guest"))
//                .roles("GUEST")
//                .authorities("READ_ONLY");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Правило 1: Всё, что начинается с /subscriptions
        // должно быть доступно только
        // после авторизации пользователя
        http.authorizeRequests()
                .antMatchers("/tasks").hasRole("ADMIN");


        // Правило 2: Разрешить всё остальные запросы
//        http.authorizeRequests()
//                .anyRequest()
//                .permitAll();

        // Настраиваем хранение сессий. Не храним сессию.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Используем авторизацию по механизму Http Basic.
        // Данные пользователя передаются через заголовок запроса
        http.httpBasic();

        // Так как мы авторизуемся через заголовок запроса, то
        // форма входа на сайт и выхода с него нам тоже не нужны.
        http.formLogin().disable().logout().disable();

        // Так как у нас REST сервис, нам не нужна защита от CSRF
        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
