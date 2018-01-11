package ie.gmit.sw;

/**
 * 
 * @author shanedaniels
 *
 */

public class Poison extends Shingle{

	/**
	 * interrupt consumer and close BlockingQueue
	 * @param docId
	 * @param hashCode
	 */
	public Poison(int docId, int hashCode) {
		super(docId, hashCode);
	}
	
}
