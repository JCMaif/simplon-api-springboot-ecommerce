package fr.simplon.api.controller;

import fr.simplon.api.Exceptions.InvalidCredentialException;
import fr.simplon.api.model.User;
import fr.simplon.api.repository.UserRepository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.InputMismatchException;

@RestController
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
//    public Optional<User> login(
//            @ModelAttribute("username") String username,
//            @ModelAttribute("password") String password
//    ) {
//        return userRepository.findByUsernameAndPassword(username, password);
//    }

    // autre façon, en pouvant personnaliser l'exception :

    public User login(
            @ModelAttribute("username") String username,
            @ModelAttribute("password") String password
    ) throws InvalidCredentialException {
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(
                () -> new InvalidCredentialException("Check your credentials")
                // () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED)
        );
    }

    @PostMapping("/register")
    public User register(
            @ModelAttribute("username") String username,
            @ModelAttribute("password") String password,
            @ModelAttribute("passwordConfirm") String passwordConfirm,
            @ModelAttribute("email") String email
    ) {
        if (password.equals(passwordConfirm)) {
            User user = new User(username);
            user.setEmail(email);
            user.setPassword(password);
            return userRepository.save(user);
        } else {
            throw new InputMismatchException("Passwords didn't match");
        }
    }

}
