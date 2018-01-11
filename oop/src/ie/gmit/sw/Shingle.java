package ie.gmit.sw;

/**
 * 
 * @author shanedaniels
 *
 */

public class Shingle {
	private int docId;
	private int hashCode;
	
	/**
	 * 
	 */
	public Shingle() {
		super();
	}

	/**
	 * 
	 * @param docId
	 * @param hashCode
	 */
	public Shingle(int docId, int hashCode) {
		super();
		this.docId = docId;
		this.hashCode = hashCode;
	}

	/**
	 * 
	 * @return
	 */
	public int getDocId() {
		return docId;
	}

	/**
	 * 
	 * @param docId
	 */
	public void setDocId(int docId) {
		this.docId = docId;
	}

	/**
	 * 
	 * @return
	 */
	public int getHashCode() {
		return hashCode;
	}

	/**
	 * 
	 * @param hashCode
	 */
	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}
	
}

