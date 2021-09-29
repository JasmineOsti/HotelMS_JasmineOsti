package finalassignment_HMS;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener {
    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");
    JButton signupButton = new JButton("SignUp");
 
 
    Login() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
 
    }
 
    public void setLayoutManager() {
        container.setLayout(null);
    }
 
    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);
        signupButton.setBounds(130, 350, 100, 35);
 
 
    }
 
    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(signupButton);
    }
 

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        signupButton.addActionListener(this);
    }
    


    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
    	if (e.getSource()== loginButton) {
    	try {
            //Creating Connection Object
            Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hms","root","xjq18khaha");
            //Prepared Statement
            PreparedStatement Pstatement=connection.prepareStatement("select email,pass from tbl_register where email=? and pass=?");
            //Specifying the values of it's parameter
            Pstatement.setString(1,userTextField.getText());
            Pstatement.setString(2,passwordField.getText());
            
            ResultSet result = Pstatement.executeQuery();
            
            if (result.next()) {
            	JOptionPane.showMessageDialog(loginButton, "Login Successful.");
            	
            	CustomerBook callRun = new CustomerBook(); 
            	dispose();
            	callRun.run();
            } else {
                JOptionPane.showMessageDialog(loginButton, "Invalid Username or Password");
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    	}
    	
    
       //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }


        }
    }
    public static void main(String[] args) {
    	new Login();
    }

}