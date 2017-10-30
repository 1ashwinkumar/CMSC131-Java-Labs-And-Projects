package studentCode;
import java.util.Scanner;

import cmsc131_squareGridTools.SquareGrid;


public class ExampleDriver{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		System.out.println("What arithmetic operator would you like to see?");
		System.out.println("1 for minus");
		System.out.println("2 for plus");
		System.out.println("3 for division");
		System.out.println("4 for multiplication");
		int symbol = scanner.nextInt();
		int size;
		do{
			System.out.print("Enter a size (odd number larger than 4):  ");
			size = scanner.nextInt();
		}while(size<=4 || size%2==0);
		
		/* Create drawing grid of the size requested */
		SquareGrid grid = new SquareGrid(size);

		/* Draw the symbol on the grid */
		OperatorMaker.drawOp(grid, symbol);
		
		scanner.close();
	}
}
