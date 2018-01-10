package ie.gmit.sw;

/**
 * 
 * @author nlteshade
 *
 */

public class Runner {
	
	//Main Menu
	public static void main(String [] args) {
	
		
		//If finished - Stop Menu
		boolean finished = false;
		
		//Loop until Finished
		while(finished == false){
			
			//Call Menu
			finished = new Menu().Start(finished); 
			
		}
		
	}
	
}
