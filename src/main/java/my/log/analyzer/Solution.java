package my.log.analyzer;

/**
 * A java applicaiton which can read a given access log file and print the
 * successful request number per minute, error request number per minute, mean
 * response time per minute and the MBs sent per minutes to the standard output.
 * 
 * @author Jinge Dai
 *
 */
public class Solution {

	private static AccessLogParser parser = new AccessLogParser();
	private static UserInterface ui = new UserInterface(); 
	
	public static void main(String[] args) {

		String filePath = null;
		if (args.length == 0) {
			ui.showMessage("######## LogAnalyzer Start ########");
			ui.showMessage("Please give a correct path the log file to be analyze: ");
			filePath = ui.readUserInput();
		}else{
			filePath = args[0];
		}
		analzeFile(filePath);
		ui.showResult(parser);
		ui.showMessage("######## LogAnalyzer Complete ########");
	}
	
	/**
	 * Analyze the given file. If an {@link RecoverableException} happened, a message will notify the user to restart.
	 * @param filePath the file to be analyzed.
	 */
	private static void analzeFile(String filePath){
		try {
			parser.processFile(filePath);
		} catch (RecoverableException recEx) {
			ui.showMessage(recEx.getMessage()
					+ " Please check the README.md and try again.");
			String newFilePath = ui.readUserInput();
			analzeFile(newFilePath);
		} catch (Exception e) {
			ui.showMessage("Sorry! We have a technical problem now. The application is terminated.");
		}
	}



}
