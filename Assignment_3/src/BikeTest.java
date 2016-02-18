import java.util.ArrayList;

public class BikeTest {
	static ArrayList<Bike> bikeList = new ArrayList<Bike>();
	
	public static void main(String[] args) {
		  bikeList.add(new Bike("Red", 22, 4000));
		  bikeList.add(new Bike("Blue", 24, 4500));
		  bikeList.add(new Bike("Green", 26, 4900));
		  bikeList.add(new Bike("Red", 24, 3800));
		  bikeList.add(new Bike("Black", 20, 4500));
		  bikeList.add(new Bike("Silver", 24, 4300));
		  bikeList.add(new Bike("Blue", 18, 3300));
		  bikeList.add(new Bike("White", 22, 3700));
		  bikeList.add(new Bike("Black", 28, 5300));
		  bikeList.add(new Bike("Black", 20, 3000));
		  
		  for (int i = 0; i < 10; i++) {
			  Bike tempBike = bikeList.get(i);
			  System.out.println("No: " + (i + 1) + " Color: " + tempBike.getColor() + "Size: " + tempBike.getSize() + "Price: " + tempBike.getPrice());
		  }
	}

}


