package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Launcher {

	public void Launch(String f1, String f2) throws InterruptedException {

		int shingleSize = 5;
		int pool = 200;
		int k = 500;

		BlockingQueue<Shingle> q = new LinkedBlockingQueue<Shingle>(100);	
		// threadPoolSize	

		Thread t1 = new Thread(new DocumentParser(f1, q, shingleSize), "T1");
		Thread t2 = new Thread(new DocumentParser(f2, q, shingleSize), "T2");
		Thread t3 = new Thread(new Consumer(q, k, pool), "T3");
		
		t1.start();
		t2.start();
		t3.start();
		
		t1.join();
		t2.join();
		t3.join();
	}
}
