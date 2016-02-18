
public class House{
 private int yearBuilt;
 private int size;
 private final int minSize = 10;
 private final int maxSize = 1000;
 
 public House(int yearBuilt,int size){
   size = constrain(size, minSize, maxSize);
   yearBuilt = constrain(yearBuilt, 1800, 2015);
   this.yearBuilt = yearBuilt;
   this.size = size;
 }
 
 public int getYearBuilt(){
   return this.yearBuilt;
 }
 
 public int getSize(){
   return this.size;
 }
 
 int constrain(int value, int min, int max) {
	   return Math.min(Math.max(value, min), max);
	}
}