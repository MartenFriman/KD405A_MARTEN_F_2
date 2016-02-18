/**Class of constants constraining constructor values for "Bike" class*/
public class Constants {
  /**Minimum bike size*/
  public static final int MIN_BIKE_SIZE = 8;
  /**Maximum bike size*/
  public static final int MAX_BIKE_SIZE = 28;
  /**Minimum bike price*/
  public static final int MIN_BIKE_PRICE = 0;
  /**Maximum bike price*/
  public static final int MAX_BIKE_PRICE = 30000;
  
  /**Compares input string with list of pre-defined colors*/
  public static boolean allowedColors(String color) {
	  String checkColor = color.toLowerCase();
	  boolean accepted = false;
      final String[] ALLOWED_COLORS = new String[10];
      ALLOWED_COLORS[0] = "black";
      ALLOWED_COLORS[1] = "white";
      ALLOWED_COLORS[2] = "red";
      ALLOWED_COLORS[3] = "green";
      ALLOWED_COLORS[4] = "blue";
      ALLOWED_COLORS[5] = "silver";
      ALLOWED_COLORS[6] = "yellow";
      ALLOWED_COLORS[7] = "purple";
      ALLOWED_COLORS[8] = "grey";
      ALLOWED_COLORS[9] = "pink";
      
      for(int i = 0; i < ALLOWED_COLORS.length; i++) {
    	  if (checkColor.equals(ALLOWED_COLORS[i])) {
    		  accepted = true;
    		  i = ALLOWED_COLORS.length;
    	  }
      }
  
  return accepted;
  }
}
