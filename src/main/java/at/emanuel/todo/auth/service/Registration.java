package at.emanuel.todo.auth.service;

import at.emanuel.todo.auth.model.User;
import at.emanuel.todo.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class Registration {

    private final UserRepository userRepository;

    public Registration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String CreateUser(String email, String password, String password2) {

        if (email.isEmpty() || password.isEmpty() || password2.isEmpty()) return "Please fill in all fields";

        if (!Objects.equals(password, password2)) {
            System.out.println("password do not match");
            return "The passwords do not match";
        }

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

        //check if password is valid
        if (!checkIfPasswordIsValid(password)) return "The password is not valid";


        byte[] hash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        User user = new User(email, hash);
        userRepository.save(user);
        System.out.println("uid:" + user.getUid());
        return "uid:" + user.getUid();
    }

    private boolean checkIfPasswordIsValid(String password) {
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        return password.matches(pattern);
    }
}
