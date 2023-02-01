package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(
        origins = {"*"},
        allowedHeaders = {"*"}
)
@RequestMapping({"/users"})
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return !user.isPresent() ? ResponseEntity.notFound().build() : ResponseEntity.ok((User)user.get());
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return (User)this.userRepository.save(user);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            this.userRepository.delete((User)user.get());
            return ResponseEntity.ok().build();
        }
    }
}

