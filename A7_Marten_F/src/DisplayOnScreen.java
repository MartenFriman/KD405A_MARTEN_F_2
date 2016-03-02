import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import se.mah.k3.klara.PixelController;
import se.mah.k3.klara.PixelController.Screen;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DisplayOnScreen extends JFrame {
    
	private JButton btnSend;
	private JPanel contentPane;
	private PixelController pixelController = PixelController.getInstance(Screen.MEDEA_1);
	private boolean send = false;
	
	int rColorValue = 0;
	int gColorValue = 127;
	int bColorValue = 255;
	Color c = new Color(rColorValue, gColorValue, bColorValue);
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayOnScreen frame = new DisplayOnScreen();
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
	public DisplayOnScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); 
		
		//Starts CheckIfConnected -- a thread which keeps looping until a connection has been established
		new CheckIfConnected().start();
		
		
		//If CheckIfConnected verifies a connection has been established, button becomes clickable
		//Changes button label to reflect what the program is doing
		btnSend = new JButton("SEND");
		btnSend.setEnabled(false);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btnSend.getText().equals("SEND"))btnSend.setText("STOP SENDING");
				else btnSend.setText("SEND");
				new SendPixelsToScreen().start(); //Starts sending pixels
				send = !send;
			}
		});
		btnSend.setBounds(247, 399, 115, 29);
		contentPane.add(btnSend);
		
		
	}
	
	private class SendPixelsToScreen extends Thread {
		
		@Override
		public void run() {
			while (send && pixelController.isConnected()) {	
				
				//Creates a rectangle with constantly changing colors, and sends the pixels
				for (int x = 1; x < 150; x++) {
				    if (rColorValue >= 254) rColorValue = 1;
				    else rColorValue+=1;
				    if (gColorValue >= 254) gColorValue = 1;
				    else gColorValue+=1;
				    if (bColorValue >= 254) bColorValue = 1;
				    else bColorValue+=1;
				    c = new Color(rColorValue, gColorValue, bColorValue); 	    
				    for (int y = 1; y < 80; y++) { 
				        pixelController.setPixel(x, y, c); //Pixels are sent
				    }
				  }
				System.out.println("SENDING"); //System debug message
				
				try {
					Thread.sleep(100); //Sleeps for 100ms to avoid overloading reciever
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//Code below is intended to aid if the connection is broken
			//Currently, though, PixelController doesn't seem to be able to determine if that has happened
			if (pixelController.isConnected() == false) {
				System.out.println("CONNECTION LOST");
				System.out.println("ATTEMPTING TO RECONNECT");	
			}
		}
	}
	
	//Checks whether a connection has been established, and enables the button to start sending 
	private class CheckIfConnected extends Thread {
		
		@Override
		public void run() {
			while(pixelController.isConnected() == false) {
				System.out.println("NOT CONNECTED");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (pixelController.isConnected()) {
				System.out.println("CONNECTION ESTABLISHED");
				btnSend.setEnabled(true);
			}
		}
	}
}
