package at.emanuel.todo.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    private String id;
    private String email;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "uid='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
