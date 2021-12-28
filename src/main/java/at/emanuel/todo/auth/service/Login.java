package at.emanuel.todo.auth.service;

import at.emanuel.todo.auth.model.User;
import at.emanuel.todo.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Service
public class Login {

    private final UserRepository userRepository;

    public Login(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String login(String email, String password) {

        if (email.isEmpty() || password.isEmpty()) return "Please fill in all fields";

        if (userRepository.findByEmail(email) == null) return "User not found";
        User user = userRepository.findByEmail(email);

        if (!Arrays.equals(user.getPassword(), hashPassword(password))) return "Wrong password";

        return "uid:" + user.getUid();
    }

    private byte[] hashPassword(String password) {
        byte[] hash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }
}
