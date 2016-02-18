
public class Snake extends Animal{
	/** Whether or not the snake is poisonous. */
	private boolean poisonous;

	/** Constructor. Creates new snake. */
	public Snake(String latinName, String friendlyName, boolean poisonous) {
		super(latinName, friendlyName);
		this.poisonous = poisonous;
	}
	
	/** Returns whether or not the snake is poisonous. */
	public boolean isPoisonous() {
		return poisonous;
	}

	@Override
	/** Returns information about the snake. */
	public String getInfo() {
		if (poisonous) return "The snake " + getFriendlyName() + " is poisonous. Its latin name is " + getLatinName() + ".";
		else return "The snake " + getFriendlyName() + " isn't poisonous. Its latin name is " + getLatinName() + ".";
	}
}
