

package imsproj;

import java.sql.*;
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
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class UserRegister extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField username;
	private JTextField pswd;
	private JTextField phone;
	private JTextField email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegister frame = new UserRegister();
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
	public UserRegister() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 594, 380);
		contentPane = new JPanel();
		contentPane = new JPanel() {  
            public void paintComponent(Graphics g) {  
                    Image img = Toolkit.getDefaultToolkit().getImage(  
                           Categories.class.getResource("/images/bg.jpg"));  
                 g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
	 }  
       };
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Name = new JLabel("Name");
		Name.setBounds(171, 95, 66, 25);
		contentPane.add(Name);
		
		name = new JTextField();
		name.setBounds(248, 97, 233, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(171, 144, 66, 14);
		contentPane.add(lblNewLabel_1);
		
		username = new JTextField();
		username.setBounds(248, 141, 233, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(171, 186, 66, 14);
		contentPane.add(lblNewLabel_2);
		
		pswd = new JTextField();
		pswd.setBounds(248, 183, 233, 20);
		contentPane.add(pswd);
		pswd.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Phone");
		lblNewLabel_3.setBounds(171, 227, 66, 14);
		contentPane.add(lblNewLabel_3);
		
		phone = new JTextField();
		phone.setBounds(248, 224, 233, 20);
		contentPane.add(phone);
		phone.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setBounds(171, 268, 40, 14);
		contentPane.add(lblNewLabel_4);
		
		email = new JTextField();
		email.setBounds(248, 265, 233, 20);
		contentPane.add(email);
		email.setColumns(10);
		

		JLabel Getid = new JLabel("*");
		Getid.setBounds(248, 82, 66, 14);
		contentPane.add(Getid);

		
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Getid.setEnabled(false);
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ims","root","");
					Statement st=con.createStatement();
					String sqlselect="SELECT * FROM users ORDER BY id DESC LIMIT 1";
					ResultSet rs=st.executeQuery(sqlselect);
					if(rs.next()) {
						int id = Integer.parseInt(rs.getString("id"));
						id++;
						Getid.setText(String.valueOf(id));
				}
					String sql="insert into users values (?, ?, ?, ?,?,?,?)";
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setInt(1, Integer.parseInt(Getid.getText()));
					pst.setString(2, name.getText());
					pst.setString(3, username.getText());
					pst.setString(4, pswd.getText());
					pst.setString(5, phone.getText());
					pst.setString(6,email.getText());
					pst.setInt(7, 1);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Inserted successful" );
					
					Getid.setText("");
//					name.setText("");
//					username.setText("");
//					pswd.setText("");
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
			}
		});
		btnNewButton.setBounds(171, 307, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dashboard db =new Dashboard();
				db.setVisible(true);
			}
		});
		lblNewLabel_4_1.setBounds(0, 11, 41, 38);
		contentPane.add(lblNewLabel_4_1);
		Image icon = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		lblNewLabel_4_1.setIcon(new ImageIcon(icon));
		
		JLabel lblNewLabel = new JLabel("Add User");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(218, 22, 169, 38);
		contentPane.add(lblNewLabel);
			}
}
