package Classes.Controller;

import Classes.DataClasses.User;
import Classes.DataClasses.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "")
public class RegistrationController {

    private UsersService usersService;

    @Autowired
    public RegistrationController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping(value = "register")
    private void registration(@RequestBody User user) throws UserAlreadyExistsException {
        if (usersService.isUserByUsernameExists(user.getUsername())) {
            throw new UserAlreadyExistsException();
        } else {
            usersService.save(user);
        }
    }

}
