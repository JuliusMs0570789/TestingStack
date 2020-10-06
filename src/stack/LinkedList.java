package stack;

import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {

    Node<E> first;

    public void addFirst(E data) {
        if (first == null) {
            first = new Node(data);
        } else {
            first = new Node(data, first);
        }
    }

    public E getFirst() throws Underflow {
        try {
            return first.data;
        } catch (NullPointerException a) {
            throw new Underflow();
        }
    }

    public E removeFirst() throws Underflow {
        try {
            Node<E> oldNode = first;
            first = first.next;
            return oldNode.data;
        } catch (NullPointerException a) {
            throw new Underflow();
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Node<E> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E data = current.data;
                    current = current.next;
                    return data;
                }
                return null;
            }
        };
    }
}

class Node<E> {

    E data;
    Node<E> next;

    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }

    public Node(E data) {
        this.data = data;
    }
}