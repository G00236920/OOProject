package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class Parser implements Runnable {
	
	BlockingQueue<Shingle> q;
	private FileReader in;
	private BufferedReader br;
	private File fileName;
	private int SHINGLESIZE;
	private int docId;

/**
 * Parses a Document by using a buffered reader and iterates through the file
 * Then it divides every 3 words into an object called a shingle	
 * 
 * @param SHINGLESIZE	Max size of shingles required
 * @param q				Our blocking queue
 * @param fileName		Name of the file to be read
 * @param docId			Document id number
 */
	public Parser(int SHINGLESIZE, BlockingQueue<Shingle> q, File fileName, int docId) {

		this.q = q;
		this.fileName = fileName;
		this.SHINGLESIZE = SHINGLESIZE;
		this.docId = docId;
		
	}
	
	/**
	 * Runs the thread
	 */
	
	public void run() {
		int reset = 0;
		

		try {

			//Filed reader
			in = new FileReader(fileName);

			//Buffered Reader
			br = new BufferedReader(in);

			//While the Line being read isnt null
			while (br.readLine() != null) {

				int s;

				String word = "";

				//While the Char being read isn't null
				while ((s = br.read()) != -1) {

					if (s == 32) {

						reset++;

						//Reset when the counter reached the Shingle size
						if (reset == SHINGLESIZE) {
							
							//Shingle Object
							Shingle shingle = new Shingle(word.hashCode(), docId);
							
							//add the new shingle to the blocking queue
					    		q.add(shingle);

					    		reset = 0;
					    		word = "";
						}
						
						

					} else if ((s > 64 && s < 91) || (s > 96 && s < 123)) {
						word += (char) s;

					} else {

						continue;

					}

				}
			}

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}
