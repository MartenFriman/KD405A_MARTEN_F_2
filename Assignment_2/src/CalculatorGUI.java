import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextArea;

public class CalculatorGUI extends JFrame {

	private JPanel contentPane;
	private Calculator myCalc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorGUI frame = new CalculatorGUI();
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
	public CalculatorGUI() {
		myCalc = new Calculator();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtrOutput = new JTextArea();
		txtrOutput.setFont(new Font("Calibri", Font.PLAIN, 34));
		txtrOutput.setText("");
		txtrOutput.setBounds(10, 11, 414, 99);
		contentPane.add(txtrOutput);
		
		JButton button = new JButton("1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCalc.numberButtonPressed(1);
				txtrOutput.append("1 ");
			}
		});
		button.setFont(new Font("Calibri", Font.PLAIN, 34));
		button.setBounds(10, 221, 89, 88);
		contentPane.add(button);
		
		JButton button_1 = new JButton("2");
		button_1.setFont(new Font("Calibri", Font.PLAIN, 34));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCalc.numberButtonPressed(2);
				txtrOutput.append("2 ");
			}
		});
		button_1.setBounds(109, 221, 89, 88);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("3");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCalc.numberButtonPressed(3);
				txtrOutput.append("3 ");
			}
		});
		button_2.setFont(new Font("Calibri", Font.PLAIN, 34));
		button_2.setBounds(208, 221, 89, 88);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("4");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCalc.numberButtonPressed(4);
				txtrOutput.append("4 ");
			}
		});
		button_3.setFont(new Font("Calibri", Font.PLAIN, 34));
		button_3.setBounds(10, 320, 89, 88);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("5");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCalc.numberButtonPressed(5);
				txtrOutput.append("5 ");
			}
		});
		button_4.setFont(new Font("Calibri", Font.PLAIN, 34));
		button_4.setBounds(109, 320, 89, 88);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("6");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCalc.numberButtonPressed(6);
				txtrOutput.append("6 ");
			}
		});
		button_5.setFont(new Font("Calibri", Font.PLAIN, 34));
		button_5.setBounds(208, 320, 89, 88);
		contentPane.add(button_5);
		
		JButton button_6 = new JButton("7");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCalc.numberButtonPressed(7);
				txtrOutput.append("7 ");
			}
		});
		button_6.setFont(new Font("Calibri", Font.PLAIN, 34));
		button_6.setBounds(10, 419, 89, 88);
		contentPane.add(button_6);
		
		JButton button_7 = new JButton("8");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCalc.numberButtonPressed(8);
				txtrOutput.append("8 ");
			}
		});
		button_7.setFont(new Font("Calibri", Font.PLAIN, 34));
		button_7.setBounds(109, 419, 89, 88);
		contentPane.add(button_7);
		
		JButton button_8 = new JButton("9");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCalc.numberButtonPressed(9);
				txtrOutput.append("9 ");
			}
		});
		button_8.setFont(new Font("Calibri", Font.PLAIN, 34));
		button_8.setBounds(208, 419, 89, 88);
		contentPane.add(button_8);
		
		JButton button_9 = new JButton("+");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCalc.plus();
				txtrOutput.append("+ ");
			}
		});
		button_9.setFont(new Font("Calibri", Font.PLAIN, 34));
		button_9.setBounds(335, 122, 89, 88);
		contentPane.add(button_9);
		
		JButton button_10 = new JButton("-");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCalc.minus();
				txtrOutput.append("- ");
			}
		});
		button_10.setFont(new Font("Calibri", Font.PLAIN, 34));
		button_10.setBounds(335, 221, 89, 88);
		contentPane.add(button_10);
		
		JButton button_11 = new JButton("*");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCalc.mult();
				txtrOutput.append("* ");
			}
		});
		
		button_11.setFont(new Font("Calibri", Font.PLAIN, 34));
		button_11.setBounds(335, 320, 89, 88);
		contentPane.add(button_11);
		
		JButton button_12 = new JButton("=");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCalc.equals();
				txtrOutput.append("= "+myCalc.getResult());
			}
		});
		button_12.setFont(new Font("Calibri", Font.PLAIN, 34));
		button_12.setBounds(335, 419, 89, 88);
		contentPane.add(button_12);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCalc.clear();
				txtrOutput.setText("");
			}
		});
		btnClear.setFont(new Font("Calibri", Font.PLAIN, 34));
		btnClear.setBounds(109, 121, 188, 89);
		contentPane.add(btnClear);
		
		
	}
}
