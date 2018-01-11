package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Launcher {

	public void Launch(String f1, String f2) {
		int blockingQueueSize = 5;
		int shingleSize = 5;
		int k = 10;
		
		BlockingQueue<Shingle> q = new LinkedBlockingQueue<>(blockingQueueSize);
		// threadPoolSize	

		Thread t1 = new Thread(new DocumentParser(f1, q, shingleSize, k ), "T1");
		Thread t2 = new Thread(new DocumentParser(f2, q, shingleSize, k), "T2");
		// t3 for consumer
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
