package at.emanuel.todo.auth.controller;

import at.emanuel.todo.auth.model.User;
import at.emanuel.todo.auth.repository.UserRepository;
import at.emanuel.todo.auth.service.UserService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User > getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/")
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    @PutMapping("/")
    public String addUser(@RequestParam("email") String email,
                        @RequestParam("password") String password) {

        return userService.CreateUser(email, password);
    }

    @PostMapping("/login")
    public User login(@RequestParam("email") String email,
                      @RequestParam("password") String password) {

        return new User(email, password.getBytes(StandardCharsets.UTF_8));
    }
}
