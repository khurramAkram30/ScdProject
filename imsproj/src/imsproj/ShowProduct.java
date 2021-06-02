package imsproj;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowProduct extends JFrame {

	private JPanel contentPane;
	private JPanel contentPane_1;
	private JTable Jtable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowProduct frame = new ShowProduct();
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
	public ShowProduct() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 300);
		contentPane = new JPanel();
		contentPane_1 = new JPanel() {  
            public void paintComponent(Graphics g) {  
                    Image img = Toolkit.getDefaultToolkit().getImage(  
                           ShowProduct.class.getResource("/images/bg.jpg"));  
                 g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
	 }  
       }; 
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(126, 45, 500, 205);
		contentPane_1.add(scrollPane);
		
		Jtable = new JTable();
		scrollPane.setViewportView(Jtable);
		Jtable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Category Name", "Price", "Quantity", "Status"
			}
		));
		
		JButton btnNewButton_1 = new JButton("Add Product");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product frame = new Product();
				frame.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton_1.setBounds(0, 94, 116, 23);
		contentPane_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update Product");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateProduct frame = new UpdateProduct();
				frame.setVisible(true);
		
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton_2.setBounds(0, 139, 116, 23);
		contentPane_1.add(btnNewButton_2);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dashboard frame = new Dashboard();
				frame.setVisible(true);
			}
		});
		lblNewLabel_4.setBounds(0, 0, 41, 38);
		contentPane_1.add(lblNewLabel_4);
		Image icon = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		lblNewLabel_4.setIcon(new ImageIcon(icon));
		
		JLabel lblNewLabel = new JLabel("Show Product");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel.setBounds(253, 11, 164, 27);
		contentPane_1.add(lblNewLabel);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ims","root","");
			Statement st=con.createStatement();
			String sqlselect="SELECT Product.Id,Product.Name,categories.Name as catname,product.Price,product.Quantity,product.Status FROM product INNER JOIN categories ON product.Cat_id = categories.Id";
			ResultSet rs=st.executeQuery(sqlselect);
			while(rs.next()) {
			String id=String.valueOf(rs.getInt("Id"));
			String Name=rs.getString("Name");
			String Cat_Name=rs.getString("catname");
			String Price=String.valueOf(rs.getInt("Quantity"));
			String Qty=String.valueOf(rs.getInt("Price"));
			String Status=String.valueOf(rs.getInt("Status"));
//			JOptionPane.showMessageDialog(null, Name);
			

				String tblData[]= {id,Name,Cat_Name,Price,Qty,Status};
				DefaultTableModel tbldata = (DefaultTableModel)Jtable.getModel();
				tbldata.addRow(tblData);
					
			//	JOptionPane.showMessageDialog(null, id);
			
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
	

