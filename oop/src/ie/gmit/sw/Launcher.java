package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
 * @author shanedaniels
 *
 */

public class Launcher {

	/**
	 * create threads for both files
	 * start and join threads
	 * @param f1		String filename 1
	 * @param f2		String filename 2
	 * @throws InterruptedException
	 */
	public void Launch(String f1, String f2) throws InterruptedException {

		int shingleSize = 5;
		int pool = 200;
		int k = 200;

		BlockingQueue<Shingle> q = new LinkedBlockingQueue<Shingle>(100);	

		Thread t1 = new Thread(new DocumentParser(f1, q, shingleSize, 1), "T1");
		Thread t2 = new Thread(new DocumentParser(f2, q, shingleSize, 2), "T2");
		Thread t3 = new Thread(new Consumer(q, k, pool), "T3");
		
		t1.start();
		t2.start();
		t3.start();
		
		t1.join();
		t2.join();
		t3.join();
	}
}
