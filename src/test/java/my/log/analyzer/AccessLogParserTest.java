package my.log.analyzer;
import java.io.File;
import java.net.URL;

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
	public void testProcessFile(){
		URL url = this.getClass().getResource("/sample_access_goodcase.log");
		this.logParser.processFile(url.getFile());
		AccessRecord record = this.logParser.getRecordMap().get(TestUtils.SAMPLE_TIME_STAM[0]);
		TestUtils.verifyRecord(record,2,0,TestUtils.SAMPLE_RESPONSE_TIME[0]+TestUtils.SAMPLE_RESPONSE_TIME[1],TestUtils.SAMPLE_RETURN_SIZE[0]+TestUtils.SAMPLE_RETURN_SIZE[1]);
	}
	
	/**
	 * Tests {@link AccessLogParser#processFile(String)} with an empty file path.
	 */
	@Test(expected=RecoverableException.class)
	public void testProcessFile_FilePathEmpty(){
		this.logParser.processFile("");
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
	public void testProcessFile_NoReturnSize(){
		URL url = this.getClass().getResource("/sample_access_no_returnSize.log");
		this.logParser.processFile(url.getFile());
		AccessRecord record = this.logParser.getRecordMap().get(TestUtils.SAMPLE_TIME_STAM[0]);
		TestUtils.verifyRecord(record,2,0,TestUtils.SAMPLE_RESPONSE_TIME[0]+TestUtils.SAMPLE_RESPONSE_TIME[1],TestUtils.SAMPLE_RETURN_SIZE[0]);
	}
	
	/**
	 * Test for {@link AccessLogParser#processFile(File)}.
	 * <ul>
	 * <li> Set a log file containing a access record without response time</li>
	 * <li> Call the method to parse the file</li>
	 * <li> verify that the analysis completed successfully and all information are correct</li>
	 * </ul>
	 * 
	 */
	@Test
	public void testProcessFile_NoResponseTime(){
		URL url = this.getClass().getResource("/sample_access_no_responseTime.log");
		this.logParser.processFile(url.getFile());
		AccessRecord record = this.logParser.getRecordMap().get(TestUtils.SAMPLE_TIME_STAM[0]);
		TestUtils.verifyRecord(record,2,0,TestUtils.SAMPLE_RESPONSE_TIME[1],TestUtils.SAMPLE_RETURN_SIZE[0]+TestUtils.SAMPLE_RETURN_SIZE[1]);
	}
	
}
