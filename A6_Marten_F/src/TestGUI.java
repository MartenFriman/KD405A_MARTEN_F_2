import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import se.mah.k3lara.skaneAPI.control.Constants;
import se.mah.k3lara.skaneAPI.model.Journey;
import se.mah.k3lara.skaneAPI.model.Journeys;
import se.mah.k3lara.skaneAPI.model.Line;
import se.mah.k3lara.skaneAPI.model.Lines;
import se.mah.k3lara.skaneAPI.model.Station;
import se.mah.k3lara.skaneAPI.xmlparser.Parser;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class TestGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	private JTextField txtFrom;
	private JTextField txtTo;
	private JTextArea textArea;
	private JTextArea textArea_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestGUI frame = new TestGUI();
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
	public TestGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 849, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//CODE RELATED TO PART 4
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch.setBounds(46, 26, 261, 42);
		txtSearch.setText("Search for stations...");
		panel.add(txtSearch);
		txtSearch.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 155, 261, 274);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Displays the message "Searching..." in the textArea
	            textArea.setText("Searching...");
	            //Start search thread
		 	    new stationSearchThread().start();
			}
		});
		btnSearch.setBounds(98, 84, 168, 55);
		panel.add(btnSearch);
		
		//CODE RELATED TO PART 5
		
		txtFrom = new JTextField();
		txtFrom.setHorizontalAlignment(SwingConstants.CENTER);
		txtFrom.setText("From");
		txtFrom.setBounds(381, 26, 161, 42);
		panel.add(txtFrom);
		txtFrom.setColumns(10);
		
		txtTo = new JTextField();
		txtTo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTo.setText("To");
		txtTo.setColumns(10);
		txtTo.setBounds(591, 26, 161, 42);
		panel.add(txtTo);
		
		textArea_1 = new JTextArea();
		textArea_1.setBounds(381, 156, 371, 274);
		panel.add(textArea_1);
		
		JButton btnSearch_1 = new JButton("Search");
		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Displays the message "Searching..." in the textArea
				textArea_1.setText("Searching...");
				//Start search thread
			  	new journeySearchThread().start();	
			}
		});
		btnSearch_1.setBounds(482, 84, 168, 55);
		panel.add(btnSearch_1);
	}
    
	//Thread which handles station searches
    private class stationSearchThread extends Thread {
    	@Override
    	public void run() {
    		//Creates a new ArrayList, intended to hold Station objects.
			ArrayList<Station> searchStations = new ArrayList<Station>(); 
			//Searches for all stations that contains "Malm" in name, and places results in the searchStations ArrayList. The "ö" of Malmö omitted due to ascii limitations.
			searchStations.addAll(Parser.getStationsFromURL(txtSearch.getText()));
			
			//Clear "searching" message from textArea
			textArea.setText("");
			
			//Loops through searchStations and appends stations to textArea
			for (Station s: searchStations){
				textArea.append(s.getStationName() +"\n");
			}
    	}
    }
    
    private class journeySearchThread extends Thread {
    	@Override
    	public void run() {
    		//Constructs a search URL formatted according to API guidelines. Constants.getURL returns formatted URL.
			//Upon button press, searches for trips from the station code present in "from" textField, to the station code present in "to" textField, and asks for the first result)
			String searchURL = Constants.getURL(txtFrom.getText(),txtTo.getText(),1); //Malmö C = 80000,  Lund C, 81216 Malmö Gatorg 80100, Hässleholm C 93070
			
			//Queries skanetrafiken using a formatted URL, and constructs a Journeys instance from results.
			Journeys journeys = Parser.getJourneys(searchURL);
			
			//Clear "searching" message from textArea
			textArea_1.setText("");
			
			//Prints information about the journey
			for (Journey journey : journeys.getJourneys()) {
				String depTime = journey.getDepDateTime().get(Calendar.HOUR_OF_DAY)+":"+journey.getDepDateTime().get(Calendar.MINUTE);
				String arrTime = journey.getArrDateTime().get(Calendar.HOUR_OF_DAY)+":"+journey.getArrDateTime().get(Calendar.MINUTE);
				textArea_1.append("Trip from " + journey.getStartStation() + " to " + journey.getEndStation() + "\n" + 
						"Departs: " + depTime +" which is in "+journey.getTimeToDeparture()+ " minutes. \n" +
						"It is "+journey.getDepTimeDeviation()+" minutes late at departure. \n" +
				        "Arrives: " + arrTime + "\n" +
				        "It will be "+journey.getArrTimeDeviation()+" minutes late upon arrival. \n" +
				        "Travel time: " + journey.getTravelMinutes() + " minutes. \n" +
				        "Changes: " + journey.getNoOfChanges() + "\n" +
				        "Zones: " + journey.getNoOfZones() + "\n" +
				        "Transport type: " + journey.getLineTypeName() + "\n" +
				        "Line: " + journey.getLineOnFirstJourney());
			} 
    	}
    }
}
