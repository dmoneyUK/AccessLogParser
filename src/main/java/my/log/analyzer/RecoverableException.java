package my.log.analyzer;

/**
 * An exception that could be fixed without terminate the application, e.g. reset the file name.
 * @author Jinge Dai
 *
 */
public class RecoverableException extends RuntimeException{

    /**
	 * Serialization id.
	 */
	private static final long serialVersionUID = 362551976414571275L;

	public RecoverableException(String message, Throwable cause) {
        super(message, cause);
    }
}
