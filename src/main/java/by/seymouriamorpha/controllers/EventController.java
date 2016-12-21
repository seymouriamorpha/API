package by.seymouriamorpha.controllers;

import by.seymouriamorpha.beans.Event;
import by.seymouriamorpha.beans.errors.ErrorMessages;
import by.seymouriamorpha.repositories.EventRepository;
import by.seymouriamorpha.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugene_Kortelyov on 12/13/2016.
 */
@RestController
@RequestMapping("events")
public class EventController implements AbstractController
{

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Object> getAllEvents()
    {
        // temporary - for debugging
        // return new ResponseEntity<>(new Error(ErrorMessages.REJECTED), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(eventRepository.findAll(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Object> getEvent(@PathVariable String id)
    {
        Event event = eventRepository.findOne(id);
        if (event == null)
        {
            return error(ErrorMessages.EVENT_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @RequestMapping(value = "/creator/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Object> findByCreatorId(@PathVariable String creatorId)
    {
        if (userRepository.findOne(creatorId) == null)
        {
            return error(ErrorMessages.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(eventRepository.findByCreatorId(creatorId), HttpStatus.OK);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Object> insert(@RequestBody() Event event)
    {
        event.setCreationTime(LocalDateTime.now());
        List<String> members = new ArrayList<>();
        members.add(event.getCreatorId());
        event.setMembers(members);
        eventRepository.save(event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<Object> delete(@PathVariable String id)
    {
        if (eventRepository.findOne(id) == null)
        {
            return error(ErrorMessages.EVENT_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        eventRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Object> findNearest(@RequestParam String x, @RequestParam String y, @RequestParam String distance)
    {
        List<Event> events = eventRepository.findByPointNear(
                new Point(Double.parseDouble(x), Double.parseDouble(y)),
                new Distance(Double.parseDouble(distance)));
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

}