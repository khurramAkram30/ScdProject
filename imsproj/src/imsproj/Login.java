package imsproj;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Login extends JFrame {
  
	public static int id;
	private JPanel contentPane;
	private JTextField username;
	private JPasswordField pswd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 274);
		contentPane = new JPanel();
		contentPane = new JPanel() {  
            public void paintComponent(Graphics g) {  
                    Image img = Toolkit.getDefaultToolkit().getImage(  
                           UpdateProduct.class.getResource("/images/bg.jpg"));  
                 g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
	 }  
       }; 
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(193, 160, 111, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ims","root","");
					Statement st=con.createStatement();
					String sql="select * from users where username = '"+username.getText()+"' and password='"+pswd.getText()+"'";
					ResultSet rs=st.executeQuery(sql);
					while(rs.next()) {
					id=rs.getInt("Id");
					Dashboard db =new Dashboard();
					db.setVisible(true);
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setBounds(193, 71, 78, 14);
		contentPane.add(lblNewLabel);
		
		username = new JTextField();
		username.setBounds(281, 68, 181, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(193, 114, 71, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(27, 71, 156, 112);
		contentPane.add(lblNewLabel_2);
		Image icon = new ImageIcon(this.getClass().getResource("/logon.png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(icon));
		
		pswd = new JPasswordField();
		pswd.setBounds(281, 111, 181, 20);
		contentPane.add(pswd);
	}
}
