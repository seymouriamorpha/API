package by.seymouriamorpha.controllers;

import by.seymouriamorpha.beans.DataList;
import by.seymouriamorpha.beans.Event;
import by.seymouriamorpha.beans.User;
import by.seymouriamorpha.beans.errors.ErrorMessages;
import by.seymouriamorpha.repositories.EventRepository;
import by.seymouriamorpha.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        return new ResponseEntity<>(eventRepository.findAll(), HttpStatus.OK);
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
        if (!userRepository.exists(creatorId))
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
        final String creatorId = event.getCreatorId();
        if(null==creatorId || !userRepository.exists(creatorId))
        {
            return error(ErrorMessages.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        members.add(event.getCreatorId());
        event.setMembers(members);
        eventRepository.save(event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<Object> delete(@PathVariable String id)
    {
        if (!eventRepository.exists(id))
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
                new Distance(Double.parseDouble(distance), Metrics.KILOMETERS));
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @RequestMapping(value = {"/{eventId}/members/{memberId}"}, method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Object> addMember(@PathVariable("eventId") String eventId, @PathVariable("memberId") String memberId)
    {
        final Event event = eventRepository.findOne(eventId);
        if (event == null)
        {
            return error(ErrorMessages.EVENT_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        if(!userRepository.exists(memberId))
        {
            return error(ErrorMessages.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        if(event.getMembers().contains(memberId))
        {
            new ResponseEntity<>(event, HttpStatus.SEE_OTHER);
        }
        event.getMembers().add(memberId);
        eventRepository.save(event);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @RequestMapping(value = {"/{eventId}/members/{memberId}"}, method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<Object> removeMember(@PathVariable("eventId") String eventId, @PathVariable("memberId") String memberId)
    {
        final Event event = eventRepository.findOne(eventId);
        if (event == null)
        {
            return error(ErrorMessages.EVENT_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        event.getMembers().remove(memberId);
        eventRepository.save(event);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }


    @RequestMapping(value = {"/{eventId}/tags"}, method = RequestMethod.PATCH)
    public @ResponseBody
    ResponseEntity<Object> updateTags(@PathVariable("eventId") String eventId, @RequestBody() DataList<String> tags)
    {
        final Event event = eventRepository.findOne(eventId);
        if (event == null)
        {
            return error(ErrorMessages.EVENT_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        event.setTags(tags);
        eventRepository.save(event);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }
}