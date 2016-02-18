import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class BikeStoreGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtColor;
	private JTextField txtSize;
	private JTextField txtPrice;
	private JLabel lblColor;
	private JLabel lblSize;
	private JLabel lblPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BikeStoreGUI frame = new BikeStoreGUI();
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
	public BikeStoreGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//TEST CODE - Enters 20 generic bikes into the registry.
		
		for(int i = 0; i < 20; i++) {
			BikeStore.addBike("Red", 20, 10000);
			}
		
		String allBikes = BikeStore.getAllBikes();
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(402, 11, 255, 419);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtColor = new JTextField();
		txtColor.setFont(new Font("Calibri", Font.PLAIN, 17));
		txtColor.setBounds(114, 11, 131, 40);
		panel.add(txtColor);
		txtColor.setColumns(10);
		
		txtSize = new JTextField();
		txtSize.setFont(new Font("Calibri", Font.PLAIN, 17));
		txtSize.setColumns(10);
		txtSize.setBounds(114, 111, 131, 40);
		panel.add(txtSize);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Calibri", Font.PLAIN, 17));
		txtPrice.setColumns(10);
		txtPrice.setBounds(114, 211, 131, 40);
		panel.add(txtPrice);
		
		lblColor = new JLabel("Color");
		lblColor.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblColor.setBounds(10, 18, 62, 27);
		panel.add(lblColor);
		
		lblSize = new JLabel("Size");
		lblSize.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblSize.setBounds(10, 118, 62, 27);
		panel.add(lblSize);
		
		lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblPrice.setBounds(10, 218, 62, 27);
		panel.add(lblPrice);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 382, 419);
		contentPane.add(scrollPane);
		
		JTextArea txtrBikes = new JTextArea();
		scrollPane.setViewportView(txtrBikes);
		txtrBikes.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtrBikes.setText(BikeStore.getAllBikes());
		txtrBikes.setText(allBikes);
		
		//Below code checks that text fields are not empty. If that is the case, it creates a new bike from entered specifications, and updates the bike list.
		
		JButton btnNewButton = new JButton("Add New Bike");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtColor.getText().equals("") == false && txtSize.getText().equals("") == false && txtPrice.getText().equals("") == false) {
				  BikeStore.addBike(txtColor.getText(), Integer.parseInt(txtSize.getText()), Integer.parseInt(txtPrice.getText()));
				  txtrBikes.setText(BikeStore.getAllBikes());
				}
			}
		});
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 24));
		btnNewButton.setBounds(10, 350, 235, 58);
		panel.add(btnNewButton);
	}
}
