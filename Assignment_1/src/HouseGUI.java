import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class HouseGUI extends JFrame {

	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HouseGUI frame = new HouseGUI();
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
	public HouseGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 316, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtrHouse = new JTextArea();
		txtrHouse.setRows(10);
		txtrHouse.setWrapStyleWord(true);
		txtrHouse.setLineWrap(true);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(txtrHouse);
		scrollPane.setBounds(0, 0, 300, 530);
		contentPane.add(scrollPane);
		
		//UPPGIFT 1.2
		
		House house1 = new House(1799, 600);
		House house2 = new House(2016, 200);
		House house3 = new House(1963, 1010);
		System.out.println("Byggår: "+house1.getYearBuilt()+ ", Storlek: "+house1.getSize()+"kvm ");
		System.out.println("Byggår: "+house2.getYearBuilt()+ ", Storlek: "+house2.getSize()+"kvm ");
		System.out.println("Byggår: "+house3.getYearBuilt()+ ", Storlek: "+house3.getSize()+"kvm ");
		txtrHouse.setText("Byggår: "+house1.getYearBuilt()+ ", Storlek: "+house1.getSize()+"kvm \n");
		txtrHouse.append("Byggår: "+house2.getYearBuilt()+ ", Storlek: "+house2.getSize()+"kvm \n");
		txtrHouse.append("Byggår: "+house3.getYearBuilt()+ ", Storlek: "+house3.getSize()+"kvm \n");
		
		//UPPGIFT 1.3
		
		House[] houses = new House[10];
		houses[0] = new House(2003, 784);
		houses[1] = new House(1976, 125);
		houses[2] = new House(1898, 63);
		houses[3] = new House(1923, 856);
		houses[4] = new House(1979, 321);
		houses[5] = new House(1982, 41);
		houses[6] = new House(1837, 641);
		houses[7] = new House(2012, 125);
		houses[8] = new House(1992, 89);
		houses[9] = new House(2010, 92);
		for (int i = 0; i < 10; i++) {
			txtrHouse.append("Byggår: "+houses[i].getYearBuilt()+ ", Storlek: "+houses[i].getSize()+"kvm \n");
		}
		
		//UPPGIFT 1.4
		
		ArrayList<House> houseList = new ArrayList<House>();
		Random rand = new Random();
		
		for(int i = 0; i <= 100; i++) {	
			int yearBuilt = rand.nextInt((2016 - 1799) + 1) + 1799;
			int size = rand.nextInt((1001 - 9) + 1) + 9;
			House thisHouse = new House(yearBuilt, size);
	    	houseList.add(thisHouse);
		}
		for(int i = 0; i <= 100; i++) {
			House tempHouse = houseList.get(i);
			txtrHouse.append("Byggår: "+tempHouse.getYearBuilt()+ ", Storlek: "+tempHouse.getSize()+"kvm \n");
		}
	}
}
