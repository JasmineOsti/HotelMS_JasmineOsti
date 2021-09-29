package finalassignment_HMS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerBook {

	private JFrame frame;
	private JTextField name_entry;
	private JTextField address_entry;
	private JTextField phone_entry;
	private JTextField email_entry;
	private JTextField room_entry;
	private JTextField date_entry;
	Connection connection;

	String url = "jdbc:mysql://127.0.0.1:3306/hms";
	String user = "root";
	String pass = "xjq18khaha";
	private JTable table;
	private JTextField id_entry;
	private JTable table_1;
	

	
	
	private JTextField room_id_entry;
	private JTextField status_entry;

	/**
	 * Launch the application.
	 */
	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerBook window = new CustomerBook();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerBook() {
		initialize();
		display();
		display_room();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void clear() {
		id_entry.setText(null);
		name_entry.setText(null);
		address_entry.setText(null);
		phone_entry.setText(null);
		email_entry.setText(null);
		room_entry.setText(null);
		date_entry.setText(null);
		room_id_entry.setText(null);
		status_entry.setText(null);
		
		display();
		display_room();
	}
	public void search() {
		try {

			String query = "select * from tbl_customer WHERE customer_name=?";

			PreparedStatement pst = connection.prepareStatement(query);

			pst.setString(1, name_entry.getText());

			ResultSet rs = pst.executeQuery();

			table.setModel(DbUtils.resultSetToTableModel(rs));
			DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
			table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer1);
			table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer1);
			table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer1);
			table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer1);
			table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer1);
			table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer1);
			table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer1);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	public void display() {

		try {

			connection = DriverManager.getConnection(url, user, pass);
			String query = "select * from tbl_customer";

			PreparedStatement pst = connection.prepareStatement(query);

			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));

			if (table.getRowCount() != 0) {

				DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
				centerRenderer1.setHorizontalAlignment(JLabel.CENTER);
				table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer1);
				table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer1);
				table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer1);
				table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer1);
				table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer1);
				table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer1);
				table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer1);


				table.getTableHeader().setBorder(new LineBorder(Color.white));
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}
	
	public void display_room() {

		try {

			connection = DriverManager.getConnection(url, user, pass);
			String query = "select * from tbl_room";

			PreparedStatement pst = connection.prepareStatement(query);

			ResultSet rs = pst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));

			if (table_1.getRowCount() != 0) {

				DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
				centerRenderer1.setHorizontalAlignment(JLabel.CENTER);

				table_1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer1);
				table_1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer1);
				table_1.getColumnModel().getColumn(2).setCellRenderer( centerRenderer1 );
				table_1.getColumnModel().getColumn(3).setCellRenderer(centerRenderer1);



				table_1.getTableHeader().setBorder(new LineBorder(Color.white));
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}


	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBackground(Color.GRAY);
		frame.setBounds(100, 100, 1059, 531);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 1025, 211);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int i = table.getSelectedRow();
				TableModel model = table.getModel();

				id_entry.setText(model.getValueAt(i, 0).toString());
				name_entry.setText(model.getValueAt(i, 1).toString());
				address_entry.setText(model.getValueAt(i, 2).toString());
				phone_entry.setText(model.getValueAt(i, 3).toString());
				email_entry.setText(model.getValueAt(i, 4).toString());
				room_entry.setText(model.getValueAt(i, 5).toString());
				date_entry.setText(model.getValueAt(i, 6).toString());
				
			}
		});
		
		
		scrollPane.setViewportView(table);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(735, 231, 300, 193);
		frame.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int r = table_1.getSelectedRow();
				TableModel model2 = table_1.getModel();

				room_id_entry.setText(model2.getValueAt(r, 0).toString());
				status_entry.setText(model2.getValueAt(r, 3).toString());

				
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		name_entry = new JTextField();
		name_entry.setBounds(147, 281, 220, 41);
		frame.getContentPane().add(name_entry);
		name_entry.setColumns(10);

		JLabel lblNewLabel = new JLabel("Customer Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 295, 118, 27);
		frame.getContentPane().add(lblNewLabel);

		address_entry = new JTextField();
		address_entry.setColumns(10);
		address_entry.setBounds(147, 332, 220, 41);
		frame.getContentPane().add(address_entry);

		JLabel lblCustomerAddress = new JLabel("Address");
		lblCustomerAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCustomerAddress.setBounds(20, 346, 118, 27);
		frame.getContentPane().add(lblCustomerAddress);

		phone_entry = new JTextField();
		phone_entry.setColumns(10);
		phone_entry.setBounds(147, 383, 220, 41);
		frame.getContentPane().add(phone_entry);

		JLabel lblPhoneNo = new JLabel("Phone No.");
		lblPhoneNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPhoneNo.setBounds(20, 397, 118, 27);
		frame.getContentPane().add(lblPhoneNo);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(393, 295, 127, 27);
		frame.getContentPane().add(lblEmail);

		email_entry = new JTextField();
		email_entry.setColumns(10);
		email_entry.setBounds(505, 281, 220, 41);
		frame.getContentPane().add(email_entry);

		JLabel lblNewLabel_1_1 = new JLabel("Room No.");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(393, 346, 127, 27);
		frame.getContentPane().add(lblNewLabel_1_1);

		room_entry = new JTextField();
		room_entry.setColumns(10);
		room_entry.setBounds(505, 332, 220, 41);
		frame.getContentPane().add(room_entry);

		JLabel lblNewLabel_2_1 = new JLabel("Booking Date");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(393, 397, 127, 27);
		frame.getContentPane().add(lblNewLabel_2_1);

		date_entry = new JTextField();
		date_entry.setColumns(10);
		date_entry.setBounds(505, 383, 220, 41);
		frame.getContentPane().add(date_entry);

		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String custName = name_entry.getText();
				String custAddress = address_entry.getText();
				String custPhone = phone_entry.getText();
				String custEmail = email_entry.getText();
				String custRoom = room_entry.getText();
				String bookDate = date_entry.getText();

				String url = "jdbc:mysql://127.0.0.1:3306/hms";
				String user = "root";
				String pass = "xjq18khaha";

				if (custName.isEmpty() && custAddress.isEmpty() && custPhone.isEmpty() && custEmail.isEmpty()
						&& custRoom.isEmpty()) {
					JOptionPane.showMessageDialog(btnNewButton, "Enter valid details");
				} else {
					try {
						connection = DriverManager.getConnection(url, user, pass);

						String query = " insert into tbl_customer (customer_name, address, phone_no, email, room_id, booking_date)"
								+ " values (?, ?, ?, ?, ?, ?)";

						Statement sta = connection.createStatement();

						PreparedStatement preparedStmt = connection.prepareStatement(query);

						preparedStmt.setString(1, custName);
						preparedStmt.setString(2, custAddress);
						preparedStmt.setString(3, custPhone);
						preparedStmt.setString(4, custEmail);
						preparedStmt.setString(5, custRoom);
						preparedStmt.setString(6, bookDate);

						boolean x = preparedStmt.execute();
						if (x == true) {
							JOptionPane.showMessageDialog(btnNewButton, "Customer Data Inserted");
						}
						connection.close();
						display();
						clear();

					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(20, 445, 99, 39);
		frame.getContentPane().add(btnNewButton);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int custId = Integer.parseInt(id_entry.getText());
				String custName = name_entry.getText();
				String custAddress = address_entry.getText();
				String custPhone = phone_entry.getText();
				String custEmail = email_entry.getText();
				String custRoom = room_entry.getText();
				String bookDate = date_entry.getText();
				

				
					try {
						connection = DriverManager.getConnection(url, user, pass);
						
						
						String query = ("update tbl_customer set customer_name=?,address=?,phone_no=?,email=?,room_id=?,booking_date=? where customer_id=?");
                              
                        
                        PreparedStatement preparedStmt = connection.prepareStatement(query);
                        
                        
                        preparedStmt.setString (1, custName);
                        preparedStmt.setString (2, custAddress);
                        preparedStmt.setString (3, custPhone);
                        preparedStmt.setString (4, custEmail);
                        preparedStmt.setString (5, custRoom);
                        preparedStmt.setString (6, bookDate);
                        preparedStmt.setInt(7, custId);
                        
                        
                        
                        preparedStmt.executeUpdate();
						JOptionPane.showMessageDialog(btnUpdate,"Record updated Successfully!");
						display();
						
						
						
					}
					catch (SQLException el) {
						el.printStackTrace();
					}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(144, 445, 99, 39);
		frame.getContentPane().add(btnUpdate);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			search();			          
					
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(269, 445, 99, 39);
		frame.getContentPane().add(btnSearch);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(255, 0, 0));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String query = " delete from tbl_customer where customer_id=?";
				
				 int custId;
					custId  = Integer.parseInt(id_entry.getText());
					
					 try {
						 PreparedStatement preparedStmt = connection.prepareStatement(query);
					
						 preparedStmt.setInt(1, custId);
						 preparedStmt.executeUpdate();
				            JOptionPane.showMessageDialog(null, "Record Deleted Successfully!");
				            clear();
								
						}
		 
			            catch (SQLException e1) {
							
							e1.printStackTrace();
						}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(516, 445, 99, 39);
		frame.getContentPane().add(btnDelete);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
							clear();
				}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReset.setBackground(Color.RED);
		btnReset.setBounds(393, 445, 99, 39);
		frame.getContentPane().add(btnReset);
		
		id_entry = new JTextField();
		id_entry.setEditable(false);
		id_entry.setColumns(10);
		id_entry.setBounds(147, 231, 220, 41);
		frame.getContentPane().add(id_entry);
		
		JLabel lblId = new JLabel("Customer Id");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(21, 244, 115, 27);
		frame.getContentPane().add(lblId);
		
		JLabel lblRoomId = new JLabel("Room Id");
		lblRoomId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRoomId.setBounds(393, 244, 115, 27);
		frame.getContentPane().add(lblRoomId);
		
		room_id_entry = new JTextField();
		room_id_entry.setEditable(false);
		room_id_entry.setColumns(10);
		room_id_entry.setBounds(505, 231, 220, 41);
		frame.getContentPane().add(room_id_entry);
		
		JButton btnUpdateRoom = new JButton("Update Room");
		btnUpdateRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int roomID = Integer.parseInt(room_id_entry.getText());
				String roomStatus = status_entry.getText();
				
				try {
					connection = DriverManager.getConnection(url, user, pass);
					
					
					String query = ("update tbl_room set reserved=? where room_no=?");
                          
                    
                    PreparedStatement preparedStmt = connection.prepareStatement(query);
                    
                    
                    preparedStmt.setString (1, roomStatus);
                    preparedStmt.setInt (2, roomID);

                    
                    
                    
                    preparedStmt.executeUpdate();
					JOptionPane.showMessageDialog(btnUpdate,"Record updated Successfully!");
					
					display_room();		
					clear();
			}
				catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		
		
		btnUpdateRoom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdateRoom.setBounds(901, 445, 134, 39);
		frame.getContentPane().add(btnUpdateRoom);
		
		status_entry = new JTextField();
		status_entry.setColumns(10);
		status_entry.setBounds(824, 446, 76, 41);
		frame.getContentPane().add(status_entry);
		
		JLabel lblReserved = new JLabel("Reserved");
		lblReserved.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblReserved.setBounds(735, 457, 300, 27);
		frame.getContentPane().add(lblReserved);
		
		
		
		
		
	}
}
