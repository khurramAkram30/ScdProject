package imsproj;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShowCategories extends JFrame {

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
					ShowCategories frame = new ShowCategories();
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
	public ShowCategories() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 679, 300);
		contentPane = new JPanel();
		contentPane_1 = new JPanel() {  
            public void paintComponent(Graphics g) {  
                    Image img = Toolkit.getDefaultToolkit().getImage(  
                           ShowCategories.class.getResource("/images/bg.jpg"));  
                 g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
	 }  
       }; 
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(239, 51, 424, 199);
		contentPane_1.add(scrollPane);
		
		Jtable = new JTable();
		scrollPane.setViewportView(Jtable);
		Jtable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Categories Name", "Status"
			}
		));
		Jtable.getColumnModel().getColumn(1).setPreferredWidth(175);
		
		JButton btnNewButton = new JButton("Load Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ims","root","");
					Statement st=con.createStatement();
					String sqlselect="SELECT * FROM categories";
					ResultSet rs=st.executeQuery(sqlselect);
					while(rs.next()) {
					String id=String.valueOf(rs.getInt("Id"));
					String Cat_name=rs.getString("Name");
					String Status=String.valueOf(rs.getInt("Status"));
				//	JOptionPane.showMessageDialog(null, id);
					
					String tblData[]= {id,Cat_name,Status};
					DefaultTableModel tbldata = (DefaultTableModel)Jtable.getModel();
					tbldata.addRow(tblData);
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
		btnNewButton.setBounds(239, 27, 89, 23);
		contentPane_1.add(btnNewButton);
		
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
		
		JButton btnNewButton_1 = new JButton("Add Categories");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Categories frame = new Categories();
				frame.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton_1.setBounds(10, 105, 131, 23);
		contentPane_1.add(btnNewButton_1);
		
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
	
		
		
	
	}
	public void showdata() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ims","root","");
			Statement st=con.createStatement();
			String sqlselect="SELECT * FROM categories";
			ResultSet rs=st.executeQuery(sqlselect);
			while(rs.next()) {
			String id=String.valueOf(rs.getInt("Id"));
			String Cat_name=rs.getString("Name");
			String Status=String.valueOf(rs.getInt("Status"));
		//	JOptionPane.showMessageDialog(null, id);
			
			String tblData[]= {id,Cat_name,Status};
			DefaultTableModel tbldata = (DefaultTableModel)Jtable.getModel();
			tbldata.addRow(tblData);
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

}
