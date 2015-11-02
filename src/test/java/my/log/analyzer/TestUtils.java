package my.log.analyzer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

/**
 * An utility class for tests 
 * @author Jinge Dai
 *
 */
public class TestUtils {
	
	
	/** A samples of successful request numbers */
	public final static int[] SAMPLE_SUCCESS_NUM = {10,20};
	
	/** A samples of error request numbers */
	public final static int[] SAMPLE_ERROR_NUM = {1,2};

	/** Some samples of return size in bytes */
	public static final double SAMPLE_RETURN_SIZE[] = {1.0,2.0};
	
	/** Some samples of the response times */
	public static long SAMPLE_RESPONSE_TIME[]={1000,2000};
	
	/** The sample of time stamps.*/
	@SuppressWarnings("deprecation")
	public static  Date[] SAMPLE_TIME_STAM = { new Date(115,2,30,5,4),new Date(115,2,35,5,4) };
	
	/** A sample of message */
	public static String SAMPLE_MESSAGE = "Test";
	
	/**
	 * 
	 * @return A sample {@link AccessRecord}.
	 */
	public static Map<Date, AccessRecord> createSampleAccessRecordMap(){
		Map<Date, AccessRecord> recordMap = new HashMap<Date, AccessRecord>();
		AccessRecord recordA = new AccessRecord(SAMPLE_SUCCESS_NUM[0], SAMPLE_ERROR_NUM[0], SAMPLE_RETURN_SIZE[0], SAMPLE_RESPONSE_TIME[0]);
		AccessRecord recordB = new AccessRecord(SAMPLE_SUCCESS_NUM[1], SAMPLE_ERROR_NUM[1], SAMPLE_RETURN_SIZE[1], SAMPLE_RESPONSE_TIME[1]);
		recordMap.put(SAMPLE_TIME_STAM[0], recordA);
		recordMap.put(SAMPLE_TIME_STAM[1], recordB);
		return recordMap;
	
	}
	
	/**
	 * Verifies the given records containing the expected information.
	 */
	public static void verifyRecord(AccessRecord record, int numSuccess, int numError, long responseTime, double returnSize){
		
		Assert.assertNotNull(record);
		Assert.assertEquals(numSuccess, record.getNumSuccess());
		Assert.assertEquals(numError, record.getNumError());
		Assert.assertEquals(responseTime, record.getResponseTime());
		Assert.assertEquals(returnSize, record.getReturnSize(),0);	
	}
	

}
