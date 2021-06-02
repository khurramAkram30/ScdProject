package imsproj;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateCategories extends JFrame {

	private JPanel contentPane;
	private JPanel contentPane_1;
	private JTextField CatName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCategories frame = new UpdateCategories();
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

	final static String jdb="jdbc:mysql://localhost:3306/ims";
	final static String user="root";
	public UpdateCategories() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 300);
		contentPane = new JPanel();
		contentPane_1 = new JPanel() {  
            public void paintComponent(Graphics g) {  
                    Image img = Toolkit.getDefaultToolkit().getImage(  
                           UpdateCategories.class.getResource("/images/bg.jpg"));  
                 g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
	 }  
       }; 
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("Categories Name");
		lblNewLabel.setBounds(180, 63, 109, 14);
		contentPane_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Category Id");
		lblNewLabel_1.setBounds(180, 101, 109, 14);
		contentPane_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Category Name");
		lblNewLabel_2.setBounds(180, 130, 109, 14);
		contentPane_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Category Status");
		lblNewLabel_3.setBounds(180, 168, 109, 14);
		contentPane_1.add(lblNewLabel_3);
		
		CatName = new JTextField();
		CatName.setBounds(300, 127, 172, 20);
		contentPane_1.add(CatName);
		CatName.setColumns(10);
		
		JComboBox CatStatus = new JComboBox();
		CatStatus.setBounds(299, 164, 100, 22);
		contentPane_1.add(CatStatus);
		
		JLabel CatId = new JLabel("*");
		CatId.setBounds(299, 98, 100, 21);
		contentPane_1.add(CatId);
		
		JButton btnNewButton = new JButton("UPDATE");
		btnNewButton.addActionListener(new ActionListener() {
			public synchronized void actionPerformed(ActionEvent e) {
				try {
					int St;
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection(jdb,user,"");
					Statement st=con.createStatement();
					if(CatStatus.getSelectedItem() == "Active") {
						St=1;
					}
					if(CatStatus.getSelectedItem() == "InActive") {
						St=2;
					}
					String sqlselect="UPDATE `categories` SET Name = '"+CatName.getText()+"' WHERE `categories`.`Id` = '"+Integer.parseInt(CatId.getText()) +"';";
					
					PreparedStatement pst = con.prepareStatement(sqlselect);
					pst.execute();
					JOptionPane.showMessageDialog(null, "updated");
					
				}
				 catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton.setBounds(178, 208, 89, 23);
		contentPane_1.add(btnNewButton);
	
		JComboBox Catname = new JComboBox();
		Catname.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ims","root","");
					Statement st=con.createStatement();
					String sqlselect="SELECT * FROM categories where Name='"+Catname.getSelectedItem()+"'";
					ResultSet rs=st.executeQuery(sqlselect);
					while(rs.next()) {
						CatId.setText(String.valueOf(rs.getInt("Id")));
						CatName.setText(rs.getString("Name"));
						if(rs.getInt("Status") == 1 ) {
							CatStatus.addItem("Active");
							CatStatus.addItem("InActive");
							
						}
						else if(rs.getInt("Status") == 2 ) {
							CatStatus.addItem("InActive");
							CatStatus.addItem("Active");
							
						}
						
					}
					
			}
			 catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		Catname.setBounds(299, 59, 173, 22);
		contentPane_1.add(Catname);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dashboard frame = new Dashboard();
				frame.setVisible(true);
				
			}
		});
		lblNewLabel_4.setBounds(10, 11, 32, 38);
		contentPane_1.add(lblNewLabel_4);
		Image icon = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		lblNewLabel_4.setIcon(new ImageIcon(icon));
		
		
		JButton btnNewButton_1 = new JButton("Categories");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Categories frame = new Categories();
				frame.setVisible(true);
				
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton_1.setBounds(10, 106, 125, 23);
		contentPane_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Show Categories");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowCategories frame = new ShowCategories();
				frame.setVisible(true);
				
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton_2.setBounds(10, 154, 125, 23);
		contentPane_1.add(btnNewButton_2);
		
		JLabel lblNewLabel_5 = new JLabel("Update Categories");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_5.setBounds(226, 11, 156, 22);
		contentPane_1.add(lblNewLabel_5);
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ims","root","");
			Statement st=con.createStatement();
			String sqlselect="SELECT * FROM categories";
			ResultSet rs=st.executeQuery(sqlselect);
			while(rs.next()) {
				Catname.addItem(rs.getString("Name"));
			}
			
		}
		catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
	
		
	}
}

