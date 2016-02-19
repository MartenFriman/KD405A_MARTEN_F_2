
public class ClockLogic {
  private digitalClockGUI = new DigitalClockGUI;
  private int alarmHour;
  private int alarmMinute;
  
  public ClockLogic(digitalClockGUI DigitalClockGUI) {

    private class ClockThread extends Thread {
    	
    	@override
    	public void run() {
    		while(true) {
    			
    			digitalClockGUI.setTimeOnLabel();
    			
    			Thread.sleep(900);
    		}
    	}
    }
  }
  
  public void setAlarm(int hours, int minute) {
	this.alarmHour = hours;
	this.alarmMinute = minute;
  }
  
  public void clearAlarm() {
	  
  }
  
}
