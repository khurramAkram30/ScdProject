package imsproj;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Categories extends JFrame {

	private JPanel contentPane;
	private JPanel contentPane_1;
	private JTextField cat_name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Categories frame = new Categories();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Categories() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane_1 = new JPanel() {  
            public void paintComponent(Graphics g) {  
                    Image img = Toolkit.getDefaultToolkit().getImage(  
                           Categories.class.getResource("/images/bg.jpg"));  
                 g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
	 }  
       }; 
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Category Name");
		lblNewLabel.setBounds(157, 111, 84, 14);
		contentPane_1.add(lblNewLabel);
		
		cat_name = new JTextField();
		cat_name.setBounds(250, 108, 156, 20);
		contentPane_1.add(cat_name);
		cat_name.setColumns(10);
	
		
		JLabel Get_id = new JLabel("");
		Get_id.setBounds(157, 55, 46, 14);
		contentPane_1.add(Get_id);
		
		JButton btnNewButton = new JButton("Add Category");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					Get_id.setEnabled(false);
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ims","root","");
					Statement st=con.createStatement();
					String sqlselect="SELECT * FROM categories ORDER BY id DESC LIMIT 1";
					ResultSet rs=st.executeQuery(sqlselect);
					if(rs.next()) {
						int id = Integer.parseInt(rs.getString("Id"));
							id++;
							Get_id.setText(String.valueOf(id));							
							JOptionPane.showMessageDialog(null, Get_id);
									

				}
					String sql="insert into categories values (?, ?, ?)";
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setInt(1, Integer.parseInt(Get_id.getText()));
					pst.setString(2, cat_name.getText());
					pst.setInt(3, 1);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Inserted successful" );
					
//					Getid.setText("");
//					username.setText("");
//					pswd.setText("");
//					status.setText("");
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
			
			}
		});
		btnNewButton.setBounds(246, 150, 99, 23);
		contentPane_1.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("ADD CATEGORIES");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1.setBounds(187, 28, 115, 40);
		contentPane_1.add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("Update Categories");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateCategories frame = new UpdateCategories();
				frame.setVisible(true);
		
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton_2.setBounds(10, 150, 131, 23);
		contentPane_1.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Show Categories");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowCategories frame = new ShowCategories();
				frame.setVisible(true);
		
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton_1.setBounds(10, 105, 131, 23);
		contentPane_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Dashboard frame = new Dashboard();
				frame.setVisible(true);
		
			}
		});
		lblNewLabel_4.setBounds(10, 11, 41, 38);
		contentPane_1.add(lblNewLabel_4);

		Image icon = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		lblNewLabel_4.setIcon(new ImageIcon(icon));
		
		
	
	}
}
