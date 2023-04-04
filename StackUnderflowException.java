/**
 * exception thrown pop method is called on an empty stack
 * @author Meba Tadesse
 *
 */
public class StackUnderflowException extends RuntimeException {
	public StackUnderflowException() {
		super("Pop method called an empty stack.");
	}

}
