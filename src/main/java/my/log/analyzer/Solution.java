package my.log.analyzer;
import java.io.File;

/**
 * A java applicaiton which can read a given access log file and print the successful request number per minute, error request number per minute, mean response time per minute and the MBs sent per minutes to the standard output.
 * @author Jinge Dai
 *
 */
public class Solution {
	
	public static void main(String[] args){
		
		AccessLogParser parser = new AccessLogParser();
		File file = new File(args[0]);
		parser.processFile(file);
		parser.showResult();
	}

}
