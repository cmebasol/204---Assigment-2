import java.util.ArrayList;
/**
 * Queue data structure
 * @author Meba Tadesse
 *
 * @param <T> a generic type used in this class
 */
public class MyQueue<T> implements QueueInterface<T>{

	private ArrayList<T> que; 
	private int size;
	public MyQueue(int count) {
		que = new ArrayList<T>();
		size = count;
	}
	@Override
	public boolean isEmpty() {
		
		// TODO Auto-generated method stub
		return que.size() == 0;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return size == que.size();
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}
		
		// TODO Auto-generated method stub
		return  que.remove(0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return que.size();
	}

	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if (que.size() == size) {
			throw new QueueOverflowException();
		}
		// TODO Auto-generated method stub
		que.add(e);
		return true;
	}

	@Override
	public String toString(String delimiter) {
		String  info = "";
		for (T each: que) {
			
			info += ( each.toString() +  delimiter);
		}
		
		// TODO Auto-generated method stub
		return info.substring(0,info.length()-1);
	}

	@Override 
	public String toString() {
		String  info = "";
		for (T each: que) {
			info += ( each  );
		}
		// TODO Auto-generated method stub
		return info;
	}
	@Override
	public void fill(ArrayList<T> list) {
		for (T each: list) {
			try {
				this.enqueue(each);
			} catch (QueueOverflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		
	}
	

}
