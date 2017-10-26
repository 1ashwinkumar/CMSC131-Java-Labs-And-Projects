package fishPond;

/**
 * Thrown when a plant is positioned in a way that doesn't make sense (either
 * above another plant, or over a rock.)
 */
public class IllegalPlantPositionException extends RuntimeException{

	/* Field required by all Serializable classes */
	private static final long serialVersionUID = 0;
	
	/* Codes that specify which type of error has occurred */
	public static final int TWO_PLANTS_IN_ONE_PLACE = 1;
	public static final int PLANT_OVER_ROCK = 2;
	
	private String message;
	private int code;
	
	/**
	 * @param code either the value of TWO_PLANTS_IN_ONE_PLACE or PLANT_OVER_ROCK
	 */
	public IllegalPlantPositionException(int code){
		this.code = code;
		message = (code == TWO_PLANTS_IN_ONE_PLACE) ?
			"You're trying to put two plants in the same place!"
			:
			"You're trying to put a plant over a rock.";
	}
	
	public String getMessage(){return message;}
	
	public int getCode(){return code;}
}
