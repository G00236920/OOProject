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
	private BlockingQueue<Shingle> q;
	private ConcurrentHashMap<Integer, List<Integer>> map = new ConcurrentHashMap<>();

	Consumer(BlockingQueue<Shingle> queue, int k, int poolSize) {
		super();

		this.q = queue;
		this.k = k;
		
		pool = Executors.newFixedThreadPool(poolSize);

		init();

	}

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

	public void run() {

		int docCount = 2;
		
		while (!q.isEmpty()&& docCount > 0) {

			try {

				Shingle s = q.take();
				System.out.println(q.size());
				
				if (s instanceof Poison) {
					
					docCount--;
				} 
				else {

					pool.execute(new Runnable() {

						public void run() {

							List<Integer> list = map.get(s.getDocumentId());

							synchronized (list) {

								for (int i = 0; i < minHashes.length; i++) {
									
									int value = s.getHashValue() ^ minHashes[i];

									if (list.get(i) > value) {

										list.set(i, value);

									}

								}
							}

						}

					});

				}

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

		}

	}

}
