package go.it.java_notepad.endpoint;

import go.it.java_notepad.entity.User;
import go.it.java_notepad.repository.UserRepository;
import go.it.java_notepad.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class RegisterController {
    private final UserRepository userRepository;

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public RedirectView registerAdd(@RequestParam String username,
                                    @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("ROLE_USER");
        user.setEnabled(1);
        userRepository.save(user);
        return new RedirectView("/login");
    }
}
