
public class Dog extends Mammal {
	/** Name of dog. */
  private String name;
  /** Whether or not the dog is stupid. */
  private boolean stupid;
    
  /** Constructor. Creates new dog. */
	public Dog(String latinName, String friendlyName, int gestationTime, boolean stupid) {
		super(latinName, friendlyName, gestationTime);
		this.stupid = stupid;
	}
	
	/** Returns name of dog. */
	public String getName() {
		return this.name;
	}

	@Override
	/** Returns information about dog. */
	public String getInfo() {
		if (stupid) return "The dog " + getFriendlyName() + " is stupid. Latin name of species: " + getLatinName() + ". Nurses for " + getGestationTime() + " months.";
		else return "The dog " + getFriendlyName() + " is not stupid. Latin name of species: " + getLatinName() + ". Nurses for " + getGestationTime() + " months.";
	}
}
