package fr.simplon.api.controller;

import fr.simplon.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public String helloWorld() {
        return "Hello, World!";
    }

    @GetMapping("/{username}")
    public String helloUser(@PathVariable String username) {
        return "Hello, " + username + " !";
    }
}
