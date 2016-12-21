package by.seymouriamorpha.repositories;

import by.seymouriamorpha.beans.Event;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Eugene_Kortelyov on 12/13/2016.
 */
public interface EventRepository extends MongoRepository<Event, String>
{

    List<Event> findAll();

    List<Event> findByCreatorId(String creatorId);

    List<Event> findByPointNear(Point point, Distance max);

}