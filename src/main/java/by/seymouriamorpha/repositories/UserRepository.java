package by.seymouriamorpha.repositories;

import by.seymouriamorpha.beans.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Eugene_Kortelyov on 12/13/2016.
 */
public interface UserRepository extends MongoRepository<User, String>
{

    List<User> findAll();

    User findByEmail(String email);

    User findByToken(String token);

}