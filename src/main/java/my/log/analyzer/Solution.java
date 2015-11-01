package my.log.analyzer;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * A java applicaiton which can read a given access log file and print the successful request number per minute, error request number per minute, mean response time per minute and the MBs sent per minutes to the standard output.
 * @author Jinge Dai
 *
 */
public class Solution {
	
	/** The date format of the displayed time stamp */
	private static final String DISPLAY_DATE_FORMAT = "dd/MMM/yyyy:hh:mm Z";
	
	public static void main(String[] args){
		
		AccessLogParser parser = new AccessLogParser();
		File file = new File(args[0]);
		System.out.println("Procesing log file... Please wait.");
		parser.processFile(file);
		Solution.showResult(parser);
	}
	
	/**
	 * Shows the final results in time order:
	 * <ul>
	 * <li>Number of successful request per minute</li>
	 * <li>Number of error request per minute</li>
	 * <li>Mean response time per minute</li>
	 * <li>MBs sent per minute</li>
	 * </ul>
	 */
	private static void showResult(AccessLogParser parser) {
		Map<Date, AccessRecord> recordMap =parser.getRecordMap();
		for(Date timestamp: parser.sortMapKeys()){
			DateFormat df = new SimpleDateFormat(DISPLAY_DATE_FORMAT);
			df.setTimeZone(TimeZone.getTimeZone("UTC+0100"));
			AccessRecord record = recordMap.get(timestamp);
			System.out.println(new SimpleDateFormat(DISPLAY_DATE_FORMAT)
					.format(timestamp));
			System.out.println("Number of successful request per minute: "
					+ record.getNumSuccess());
			System.out.println("Number of error request per minute: "
					+ record.getNumError());
			System.out.println("Mean response time per minute: "
					+ record.getResponseTime());
			System.out
					.println("MBs sent per minute: " + record.getReturnSize());
		}
	}

}
