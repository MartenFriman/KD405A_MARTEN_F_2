import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class AnimalListGUI extends JFrame {

	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnimalListGUI frame = new AnimalListGUI();
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
	public AnimalListGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTextArea txtrAnimals = new JTextArea();
		txtrAnimals.setText("");
		contentPane.add(txtrAnimals, BorderLayout.CENTER);
		
		//Manual addition of animals to a new ArrayList
		ArrayList<Animal> animals = new ArrayList<Animal>();
		animals.add(new Dog("Canis latrans", "Fido", 3, true));
		animals.add(new Snake("Python regius","Otto",true));
		animals.add(new Dog("Canis latrans", "Molly", 3, false));
		animals.add(new Cat("Lynx pardinus", "Julia", 4, 99));
		animals.add(new Cat("Lynx Lynx", "Felcia", 4, 8));
		
		//Print list of animals in text field.
		for (Animal animal: animals) {
			txtrAnimals.append(animal.getInfo() + "\n");
		}
	}

}
