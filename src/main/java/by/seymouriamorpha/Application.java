package by.seymouriamorpha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Eugene_Kortelyov on 12/13/2016.
 */
@SpringBootApplication
public class Application
{

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

}

// For running MongoDB locally:
//"C:\Program Files\MongoDB\Server\3.2\bin\mongod.exe" --dbpath d:\workspace\mongodb\data (link to DB)

// need create index for DB
// db.events.createIndex({point: "2dsphere"})