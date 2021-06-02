package imsproj;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Product extends JFrame {

	private JPanel contentPane;
	private JPanel contentPane_1;
	private JTextField proname;
	private JTextField Price;
	private JTextField qty;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product frame = new Product();
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
	public Product() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 307);
		contentPane = new JPanel();
		contentPane_1 = new JPanel() {  
            public void paintComponent(Graphics g) {  
                    Image img = Toolkit.getDefaultToolkit().getImage(  
                           Product.class.getResource("/images/bg.jpg"));  
                 g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
	 }  
       }; 
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Product");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(165, 26, 138, 38);
		contentPane_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(165, 93, 57, 14);
		contentPane_1.add(lblNewLabel_1);
		
		proname = new JTextField();
		proname.setBounds(247, 90, 162, 20);
		contentPane_1.add(proname);
		proname.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Price");
		lblNewLabel_1_1.setBounds(172, 174, 43, 14);
		contentPane_1.add(lblNewLabel_1_1);
		
		Price = new JTextField();
		Price.setColumns(10);
		Price.setBounds(247, 171, 162, 20);
		contentPane_1.add(Price);
		
		
		JLabel lblNewLabel_3 = new JLabel("Quantity");
		lblNewLabel_3.setBounds(171, 206, 46, 14);
		contentPane_1.add(lblNewLabel_3);
		
		qty = new JTextField();
		qty.setBounds(247, 203, 162, 20);
		contentPane_1.add(qty);
		qty.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Category Id");
		lblNewLabel_2.setBounds(165, 137, 66, 14);
		contentPane_1.add(lblNewLabel_2);
		
		JLabel Cat_id = new JLabel("*");
		Cat_id.setEnabled(false);
		Cat_id.setBounds(464, 68, 6, 14);
		contentPane_1.add(Cat_id);
		
		JComboBox CatName = new JComboBox();
		CatName.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ims","root","");
					Statement st=con.createStatement();
					String sqlselect="SELECT * FROM categories where Name='"+CatName.getSelectedItem()+"'";
					ResultSet rs=st.executeQuery(sqlselect);
					while(rs.next()) {
						Cat_id.setText(String.valueOf(rs.getInt("Id")));
						
						
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
		CatName.setBounds(247, 133, 162, 22);
		contentPane_1.add(CatName);


		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ims","root","");
			Statement st=con.createStatement();
			String sqlselect="SELECT * FROM categories";
			ResultSet rs=st.executeQuery(sqlselect);
			while(rs.next()) {
				CatName.addItem(rs.getString("Name"));
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
		
		JLabel id = new JLabel("");
		id.setBounds(201, 75, 6, 14);
		contentPane_1.add(id);
		
		
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ims","root","");
					Statement st=con.createStatement();
					String sqlselect="SELECT * FROM product ORDER BY id DESC LIMIT 1";
					ResultSet rs=st.executeQuery(sqlselect);
					if(rs.next()) {
						int ids = Integer.parseInt(rs.getString("Id"));
							ids++;
							id.setText(String.valueOf(ids));							
						//	JOptionPane.showMessageDialog(null, id);
							
							
									

				}
					String sql="insert into product values (?, ?, ?,?,?,?)";
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setInt(1, Integer.parseInt(id.getText()));
					pst.setString(2, proname.getText());
					pst.setInt(3, Integer.parseInt(Cat_id.getText()));
					pst.setInt(4, Integer.parseInt(Price.getText()));
					pst.setInt(5, Integer.parseInt(qty.getText()));
					pst.setInt(6, 1);

					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Inserted successful" );
					
					id.setText("");
					proname.setText("");
					Price.setText("");
					qty.setText("");
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(172, 234, 89, 23);
		contentPane_1.add(btnNewButton);
		
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
		
		JButton btnNewButton_1 = new JButton("Show Product");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowProduct frame = new ShowProduct();
				frame.setVisible(true);
		
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton_1.setBounds(10, 106, 116, 23);
		contentPane_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update Product");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateProduct frame = new UpdateProduct();
				frame.setVisible(true);
		
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton_2.setBounds(10, 154, 116, 23);
		contentPane_1.add(btnNewButton_2);
		
		
		
		
		
	}

}
