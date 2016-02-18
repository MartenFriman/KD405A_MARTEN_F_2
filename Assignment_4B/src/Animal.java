
public abstract class Animal {
  /** Animal's latin name. */
  private String latinName;
  /** Animal's "friendly" name. */
  private String friendlyName;
    
    /** Constructor. Creates new human. */
	public Animal(String latinName, String friendlyName) {
		this.latinName = latinName;
		this.friendlyName = friendlyName;
	}
    
	/** Returns information about animal. */
	public abstract String getInfo();
	
	/** Allocates a new friendly name to animal. */
	public void setFriendlyName(String name) {
		friendlyName = name;
	}
	
	/** Returns friendly name of animal. */
	public String getFriendlyName() {
		return friendlyName;
	}
	
	/** Returns latin name of animal. */
	public String getLatinName() {
		return latinName;
	}
}
