package Homework_13_8.Task1;


import Homework_13_8.TaskMongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.function.Consumer;
import java.util.stream.Stream;
/**
 * Класс TaskRun.
 * Демострация выполнения домашнего задания 13.8.1
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 * * @see package-info.java
 */
public class FirstTaskRun {

    public static void main(String[] args) {

        MongoDatabase database = new TaskMongoClient().getMongoDatabase();
        MongoCollection<Document> booksCollection = database.getCollection("books");
        booksCollection.drop();

        Stream<Book> bookStream = Stream.of(
                new Book("Философия Java", "Эккель Брюс", 2019),
                new Book("Искусство программирования на Java", "Герберт Шилдт", 2005),
                new Book("Java. Полное руководство", "Герберт Шилдт", 2018),
                new Book("Effective Java", "Блох Джошуа", 2008),
                new Book("Spring 4 Для профессионалов", "Крис Шеффер", 2015),
                new Book("Java Persistence with Hibernate. Second edition", "Кристиан Бауер", 2016)
        );

        bookStream.forEach(book -> {
            booksCollection.insertOne(
                    new Document()
                            .append("name", book.getName())
                            .append("author", book.getAuthor())
                            .append("year", book.getYear())
            );
        });

        //запрос на самую старую книгу
        System.out.println("Самая старая книга:");
        BsonDocument oneOlderBookSortQuery = BsonDocument.parse("{year: 1}");
        System.out.println(booksCollection.find().sort(oneOlderBookSortQuery).first());
        //запрос на книги любимого автора
        System.out.println("Книги любимого автора:");
        booksCollection.find(Filters.eq("author", "Герберт Шилдт"))
                .forEach((Consumer<Document>) System.out::println);
    }
}
