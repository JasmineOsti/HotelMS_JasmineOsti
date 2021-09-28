package finalassignment_HMS;

import javax.swing.JFrame;

public class LoginFrame {
    public static void main(String[] a) {
        Login frame = new Login();
        frame.setTitle("Hotel Management System");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
 
    }
 
}
