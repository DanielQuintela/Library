package dev.training.library.controller;

import dev.training.library.model.UserModel;
import dev.training.library.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserModel request) {
        String token = service.login(request.getEmail(), request.getPassword());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        service.deleteUserById(id);
    }

    @PostMapping
    public UserModel saveUser(@RequestBody UserModel userModel) {
        return service.saveUser(userModel);
    }

}
