package Homework_13_7.Task2;

import Homework_13_7.Task2.beans.Student;
import Homework_13_7.TaskMongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.BsonDocument;
import org.bson.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс TaskRun.
 * Демострация выполнения домашнего задания 13.7.1
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
public class TaskRun {

    private static final File CSV_FILE = new File("Stage13/src/main/java/Homework_13_7/Task2/mongo.csv");

    public static void main(String[] args) throws FileNotFoundException {

        CsvReader csvReader = new CsvReader(CSV_FILE);
        MongoDatabase database = new TaskMongoClient().getMongoDatabase();
        MongoCollection<Document> studentsCollection = database.getCollection("universe");
        studentsCollection.drop();

        studentsCollection.insertMany(csvReader.createStudentList().stream()
                .map(Student::createDoc).collect(Collectors.toList()));

        System.out.println("Общее количество студентов в базе: " + studentsCollection.countDocuments());

        List<Document> stList = studentsCollection.find(Filters.gt("Age", 40)).into(new ArrayList<>());
        System.out.println("Количество студентов старше 40 лет: " + stList.size());

        BsonDocument oneYoungestStudent = BsonDocument.parse("{Age: 1}");
        System.out.println("Имя самого молодого студента: "
                + studentsCollection.find().sort(oneYoungestStudent).first().getString("Name"));

        BsonDocument oneOldestStudent = BsonDocument.parse("{Age: -1}");
        System.out.println("Список курсов самого старого студента: "
                + studentsCollection.find().sort(oneOldestStudent).first().get("Courses"));

    }
}
