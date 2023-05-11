package go.it.java_notepad.endpoint;

import go.it.java_notepad.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping()
public class LoginController {

    private final LoginService loginService;
    @GetMapping("/")
    public RedirectView defaultPlaceForSiteUrl  () {
        return loginService.checkAuthentication();
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false) Object error,
                                   @RequestParam(required = false) Object logout,
                                   @RequestParam(required = false, name = "continue") Object cont) {
        return loginService.handlerLogin(error, logout, cont);
    }
}
