package at.emanuel.todo.auth.repository;

import at.emanuel.todo.auth.model.ToDo;
import at.emanuel.todo.auth.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{email: '?0'}")
    User findByEmail(String email);

    @Query("{uid: '?0'}")
    User findUserByUid(String uid);

    @Query("{uid: '?0''}")
    List<ToDo> findToDosByUid(String uid);
}
