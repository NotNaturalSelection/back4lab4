package Classes.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "login", method = RequestMethod.POST)
public class LoginController {
    @PostMapping
    void logIn() {
    }
}
