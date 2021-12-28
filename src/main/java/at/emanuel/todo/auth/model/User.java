package at.emanuel.todo.auth.model;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Document(collection = "user")
@Getter
@Setter
public class User{
    @Id
    private String uid;
    private String email;
    private byte[] password;
    private List<ToDo> todos = new ArrayList<>();

    @PersistenceConstructor
    public User(String email, byte[] password) {
        uid = uidGenerator();
        this.email = email;
        this.password = password;
    }

    public User(User user, String title, String description) {
        this.uid = user.uid;
        this.email = user.email;
        this.password = user.password;
    }

    public void addToDo(String title, String description) {
        todos.add(new ToDo(title, description));
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
