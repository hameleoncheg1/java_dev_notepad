package go.it.java_notepad.endpoint;

import go.it.java_notepad.entity.User;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/login")
public class LoginController {
    @PostMapping
    public RedirectView login(@RequestParam String username,
                                    @RequestParam String password){

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication =
                new TestingAuthenticationToken(username, password, "ROLE_USER");
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);


        return new RedirectView("/note/list");
    }

    @GetMapping
    public ModelAndView login(){
                ModelAndView result = new ModelAndView("login");
        return result;
    }
//    @PostMapping("")
//    public RedirectView registerAdd(@RequestParam String username,
//                                    @RequestParam String password) {
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(passwordEncoder.encode(password));
////        user.setRole("ROLE_USER");
////        user.setEnabled(1);
////        userRepository.save(user);
//        return new RedirectView("/login");
//    }


}
