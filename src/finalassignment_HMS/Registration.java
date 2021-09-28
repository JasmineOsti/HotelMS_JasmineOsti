package finalassignment_HMS;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Registration implements ActionListener {
	    JFrame frame;
	    String[] gender={"Male","Female"};
	    JLabel nameLabel=new JLabel("NAME");
	    JLabel genderLabel=new JLabel("GENDER");
	    JLabel PhoneNoLabel=new JLabel("PHONE NO");
	    JLabel passwordLabel=new JLabel("PASSWORD");
	    JLabel confirmPasswordLabel=new JLabel("CONFIRM PASSWORD");
	    JLabel cityLabel=new JLabel("CITY");
	    JLabel emailLabel=new JLabel("EMAIL");
	    JTextField nameTextField=new JTextField();
	    JComboBox genderComboBox=new JComboBox(gender);
	    JTextField phonenoTextField=new JTextField();
	    JPasswordField passwordField=new JPasswordField();
	    JPasswordField confirmPasswordField=new JPasswordField();
	    JTextField cityTextField=new JTextField();
	    JTextField emailTextField=new JTextField();
	    JButton registerButton=new JButton("REGISTER");
	    JButton resetButton=new JButton("RESET");


	    Registration()
	    {
	        createWindow();
	        setLocationAndSize();
	        addComponentsToFrame();
	        actionEvent();
	    }
	    public void createWindow()
	    {
	        frame=new JFrame();
	        frame.setTitle("Hotel Management System");
	        frame.setBounds(40,40,380,600);
	        frame.getContentPane().setBackground(Color.pink);
	        frame.getContentPane().setLayout(null);
	        frame.setVisible(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLocationRelativeTo(null);
	        frame.setResizable(false);
	    }
	    public void setLocationAndSize()
	    {
	        nameLabel.setBounds(20,20,40,70);
	        genderLabel.setBounds(20,70,80,70);
	        PhoneNoLabel.setBounds(20,120,100,70);
	        passwordLabel.setBounds(20,170,100,70);
	        confirmPasswordLabel.setBounds(20,220,140,70);
	        cityLabel.setBounds(20,270,100,70);
	        emailLabel.setBounds(20,320,100,70);
	        nameTextField.setBounds(180,43,165,23);
	        genderComboBox.setBounds(180,93,165,23);
	        phonenoTextField.setBounds(180,143,165,23);
	        passwordField.setBounds(180,193,165,23);
	        confirmPasswordField.setBounds(180,243,165,23);
	        cityTextField.setBounds(180,293,165,23);
	        emailTextField.setBounds(180,343,165,23);
	        registerButton.setBounds(70,400,100,35);
	        resetButton.setBounds(220,400,100,35);
	    }
	    public void addComponentsToFrame()
	    {
	        frame.add(nameLabel);
	        frame.add(genderLabel);
	        frame.add(PhoneNoLabel);
	        frame.add(passwordLabel);
	        frame.add(confirmPasswordLabel);
	        frame.add(cityLabel);
	        frame.add(emailLabel);
	        frame.add(nameTextField);
	        frame.add(genderComboBox);
	        frame.add(phonenoTextField);
	        frame.add(passwordField);
	        frame.add(confirmPasswordField);
	        frame.add(cityTextField);
	        frame.add(emailTextField);
	        frame.add(registerButton);
	        frame.add(resetButton);
	    }
	    public void actionEvent()
	    {
	        registerButton.addActionListener(this);
	        resetButton.addActionListener(this);
	    }


	    @Override
	    public void actionPerformed(ActionEvent e) {
	        if(e.getSource()==registerButton)
	        {
	            try {
	                //Creating Connection Object
	                Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hms","root","xjq18khaha");
	                //Prepared Statement
	                PreparedStatement Pstatement=connection.prepareStatement("insert into tbl_register (name,gender,contact,pass,confirm_pass,city,email)"
	                		+ "values(?,?,?,?,?,?,?)");
	                //Specifying the values of it's parameter
	                Pstatement.setString(1,nameTextField.getText());
	                Pstatement.setString(2,genderComboBox.getSelectedItem().toString());
	                Pstatement.setString(3,phonenoTextField.getText());
	                Pstatement.setString(4,passwordField.getText());
	                Pstatement.setString(5,confirmPasswordField.getText());
	                Pstatement.setString(6,cityTextField.getText());
	                Pstatement.setString(7,emailTextField.getText());
	                //Checking for the Password match
	                if(passwordField.getText().equalsIgnoreCase(confirmPasswordField.getText()))
	                {
	                    //Executing query
	                	  boolean ans = Pstatement.execute();
	                      if (ans == true) {
	                     	 JOptionPane.showMessageDialog(registerButton, "Account not created");
	                      } 
	                      else {
	                     	  JOptionPane.showMessageDialog(registerButton, "Your account is sucessfully created");
	                           frame.dispose();
	                           new Login();
	                           new LoginFrame();
	                      }
	                    }
	                else
	                {
	                    JOptionPane.showMessageDialog(null,"password did not match");
	                }

	            } catch (SQLException e1) {
	                e1.printStackTrace();
	            }


	        }
	        if(e.getSource()==resetButton)
	        {
	            //Clearing Fields
	            nameTextField.setText("");
	            genderComboBox.setSelectedItem("Male");
	            phonenoTextField.setText("");
	            passwordField.setText("");
	            confirmPasswordField.setText("");
	            cityTextField.setText("");
	            emailTextField.setText("");
	        }

	    }
	    public static void main(String[] args) {
	    	new Registration();
	    }
	}




