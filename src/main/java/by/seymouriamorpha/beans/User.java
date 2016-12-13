package by.seymouriamorpha.beans;

import com.sun.istack.internal.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author seymouriamorpha on 12/13/2016.
 */
@Document(collection = "users")
public class User {

    @Id private @Nullable String id;
    @Field(value = "forename")  private String forename;
    @Field(value = "surname")   private String surname;
    @Field(value = "email")     private String email;
    @Field(value = "token")     private String token;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getForename() {
        return forename;
    }
    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

}
