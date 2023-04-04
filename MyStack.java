import java.util.ArrayList;
/**
 * stack data structure
 * @author Meba Tadesse
 *
 * @param <T> a generic type used in this class
 */
public class  MyStack <T> implements StackInterface<T>{

	private ArrayList<T> list;
	private int size;
	public MyStack(int num) {
		list = new  ArrayList<T>(num);
		this.size = num;}
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return list.size() == 0;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return list.size() == size;
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) 
			throw new StackUnderflowException();
		
		// TODO Auto-generated method stub
		return list.remove(list.size() -1);
	}

	@Override
	public T top() throws StackUnderflowException {

		
		// TODO Auto-generated method stub
		return list.get(list.size() - 1);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public boolean push(T e) throws StackOverflowException {
		if (list.size() == size) 
			throw new StackOverflowException();
		list.add(e);
		
		return true;
	}

	@Override
	public String toString(String delimiter) {
		String info = "";
		Double j = 4.0;
		
		// adding each element in the list to the string to be returned
		for (T each : list) {
			if(each.getClass().equals(j.getClass())) {
				info += Integer.getInteger(each.toString()) + delimiter;

			}
			else
				info += (each.toString()+ delimiter);
		}
		return info.substring(0, info.length()-1);
	}
	@Override
	public String toString() {
		String info = "";
		Double j = 4.0;

		// adding each element in the list to the string to be returned
		for (T each : list) {
			if(each.getClass().equals(j.getClass())) {
				info += each.toString().substring(0,1) ;

			}
			else
				info += (each.toString());
		}
		return info;
	}

	@Override
	public void fill(ArrayList<T> list) {
		for (T each : list) {
			try {
				push(each);
			} catch (StackOverflowException e) {
				e.printStackTrace();
			}
		}
		
	}


}
