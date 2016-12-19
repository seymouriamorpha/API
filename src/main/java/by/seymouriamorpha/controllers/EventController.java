package by.seymouriamorpha.controllers;

import by.seymouriamorpha.beans.Event;
import by.seymouriamorpha.beans.User;
import by.seymouriamorpha.beans.errors.Error;
import by.seymouriamorpha.beans.errors.ErrorMessages;
import by.seymouriamorpha.repositories.EventRepository;
import by.seymouriamorpha.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        return new ResponseEntity<>(new Error(ErrorMessages.REJECTED), HttpStatus.BAD_REQUEST);
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
    ResponseEntity<Object> insert(@Valid @RequestBody() Event event, BindingResult br)
    {
        if (br.hasErrors())
        {
            return error(ErrorMessages.VALIDATION_ERROR, HttpStatus.BAD_REQUEST);
        }
        event.setCreationTime(LocalDateTime.now());
        List<User> members = new ArrayList<>();
        members.add(userRepository.findOne(event.getCreatorId()));
        event.setMembers(members);
        eventRepository.save(event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

}