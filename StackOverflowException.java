/**
 * Exception thrown when a stack is full
 * @author Meba Tadesse
 * 
 *
 */
public class StackOverflowException extends RuntimeException {
	public StackOverflowException() {
		super("You are trying to add on a full stack.");
	}
}
