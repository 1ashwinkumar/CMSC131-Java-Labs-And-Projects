import java.util.Scanner;

public class ASCII_Art_Part2{
	
	//NOTE: You can add static helper methods here if you want to.
	

	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		
		System.out.print("Shape? ");
		String shape = s.next();
		System.out.print("Size? ");
		int size = s.nextInt();
	
		if(shape.equals("l-triangle"))
			//YOUR CODE HERE
			for(int col=1; col<=size; col++){
				for(int row=1; row<=col; row++)
					System.out.print("*");
				System.out.println("");
			}				
		else if(shape.equals("r-triangle"))
			//YOUR CODE HERE
			for(int row=size; row>0; row--){
				for(int star=row-1; star>0; star--)
					System.out.print(" ");
				for(int col=row-1; col<size; col++)
					System.out.print("*");
				System.out.println("");
			}
		else
		{  // stripes
			//YOUR CODE HERE
			//Remember - stripes is an optional practice part, no points.
		}
		
		s.close();
	}

}
