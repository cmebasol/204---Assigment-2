/**
 * Exception thrown when dequeue method is called on an empty queue
 * @author Meba Tadesse
 *
 */
public class QueueUnderflowException extends RuntimeException {
	public QueueUnderflowException() {
		super("You are trying to dequeue an empty queue.");
	}

}
