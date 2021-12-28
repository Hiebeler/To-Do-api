package at.emanuel.todo.auth.controller;

import at.emanuel.todo.auth.model.ToDo;
import at.emanuel.todo.auth.model.User;
import at.emanuel.todo.auth.service.Login;
import at.emanuel.todo.auth.service.Registration;
import at.emanuel.todo.auth.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final Registration registration;
    private final Login login;

    public UserController(UserService userService, Registration registration, Login login) {
        this.userService = userService;
        this.registration = registration;
        this.login = login;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/")
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    @PutMapping("/")
    public String registration(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("password2") String password2) {

        return registration.CreateUser(email, password, password2);
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password) {

        return login.login(email, password);
    }

    @PostMapping("/getUser")
    public User getUser(@RequestParam("uid") String uid) {
        return userService.getUserByUID(uid);
    }

    @PostMapping("/addToDo")
    public String createToDo(@RequestParam("uid") String uid,
                           @RequestParam("title") String title,
                           @RequestParam("description") String description) {
        return userService.createToDo(uid, title, description);
    }

    @PostMapping("/getAllToDos")
    public User getAllToDos(@RequestParam("uid") String uid) {
        return userService.getAllToDos(uid);
    }
}
