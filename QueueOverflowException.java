/**
 * Exception thrown when a queqe is full
 * @author mebatadesse
 *
 */
public class QueueOverflowException extends RuntimeException {
	public QueueOverflowException() {
		super("The queue is full ");
	}

}
