package at.emanuel.todo.auth.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@Document(collection = "user")
@Getter
@Setter
public class User {
    @Id
    private String uid;
    private String email;
    private byte[] password;

    public User(String email, byte[] password) {
        uid = uidGenerator();
        this.email = email;
        this.password = password;
    }

    private String uidGenerator() {
        return java.util.UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", password='" + Arrays.toString(password) + '\'' +
                '}';
    }
}
