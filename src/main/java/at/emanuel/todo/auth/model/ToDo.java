package at.emanuel.todo.auth.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "todo")
@Getter
@Setter
public class ToDo {

    private String title;
    private String description;

    public ToDo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
