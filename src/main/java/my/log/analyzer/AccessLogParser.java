package my.log.analyzer;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * A process to read an access log file and analyze the access records per minute.
 * @author Jinge Dai
 *
 */
public class AccessLogParser {
	
	/** The logger. */
	private static Logger LOGGER = LoggerFactory.getLogger(AccessLogParser.class);

	/**
	 * A map containing the access information of every minute. The key is the time stamp in 
	 * minute level and the value is a {@link AccessRecord}.
	 */
	private Map<Date, AccessRecord> recordMap;

	/** The format of a good http response status 2xx */
	private static final String GOOD_HTTP_RESPONSE_STATUS_FORMAT = "^2\\d\\d";

	/** The string pattern of the time stamp in the log. */
	private static final Pattern TIMESTAMP_PATTERN = Pattern
			.compile("\\d{2}\\/\\w{3}\\/\\d{4}\\:\\d{2}\\:\\d{2}\\:\\d{2} \\+\\d{4}");
	
	/** The date format of the access time stamp */
	private static final String ACCESS_DATE_FORMAT = "dd/MMM/yyyy:hh:mm:ss Z";

	/** The first position of the SECOND section in the time stamp */
	private static int INDEX_SECONED = 18;

	/**
	 * Reads a log file line by line and extract the access information.
	 * 
	 * @param filePath the file to read from.
	 */
	public void processFile(String filePath) {
		File file = null;
		try {
			file = new File(filePath);
			System.out.println("Procesing log file... Please wait.");
		
			this.recordMap = new HashMap<Date, AccessRecord>();
			String line = null;

			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				parseLine(line);
			}

		} catch (FileNotFoundException e) {
			LOGGER.error("Error happened when reading file: {}", file.getName());
			throw new RecoverableException("Error happened when reading file: "+file.getName()+".", e);
		}
	}
	
	/**
	 * Gets the {@link #recordMap} conataining all the access records.
	 * @return {@link #recordMap} contaning the access records.
	 */
	public Map<Date, AccessRecord> getRecordMap(){
		return this.recordMap;
		
	}

	/**
	 * Parses a line to analyze the time stamp, request status, the return size
	 * and the response time.
	 * 
	 * @param line the line to be parsed
	 */
	private void parseLine(String line) {

		// Read the time stamp and initialize a record array if it is not
		// existed
		Date timestamp = readTimestampInMinuteFromLine(line);
		AccessRecord record = this.recordMap.get(timestamp);
		if (record == null) {
			record = new AccessRecord(0, 0, 0, 0);
		}

		String[] lineComponets = line.split(" ");
		int length = lineComponets.length;
		
		String responseStatus = lineComponets[length - 3];

		if (responseStatus.matches(GOOD_HTTP_RESPONSE_STATUS_FORMAT)) {
			record.setNumSuccess(record.getNumSuccess() + 1);
		} else {
			record.setNumError(record.getNumSuccess() + 1);
		}

		String returnSizeString = lineComponets[length - 2];
		try {
			double returnSize = Double.valueOf(returnSizeString) / 1024 / 1024;
			record.setReturnSize(record.getReturnSize() + returnSize);
		} catch (NumberFormatException e) {
			LOGGER.debug("Cannot conver the retrun size [{}].", returnSizeString);
		}

		String responseTimeString =lineComponets[length - 1];
		try{
			long responseTime = Integer.valueOf(responseTimeString);
			record.setResponseTime(record.getResponseTime() + responseTime);
		} catch (NumberFormatException e) {
			LOGGER.warn("Cannot conver the response time [{}].", responseTimeString);
		}
		this.recordMap.put(timestamp, record);

	}

	/**
	 * Reads a time stamp in the minute level from a line.
	 * 
	 * @param line
	 *            the line to read from
	 * @return the {@link Date} representing the time stamp in minute level
	 */
	private Date readTimestampInMinuteFromLine(String line) {

		// Find the time stamp from the line and ignore the Seconds section
		Matcher m = TIMESTAMP_PATTERN.matcher(line);
		String timestamp = null;
		while (m.find()) {
			timestamp = m.group();
			timestamp = timestamp.substring(0, INDEX_SECONED)+"00"+timestamp.substring(INDEX_SECONED+2);
		}

		Date date = null;
		try {
			DateFormat df = new SimpleDateFormat(ACCESS_DATE_FORMAT);
			date = df.parse(timestamp);
		} catch (ParseException e) {
			LOGGER.error("Error when parsing time stamp: [{}]",timestamp);
		}
		return date;
	}
	
	/**
	 * Sorts the keys of the {@link #recordMap} in time order.
	 * @return the sorted keys
	 */
	public List<Date> sortMapKeys(){
		List<Date> keyList = new ArrayList<Date>(this.recordMap.keySet());
		Collections.sort(keyList);
		return keyList;
	}

}
