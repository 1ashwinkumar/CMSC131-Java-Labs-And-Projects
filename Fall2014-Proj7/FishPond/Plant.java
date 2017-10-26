package fishPond;

/**
 * The state of a plant includes it's position (row and column) and it's size.                                                                     
 *                                                      
 * A Plant grows over time.  Plants are eaten by fish that pass over them.   
 *                                                                        
 * @author Ashwin Kumar                                              
 */

public class Plant{
	/** Initial size of plants in beginning of simulation */
	public static final int PLANT_STARTING_SIZE = 250;
	/** Maximum size for a plant */
	public static final int MAX_PLANT_SIZE = 1500;
	/** Portion of plant that is eaten by a fish passing over it */
	public static final int PLANT_BITE_SIZE = 70;
	/* State of this Plant */
	private int row, col, size;
	
	/** Standard constructor that merely initializes the fields from the 
	 * parameters */
	public Plant(int rowIn, int colIn, int sizeIn){
		row=rowIn;
		col=colIn;
		size=sizeIn;
	}
	
	/** Standard copy constructor that merely copies the fields */
	public Plant(Plant other){
		row=other.row;
		col=other.col;
		size=other.size;
	}
	
	/** returns true if size is bigger than zero, false otherwise. */
	public boolean isAlive(){return size>0;}
	
	/** returns row */
	public int getRow(){return row;}
	
	/** returns column */
	public int getCol(){return col;}
	
	/** returns size */
	public int getSize(){return size;}
	
	/** increments size by one unit */
	public void grow(){size+=1;}
	
	/** decreases size by biteSize units */
	public void removeBite(int biteSize){size-=biteSize;}
}
