package by.seymouriamorpha.repositories;

import by.seymouriamorpha.beans.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author seymouriamorpha on 12/13/2016.
 */
public interface EventRepository extends MongoRepository<Event, String> {

    List<Event> findAll();

    List<Event> findByCreatorId(String creatorId);



}
