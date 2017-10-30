package studentCode;
/** Here we provide a concrete implementation of the Person interface so 
 * that we can do some tests with it.
 */
public class ExamplePerson implements Person{
	private String name;
	private int salary;
	
	public ExamplePerson(String nameIn){
		name=nameIn;
		salary=10000;
	}
	
	public Person copyMe(){
		ExamplePerson newOne=new ExamplePerson(name);
		newOne.salary=salary;
		return newOne;
	}
	public String getName(){return name;}
	
	public int getSalary(){return salary;}
	
	public void acceptRaise(int amount){salary+=amount;}
	
	public boolean equals(Object other){
		if(other==null) return false;
		try{
			ExamplePerson local=(ExamplePerson)other;
			return name.equals(local.name);
		}
		catch(Exception e){return false;}
	}
}