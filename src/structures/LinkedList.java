package structures;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
    private Node<T> head, tail;
    private int size = 0;
    private LinkedList<T> instance;

    public LinkedList() {
        this.instance = this;
    }

    public void addLast(T element) {
        if (this.head == null) {
            this.head = new Node<>(element);
            return;
        }

        if (this.tail == null) {
            this.tail = new Node<>(element, this.head);
            this.head.setNextNode(this.tail);
            return;
        }

        Node<T> newNode = new Node<>(element, this.tail);
        this.tail.setNextNode(newNode);
        this.tail = newNode;
    }

    public void addFirst(T element) {
        if (this.head == null) {
            this.head = new Node<>(element);
            return;
        }

        Node<T> newNode = new Node<>(element, null, this.head);
        this.head.setPrevNode(newNode);
        this.head = newNode;
    }

    public void add(T element) {
        this.addLast(element);
    }

    public void add(T element, int index) {
        validateIndex(index);

        if (index == 0) {
            this.addFirst(element);
        } else if (index == this.size) {
            this.addLast(element);
        } else {
            Node<T> oldNode = getNode(index);
            Node<T> newNode = new Node<>(element, oldNode.getPrevNode(), oldNode);
            oldNode.setPrevNode(newNode);
        }
    }

    public T get(int index) {
        return getNode(index).getValue();
    }

    private Node<T> getNode(int index) {
        int counter = index;
        Node<T> seekedNode = this.head;
        while (counter > 0) {
            seekedNode = seekedNode.getNextNode();
            counter--;
        }

        return seekedNode;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> currentNode = instance.head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                T value = currentNode.getValue();
                currentNode = currentNode.getNextNode();
                return value;
            }
        };
    }

    private void validateIndex(int index) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException(
                    String.format("Index: %d; Size: %d", index, this.size)
            );
        }
    }
}
