package structures;

class Node<T> {
    private T value;
    private Node<T> nextNode, prevNode;

    public Node(T value, Node<T> prevNode, Node<T> nextNode) {
        this.value = value;
        this.prevNode = prevNode;
        this.nextNode = nextNode;
    }

    public Node(T value, Node<T> prevNode) {
        this(value, prevNode, null);
    }

    public Node(T value) {
        this(value, null, null);
    }

    public T getValue() {
        return value;
    }

    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }

    public void setPrevNode(Node<T> prevNode) {
        this.prevNode = prevNode;
    }

    public Node<T> getNextNode() {
        return nextNode;
    }

    public Node<T> getPrevNode() {
        return prevNode;
    }
}
