public class Bike {
  private String color;
  private int price;
  private int size;
  
  /**Creates a "bike". Variables are limited on input by constant variables in "Constants" class. Two input variables expected*/
  public Bike(String color, int size) {
	  if (Constants.allowedColors(color)) this.color = color;
	  else this.color = "Unknown Color";
	  
	  if (size > Constants.MIN_BIKE_SIZE && size < Constants.MAX_BIKE_SIZE) this.size = size;
	  else this.size = 0;
  }
  
  /**Creates a "bike". Variables are limited on input by constant variables in "Constants" class. Three input variables expected*/
  public Bike(String color, int size, int price) {
	  if (Constants.allowedColors(color)) this.color = color;
	  else this.color = "Unknown Color";
	  
	  if (size > Constants.MIN_BIKE_SIZE && size < Constants.MAX_BIKE_SIZE) this.size = size;
	  else this.size = 0;
	  
	  if (price > Constants.MIN_BIKE_PRICE && price < Constants.MAX_BIKE_PRICE) this.price = price;
	  else this.price = 0;
  }
  
  /**Returns color of bike*/
  public String getColor() {
	  return this.color;
  }
  
  /**Returns size of bike*/
  public int getSize() {
	  return this.size;
  }
  
  /**Returns price of bike*/
  public int getPrice() {
	  return this.price;
  }
  
  /**Modifies the price variable of the bike*/
  public void setPrice(int price) {
	  this.price = price;
  }
}
