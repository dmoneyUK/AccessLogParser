package my.log.analyzer;
/**
 * 
 * A model class containing the successful requests number, error requests number, response time and return size in the minute.
 * @author Jinge Dai
 *
 */
public class AccessRecord {

	/** The number of success requests */
	private int numSuccess;

	/** The number of error requests */
	private int numError;

	/** The return size in megabytes*/
	private double returnSize;
	
	/** The response time */
	private long responseTime;

	public AccessRecord(int numSuccess, int numError,
			double returnSize, long responseTime) {
		this.numSuccess = numSuccess;
		this.numError = numError;
		this.returnSize = returnSize;
		this.responseTime = responseTime;
	}

	/**
	 * @return the numSuccess
	 */
	public int getNumSuccess() {
		return numSuccess;
	}

	/**
	 * @param numSuccess the numSuccess to set
	 */
	public void setNumSuccess(int numSuccess) {
		this.numSuccess = numSuccess;
	}

	/**
	 * @return the numError
	 */
	public int getNumError() {
		return numError;
	}

	/**
	 * @param numError the numError to set
	 */
	public void setNumError(int numError) {
		this.numError = numError;
	}

	/**
	 * @return the returnSize
	 */
	public double getReturnSize() {
		return returnSize;
	}

	/**
	 * @param returnSize the returnSize to set
	 */
	public void setReturnSize(double returnSize) {
		this.returnSize = returnSize;
	}

	/**
	 * @return the responseTime
	 */
	public long getResponseTime() {
		return responseTime;
	}

	/**
	 * @param responseTime the responseTime to set
	 */
	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}
	
	

}
