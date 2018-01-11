package ie.gmit.sw;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Consumer implements Runnable {
	

	private int[] minHashes;
	private static ExecutorService pool;
	private int k;
	private BlockingQueue <Shingle> q;
	private ConcurrentHashMap<Integer, List> map = new ConcurrentHashMap<Integer, List>();

	
	/** Consumer
	 * 
	 * This object is the thread that creates worker threads that will work from the blocking queue
	 * 
	 * @param q: this is the blocking queue
	 * @param k: this is the number of minhashes we will look for
	 * @param poolsize: the pool is the pool of worker threads - max size
	 */
	Consumer(BlockingQueue<Shingle> q, int k, int poolSize) {
		super();

		this.q = q;
		this.k = k;
		
		init();
		
		
		pool = Executors.newFixedThreadPool(poolSize);

	}
	
	/**Init
	 * 
	 * This Method runs when the consumer class is initialized
	 */
	
	public void init() {

		// Generate our MinHash Values
		Random random = new Random();

		minHashes = new int[k];

		for (int i = 0; i < minHashes.length; i++) {

			minHashes[i] = random.nextInt();

		}

		// Create two lists, Added to a map, Fill them with high Integers
		for (int i = 0; i < 2; i++) {

			List<Integer> list = map.get(i);

			// Create a list of Integers
			list = new ArrayList<Integer>();

			// Fill the list with the highest integers
			for (int j = 0; j < k; j++) {
				list.add(Integer.MAX_VALUE);

			}

			// Add the lists to the Map
			map.put(i, list);

		}

	}
	
	
	
	/**run
	 * 
	 * This Method runs when the consumer launches as a thread 
	 */
	public void run() {
		
		try {
			
			int docCount = 2;
			
			Shingle s = q.take();
			
			while (docCount > 0)
			{
				
				if (s instanceof Poison) 
				{
					docCount--;
				}
				else	
				{
					
					pool.execute( new Runnable()
					{

						public void run() 
						{
							
							for (int i = 0; i < minHashes.length; i++) 
							{
								
								
								int value = s.getHashValue() ^ minHashes[i]; 			//XOR Minhashing
								
								/**@param list 
								 * 
								 * This is a list stored in the concurrent map
								 * there is one list per document to store minHashes
								 */
								
								List<Integer> list = map.get(s.getDocumentId());

									synchronized(list) {
										
										if (list.get(i) > value) 
										{
											list.set(i, value);
										}
										
									}
										
									
									/** @param jaccard
									 * 
									 * This code take the similarity of each document and runs an equation to check 
									 * if the document are similar or not
									 */
									
									List<Integer> intersection = new ArrayList<Integer>(map.get(2));
									intersection.retainAll(map.get(1));
									
									float jaccard =((float) intersection.size())/((k)+((float)intersection.size()));
									System.out.println("documents are " + (jaccard*2)*100+ "% similar");
						
							}
						}
					});
					
				}
				
			}
			
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}

}