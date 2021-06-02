package imsproj;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;  
import java.awt.Image;  
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class UpdateProduct extends JFrame {

	private JPanel contentPane;
	private JPanel contentPane_1;
	private JTextField Pro_name;
	private JTextField Qty;
	private JTextField Price;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateProduct frame = new UpdateProduct();
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
	public UpdateProduct() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 416);
		contentPane = new JPanel();
		contentPane_1 = new JPanel() {  
                 public void paintComponent(Graphics g) {  
	                     Image img = Toolkit.getDefaultToolkit().getImage(  
                                UpdateProduct.class.getResource("/images/bg.jpg"));  
                      g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
		 }  
            }; 
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product Name");
		lblNewLabel.setBounds(170, 59, 83, 14);
		contentPane_1.add(lblNewLabel);
	
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(170, 99, 46, 14);
		contentPane_1.add(lblNewLabel_1);
		
		JLabel Product_id = new JLabel("*");
		Product_id.setBounds(263, 99, 46, 14);
		contentPane_1.add(Product_id);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setBounds(170, 134, 70, 14);
		contentPane_1.add(lblNewLabel_2);
		
		Pro_name = new JTextField();
		Pro_name.setBounds(263, 131, 178, 20);
		contentPane_1.add(Pro_name);
		Pro_name.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Quantity");
		lblNewLabel_3.setBounds(170, 219, 70, 14);
		contentPane_1.add(lblNewLabel_3);
		
		Qty = new JTextField();
		Qty.setBounds(263, 216, 178, 20);
		contentPane_1.add(Qty);
		Qty.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Price");
		lblNewLabel_4.setBounds(170, 261, 70, 14);
		contentPane_1.add(lblNewLabel_4);
		
		Price = new JTextField();
		Price.setBounds(263, 258, 178, 20);
		contentPane_1.add(Price);
		Price.setColumns(10);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(170, 177, 83, 14);
		contentPane_1.add(lblCategory);
		
		
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(170, 304, 83, 14);
		contentPane_1.add(lblStatus);
				
		JLabel Status_change = new JLabel("*");
		Status_change.setBounds(445, 304, 46, 14);
		contentPane_1.add(Status_change);
		
		JLabel Category_id = new JLabel("*");
		Category_id.setBounds(445, 177, 46, 14);
		contentPane_1.add(Category_id);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ims","root","");
					Statement st=con.createStatement();
					String sqlselect="UPDATE product SET Name='"+Pro_name.getText()+"' , Cat_id='"+Category_id.getText()+"' , Price = '"+ Price.getText()+ "' , Quantity='"+ Qty.getText() +"' WHERE `product`.`Id` = '"+Product_id.getText()+"' ";
					PreparedStatement pst = con.prepareStatement(sqlselect);
					pst.execute();
					JOptionPane.showMessageDialog(btnNewButton, "Updated");
				
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
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnNewButton.setBounds(170, 343, 120, 23);
		contentPane_1.add(btnNewButton);

		JComboBox Status = new JComboBox();
		Status.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ims","root","");
					Statement st=con.createStatement();
					String sqlselect="select * from categories";
					ResultSet rs=st.executeQuery(sqlselect);
					while(rs.next()) {
						if(rs.getInt("Status") == 1) {
							Status_change.setText(String.valueOf("1"));
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
		Status.setBounds(263, 300, 178, 22);
		contentPane_1.add(Status);
		
		JComboBox Cat_name = new JComboBox();
		Cat_name.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ims","root","");
					Statement st=con.createStatement();
					String sqlselect="select * from categories where name ='"+Cat_name.getSelectedItem()+"'";
					ResultSet rs=st.executeQuery(sqlselect);
					while(rs.next()) {
					Category_id.setText(String.valueOf(rs.getInt("Id")));
					
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
		Cat_name.setBounds(263, 173, 178, 22);
		contentPane_1.add(Cat_name);
		
		JComboBox Product = new JComboBox();
		Product.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ims","root","");
					Statement st=con.createStatement();
					String sqlselect="SELECT Product.Id,Product.Name,categories.Name as catname,product.Price,product.Quantity,product.Status FROM product INNER JOIN categories ON product.Cat_id = categories.Id and product.Name='"+Product.getSelectedItem()+"'";
					ResultSet rs=st.executeQuery(sqlselect);
					while(rs.next()) {
						Pro_name.setText(rs.getString("Name"));
						Product_id.setText(String.valueOf(rs.getInt("Id")));
						Cat_name.addItem(rs.getString("catname"));
						Qty.setText(String.valueOf(rs.getInt("Quantity")));
						Price.setText(String.valueOf(rs.getInt("Price")));
					
						if(rs.getInt("Status") == 1) {
							Status.addItem("Active");
							Status.addItem("Inactive");
								
						}
						else {
							Status.addItem("Inactive");
							Status.addItem("Active");
									
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
		Product.setBounds(263, 55, 178, 22);
		contentPane_1.add(Product);
		
		JLabel lblNewLabel_5 = new JLabel("Update Products");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_5.setBounds(225, 11, 139, 33);
		contentPane_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dashboard frame = new Dashboard();
				frame.setVisible(true);
			}
		});
		lblNewLabel_4_1.setBounds(10, 11, 41, 38);
		contentPane_1.add(lblNewLabel_4_1);
		Image icon = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		lblNewLabel_4_1.setIcon(new ImageIcon(icon));
		
		JButton btnNewButton_1 = new JButton("Add Product");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product frame = new Product();
				frame.setVisible(true);
				
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton_1.setBounds(10, 105, 116, 23);
		contentPane_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Show Product");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowProduct frame = new ShowProduct();
				frame.setVisible(true);
				
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton_2.setBounds(10, 150, 116, 23);
		contentPane_1.add(btnNewButton_2);
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ims","root","");
			Statement st=con.createStatement();
			String sqlselect="SELECT Product.Id,Product.Name,categories.Name as catname,product.Price,product.Quantity,product.Status FROM product INNER JOIN categories ON product.Cat_id = categories.Id";
			ResultSet rs=st.executeQuery(sqlselect);
			while(rs.next()) {
			
				Product.addItem(rs.getString("Name"));
			}
			
	}
	 catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		System.out.print(e1);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		System.out.print(e1);
	}
	
	
	
	}
}
