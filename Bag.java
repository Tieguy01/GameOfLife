import java.util.Iterator;

public class Bag<T> implements Iterable<T> {
    
    private class Node {
        T data;
        Node next;
    }

    private Node head;
    int n;

    public void add(T obj) {
        Node newNode = new Node();
        newNode.data = obj;
        newNode.next = head;
        head = newNode;
        n++;
    }

    public int size() {
        return n;
    }

    public Iterator<T> iterator() {
        return new BagIterator();
    }

    public class BagIterator implements Iterator<T> {
        private Node currNode = head;

        public boolean hasNext() {
            return currNode != null;
        }

        public T next() {
            T obj = currNode.data;
            currNode = currNode.next;
            return obj;
        }
    }
}
