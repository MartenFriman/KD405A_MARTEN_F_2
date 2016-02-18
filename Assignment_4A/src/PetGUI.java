
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PetGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtHuman;
	private JTextField textDog;
	private JTextField txtInfo;
	Human human = new Human("Homo Sapien");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PetGUI frame = new PetGUI();
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
	public PetGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtHuman = new JTextField();
		txtHuman.setHorizontalAlignment(SwingConstants.CENTER);
		txtHuman.setText("Human");
		txtHuman.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtHuman.setBounds(10, 11, 150, 50);
		contentPane.add(txtHuman);
		txtHuman.setColumns(10);
		
		textDog = new JTextField();
		textDog.setText("Dog");
		textDog.setHorizontalAlignment(SwingConstants.CENTER);
		textDog.setFont(new Font("Calibri", Font.PLAIN, 20));
		textDog.setColumns(10);
		textDog.setBounds(274, 11, 150, 50);
		contentPane.add(textDog);
		
		txtInfo = new JTextField();
		txtInfo.setHorizontalAlignment(SwingConstants.CENTER);
		txtInfo.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtInfo.setText("Info");
		txtInfo.setBounds(10, 169, 414, 82);
		contentPane.add(txtInfo);
		txtInfo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 116, 414, 42);
		contentPane.add(lblNewLabel);
		
		//Overwrites the current human with a new one (if the entered name is consists of 3 or more letters), and prints its status
		JButton btnNewHuman = new JButton("New Human");
		btnNewHuman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtHuman.getText().length() > 2) {
					human = new Human(txtHuman.getText());
					txtInfo.setText(human.getInfo());
					lblNewLabel.setText("");
				} else lblNewLabel.setText("Invalid name entered");
			}
		});
		btnNewHuman.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnNewHuman.setBounds(10, 72, 150, 33);
		contentPane.add(btnNewHuman);
		
		//Assigns a new dog to the current human, as long as a new one has been created
		JButton btnBuyDog = new JButton("Buy Dog");
		btnBuyDog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (human.getName().equals("Homo Sapien")) {
					lblNewLabel.setText("No human who can own a dog");
				} else {
					human.buyDog(new Dog(textDog.getText()));
					txtInfo.setText(human.getInfo());
					lblNewLabel.setText("");
				}
			}
		});
		btnBuyDog.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnBuyDog.setBounds(274, 72, 150, 33);
		contentPane.add(btnBuyDog);
	}
}
