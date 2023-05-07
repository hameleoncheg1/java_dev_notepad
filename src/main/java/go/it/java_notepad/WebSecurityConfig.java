package go.it.java_notepad;

import go.it.java_notepad.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
//    private final UserRepository userRepository;
private final CustomAuthProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                        .requestMatchers("/note/share/**")
                        .permitAll()
                        .requestMatchers("/register")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                .and()
                .httpBasic()
                .and()
                    .formLogin()
//                    .loginPage("/login")
                .permitAll()
        ;

        return http.build();
    }

    @Autowired
    public void injectCustomAuthProvider(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return userRepository::findByUsername;
//    }
//
}