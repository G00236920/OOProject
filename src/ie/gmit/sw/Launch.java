package ie.gmit.sw;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//Create Threads here
//2 Document parsers

public class Launch {
	
	
	final static int K = 200;
	final int SHINGLESIZE = 3;
	
	static BlockingQueue <Shingle> q = new LinkedBlockingQueue <Shingle>();

	Launch(String fileName1, String fileName2) {
		
		File f1 = new File("/Volumes/W_10_PRO_X6/OO/Object Project/src/file1.txt");
		File f2 = new File("/Volumes/W_10_PRO_X6/OO/Object Project/src/file2.txt");
		
		
		//File f1 = new File(fileName1);
		//File f2 = new File(fileName2);
		
		//Thread Declarations
		Thread t1 =  new Thread(new Parser(SHINGLESIZE, q, f1, 1));
		Thread t2 =  new Thread(new Parser(SHINGLESIZE, q, f2, 2));
		Thread t3 = new Thread(new Consumer( getQ(), getK(), 10 ));
		
		//Start Threads
		t1.start();
		t2.start();
		t3.start();
		
		try {
			
			//Join Threads
			t1.join();
			t2.join();
			t3.join();
			
		} catch (InterruptedException e) {
			
			//Catch Exception
			e.printStackTrace();
			
		}
		
	}
	
	
	public static int getK() {
		return K;
	}


	public static BlockingQueue<Shingle> getQ() {
		return q;
	}

	public static void setQ(BlockingQueue<Shingle> q) {
		Launch.q = q;
	}
	
	public static void addToQueue(Shingle s) {
		q.add(s);
	}
	
	
}
