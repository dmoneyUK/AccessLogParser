package my.log.analyzer;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

/**
 * A test class for {@link AccessRecord}.
 * @author Jinge Dai
 *
 */
public class AccessRecordTest {
	
	/** A samples of successful request number */
	private final int SAMPLE_SUCCESS_NUM = 10;
	
	/** A samples of error request number */
	private final int SAMPLE_ERROR_NUM = 1;

	/** A samples of return size in megabyte */
	private final double SAMPLE_RETURN_SIZE = 1.1;
	
	/** A samples of the response time */
	private final long SAMPLE_RESPONSE_TIME=1000;
	
	

	/** The {@link AccessRecord} under test. */
	private AccessRecord record;
	
	/**
	 * Test for {@link AccessRecord#AccessRecord(int, int, double, long)}.
	 */
	@Test
	public void testAccessRecord(){
		this.record = new AccessRecord(SAMPLE_SUCCESS_NUM, SAMPLE_ERROR_NUM, SAMPLE_RETURN_SIZE, SAMPLE_RESPONSE_TIME);
		Assert.assertEquals(SAMPLE_SUCCESS_NUM, this.record.getNumSuccess());
		Assert.assertEquals(SAMPLE_ERROR_NUM, this.record.getNumError());
		Assert.assertEquals(SAMPLE_RETURN_SIZE, this.record.getReturnSize(),0);
		Assert.assertEquals(SAMPLE_RESPONSE_TIME, this.record.getResponseTime());
	}
	
	/**
	 * Test for {@link AccessRecord#setNumSuccess(int)} and {@link AccessRecord#getNumSuccess()}.
	 */
	@Test
	public void testSetNumSuccess(){
		this.record = new AccessRecord(0,0,0,0);
		this.record.setNumSuccess(SAMPLE_SUCCESS_NUM);
		Assert.assertEquals(SAMPLE_SUCCESS_NUM, this.record.getNumSuccess());
	}
	
	/**
	 * Test for {@link AccessRecord#setNumError(int)} and {@link AccessRecord#getNumError()}.
	 */
	@Test
	public void testSetNumError(){
		this.record = new AccessRecord(0,0,0,0);
		this.record.setNumError(SAMPLE_ERROR_NUM);
		Assert.assertEquals(SAMPLE_ERROR_NUM, this.record.getNumError());
	}
	
	/**
	 * Test for {@link AccessRecord#setReturnSize(double)} and {@link AccessRecord#getReturnSize()}.
	 */
	@Test
	public void testSetReturnSize(){
		this.record = new AccessRecord(0,0,0,0);
		this.record.setReturnSize(SAMPLE_RETURN_SIZE);
		Assert.assertEquals(SAMPLE_RETURN_SIZE, this.record.getReturnSize(),0);
	}
	
	/**
	 * Test for {@link AccessRecord#setResponseTime(long)} and {@link AccessRecord#getResponseTime()}.
	 */
	@Test
	public void testSetResponseTime(){
		this.record = new AccessRecord(0,0,0,0);
		this.record.setResponseTime(SAMPLE_RESPONSE_TIME);
		Assert.assertEquals(SAMPLE_RESPONSE_TIME, this.record.getResponseTime());
	}
}
