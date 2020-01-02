package structures;

import org.jetbrains.annotations.NotNull;
import utils.Node;

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
        } else if (this.tail == null) {
            this.tail = new Node<>(element, this.head);
            this.head.setNextNode(this.tail);
        } else {
            Node<T> newNode = new Node<>(element, this.tail);
            this.tail.setNextNode(newNode);
            this.tail = newNode;
        }

        this.size++;
    }

    public void addFirst(T element) {
        if (this.head == null) {
            this.head = new Node<>(element);
        } else {
            Node<T> newNode = new Node<>(element, null, this.head);
            this.head.setPrevNode(newNode);
            this.head = newNode;
        }

        this.size++;
    }

    public void add(T element) {
        this.addLast(element);
    }

    public void add(T element, int index) {
        this.validateIndex(index);

        if (index == 0) {
            this.addFirst(element);
        } else if (index == this.size - 1) {
            this.addLast(element);
        } else {
            Node<T> oldNode = getNode(index);
            Node<T> newNode = new Node<>(element, oldNode.getPrevNode(), oldNode);
            oldNode.getPrevNode().setNextNode(newNode);
            oldNode.setPrevNode(newNode);
        }

        this.size++;
    }

    public T get(int index) {
        return this.getNode(index).getValue();
    }

    public int indexOf(T element) {
        int index = -1;

        Node<T> currentNode = this.head;
        for (int i = 0; i < this.size; i++) {
            if (currentNode.getValue() == element) {
                index = i;
                break;
            }

            currentNode = currentNode.getNextNode();
        }

        return index;
    }

    public void set(T element, int index) {
        this.validateIndex(index);

        this.getNode(index).setValue(element);
    }

    public T remove(int index) {
        this.validateIndex(index);

        T removedElement;
        if (index == 0) {
            removedElement = this.head.getValue();
            this.head = this.head.getNextNode();
            if (this.head == this.tail) {
                this.tail = null;
            }
        } else if (index == this.size - 1) {
            removedElement = this.tail.getValue();
            this.tail = this.tail.getPrevNode();
        } else {
            Node<T> removedNode = this.getNode(index);
            removedElement = removedNode.getValue();

            removedNode.getPrevNode().setNextNode(removedNode.getNextNode());
            removedNode.getNextNode().setPrevNode(removedNode.getPrevNode());
        }

        this.size--;

        return removedElement;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void clear() {
        this.head = this.tail = null;
        this.size = 0;
    }

    public T[] toArray() {
        Node<T> currentElement = this.head;
        Object[] array = new Object[this.size];
        for (int i = 0; i < array.length; i++) {
            array[i] = currentElement.getValue();
            currentElement = currentElement.getNextNode();
        }

        return convertInstanceOfObject(array);
    }

    public LinkedList<T> sublist(int fromIndex, int toIndex) {
        this.validateIndex(fromIndex);
        this.validateIndex(toIndex);

        LinkedList<T> newList = new LinkedList<>();
        Node<T> currentNode = this.getNode(fromIndex);

        for (int i = fromIndex; i < toIndex; i++) {
            newList.add(currentNode.getValue());
            currentNode = currentNode.getNextNode();
        }

        return newList;
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

    private void validateIndex(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException(
                    String.format("Index: %d; Size: %d", index, this.size)
            );
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T convertInstanceOfObject(Object obj) {
        try {
            return (T) obj;
        } catch (ClassCastException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        Node<T> currentElement = this.head;
        StringBuilder string = new StringBuilder();
        string.append("{");
        for (int i = 0; i < this.size; i++) {
            string.append(" ").append(currentElement.getValue());

            if (i < this.size - 1) {
                currentElement = currentElement.getNextNode();
                string.append(",");
            }

            string.append(" ");
        }
        string.append("}");

        return string.toString();
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
}
