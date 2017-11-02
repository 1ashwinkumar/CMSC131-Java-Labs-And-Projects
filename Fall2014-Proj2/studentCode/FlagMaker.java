/* Ashwin Kumar
 * TA: Tak Yeon Lee
 * Section 0203
 * October 10, 2014
 */


package studentCode;

import java.awt.Color;
import cmsc131_GridTools.MyGrid;


public class FlagMaker{
	public static void drawFlag(MyGrid grid, int countryCode){
		if(countryCode==1) drawHorizontalSplit(grid, Color.white, Color.red); //draw Poland
		else if(countryCode==2)	drawHorizontalSplit(grid, Color.blue, Color.yellow); //draw Ukraine
		else if(countryCode==3){ //draw Czech Republic
			drawHorizontalSplit(grid, Color.white, Color.red);
			drawTriangle(grid, Color.blue);
		}
		else if(countryCode==4){ //draw Benin
			int size=grid.getHt();
			if(size==6 || size==12 || size==18 || size==24 || size==30){
				drawHorizontalSplit(grid, Color.yellow, Color.red);
				for(int iRow=0; iRow<size; iRow++) // left third of flag
					for(int iCol=0; iCol<2*size/3; iCol++)
						grid.setColor(iRow, iCol, Color.green);	
			}
			else drawErrorFlag(grid);
		}
		else if(countryCode==5){ //draw Rwanda
			int size=grid.getHt();
			if(size%4==0 && size>=4 && size<=30){
				drawHorizontalSplit(grid, Color.blue, Color.yellow);
				// bottom quarter of flag
				for(int iCol=0; iCol<2*size; iCol++)
					for(int iRow=3*size/4; iRow<size; iRow++)
						grid.setColor(iRow, iCol, Color.green);
			}
			else drawErrorFlag(grid);
		}
		else if(countryCode==6){ //draw Bahamas
			drawHorizontalSplit(grid, Color.blue, Color.blue);
			int size=grid.getHt();
			if(size%2==0 && size>=4 && size<=30) // middle yellow area of flag
				for(int iCol=0; iCol<2*size; iCol++)
					for(int iRow=size/2-1; iRow<size/2+1; iRow++)
						grid.setColor(iRow, iCol, Color.yellow);
			else drawErrorFlag(grid);
			drawTriangle(grid, Color.black);
		}
		else if(countryCode==7){ //draw Afghanistan
			int size=grid.getHt();
			if(size%3==0 && size>=6 && size<=30){
				int firstThird=2*size/3;
				int secondThird=2*firstThird;
				int lastThird=2*size;
				for(int iRow=0; iRow<size; iRow++){ //draws each third of flag
					for(int iCol=0; iCol<firstThird; iCol++) grid.setColor(iRow, iCol, Color.black);
					for(int iCol=firstThird; iCol<secondThird; iCol++) grid.setColor(iRow, iCol, Color.red);
					for(int iCol=secondThird; iCol<lastThird; iCol++) grid.setColor(iRow, iCol, Color.green);	
				}
			}
			else drawErrorFlag(grid);
		}
		else if(countryCode==8) drawDoubleWideX(grid, Color.white, Color.red); //draw Jersey
		else if(countryCode==9) drawDoubleWideX(grid, Color.blue, Color.white); //draw Scotland
		else drawErrorFlag(grid);
	}
	
	//draws the top half of the grid in one color and the bottom half of the grid in another color
	private static void drawHorizontalSplit(MyGrid grid, Color colorA, Color colorB){
		int size=grid.getHt();
		if(size%2==0 && size>=4 && size<=30) //size is an even number between 4 and 30 (inclusive)
			for(int iCol=0; iCol<2*size; iCol++){
				// top half of flag
				for(int iRow=0; iRow<size/2; iRow++)
					grid.setColor(iRow, iCol, colorA);
				//bottom half of flag
				for(int iRow=size/2; iRow<size; iRow++)
					grid.setColor(iRow, iCol, colorB);
			}
		else drawErrorFlag(grid);
	}
	
	//draws an X that has steps of width 2 on a background of different color than the X
	private static void drawDoubleWideX(MyGrid grid, Color colorA, Color colorB){
		int size=grid.getHt();
		if(size%2!=0 && size>=4 && size<=30){
			//background
			for(int iCol=0; iCol<2*size; iCol++)
				for(int iRow=0; iRow<size; iRow++)	
					grid.setColor(iRow, iCol, colorA);
			for(int iCol=0; iCol<2*size; iCol++){
				grid.setColor(iCol/2, iCol, colorB); //downsloping slash
				grid.setColor(iCol/2, grid.getWd()-iCol-1, colorB); // upsloping slash
			}
		}
		else drawErrorFlag(grid);
	}
	
	private static void drawTriangle(MyGrid grid, Color color){
		int size=grid.getHt();
		if(size%2==0 && size>=4 && size<=30){
			//top half of triangle
			for(int iRow=0; iRow<size/2; iRow++)
				for(int iCol=0; iCol<iRow+2; iCol++)
					grid.setColor(iRow, iRow-iCol+1, color);
			//bottom half of triangle
			for(int iRow=size/2; iRow<size; iRow++)
				for(int iCol=iRow-1; iCol<size; iCol++)
					grid.setColor(iRow, iCol-iRow+1, color);
		}
		else drawErrorFlag(grid);
	}

	// draw the Error Flag
	public static void drawErrorFlag(MyGrid grid){
		grid.setColor(0, 0, Color.red);
		grid.setColor(0, grid.getWd()-1, Color.red);		
		grid.setColor(grid.getHt()-1, 0, Color.red);
		grid.setColor(grid.getHt()-1, grid.getWd()-1, Color.red);
	}
}
