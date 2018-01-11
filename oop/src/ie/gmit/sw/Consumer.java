package ie.gmit.sw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ie.gmit.sw.Poison;

public class Consumer implements Runnable {

	private BlockingQueue<Shingle> q;
	private int k;
	private int[] minhashes;
	private ConcurrentMap<Integer,List<Integer>> map = new ConcurrentHashMap<Integer, List<Integer>>();
	private ExecutorService pool;
	private int count;
	private int count2;
	
	public ConcurrentMap<Integer, List<Integer>> getMap() {
		return map;
	}

	public Consumer(BlockingQueue<Shingle> q, int k, int poolSize) {
		this.q = q;
		this.k = k;
		pool = Executors.newFixedThreadPool(poolSize);
		init();
	}
	
	public void init() {
		Random random = new Random();
		minhashes = new int[k];
		for(int i=0; i < minhashes.length; i++) {
			minhashes[i] = random.nextInt();
		}
	}
	
	public void run() {
		int docCount = 2;
		while(docCount > 0) {
			try {
				Shingle s = q.take();
				if(s instanceof Poison) {
					docCount--;
				} else {
					pool.execute(new Runnable() {
						@Override
						public void run() {
							count++;
							List<Integer>list = map.get(s.getDocId());
							for(int i=0;i<minhashes.length;i++) {
								int value = s.getHashCode() ^ minhashes[i];
								list = map.get(s.getDocId());
								if(list == null) {
									list = new ArrayList<Integer>(Collections.nCopies(k, Integer.MAX_VALUE));
									map.put(s.getDocId(),list);
									count2++;
								}
								else {		
									if(list.get(i)>value) {
										list.set(i, value);
									}
								}
							} 
							map.put(s.getDocId(), list);			
						}
					});
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 
		}
		System.out.println("Size of list 1: " + map.get(1).size());
		System.out.println("Size of list 2: " + map.get(2).size());
		List<Integer> intersection = map.get(1);
		intersection.retainAll(map.get(2));
		float jacquared = (float)intersection.size()/(k*2-(float)intersection.size());
		
		System.out.println("Count is: " + count);
		System.out.println("Count2 is: " + count2);
		System.out.println("J: " + (jacquared) * 100);
	}
}

