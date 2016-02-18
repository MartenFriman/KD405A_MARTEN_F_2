
public class Human {
  /** Dog owned by the human. */
  private Dog dog; 
  /** Name of human. */
  private String name;
    
    /** Constructor. Creates new human. */
	public Human(String name) {
		this.name = name;
	}
    
	/** Returns name of human. */
	public String getName() {
		return this.name;
	}
	
	/** Assigns a new dog to human. */
	public void buyDog(Dog dog) {
		this.dog = dog;
	}
	
	/** Gets information about human's dog ownership. */
	public String getInfo() {
		if (dog instanceof Dog) return name + " äger en hund som heter " + dog.getName();
		else return name + " äger ingen hund";
	}
}
