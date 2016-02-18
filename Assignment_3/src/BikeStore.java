import java.util.ArrayList;

public class BikeStore {
  private static ArrayList<Bike> bikeList = new ArrayList<Bike>();
  
  /**Loops through bikeList ArrayList, and returns all bikes as a formatted string*/
  public static String getAllBikes() {
	  String allBikes = "";
	  for (int i = 0; i < bikeList.size(); i++) {
		  Bike tempBike = bikeList.get(i);
		  allBikes+= "No: " + (i+1) + " |  Color: " + tempBike.getColor() + " |  Size: " + tempBike.getSize() + " |  Price: " + tempBike.getPrice()+" \n";
	  }
	  return allBikes;
  }
  
  /**Adds a new bike to the bikeList ArrayList*/
  public static void addBike(String color, int size, int price) {
	  bikeList.add(new Bike(color, size, price));
  }
}
