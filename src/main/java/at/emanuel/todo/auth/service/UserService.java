package at.emanuel.todo.auth.service;

import at.emanuel.todo.auth.model.User;
import at.emanuel.todo.auth.repository.UserRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoClient;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String CreateUser(String email, String password) {

        if (userRepository.findByEmail(email) != null) {
            return "an account with this E-Mail already exists";
        }

        boolean emailIsValid = false;

        for (char c : email.toCharArray()) {
            if (c == '@') {
                emailIsValid = true;
                break;
            }
        }
        if (!emailIsValid) {
            return "E-Mail is not valid";
        }


        byte[] hash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        userRepository.save(new User(email, hash));
        return "worked";
    }

    public User getUser(String email) {
        User user = userRepository.findByEmail(email);
        System.out.println(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
