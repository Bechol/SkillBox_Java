package homework_42;

public class Main {
    public static void main(String[] args) {
        System.out.println("Вывод в консоль в формате: ТИП MIN/MAX");
        System.out.println(Byte.TYPE.toString().toUpperCase() + " " + Byte.MIN_VALUE + "/" + Byte.MAX_VALUE);
        System.out.println(Short.TYPE.toString().toUpperCase() + " " + Short.MIN_VALUE + "/" + Short.MAX_VALUE);
        System.out.println(Integer.TYPE.toString().toUpperCase() + " " + Integer.MIN_VALUE + "/" + Integer.MAX_VALUE);
        System.out.println(Long.TYPE.toString().toUpperCase() + " " + Long.MIN_VALUE + "/" + Long.MAX_VALUE);
        System.out.println(Float.TYPE.toString().toUpperCase() + " " + Float.MIN_VALUE + "/" + Float.MAX_VALUE);
        System.out.println(Double.TYPE.toString().toUpperCase() + " " + Double.MIN_VALUE + "/" + Double.MAX_VALUE);
    }
}
