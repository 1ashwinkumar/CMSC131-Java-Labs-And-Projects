public class NumberList{
	public int[] values;  

	//Constructor for an empty list (provided for you)
	public NumberList(){values=new int[0];}

	//Constructor passed in an array
	public NumberList(int[] a){
		values=new int[a.length];
		for(int i=0; i<a.length; i++) values[i]=a[i];
	}

	//Copy constructor
	//  (code provided for you but uses your array-based constructor)
	public NumberList(NumberList original){this(original.values);}

	//Size getter
	public int getSize(){return values.length;}

	//Single-value getter
	public int getAt(int index){
		if(index<0 || index>values.length) throw new IndexOutOfBoundsException("Index out of bounds.");
		return values[index];
	}

	//Process the values to get the sum
	public long getSum(){
		long sum=0;
		for(int i=0; i<values.length; i++) sum+=values[i];
		return sum;
	}

	//See if the given value is anywhere within the array
	public boolean contains(int searchVal){
		for(int i=0; i<values.length; i++)
			if(values[i]==searchVal) return true;
		return false;
	}

	//Add a new element at the end of the list - the array must "grow" somehow first!
	public void add(int newValue){
		int[] newBiggerArray = new int[values.length + 1];
		for(int i=0; i<values.length; i++) newBiggerArray[i]=values[i];
		newBiggerArray[newBiggerArray.length-1]=newValue;
		values=newBiggerArray;
	}	
}
