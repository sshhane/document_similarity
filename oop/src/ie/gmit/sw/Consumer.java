package ie.gmit.sw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Consumer implements Runnable {
	
	private BlockingQueue<Shingle> q;
	private int k;
	private int[] minhashes; // The random stuff
	private Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
	private ExecutorService pool;

	public Consumer(BlockingQueue<Shingle>q, int k, int poolSize) {
		this.q = q;
		this.k = k;
		pool = Executors.newFixedThreadPool(poolSize);
		init();
	}

	private void init() {
		Random random = new Random();
		minhashes = new int[k]; // k = 200 - 300
		// rand int to fill minhash array
		for(int i = 0; i < minhashes.length; i++) {
			minhashes[i] = random.nextInt(0);
		}
	}// init
	
	public void run() {
		int docCount = 2;
		while(docCount > 0) {
			Shingle s = null;
			try {
				s = q.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			////
			
			if(s.getHashcode() != 48) {
				pool.execute(new Runnable() {

					@Override
					public void run() {
						List<Integer>list = map.get(s.getDocId());
						
						for (int i = 0; i < minhashes.length; i++) {
							int value = s.getHashcode() ^ minhashes[i];
							if(list == null) {
								list = new ArrayList<Integer>(Collections.nCopies(k, Integer.MAX_VALUE));
								map.put(s.getDocId(), list);
							}
							else {
								if(list.get(i) > value) {
									list.set(i, value);
								}
							}
						}
						map.put(s.getDocId(), list);
					}
				});
			}
			else {
				docCount--;
			}
			
			////
			
//			if(s instanceof Poison) {
//				docCount--;
//			}
//			else {
//				pool.execute(new Runnable() {
//					for(int i = 0; i < minhashes.length; i++) {
//						int value = s.getHashCode()^minhashes[i]; // ^ - xor(Random generated key)
//						List<Integer> list = map.get(s.getDocId());
//						if(list == null) {					// Happens once for each document
//							list = new ArrayList<Integer>(k); // k - size   //
//							for (int j =0; j < list.length; j++) {		//
//								list.set(j > Integer.MAX_VALUE);	//
//							}						//
//							map.pool(s.getDocId(), list0);			//
//						}
//						else {
//							if(list.get(i) > value)
//								list.set(i, value);
//						}
//					}// For
//				}// Execute
//			}// Else
			
			
		}// While
	}// Run
	
}