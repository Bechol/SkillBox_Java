import javax.swing.*;

public class Main {


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(600,130);
        frame.add(new MainForm().getMainPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



    }
}
