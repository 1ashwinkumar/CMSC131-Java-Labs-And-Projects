package fishPond;

/**
 * Thrown when a fish's direction is not set to one of the four static constants
 * (Fish.UP, Fish.DOWN, Fish.LEFT, Fish.RIGHT).
 */
public class IllegalFishDirectionException extends RuntimeException{

	/* Field required for any serializable class */
	private static final long serialVersionUID=0;
	
	/**
	 * @param direction the incorrect direction's internal code
	 */
	public IllegalFishDirectionException(int direction){super("Direction attempted: "+direction);}
}
