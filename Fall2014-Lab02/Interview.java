import java.util.Scanner;

public class Interview{
	public static void main(String[] args){
		Scanner myScanner=new Scanner(System.in); //Create an input scanner
        
		//Prompt the user for their name and read it. Assume the name will be just ONE WORD.
		//The prompt needs to be "What is your name?" and allow the user to enter their answer
		//   on the same line as where the prompt appears.
		System.out.print("What is your name? ");
		String name=myScanner.next();
		
		//Prompt the user for their age in whole years and read it.
		//The prompt needs to be "How old are you in full years?" and allow the user to enter 
		//   their answer on the same line as where the prompt appears.
        System.out.print("How old are you in full years? ");
        int age=myScanner.nextInt();
		
		//Ask how many full months it has been since their last birthday and read it.
		//The prompt needs to be "How many full months has it been since your last birthday?" 
		//   and allow the user to enter their answer on the same line as where the prompt appears.
        System.out.print("How many full months has it been since your last birthday? ");
        int month=myScanner.nextInt();		
		
		myScanner.close(); //This closes the input scanner since you are done with it by this point.
		
		//Calculate their total age in months and store this in a variable. 	
		int total=(age*12)+month;
		
		/* 
		 * Print the appropriate one of the following lines, depending on their age: 
		 *	age > 900 months, print: Greetings NAME, your age is TOTALMONTHS months. 
		 *  age < 120 months, print: Hello NAME, your age is only TOTALMONTHS months. 
		 *  any other age, print: Howdy NAME, you are TOTALMONTHS months old.
		 */
		if(total>900) System.out.print("Greetings "+name+", your age is "+total+" months.");
		else if(total<120) System.out.print("Hello "+name+", your age is only "+total+" months.");
		else System.out.print("Howdy "+name+", you are "+total+" months old.");
	}	
}
