package go.it.java_notepad.service;

import go.it.java_notepad.endpoint.NoteController;
import go.it.java_notepad.entity.User;
import go.it.java_notepad.repository.NoteRepository;
import go.it.java_notepad.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final UserRepository userRepository;
    private final NoteController noteController;

    public ModelAndView handlerLogin (Object error, Object logout, Object cu){
        if(logout != null && cu !=null) {
            return noteController.list();
        }
        if(error != null) {
            return new ModelAndView("/login").addObject("message", "Невірні данні");
        }
        return new ModelAndView("/login");
    }

    public boolean userAuthentication(String username, String password){

        if(checkUser(username)){
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication =
                new TestingAuthenticationToken(username, password, "ROLE_USER");
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        return true;
        }
        return false;
    }

    private boolean checkUser(String username){
        User user = userRepository.findByUsername(username);

        if (user != null){
            return true;
        } else {
            return false;
        }
    }

}
