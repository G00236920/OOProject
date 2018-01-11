package ie.gmit.sw;

import java.io.File;
import java.util.concurrent.BlockingQueue;

//Create Threads here
//2 Document parsers

public class Launch {

	final int K = 200;
	final int SHINGLESIZE = 3;

	private BlockingQueue<Shingle> q;

/**Launches the threads we need to run
 * 
 * @param filename1: Name of the first file to be read
 * @param SHINGLESZE:  Name of the second file to be read
 */
	Launch(String fileName1, String fileName2) {

		//File f1 = new File("/Volumes/W_10_PRO_X6/OO/WarAndPeace-LeoTolstoy.txt");		//For testing
		//File f2 = new File("/Volumes/W_10_PRO_X6/OO/WarAndPeace-LeoTolstoy2.txt");		//For testing

		File f1 = new File(fileName1);
		File f2 = new File(fileName2);

		// Thread Declarations
		
		 //This Thread will read the first document and add its shingles to the blocking queue
		Thread t1 = new Thread(new Parser(SHINGLESIZE, q, f1, 1));
		

		 //This Thread will read the Second document and add its shingles to the blocking queue
		Thread t2 = new Thread(new Parser(SHINGLESIZE, q, f2, 2));
		
		 //This thread will create worker threads that consumer the objects on the blocking queue
		Thread t3 = new Thread(new Consumer(getQ(), getK(), 30));
		
		
		// Start Threads
		t1.start();
		t2.start();
		t3.start();

		try {

			// Join Threads
			t1.join();
			t2.join();
			t3.join();

		} catch (InterruptedException e) {

			// Catch Exception
			e.printStackTrace();

		}

	}

	public int getK() {
		return this.K;
	}

	public BlockingQueue<Shingle> getQ() {
		return q;
	}

	public void setQ(BlockingQueue<Shingle> queue) {
		this.q = queue;
	}

	public void addToQueue(Shingle s) {
		this.q.add(s);
	}

}
