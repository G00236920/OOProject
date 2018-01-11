package ie.gmit.sw;

/**
 * 
 * @author nlteshade
 *
 */

public class Runner {
	
	/**
	* Runner Starts the Program as the Main Method
	* 
	* @tag1 Finished Boolean
	* Tells the program when to stop running the menu
	*
	*/

	// Main Menu
	public static void main(String[] args) {

		// If finished - Stop Menu
		boolean finished = false;

		// Loop until Finished
		while (finished == false) {

			// Call Menu
	
			finished = new Menu().Start(finished);

		}

	}

}
