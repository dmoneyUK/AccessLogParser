package my.log.analyzer;
import java.io.File;
import java.net.URL;

import my.log.analyzer.AccessLogParser;

import org.junit.Before;
import org.junit.Test;


/**
 * Test class for {@link AccessLogParser}.
 * 
 * @author Jiinge Dai
 *
 */
public class AccessLogParserTest {

	/** The {@link AccessLogParser} undert test */
	AccessLogParser logParser;
	
	/**
	 * Runs before each test.
	 */
	@Before
	public void setUp(){
		logParser = new AccessLogParser();
	}
	
	/**
	 * Test for {@link AccessLogParser#processFile(File)}.
	 */
	@Test
	public void readFile(){
		URL url = this.getClass().getResource("/sample_access.log");
		File file = new File(url.getFile());
		this.logParser.processFile(file);
	}
	
	
	
}
