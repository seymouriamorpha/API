package by.seymouriamorpha.beans.errors;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Eugene_Kortelyov on 12/13/2016.
 */
public class Error
{

    @Field(value = "message")  private String message;

    public Error(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }

}