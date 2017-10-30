package studentCode;

public interface Person{
	/** Returns a copy of the Person */
	public Person copyMe();
	
	/** Returns the person's name */
	public String getName();
	
	/** Returns the person's annual salary */
	public int getSalary();
	
	/** Increases this person's salary by the amount of the parameter */
	public void acceptRaise(int amount);
}
