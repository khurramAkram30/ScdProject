package imsproj;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Invoice extends JFrame {

	private JPanel contentPane;
	private JTable jtable;
	private JTextField Cus_name;
	private JTextField Cus_pho;
	private JTextField qty;
	Login lo= new Login();
	int U_id=lo.id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Invoice frame = new Invoice();
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
	int id=1,totalamount=0;
	private JPanel contentPane_2;
	
	public Invoice() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 434);
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
		contentPane.setLayout(null);
		
		
		JPanel contentPane_1 = new JPanel();
		contentPane_2 = new JPanel() {  
            public void paintComponent(Graphics g) {  
                    Image img = Toolkit.getDefaultToolkit().getImage(  
                           UpdateProduct.class.getResource("/images/bg.jpg"));  
                 g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
	 }  
       }; 
		contentPane_2.setLayout(null);
		contentPane_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_2.setBounds(0, 0, 738, 395);
		contentPane.add(contentPane_2);
	
		
		JLabel User_id = new JLabel("");
		User_id.setBounds(46, 30, 46, 14);
		contentPane_2.add(User_id);
		
		User_id.setText(String.valueOf(U_id));
		
		
		JLabel Sale_id = new JLabel("");
		Sale_id.setBounds(626, 78, 46, 14);
		contentPane_2.add(Sale_id);
	
		JLabel O_id = new JLabel("*");
		O_id.setBounds(128, 103, 16, 14);
		contentPane_2.add(O_id);
		
		JLabel lblNewLabel = new JLabel("Customer Name");
		lblNewLabel.setBounds(36, 78, 101, 14);
		contentPane_2.add(lblNewLabel);
		
		Cus_name = new JTextField();
		Cus_name.setColumns(10);
		Cus_name.setBounds(128, 75, 201, 20);
		contentPane_2.add(Cus_name);
		
		JLabel lblNewLabel_1 = new JLabel("Phone");
		lblNewLabel_1.setBounds(358, 78, 46, 14);
		contentPane_2.add(lblNewLabel_1);
		
		Cus_pho = new JTextField();
		Cus_pho.setColumns(10);
		Cus_pho.setBounds(399, 75, 222, 20);
		contentPane_2.add(Cus_pho);
		
		JLabel lblNewLabel_2 = new JLabel("Product");
		lblNewLabel_2.setBounds(36, 123, 46, 14);
		contentPane_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Price");
		lblNewLabel_3.setBounds(36, 168, 46, 14);
		contentPane_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Quantity");
		lblNewLabel_4.setBounds(36, 216, 46, 14);
		contentPane_2.add(lblNewLabel_4);
		
		
		
		JLabel price = new JLabel("*");
		price.setBounds(130, 168, 46, 14);
		contentPane_2.add(price);
		
		JLabel lblNewLabel_6 = new JLabel("Total");
		lblNewLabel_6.setBounds(36, 262, 46, 14);
		contentPane_2.add(lblNewLabel_6);
		
		JLabel Total = new JLabel("*");
		Total.setBounds(128, 262, 46, 14);
		contentPane_2.add(Total);
	
		JLabel TotalAmount = new JLabel("*");
		TotalAmount.setBounds(682, 322, 46, 14);
		contentPane_2.add(TotalAmount);
			
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(293, 116, 435, 197);
		contentPane_2.add(scrollPane);
		
		jtable = new JTable();
		scrollPane.setViewportView(jtable);
		jtable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"SNO", "Product Name", "Price", "Quantity", "Total"
			}
		));
		jtable.getColumnModel().getColumn(1).setPreferredWidth(91);
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel tblmodel=(DefaultTableModel) jtable.getModel();
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ims","root","");
					Statement st=con.createStatement();
					String sqlselect="SELECT * FROM orders ORDER BY id DESC LIMIT 1";
					ResultSet rs=st.executeQuery(sqlselect);
					if(rs.next()) {
						int ids = Integer.parseInt(rs.getString("Id"));
							ids++;
							O_id.setText(String.valueOf(ids));							
						//	JOptionPane.showMessageDialog(null, id);
				}
					String sql="insert into orders values (?, ?, ?,?)";
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setInt(1, Integer.parseInt(O_id.getText()));
					pst.setString(2, Cus_name.getText());
					pst.setString(3, Cus_pho.getText());
					pst.setInt(4, Integer.parseInt(User_id.getText()));
					pst.executeUpdate();
					JOptionPane.showMessageDialog(btnNewButton_1, "inserted");
					String Name,qty,id;
					for (int i = 0; i < tblmodel.getRowCount(); i++) {
						
						Statement sta=con.createStatement();
						String sale="SELECT * FROM sales ORDER BY id DESC LIMIT 1";
						ResultSet rsa=sta.executeQuery(sale);
						if(rsa.next()) {
							int ids = Integer.parseInt(rsa.getString("Id"));
								ids++;
								Sale_id.setText(String.valueOf(ids));							
							//	JOptionPane.showMessageDialog(null, id);
					}	
						
							Name=tblmodel.getValueAt(i, 1).toString();
							qty=tblmodel.getValueAt(i, 3).toString();	
							String sqlsale="insert into sales values (?, ?, ?,?,?)";						
							PreparedStatement pstd = con.prepareStatement(sqlsale);
							pstd.setInt(1, Integer.parseInt(Sale_id.getText()));
							pstd.setString(2, Name);
							pstd.setInt(3, Integer.parseInt(qty));
							pstd.setInt(4, Integer.parseInt(TotalAmount.getText()));
							pstd.setInt(5, Integer.parseInt(O_id.getText()));
							pstd.executeUpdate();						
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
		btnNewButton_1.setBounds(639, 343, 89, 23);
		contentPane_2.add(btnNewButton_1);
		
//	try {
//		String sale="SELECT * FROM orders sales BY id DESC LIMIT 1";
//		ResultSet rsa=st.executeQuery(sqlselect);
//		if(rsa.next()) {
//			int ids = Integer.parseInt(rs.getString("Id"));
//				ids++;
//				Sale_id.setText(String.valueOf(ids));							
//			//	JOptionPane.showMessageDialog(null, id);
//	}	
//	
//	}
//	catch (Exception e) {
//		// TODO: handle exception
//	}
		
		
		
		
		
		qty = new JTextField();
		qty.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {	}
		});
		qty.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
					
			
			}
			@Override
			public void keyReleased(KeyEvent e) {
				int quantity=Integer.parseInt(qty.getText());
				int Price=Integer.parseInt(price.getText());
				int totalPrice=quantity*Price;
				Total.setText(String.valueOf(totalPrice));
				//JOptionPane.showMessageDialog(btnNewButton_1, total);

			}
		});
		qty.setColumns(10);
		qty.setBounds(128, 213, 155, 20);
		contentPane_2.add(qty);
		
		
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
						price.setText(String.valueOf(rs.getInt("Price")));			
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
		Product.setBounds(128, 119, 155, 22);
		contentPane_2.add(Product);
		
		
		
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				
				String Sno=String.valueOf(id);
				String name=String.valueOf(Product.getSelectedItem());
				String Qty=String.valueOf(qty.getText());
				String Pric=String.valueOf(price.getText());
				String TotalAm=String.valueOf(Total.getText());
				

					String tblData[]= {Sno,name,Pric,Qty,TotalAm};
					DefaultTableModel tbldata = (DefaultTableModel)jtable.getModel();
					tbldata.addRow(tblData);
					id++;
					
					totalamount+=Integer.parseInt(TotalAm);
					TotalAmount.setText(String.valueOf(totalamount));
					
			//		DefaultTableModel dt = new DefaultTableModel();
					
					
			}
			catch (Exception e1) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(btnNewButton, e1);
			}
			
			}
		});
		btnNewButton.setBounds(35, 318, 89, 23);
		contentPane_2.add(btnNewButton);
		
		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dashboard db=new Dashboard();
				db.setVisible(true);
			}
		});
		lblNewLabel_4_1.setBounds(10, 11, 41, 38);
		contentPane_2.add(lblNewLabel_4_1);
		Image icon = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		lblNewLabel_4_1.setIcon(new ImageIcon(icon));
		
		JLabel lblNewLabel_5 = new JLabel("INVOICE");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_5.setBounds(324, 23, 72, 26);
		contentPane_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_7 = new JLabel("Total Amount");
		lblNewLabel_7.setBounds(599, 322, 73, 14);
		contentPane_2.add(lblNewLabel_7);
		
		
	
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
