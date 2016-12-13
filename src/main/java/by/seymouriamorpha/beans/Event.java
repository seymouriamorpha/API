package by.seymouriamorpha.beans;

import com.sun.istack.internal.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author seymouriamorpha on 12/13/2016.
 */
@Document(collection = "events")
public class Event {

    @Id private @Nullable String id;
    @Field(value = "title")     private String title;
    @Field(value = "creatorId") private String creatorId;
    @Field(value = "lat")       private String lat;
    @Field(value = "lon")       private String lon;
    @Field(value = "time")      private LocalDateTime time;
    @Field(value = "members")   private List<User> members;
    @Field(value = "tags")      private String[] tags;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatorId() {
        return creatorId;
    }
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getLat() {
        return lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }
    public void setLon(String lon) {
        this.lon = lon;
    }

    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public List<User> getMembers() {
        return members;
    }
    public void setMembers(List<User> members) {
        this.members = members;
    }

    public String[] getTags() {
        return tags;
    }
    public void setTags(String[] tags) {
        this.tags = tags;
    }

}
