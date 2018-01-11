package ie.gmit.sw;

import java.util.Scanner;

public class Menu {

	private int intInput;
	private String fileName1;
	private String fileName2;

	Menu() {

	}

/**
 * Start
 * 
 * @param finished 
 * 
 * if the program or menu is finished yet
 * 
 * @return Boolean
 */
	public boolean Start(boolean finished) {

		// Menu output
		System.out.println("Please Choose an Option");
		System.out.println("\t1.Name of File 1");
		System.out.println("\t2.Name of File 2");
		System.out.println("\t3.Start Comparison");
		System.out.println("\t4.Exit");

		try {
			// User Input
			intInput = Integer.parseInt(userInput("Select between 1-4"));
		} catch (Exception e) {
			System.out.println("\nSelect Between 1-4");
		}

		// What happens with the input from the user
		switch (intInput) {
		case 1:
			
			//ask the user for the path to the first file
			fileName1 = userInput("Please Enter the first File Path");

			break;
		case 2:
			//ask the user for the path to the second file
			fileName2 = userInput("Please Enter the Second File Path");

			break;
		case 3:
			//Launch the threads
			new Launch(fileName1, fileName2);

			break;
		case 4:
			
			//User is finished with the menu
			finished = true;

			break;

		default:
			System.out.println("Value is Incorrect\n");

		}

		return finished;

	}

	/**
	 * Used so that we dont need the scanner multiple times, 
	 * just call this method
	 * 
	 * @param output
	 * 
	 * Output message to the user
	 * 
	 * @return string
	 */
	
	static String userInput(String output) {

		System.out.println(output);

		Scanner sc = new Scanner(System.in);

		String input = sc.nextLine();

		return input;
	}

}
