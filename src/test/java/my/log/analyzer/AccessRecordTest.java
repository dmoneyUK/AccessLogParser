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

	/** The {@link AccessRecord} under test. */
	private AccessRecord record;
	
	/**
	 * Test for {@link AccessRecord#AccessRecord(int, int, double, long)}.
	 */
	@Test
	public void testAccessRecord(){
		this.record = new AccessRecord(TestUtils.SAMPLE_SUCCESS_NUM[0], TestUtils.SAMPLE_ERROR_NUM[0], TestUtils.SAMPLE_RETURN_SIZE[0], TestUtils.SAMPLE_RESPONSE_TIME[0]);
		Assert.assertEquals(TestUtils.SAMPLE_SUCCESS_NUM[0], this.record.getNumSuccess());
		Assert.assertEquals(TestUtils.SAMPLE_ERROR_NUM[0], this.record.getNumError());
		Assert.assertEquals(TestUtils.SAMPLE_RETURN_SIZE[0], this.record.getReturnSize(),0);
		Assert.assertEquals(TestUtils.SAMPLE_RESPONSE_TIME[0], this.record.getResponseTime());
	}
	
	/**
	 * Test for {@link AccessRecord#setNumSuccess(int)} and {@link AccessRecord#getNumSuccess()}.
	 */
	@Test
	public void testSetNumSuccess(){
		this.record = new AccessRecord(0,0,0,0);
		this.record.setNumSuccess(TestUtils.SAMPLE_SUCCESS_NUM[0]);
		Assert.assertEquals(TestUtils.SAMPLE_SUCCESS_NUM[0], this.record.getNumSuccess());
	}
	
	/**
	 * Test for {@link AccessRecord#setNumError(int)} and {@link AccessRecord#getNumError()}.
	 */
	@Test
	public void testSetNumError(){
		this.record = new AccessRecord(0,0,0,0);
		this.record.setNumError(TestUtils.SAMPLE_ERROR_NUM[0]);
		Assert.assertEquals(TestUtils.SAMPLE_ERROR_NUM[0], this.record.getNumError());
	}
	
	/**
	 * Test for {@link AccessRecord#setReturnSize(double)} and {@link AccessRecord#getReturnSize()}.
	 */
	@Test
	public void testSetReturnSize(){
		this.record = new AccessRecord(0,0,0,0);
		this.record.setReturnSize(TestUtils.SAMPLE_RETURN_SIZE[0]);
		Assert.assertEquals(TestUtils.SAMPLE_RETURN_SIZE[0], this.record.getReturnSize(),0);
	}
	
	/**
	 * Test for {@link AccessRecord#setResponseTime(long)} and {@link AccessRecord#getResponseTime()}.
	 */
	@Test
	public void testSetResponseTime(){
		this.record = new AccessRecord(0,0,0,0);
		this.record.setResponseTime(TestUtils.SAMPLE_RESPONSE_TIME[0]);
		Assert.assertEquals(TestUtils.SAMPLE_RESPONSE_TIME[0], this.record.getResponseTime());
	}
}
