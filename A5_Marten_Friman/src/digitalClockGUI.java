import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class digitalClockGUI extends JFrame {
    
	private ClockLogic clockLogic;
	private JPanel contentPane;
	private JLabel lblTime;
	private JPanel alarmPanel;
	private JLabel lblAlarm;
	private JPanel panel_mainPanel;
	private JPanel panel_setAlarm;
	private JTextField txtAlarmHour;
	private JPanel panel_MinutePanel;
	private JTextField txtAlarmMinute;
	private JLabel lblAlarmLabel;
	private JButton btnRevealAlarmFrame;
	private JPanel panel_HourPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					digitalClockGUI frame = new digitalClockGUI();
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
	public digitalClockGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel_mainPanel = new JPanel();
		panel_mainPanel.setBackground(Color.WHITE);
		contentPane.add(panel_mainPanel, BorderLayout.CENTER);
		panel_mainPanel.setLayout(null);
		
		panel_setAlarm = new JPanel();
		panel_setAlarm.setVisible(false);
		
		alarmPanel = new JPanel();
		alarmPanel.setVisible(false);
		alarmPanel.setBackground(Color.WHITE);
		alarmPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		alarmPanel.setBounds(80, 71, 265, 117);
		panel_mainPanel.add(alarmPanel);
		alarmPanel.setLayout(null);
		
		lblAlarm = new JLabel("Wake up!");
		lblAlarm.setForeground(Color.RED);
		lblAlarm.setFont(new Font("Roboto Cn", Font.PLAIN, 45));
		lblAlarm.setBounds(0, 0, 265, 117);
		lblAlarm.setHorizontalAlignment(SwingConstants.CENTER);
		alarmPanel.add(lblAlarm);
		panel_setAlarm.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_setAlarm.setBackground(Color.WHITE);
		panel_setAlarm.setBounds(100, 149, 228, 65);
		panel_mainPanel.add(panel_setAlarm);
		panel_setAlarm.setLayout(null);
		
		panel_HourPanel = new JPanel();
		panel_HourPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_HourPanel.setBackground(Color.WHITE);
		panel_HourPanel.setBounds(10, 11, 97, 44);
		panel_setAlarm.add(panel_HourPanel);
		panel_HourPanel.setLayout(null);
		
		txtAlarmHour = new JTextField();
		txtAlarmHour.setBounds(0, 0, 97, 44);
		panel_HourPanel.add(txtAlarmHour);
		txtAlarmHour.setText("Hour");
		txtAlarmHour.setHorizontalAlignment(SwingConstants.CENTER);
		txtAlarmHour.setFont(new Font("Roboto Cn", Font.PLAIN, 33));
		txtAlarmHour.setColumns(10);
		
		panel_MinutePanel = new JPanel();
		panel_MinutePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_MinutePanel.setBackground(Color.WHITE);
		panel_MinutePanel.setLayout(null);
		panel_MinutePanel.setBounds(10, 70, 97, 44);
		panel_setAlarm.add(panel_MinutePanel);
		
		txtAlarmMinute = new JTextField();
		txtAlarmMinute.setBounds(121, 11, 97, 44);
		panel_setAlarm.add(txtAlarmMinute);
		txtAlarmMinute.setText("Minute");
		txtAlarmMinute.setHorizontalAlignment(SwingConstants.CENTER);
		txtAlarmMinute.setFont(new Font("Roboto Cn", Font.PLAIN, 33));
		txtAlarmMinute.setColumns(10);
		
		lblAlarmLabel = new JLabel("Alarm Time");
		lblAlarmLabel.setVisible(false);
		lblAlarmLabel.setBounds(155, 149, 134, 23);
		panel_mainPanel.add(lblAlarmLabel);
		lblAlarmLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlarmLabel.setFont(new Font("Roboto Cn", Font.PLAIN, 16));
		
		lblTime = new JLabel("time");
		lblTime.setBackground(Color.WHITE);
		lblTime.setBounds(0, 0, 424, 252);
		lblTime.setFont(new Font("Roboto Cn", Font.PLAIN, 50));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		panel_mainPanel.add(lblTime);
		
		btnRevealAlarmFrame = new JButton("Alarm");
		btnRevealAlarmFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Removes, disables and sets new alarm based on text of button
				if (btnRevealAlarmFrame.getText().equals("Alarm")) {
					panel_setAlarm.setVisible(true);
					btnRevealAlarmFrame.setText("Set Alarm");
				} else if (btnRevealAlarmFrame.getText().equals("Set Alarm")){
					panel_setAlarm.setVisible(false);
					try {
						clockLogic.setAlarm(Integer.parseInt(txtAlarmHour.getText()), Integer.parseInt(txtAlarmMinute.getText()));
						btnRevealAlarmFrame.setText("Remove Alarm");
						
						String alarmHourCorrection = "";
						String alarmMinuteCorrection = "";
						
						if (Integer.parseInt(txtAlarmHour.getText()) < 10 && txtAlarmHour.getText().charAt(0) != '0') {
							alarmHourCorrection = "0";
						}
						
						if (Integer.parseInt(txtAlarmMinute.getText()) < 10 && 
							  txtAlarmMinute.getText().equals("00") != true && 
								     txtAlarmMinute.getText().charAt(0) != '0') {
							alarmMinuteCorrection = "0";
						}
						
						lblAlarmLabel.setText(alarmHourCorrection + txtAlarmHour.getText() + " : " + alarmMinuteCorrection + txtAlarmMinute.getText());
						lblAlarmLabel.setVisible(true);
						
					} catch (NumberFormatException e) {
						e.printStackTrace();
						btnRevealAlarmFrame.setText("Alarm");
					}								
				} else {
					btnRevealAlarmFrame.setText("Alarm");
					clockLogic.clearAlarm();
					activateAlarm(false);
					lblAlarmLabel.setVisible(false);
				}
			}
		});
		btnRevealAlarmFrame.setFont(new Font("Roboto Cn", Font.PLAIN, 17));
		btnRevealAlarmFrame.setBounds(135, 216, 154, 36);
		panel_mainPanel.add(btnRevealAlarmFrame);
		
		clockLogic = new ClockLogic(this);
	}
	
	void setTimeOnLabel(String time) {
		lblTime.setText(time);
	}
	
	//sets visibility of alarm panel based on input boolean
	void activateAlarm(boolean activate) {
		if (activate) {
			alarmPanel.setVisible(true);
		} else {
			alarmPanel.setVisible(false);
		}
	}
}


