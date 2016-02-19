import java.util.Calendar;

public class ClockLogic {
  private digitalClockGUI DigitalClockGUI;
  private int alarmHour;
  private int alarmMinute;

  private class ClockThread extends Thread {
    	
    @Override
    public void run() {
    	while(true) {
    		Calendar calendar = Calendar.getInstance();
    		int seconds = calendar.get(Calendar.SECOND);
    		int minutes = calendar.get(Calendar.MINUTE);
    		int hours   = calendar.get(Calendar.HOUR_OF_DAY);
    		
    		//Corrects time display by adding a 0 if each time variable is less than 10
    		String hourCorrection = "";
    		String minuteCorrection ="";
    		String secondCorrection ="";
    		
    		if (hours < 10) hourCorrection = "0";
    		if (minutes <10) minuteCorrection = "0";
    		if (seconds <10) secondCorrection = "0";
    			
    		String currentTime = hourCorrection + hours + " : " + minuteCorrection + minutes + " : " + secondCorrection + seconds;
    			
    		DigitalClockGUI.setTimeOnLabel(currentTime);
    			
    		if (hours == alarmHour && minutes == alarmMinute) {
    			DigitalClockGUI.activateAlarm(true);
    		} else {
    			DigitalClockGUI.activateAlarm(false);
    		}
    		
    		try {
				Thread.sleep(900);
			} catch (InterruptedException e) {
					
				e.printStackTrace();
			}
    	}
    }
  }
  
  public ClockLogic(digitalClockGUI DigitalClockGUI) {
	  this.DigitalClockGUI = DigitalClockGUI;
	  this.alarmHour = -1;
	  this.alarmMinute = -1;
	  
	  new ClockThread().start();
  }
  
  public void setAlarm(int hours, int minute) {
	this.alarmHour = hours;
	this.alarmMinute = minute;
  }
  
  public void clearAlarm() {
	  //sets alarm times to -1, thus effectively "disabling" the alarm
	  this.setAlarm(-1, -1);
  }
  
}
