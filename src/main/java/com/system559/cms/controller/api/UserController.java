package com.system559.cms.controller.api;

import com.system559.cms.model.User;
import com.system559.cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable String id) {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }
}
