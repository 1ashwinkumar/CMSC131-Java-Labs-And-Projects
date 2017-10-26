package fishPond;

import java.util.*;
import cmsc131Utilities.Random131;

/**
 * Model for the Fish Pond Simulation.
 * 
 * The model consists of a List of Fish, a List of Plants, and a two dimensional
 * array of int values representing the pond (each element in the array is
 * either ROCK, or WATER).
 * 
 * Each time the simulation is re-started a new Model object is created.
 * 
 * STUDENTS, YOU MAY NOT ADD ANY FIELDS. ALSO, STUDENTS, YOU MAY NOT ADD ANY
 * PUBLIC METHODS. (PRIVATE METHODS OF YOUR OWN ARE OKAY.)
 * 
 * @author Ashwin Kumar
 */
public class Model{

	/* State of this Model. STUDENTS, YOU MAY NOT ADD ANY FIELDS! */
	private ArrayList<Fish> fish;
	private ArrayList<Plant> plants;
	private int[][] landscape;

	/** Value stored in landscape array to represent water */
	public static final int WATER = 88;

	/** Value stored in landscape array to represent rock */
	public static final int ROCK = 99;

	/* Minimum pond dimensions */
	private static final int MIN_POND_ROWS = 5;
	private static final int MIN_POND_COLS = 5;

	/**
	 * THIS METHOD HAS BEEN WRITTEN FOR YOU!
	 * 
	 * If numRows is smaller than MIN_POND_ROWS, or if numCols is smaller than
	 * MIN_POND_COLS, then this method will throw an IllegalPondSizeException.
	 * 
	 * The fields "rows" and "cols" are initialized with the values of
	 * parameters numRows and numCols.
	 * 
	 * The field "landscape" is initialized as a 2-dimensional array of boolean.
	 * The size is determined by rows and cols. Every entry in the landscape
	 * array is filled with WATER. The border around the perimeter of the
	 * landscape array (op, bottom, left, right) is then overwritten with ROCK.
	 * 
	 * Random rocks are placed in the pond until the number of rocks (in
	 * addition to those in the border) reaches numRocks.
	 * 
	 * The "plants" ArrayList is instantiated. Randomly placed Plant objects are
	 * put into the List. Their positions are chosen so that they are never
	 * above rocks or in the same position as another plant. Plants are
	 * generated in this way until the list reaches size numPlants.
	 * 
	 * The "fish" ArrayList is instantiated. Now randomly placed Fish objects
	 * are put into the List. Their directions are also randomly selected. The
	 * positions are chosen so that they are never above rocks, plants, or other
	 * fish. Fish are generated in this way until the list reaches size numFish.
	 * 
	 * @param numRows
	 *            number of rows for pond
	 * @param numCols
	 *            number of columns for pond
	 * @param numRocks
	 *            number of rocks to be drawn in addition to rocks around border
	 *            of pond
	 * @param numFish
	 *            number of fish to start with
	 * @param numPlants
	 *            number of plants to start with
	 */
	public Model(int numRows, int numCols, int numRocks, int numFish, int numPlants){

		if(numRows<MIN_POND_ROWS || numCols<MIN_POND_COLS)
			throw new IllegalPondSizeException(numRows, numCols);

		landscape=new int[numRows][numCols];
		plants=new ArrayList<Plant>();
		fish=new ArrayList<Fish>();

		/* Fill landscape with water and a border of rocks around perimeter */
		for(int i=0; i<numRows; i++) {
			for(int j=0; j<numCols; j++)
				landscape[i][j]=WATER;
			landscape[i][0]=ROCK;
			landscape[i][numCols-1]=ROCK;
		}
		for (int j = 1; j < numCols - 1; j++) {
			landscape[0][j] = ROCK;
			landscape[numRows - 1][j] = ROCK;
		}

		/* Place random rocks */
		int rocksPlaced = 0;
		while (rocksPlaced < numRocks) {

			int row = Random131.getRandomInteger(numRows);
			int col = Random131.getRandomInteger(numCols);
			if (landscape[row][col] == WATER) {
				landscape[row][col] = ROCK;
				rocksPlaced++;
			}
		}

		/* Place random plants */
		for (int i = 0; i < numPlants; i++) {
			int row = Random131.getRandomInteger(numRows);
			int col = Random131.getRandomInteger(numCols);
			try {
				addPlant(new Plant(row, col, Plant.PLANT_STARTING_SIZE));
			} catch (IllegalPlantPositionException e) {
				i--;
			}
		}

		/* Place random fish */
		for (int fishCounter = 0; fishCounter < numFish; fishCounter++) {
			int row = Random131.getRandomInteger(numRows);
			int col = Random131.getRandomInteger(numCols);
			int randVal = Random131.getRandomInteger(4);
			int dir;
			if(randVal==0) dir=Fish.UP;
			else if(randVal==1) dir=Fish.DOWN;
			else if(randVal==2) dir=Fish.LEFT;
			else dir=Fish.RIGHT;

			Fish newFish = new Fish(row, col, Fish.FISH_STARTING_SIZE, dir);
			try{
				addFish(newFish);
			} catch(IllegalFishPositionException e){
				fishCounter--;
			}
		}
	}

	/**
	 * THIS METHOD HAS BEEN WRITTEN FOR YOU.
	 * 
	 * When a plant gets bigger than Plant.MAX_PLANT_SIZE, it will explode into
	 * 2 to 9 smaller plants, whose sizes add up to the twice the size of the
	 * original plant.
	 * 
	 * The new plants will be placed in the 9 regions of the landscape array
	 * that surround the original plant. If there are rocks, fish, or other
	 * plants already occupying these adjacent regions, then fewer than 9 plants
	 * are created.
	 * 
	 * If there are no available regions nearby, the plant will not explode.
	 */
	public void plantExplosions() {

		Iterator<Plant> currPlant = plants.iterator();
		while (currPlant.hasNext()) {
			Plant curr = currPlant.next();
			if (curr.getSize() > Plant.MAX_PLANT_SIZE) {
				int count = 0; // number of available slots for little plants
				boolean[] freeSpace = new boolean[9]; // true if just water
				int sizeAvail = curr.getSize() * 2;

				/*
				 * locations of 8 little plants are determined by these offsets
				 * to the coordinates of the plant that is exploding.
				 */
				int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
				int[] dy = { 1, 1, 0, -1, -1, -1, 0, 1 };

				int currRow = curr.getRow();
				int currCol = curr.getCol();

				/* Look to see if space is available in all eight directions */
				for(int spaceToCheck=0; spaceToCheck<8; spaceToCheck++) {
					freeSpace[spaceToCheck]=isSpaceAvailable(currRow+dy[spaceToCheck], currCol+dx[spaceToCheck]);
					if(freeSpace[spaceToCheck]) count++;
				}

				/* We'll split only if 1 or more spaces are available */
				if (count > 0) {
					currPlant.remove(); // kill off original plant
					int newSize = sizeAvail / (count + 1);

					/* Add little plants to the list - iterator now "broken"! */
					for (int currSpace = 0; currSpace < 8; currSpace++)
						if(freeSpace[currSpace])
							plants.add(new Plant(currRow+dy[currSpace], currCol+dx[currSpace], newSize));
					// replace original
					plants.add(new Plant(currRow, currCol, newSize));
					/*
					 * Since we've modified the List, the original iterator is
					 * no longer useful. Start iterating from the beginning.
					 */
					currPlant = plants.iterator();
				}
			}
		}
	}

	/**
	 * THIS METHOD HAS BEEN WRITTEN FOR YOU!
	 * 
	 * When a fish gets bigger than Fish.MAX_FISH_SIZE, it will explode into 4
	 * to 8 smaller fish, whose sizes add up to the size of the original fish.
	 * The smaller fish will be placed in the eight regions of the landscape
	 * array surrounding the original fish. The little fish will be begin moving
	 * in directions that point away from the original location. (Note that no
	 * little fish is placed into the original location of the landscape array
	 * where the exploding fish was -- just in the surrounding squares.) If
	 * there are rocks, fish, or plants already occupying these adjacent
	 * squares, then fewer than eight little fish are created. If there are not
	 * at least four available surrounding squares, then the fish will not
	 * explode.
	 */
	public void fishExplosions() {
		Iterator<Fish> currFish = fish.iterator();
		while (currFish.hasNext()) {
			Fish curr = currFish.next();
			if (curr.getSize() > Fish.MAX_FISH_SIZE) {
				int count = 0; // number of available squares for little fish
				boolean[] freeSpace = new boolean[8]; // true if water there

				/*
				 * locations of the 8 little fish are determined by the offsets
				 * to the coordinates of the original fish that is exploding
				 */
				int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
				int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };

				/* directions for the 8 little fish */
				int[] newDir = { Fish.UP, Fish.UP, Fish.RIGHT, Fish.RIGHT,
						Fish.DOWN, Fish.DOWN, Fish.LEFT, Fish.LEFT };

				int currRow = curr.getRow();
				int currCol = curr.getCol();

				/* Look to see if space is available in all directions */
				for (int spaceToCheck = 0; spaceToCheck < 8; spaceToCheck++) {
					freeSpace[spaceToCheck] = isSpaceAvailable(currRow
							+ dy[spaceToCheck], currCol + dx[spaceToCheck]);
					if (freeSpace[spaceToCheck])
						count++;
				}

				/* We'll split only if 4 or more spaces are available */
				if (count > 3) {
					currFish.remove(); // remove original fish from List
					int newSize = curr.getSize() / count;

					/* Add little fish to the list - iterator now "broken"! */
					for(int currSpace=0; currSpace<8; currSpace++)
						if(freeSpace[currSpace])
							fish.add(new Fish(currRow + dy[currSpace], currCol + dx[currSpace], 
									newSize, newDir[currSpace]));

					/*
					 * Since we have modified the List, the original Iterator is
					 * no longer valid. We'll start iterating again from the
					 * beginning.
					 */
					currFish = fish.iterator();
				}
			}
		}
	}

	/*
	 * Checks the specified location to see if it has a rock, fish, or plant in
	 * it. If so, returns false; if it is just water, returns true.
	 */
	public boolean isSpaceAvailable(int row, int col){
		if(landscape[row][col]==ROCK) return false;
		for(Fish fish1: fish) //fish
			if(fish1.getRow()==row && fish1.getCol()==col)
				return false;
		for(Plant plant1:plants) //plants
			if(plant1.getRow()==row && plant1.getCol()==col)
				return false;
		return true; //water
	}

	/**
	 * Copy Constructor. Since landscape is immutable in the scope of our
	 * project, you could do a simple reference copy for it. However, Fish and
	 * Plants are mutable, so those lists must be copied with a DEEP copy! (In
	 * other words, each fish and each plant must be copied.)
	 */
	public Model(Model other){
		landscape = other.landscape;
		fish = new ArrayList<Fish>();
		plants = new ArrayList<Plant>();
		for(Fish newFish:other.fish)
			fish.add(new Fish(newFish));
		for(Plant newPlant:other.plants)
			plants.add(new Plant(newPlant));
	}

	/**
	 * Fish f eats a portion of plant p. The amount eaten is either
	 * Plant.PLANT_BITE_SIZE or the current size of the plant, whichever is
	 * smaller. The fish's size is increased by this amount and the plant's size
	 * is decreased by this amount.
	 */
	public static void fishEatPlant(Fish f, Plant p){
		if(Plant.PLANT_BITE_SIZE<p.getSize()){
			f.eat(Plant.PLANT_BITE_SIZE);
			p.removeBite(Plant.PLANT_BITE_SIZE);
		}
		else{
			f.eat(p.getSize());
			p.removeBite(p.getSize());
		}
	}

	/** returns number of rows in landscape array */
	public int getRows(){return landscape.length;}

	/** returns number of columns in landscape array */
	public int getCols(){return landscape[0].length;}

	/**
	 * Iterates through fish list. For each fish that isAlive, shrinks the fish
	 * by invoking it's "shrink" method.
	 */
	public void shrinkFish(){
		for(Fish fish1: fish)
			if(fish1.isAlive())
				fish1.shrink();
	}

	/**
	 * Iterates through the plants list, growing each plant by invoking it's
	 * "grow" method.
	 */
	public void growPlants(){
		for(Plant plant1: plants) plant1.grow();
	}

	/**
	 * Iterates through the list of Fish. Any fish that is no longer alive is
	 * removed from the list.
	 */
	public void removeDeadFish(){
		ArrayList<Fish> fish1 = new ArrayList<Fish>(fish); //makes copy of fish
		Iterator<Fish> nextFish = fish1.iterator();
		while(nextFish.hasNext()){
			Fish newFish=nextFish.next();
			if(newFish.isAlive()==false)
				fish.remove(newFish); //removes dead fish from fish
		}
	}

	/**
	 * Iterates through the list of Plants. Any plant that is no longer alive is
	 * removed from the list.
	 */
	public void removeDeadPlants(){
		ArrayList<Plant> plants1 = new ArrayList<Plant>(plants); //makes copy of plants
		Iterator<Plant> nextPlant = plants1.iterator();
		while(nextPlant.hasNext()){
			Plant newPlant = nextPlant.next();
			if(newPlant.isAlive()==false)
				plants.remove(newPlant); //removes dead plants from plants
		}		
	}

	/**
	 * Checks if the fish fishy is surrounded ON FOUR SIDES (above, below, left,
	 * right) by rocks. If so, return true. If there is at least one side
	 * without a rock, then return false.
	 */
	private boolean fishIsSurroundedByRocks(Fish fishy){
		int row = fishy.getRow();
		int col = fishy.getCol();
		int above = row-1;
		int below = row+1;
		int left = col-1;
		int right = col+1;
		return landscape[above][col]==ROCK && landscape[below][col]==ROCK && landscape[row][left]==ROCK 
				&& landscape[row][right]==ROCK;
	}
	
	//checks to see if there are rocks anywhere
	private boolean checkRocks(Fish f){
		int row = f.getRow();
		int col = f.getCol();
		if(f.getDirection()==Fish.UP && landscape[row-1][col]==ROCK)
			return false;
		else if(f.getDirection()==Fish.DOWN && landscape[row+1][col]==ROCK)
			return false;
		else if(f.getDirection()==Fish.LEFT && landscape[row][col-1]==ROCK)
			return false;
		else if(f.getDirection()==Fish.RIGHT && landscape[row][col+1]==ROCK)
			return false;
		return true;
	}
	

	/**
	 * Iterate through list of Fish. For each fish that isAlive, do the
	 * following in this order:
	 * 
	 * 1. If this fishIsSurroundedByRocks, DO NOTHING, and move on to the next
	 * fish. (This fish will not turn.)
	 * 
	 * 2. If this fish's direction is not equal to one of the codes UP, DOWN,
	 * LEFT, or RIGHT, then throw an IllegalFishDirectionException, passing this
	 * fish's direction to the constructor.
	 * 
	 * 3. Check whether or not this fish is about to hit a rock if it moves in
	 * it's current direction. If it is about to hit a rock, call the fish's
	 * setRandomDirection method. Repeat this step until the fish is no longer
	 * about to hit a rock. Do not make any EXTRA calls to setRandomDirection or
	 * you will fail our tests!
	 */
	public void turnFish(){
		for(Fish nextFish: fish)
			if(nextFish.isAlive() && fishIsSurroundedByRocks(nextFish)==false){
				if(nextFish.getDirection()!=Fish.UP && nextFish.getDirection()!=Fish.DOWN &&
						nextFish.getDirection()!=Fish.LEFT && nextFish.getDirection()!=Fish.RIGHT)
					throw new IllegalFishDirectionException(nextFish.getDirection());
				while(checkRocks(nextFish)==false)
					nextFish.setRandomDirection();
			}
	}

	/**
	 * Note: This method assumes that each live fish that is not surrounded by
	 * rocks is already facing a direction where there is no rock! (Typically
	 * the call to this method should immediately follow a call to "turnFish",
	 * which ensures that these conditions are satisfied.)
	 * 
	 * This method iterates through the list of fish. For each fish that isAlive
	 * and is not surrounding by rocks, do the following in the given order.  
	 * If the fish is not alive or is surrounded by rocks, then DO NOTHING
	 * and move along to the next fish in the list.  (This fish does not move,
	 * does not eat, does not fight.)
	 * 
	 * 1. Move this fish by calling it's "move" method.
	 * 
	 * 2. Check if there is another fish (distinct from this fish) that is in
	 * the same location as this fish. If so, have the two fish fight each other
	 * by calling the fight method. IMPORTANT -- the fight method is not
	 * symmetrical. You must use THIS fish as the current object, and pass the
	 * OTHER fish as the parameter (otherwise you will not pass our tests.)
	 * 
	 * 3. If the fish is STILL alive, check if there is a plant that isAlive 
	 * and is located in the same position as this fish. If so, have the fish 
	 * eat part of the plant by calling fishEatPlant.
	 */
	public void moveFish(){
		for(Fish nextFish: fish)
			if(nextFish.isAlive() && fishIsSurroundedByRocks(nextFish)==false){
				nextFish.move();
				for(Fish fishEat: fish)
					if(fishEat.isAlive() && nextFish!=fishEat)
						if(nextFish.getRow()==fishEat.getRow() && nextFish.getCol()==fishEat.getCol())
							nextFish.fight(fishEat);
				if(nextFish.isAlive()){
					for(Plant nextPlant:plants){
						if(nextPlant.isAlive()){
							if(nextFish.getRow()==nextPlant.getRow() && nextFish.getCol()==nextPlant.getCol()){
								fishEatPlant(nextFish,nextPlant);
							}
						}
					}	
				}
			}
	}

	/**
	 * Attempts to add the plant p to plant list, if possible.
	 * 
	 * First checks if the landscape in the plant's location is equal to ROCK.
	 * If it is, then does not add the plant to the list. Instead throws an
	 * IllegalPlantPositionException, passing
	 * IllegalPlantPositionException.PLANT_OVER_ROCK to the constructor.
	 * 
	 * Now checks for another plant (distinct from the parameter) that is in the
	 * same location as the parameter. If one is found, then does not add the
	 * plant to the list. Instead throws an IllegalPlantPositionException,
	 * passing IllegalPlantPositionException.TWO_PLANTS_IN_ONE_PLACE to the
	 * constructor.
	 * 
	 * Otherwise, adds the plant to the list "plants".
	 */
	public void addPlant(Plant p){
		int row=p.getRow();
		int col=p.getCol();
		if(landscape[row][col]==ROCK)
			throw new IllegalPlantPositionException(IllegalPlantPositionException.PLANT_OVER_ROCK);
		for(Plant addedPlant:plants)
			if(addedPlant.getRow()==row && addedPlant.getCol()==col)
				throw new IllegalPlantPositionException(IllegalPlantPositionException.TWO_PLANTS_IN_ONE_PLACE);
		plants.add(p);
	}

	/**
	 * Adds the fish f to the fish list, if possible.
	 * 
	 * First checks if the landscape in the fish's location is equal to ROCK. If
	 * it is, then the fish is not added to the list. Instead, throws an
	 * IllegalFishPositionException, passing
	 * IllegalFishPositionException.FISH_OVER_ROCK to the constructor.
	 * 
	 * Next checks for another fish (distinct from the parameter) that is in the
	 * same location as the parameter. If one is found, then the fish is not
	 * added to the list. Instead throws an IllegalFishPositionException,
	 * passing IllegalFishPositionException.TWO_FISH_IN_ONE_PLACE to the
	 * constructor.
	 * 
	 * Otherwise, adds the parameter to the fish list.
	 */
	public void addFish(Fish f){
		int row=f.getRow();
		int col=f.getCol();
		if(landscape[row][col]==ROCK)
			throw new IllegalFishPositionException(IllegalFishPositionException.FISH_OVER_ROCK);
		for(Fish addedFish:fish)
			if(addedFish.getRow()==row && addedFish.getCol()==col)
				throw new IllegalFishPositionException(IllegalFishPositionException.TWO_FISH_IN_ONE_PLACE);
		fish.add(f);
	}

	/** Returns a SHALLOW COPY of the fish list. */
	public ArrayList<Fish> getFish(){return new ArrayList<Fish>(fish);}

	/** Returns a SHALLOW COPY of the plants list. */
	public ArrayList<Plant> getPlants(){return new ArrayList<Plant>(plants);}

	/**
	 * Returns the specified entry of the landscape array (either WATER or
	 * ROCK).
	 */
	public int getShape(int row, int col){return landscape[row][col];}
}
