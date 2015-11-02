package my.log.analyzer;
import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.text.DateFormatter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Test class for {@link AccessLogParser}.
 * @author Jiinge Dai
 *
 */
public class AccessLogParserTest {

	/** The {@link AccessLogParser} under test */
	private AccessLogParser logParser;

	/** Some samples of return size in bytes */
	private final double SAMPLE_RETURN_SIZE[] = {1,2};
	
	/** Some samples of the response time */
	private final long SAMPLE_RESPONSE_TIME[]={1000,2000};
	
	/** The sample of time stamp.*/
	@SuppressWarnings("deprecation")
	private  Date SAMPLE_TIME_STAMP = new Date(115,2,30,5,4);
	
	
	/**
	 * Runs before each test.
	 */
	@Before
	public void setUp(){
		logParser = new AccessLogParser();
	}
	
	/**
	 * Test for {@link AccessLogParser#processFile(File)}.
	 * <ul>
	 * <li> Set a log file with all information</li>
	 * <li> Call the method to parse the file</li>
	 * <li> verify that the analysis completed successfully and all information are correct</li>
	 * </uL>
	 */
	@Test
	public void readFile(){
		URL url = this.getClass().getResource("/sample_access_goodcase.log");
		File file = new File(url.getFile());
		this.logParser.processFile(file);
		verifyRecords(2,0,SAMPLE_RESPONSE_TIME[0]+SAMPLE_RESPONSE_TIME[1],SAMPLE_RETURN_SIZE[0]+SAMPLE_RETURN_SIZE[1]);
	}
	
	/**
	 * Test for {@link AccessLogParser#processFile(File)}.
	 * <ul>
	 * <li> Set a log file containing a access record without return size</li>
	 * <li> Call the method to parse the file</li>
	 * <li> verify that the analysis completed successfully and all information are correct</li>
	 * </ul>
	 * 
	 */
	@Test
	public void readFile_NoReturnSize(){
		URL url = this.getClass().getResource("/sample_access_no_returnsize.log");
		File file = new File(url.getFile());
		this.logParser.processFile(file);
		verifyRecords(2,0,SAMPLE_RESPONSE_TIME[0]+SAMPLE_RESPONSE_TIME[1],SAMPLE_RETURN_SIZE[0]);
	}
	

	/**
	 * Verifies the records.
	 */
	private void verifyRecords(int numSuccess, int numError, long responseTime, double returnSize){
		AccessRecord record = this.logParser.getRecordMap().get(SAMPLE_TIME_STAMP);
		Assert.assertNotNull(record);
		Assert.assertEquals(numSuccess, record.getNumSuccess());
		Assert.assertEquals(numError, record.getNumError());
		Assert.assertEquals(responseTime, record.getResponseTime());
		Assert.assertEquals(returnSize, record.getReturnSize(),0);	
	}
	
	
	
}
