package Classes.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/welcome", "/main"})
public class WelcomeController {
    @GetMapping
    String index(){
        return "index.html";
    }
}
