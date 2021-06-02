package imsproj;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dashboard extends JFrame {

	private JPanel contentPane;
	private JPanel contentPane_1;
	Login lo= new Login();
	int U_id=lo.id;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
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
	public Dashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 456);
		contentPane = new JPanel();
		contentPane_1 = new JPanel() {  
            public void paintComponent(Graphics g) {  
                    Image img = Toolkit.getDefaultToolkit().getImage(  
                           UpdateProduct.class.getResource("/images/bg.jpg"));  
                 g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
	 }  
       };
       
       if(U_id >= 0 ) {

       
       }

       else {
    	   Login lo=new Login();
    	   lo.setVisible(true);
       }
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DASHBOARD");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(244, 52, 122, 14);
		contentPane_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Invoice frame = new Invoice();
				frame.setVisible(true);
				
			}
		});
		lblNewLabel_1.setBounds(26, 95, 122, 135);
		contentPane_1.add(lblNewLabel_1);
		Image icon = new ImageIcon(this.getClass().getResource("/invoic.png")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(icon));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Product frame = new Product();
				frame.setVisible(true);
		
			}
		});
		lblNewLabel_2.setBounds(147, 82, 135, 148);
		contentPane_1.add(lblNewLabel_2);
		Image icon1 = new ImageIcon(this.getClass().getResource("/product.png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(icon1));
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Categories frame = new Categories();
				frame.setVisible(true);
			}
		});
		lblNewLabel_3.setBounds(279, 95, 128, 128);
		contentPane_1.add(lblNewLabel_3);
		Image icon2 = new ImageIcon(this.getClass().getResource("/categories.png")).getImage();
		lblNewLabel_3.setIcon(new ImageIcon(icon2));
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserRegister frame = new UserRegister();
				frame.setVisible(true);
			}
		});
		lblNewLabel_4.setBounds(431, 95, 128, 128);
		contentPane_1.add(lblNewLabel_4);
		Image icon3 = new ImageIcon(this.getClass().getResource("/user.png")).getImage();
		lblNewLabel_4.setIcon(new ImageIcon(icon3));
		
		JLabel lblNewLabel_5 = new JLabel("INVOICE");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_5.setBounds(63, 234, 65, 14);
		contentPane_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("PRODUCT");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_6.setBounds(175, 234, 71, 14);
		contentPane_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("CATEGORIES");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_7.setBounds(305, 234, 102, 14);
		contentPane_1.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("USER");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_8.setBounds(474, 234, 58, 14);
		contentPane_1.add(lblNewLabel_8);
		
		JLabel User_id = new JLabel("*");
		User_id.setBounds(523, 193, 46, 14);
		contentPane_1.add(User_id);
		
		User_id.setText(String.valueOf(U_id));
		
		JLabel Signout = new JLabel("");
		Signout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login Lo=new Login();
				Lo.setVisible(true);
			}
		});
		Signout.setBounds(545, 11, 46, 37);
		contentPane_1.add(Signout);

		Image icon4= new ImageIcon(this.getClass().getResource("/logout1.png")).getImage();
		Signout.setIcon(new ImageIcon(icon4));
		
		
	}

}
