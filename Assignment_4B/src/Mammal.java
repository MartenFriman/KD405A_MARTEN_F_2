
public abstract class Mammal extends Animal {
	/** The animal's gestation time. */
	private int gestationTime;
    
	/** Constructor. Creates a new mammal. */
	public Mammal(String latinName, String friendlyName, int gestationTime) {
		super(latinName, friendlyName);
		this.gestationTime = gestationTime;
	}
	
	/** Returns gestation time of mammal. */
	public int getGestationTime() {
		return gestationTime;
	}

}
