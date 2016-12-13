package by.seymouriamorpha.beans.errors;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author seymouriamorpha on 12/13/2016.
 */
public class Error {

    @Field(value = "message")  private String message;

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
