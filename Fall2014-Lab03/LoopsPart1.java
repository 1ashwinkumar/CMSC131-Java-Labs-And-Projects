import java.util.Scanner;

public class LoopsPart1{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		System.out.print("Shape? ");
		String shape = sc.next();
		int row = 0;
		int col = 0;

		
		//YOUR CODE STARTS HERE
		if(shape.equals("rectangle")){
			System.out.print("Width? ");
			int width = sc.nextInt();
			System.out.print("Height? ");
			int height = sc.nextInt();
			while (row < height){
				while(col < width){
					System.out.print("*");
					col++;
				}
				System.out.println();
				row++;
				col = 0;
			}
		}
		else{
			System.out.print("Size? ");
			int size = sc.nextInt();
			while (row < size){
				while(col < size){
					System.out.print("*");
					col++;
				}
				System.out.println();
				row++;
				col = 0;
			}
		}
		//YOUR CODE ENDS HERE

		sc.close();
	}
}
