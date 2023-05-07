package go.it.java_notepad.endpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.rmi.MarshalledObject;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @GetMapping
    public ModelAndView registration (){
        ModelAndView result = new ModelAndView("register");
        return result;
    }
}
