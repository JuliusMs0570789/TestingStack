package stack;

public interface Stack<E> {

	public boolean isEmpty();

	public E top() throws Underflow;

	public void push(E element);

	public E pop() throws Underflow;

}