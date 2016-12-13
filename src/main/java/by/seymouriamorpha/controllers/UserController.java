package by.seymouriamorpha.controllers;

import by.seymouriamorpha.beans.User;
import by.seymouriamorpha.beans.errors.Error;
import by.seymouriamorpha.beans.errors.ErrorMessages;
import by.seymouriamorpha.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author seymouriamorpha on 12/13/2016.
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(repository.findAll(),  HttpStatus.OK);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Object> insert(@Valid @RequestBody() User user, BindingResult br) throws URISyntaxException {
        if (br.hasErrors()) {
            Error error = new Error();
            error.setMessage(ErrorMessages.VALIDATION_ERROR);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        if (repository.findByEmail(user.getEmail()) != null){
            Error error = new Error();
            error.setMessage(ErrorMessages.USER_ALREADY_EXISTS);
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
        repository.save(user);
        HttpHeaders headers = new HttpHeaders();
        URI location = new URI("/users/" + repository.findByToken(user.getToken()).getId());
        headers.setLocation(location);
        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Object> getUser(@PathVariable String id){
        User user = repository.findOne(id);
        if (user == null){
            Error error = new Error();
            error.setMessage(ErrorMessages.USER_NOT_FOUND);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Object> getUserByEmail(@PathVariable String email){
        User user = repository.findByEmail(email);
        if (user == null){
            Error error = new Error();
            error.setMessage(ErrorMessages.USER_NOT_FOUND);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public @ResponseBody
    ResponseEntity<Object> updateUserById(@PathVariable String id, @Valid @RequestBody() User user, BindingResult br){
        if (br.hasErrors()) {
            Error error = new Error();
            error.setMessage(ErrorMessages.VALIDATION_ERROR);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        if (repository.findOne(id) == null) {
            Error error = new Error();
            error.setMessage(ErrorMessages.USER_NOT_FOUND);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        if (!id.equals(user.getId())){
            Error error = new Error();
            error.setMessage(ErrorMessages.MISMATCH_IDS);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        repository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<Object> deleteById(@PathVariable String id){
        if (repository.findOne(id) == null) {
            Error error = new Error();
            error.setMessage(ErrorMessages.USER_NOT_FOUND);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        repository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
