package stack;

import java.util.Iterator;


public class LinkedListStack<E> implements Stack<E> {

	LinkedList<E> list = new LinkedList<>();

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public E top() throws Underflow {
		return list.getFirst();
	}

	@Override
	public void push(E element) {
		list.addFirst(element);
	}

	@Override
	public E pop() throws Underflow {
		return list.removeFirst();

	}

	@Override
	public String toString() {
		String s = "";
		for (Iterator i = list.iterator(); i.hasNext(); ) {
			s = s + i.next();
			if (i.hasNext())
				s = s + ", ";
		}
		return s + "";
	}

	public int size() {
		int count = 0;
		while (!isEmpty()) {
			count++;
		}
		return count;
	}

}