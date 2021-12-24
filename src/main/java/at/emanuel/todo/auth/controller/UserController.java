package at.emanuel.todo.auth.controller;

import at.emanuel.todo.auth.model.User;
import at.emanuel.todo.auth.repository.UserRepository;
import at.emanuel.todo.auth.service.UserService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public User getCurrentUser(@RequestBody String email) {
        System.out.println(email);
        return userService.getUser(email);
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
    public User addUser(@RequestParam("email") String email,
                        @RequestParam("password") String password) {
        String passwordHash = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            passwordHash = new String(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return userService.CreateUser(new User(email, passwordHash));
    }
}
