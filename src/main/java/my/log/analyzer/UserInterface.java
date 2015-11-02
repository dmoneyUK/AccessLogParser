package my.log.analyzer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.TimeZone;

/**
 * A simple UI based-on standard input/output in order to interact with users.
 * 
 * @author Jinge Dai
 *
 */
public class UserInterface {

	/** The date format of the displayed time stamp */
	private static final String DISPLAY_DATE_FORMAT = "dd/MMM/yyyy:hh:mm Z";

	/**
	 * Constructor.
	 */
	public UserInterface() {
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
	public void showResult(AccessLogParser parser) {
		Map<Date, AccessRecord> recordMap = parser.getRecordMap();
		for (Date timestamp : parser.sortMapKeys()) {
			DateFormat df = new SimpleDateFormat(DISPLAY_DATE_FORMAT);
			df.setTimeZone(TimeZone.getTimeZone("UTC+0100"));
			AccessRecord record = recordMap.get(timestamp);
			showMessage(new SimpleDateFormat(DISPLAY_DATE_FORMAT)
					.format(timestamp));
			showMessage("Number of successful request per minute: "
					+ record.getNumSuccess());
			showMessage("Number of error request per minute: "
					+ record.getNumError());
			showMessage("Mean response time per minute: "
					+ record.getResponseTime());
			showMessage("MBs sent per minute: " + record.getReturnSize());
		}
	}

	/**
	 * Reads from the standard input. If input is "q" or "quite", the
	 * application will be terminated.
	 * 
	 * @return the input from users
	 */
	public String readUserInput() {
		
		showMessage("(Enter \"q\" or \"quit\"to exit. IgnoreCase. )");
		String input = "";
		Scanner sc = new Scanner(System.in);
		if (sc.hasNext()) {
			input = sc.nextLine();
			if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
				showMessage("Thanks! Goodbye!");
				System.exit(0);
			}
		} 
		return input;
	}

	/**
	 * Shows the give message to the standard output.
	 * 
	 * @param message the content to be displayed
	 */
	public void showMessage(String message) {
		System.out.println(message);
	}
}
