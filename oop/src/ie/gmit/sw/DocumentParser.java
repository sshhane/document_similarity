package ie.gmit.sw;

import java.io.BufferedReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

public class DocumentParser implements Runnable{

	private BlockingQueue<Shingle>queue;
	private String file;
	private int shingleSize, k;
	private Deque<String> buffer = new LinkedList<>();
	private int docId;

	public DocumentParser(String file, BlockingQueue<Shingle> q, int shingleSize, int k) {
		this.queue = q;
	}
	
	public void run() {
		BufferedReader br = new BufferedReader(new InputStringReader(new FileInputString(file)));
		String line = null;
		while((line = br.readLine()) != null) {
			String uLine = line.toUpperCase();
			String[] words = uLine.split(" "); // Can also take a regexpression
			addWordsToBuffer(words);
			Shingle s = getNextShingle();
			queue.put(s); // Blocking method. Add is not a blocking method
		}
		flushBuffer();
		br.close();
	}// Run


	private void addWordsToBuffer(String [] words) {
		for(String s : words) {
			buffer.add(s);
		}
  
    }

  	private Shingle getNextShingle() {
		StringBuffer sb = new StringBuffer();
		int counter = 0;
		while(counter < shingleSize) {
			if(buffer.peek() != null) {
				sb.append(buffer.poll());
				counter++;
			}
		}  
		if (sb.length() > 0) {
			return(new Shingle(docId, sb.toString().hashCode());
		}
		else {
			return(null);
		}
  	} // Next shingle
	

	private void flushBuffer() {
		while(buffer.size() > 0) {
			Sh(Shingle s = getNextShingle();
			if(s != null) {
				queue.put(s);
			}
			else {
				queue.put(new Poison(docId, 0));
			}
		}
	}

}
