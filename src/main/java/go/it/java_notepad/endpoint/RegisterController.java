package go.it.java_notepad.endpoint;

import go.it.java_notepad.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class    RegisterController {
    private final RegisterService registerService;

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }


    @PostMapping("/register")
    public ModelAndView registerAdd(@RequestParam String username,
                                    @RequestParam String password) {
        return registerService.handlerRegister(username, password);
    }
}
