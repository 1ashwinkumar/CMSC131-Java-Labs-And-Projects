package studentCode;
/** Here we provide a concrete implementation of the Person interface so 
 * that we can do some tests with it.
 */
public class AnotherExamplePerson implements Person{
	private String name;
	private int salary;
	
	public AnotherExamplePerson(String nameIn){
		name = nameIn;
		salary = 5000;
	}
	
	public Person copyMe(){
		AnotherExamplePerson newOne = new AnotherExamplePerson(name);
		newOne.salary = this.salary;
		return newOne;
	}
	
	public String getName(){return name;}
	
	public int getSalary(){return salary;}
	
	public void acceptRaise(int amount){salary+=amount;}
	
	public boolean equals(Object other){
		try{
			AnotherExamplePerson local = (AnotherExamplePerson)other;
			return this.name.equals(local.name);
		}
		catch(Exception e){return false;}
	}	
}