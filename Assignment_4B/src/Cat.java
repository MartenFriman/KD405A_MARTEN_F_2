
public class Cat extends Mammal{
	/** Cat's number of lives. */
  private int numberOfLifes;
  
    /** Constructor. Creates a new cat. */
	public Cat(String latinName, String friendlyName, int gestationTime, int numberOfLifes) {
		super(latinName, friendlyName, gestationTime);
		this.numberOfLifes = numberOfLifes;
	}
	
	/** Returns cat's number of lives. */
	public int getNumberOfLives() {
		return numberOfLifes;
	}
	
	/** Sets new number of lives of cat. */
	public void setNumberOfLifes(int i) {
		this.numberOfLifes = i;
	}

	@Override
	/** Returns information about the cat. */
	public String getInfo() {
		return "The cat " + getFriendlyName() + " has " + getNumberOfLives() + " lives. Latin name of species: " + getLatinName() + ". Nurses for " + getGestationTime() + " months.";
	}

}
