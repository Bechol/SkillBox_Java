package Homework_15_4;

/**
 * Домашняя работа 15.4
 * @autor Oleg Bech.
 * @email oleg071984@gmail.com
 */
public class Main {
    private static final String HADOOP_CONNECTION_URI = "hdfs://0.0.0.0:19000";

    public static void main(String[] args) throws Exception {

        FileAccess hadoop = new FileAccess(HADOOP_CONNECTION_URI);

        hadoop.create("test5/ok1.txt");

        hadoop.append("test5/append_test1.txt", "\ntesting content for append");


        System.out.println(hadoop.read("test5/append_test1.txt"));

        hadoop.delete("test5/append_test.txt");

        if (hadoop.isDirectory("test5/append_test.txt")) {
            System.out.println("yes! it's directory!");
        } else {
            System.out.println("Something goes wrong.");
        }

        System.out.println(hadoop.list("/"));
    }
}
