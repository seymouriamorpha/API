package by.seymouriamorpha.beans;

import com.sun.istack.internal.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Eugene_Kortelyov on 12/13/2016.
 */
@Document(collection = "events")
public class Event
{

    @Id private @Nullable String id;
    @Field(value = "title")         private String title;
    @Field(value = "creatorId")     private String creatorId;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    @Field(value = "point")         private Point point;
    @Field(value = "creationTime")  private LocalDateTime creationTime;
    @Field(value = "eventTime")     private LocalDateTime eventTime;
    @Field(value = "members")       private List<String> members;
    @Field(value = "tags")          private String[] tags;

    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getCreatorId()
    {
        return creatorId;
    }
    public void setCreatorId(String creatorId)
    {
        this.creatorId = creatorId;
    }

    public Point getPoint() {
        return point;
    }
    public void setPoint(Point point) {
        this.point = point;
    }

    public LocalDateTime getCreationTime()
    {
        return creationTime;
    }
    public void setCreationTime(LocalDateTime creationTime)
    {
        this.creationTime = creationTime;
    }

    public LocalDateTime getEventTime()
    {
        return eventTime;
    }
    public void setEventTime(LocalDateTime eventTime)
    {
        this.eventTime = eventTime;
    }

    public List<String> getMembers()
    {
        return members;
    }
    public void setMembers(List<String> members)
    {
        this.members = members;
    }

    public String[] getTags()
    {
        return tags;
    }
    public void setTags(String[] tags)
    {
        this.tags = tags;
    }

}