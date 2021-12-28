package at.emanuel.todo.auth.service;

import at.emanuel.todo.auth.model.ToDo;
import at.emanuel.todo.auth.model.User;
import at.emanuel.todo.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }


    public User getUserByUID(String uid) {
        return userRepository.findUserByUid(uid);
    }

    public String createToDo(String uid, String title, String description) {

        User user = userRepository.findUserByUid(uid);
        user.addToDo(title, description);
        userRepository.save(user);

        return "worked";
    }

    public User getAllToDos(String uid) {
        return userRepository.findUserByUid(uid);
    }
}
