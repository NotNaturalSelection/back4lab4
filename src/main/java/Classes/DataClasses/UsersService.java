package Classes.DataClasses;

import Classes.SpringRepository.UserRepository;
import Classes.SpringSecurity.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsersService  {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean isCredentialsValid(String username, String password) {
        User found = findUserByUsername(username);
        if(found!=null){
            return passwordEncoder.encode(password).equals(found.getPassword());
        } else {
            return false;
        }
    }

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }
}
