package go.it.java_notepad.service;

import go.it.java_notepad.entity.User;
import go.it.java_notepad.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RequiredArgsConstructor
@Service
public class RegisterService {
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ModelAndView handlerRegister (String username, String password){
        if ((5 > username.length() | username.length() > 50) & (8 > password.length() | password.length() > 100)) {
            return new ModelAndView("/register")
                    .addObject("error",
                            "Помилка - і'мя користувача повинно бути від 5 до 20 символів," +
                                    " пароль користувача повинен бути від 8 до 100 символів");
        }
        if (5 > username.length() | username.length() > 50) {
            return new ModelAndView("/register")
                    .addObject("error",
                            "Помилка - і'мя користувача повинно бути від 5 до 20 символів");
        }
        if (8 > password.length() | password.length() > 100) {
            return new ModelAndView("/register").addObject("error",
                    "Помилка - пароль користувача повинен бути від 8 до 100 символів");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("ROLE_USER");
        user.setEnabled(1);
        userRepository.save(user);

        return new ModelAndView(new RedirectView("/login"));
    }
}
