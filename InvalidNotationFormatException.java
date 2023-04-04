/**
 * Exception thrown if there is an invalid notation
 * @author Meba Tadesse
 *
 */
public class InvalidNotationFormatException extends RuntimeException {
	public InvalidNotationFormatException() {
		super("the syntax is invalid");
	}
}
