package Homework_13_7;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;

@Getter
public class TaskMongoClient {

    private final MongoClient mongoClient;
    private final MongoDatabase mongoDatabase;

    public TaskMongoClient() {
       this.mongoClient = new MongoClient("127.0.0.1", 27017);
       this.mongoDatabase = mongoClient.getDatabase("local");
    }

}
