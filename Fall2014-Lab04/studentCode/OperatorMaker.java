package studentCode;
import java.awt.Color;

import cmsc131_squareGridTools.SquareGrid;


public class OperatorMaker{
	/* *************************************************
	 * Draws a single Operator into the already created grid
	 * that is passed as the first parameter to the drawOp
	 * method according to the symbol selection passed as the 
	 * second parameter.
	 * 
	 * It accomplishes this by calling the appropriate
	 * helper method and passing the grid into that.	
	 * 
	 * All of the methods are static and have a parameter of
	 * type SquareGrid.
	 * 
	 * Each method can determine how "big" the operator is
	 * by making use of grid.getHt() to find out the size
	 * of the SquareGrid that was passed in.
	 * *************************************************/
	
	public static void drawOp(SquareGrid grid, int symbol){
		//Your code here will essentially be a chain of if-else statements
		//  that call the appropriate helper and pass in grid.
		if(symbol==1) minus(grid);
		else if(symbol==2) plus(grid);
		else if(symbol==3) divide(grid);
		else if(symbol==4) multiply(grid);
	}
	
	/*
	 * The helper methods you need to write are indicated below.
	 * 
	 * You may add more helper methods if you want, but they 
	 * all need to be static as well.	
	 * 
	 * You will be using for loops here but you should also
	 * think about how one helper method might be able to make
	 * use of calling other helper methods that you have written.
	 * 
	 * In addition to the getHt() method, you'll be using the setColor
	 * method.  The way you'll use this will appear similar to
	 *    grid.setColor(row, column, Color.BLUE);
     * where you'll indicate the correct row and column and this 
     * line of code would then set that position in the grid to be
     * the color blue.
     * 
	 * Start by implementing the minus helper and testing that on
	 * your computer and on the submit server, then move on to each 
	 * remaining symbol in turn.
	 * 
	 * To run the program on your computer, you'll need to open the
	 * ExampleDriver.java file and run from there.  You do not change
	 * that file in any way.  All changes are done in this file.
	 * 
	 */
	
	public static void minus(SquareGrid grid){
		int size=grid.getHt();
		int midRow=size/2;
		for(int col=0; col<size; col++) grid.setColor(midRow, col, Color.BLUE);
	}

	public static void plus(SquareGrid grid){
		minus(grid);
		int size=grid.getHt();
		int midCol=size/2;
		for(int row=0; row<size; row++) grid.setColor(row, midCol, Color.BLUE);
	}

	public static void divide(SquareGrid grid){
		int size=grid.getHt()-1;
		for(int row=0; row<=size; row++) grid.setColor(row, size-row, Color.BLUE);
	}

	public static void multiply(SquareGrid grid){
		plus(grid);
		divide(grid);
		int size=grid.getHt();
		for(int iRow=0; iRow<size; iRow++) grid.setColor(iRow, iRow, Color.BLUE);
	}
}
