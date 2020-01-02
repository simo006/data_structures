package structures;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class ArrayList<T> implements Iterable<T> {
    private Object[] arr;
    private int size = 0;
    private int initialCapacity;
    private ArrayList<T> instance;

    public ArrayList(int initialCapacity) {
        this.arr = new Object[initialCapacity];
        this.initialCapacity = initialCapacity;
        this.instance = this;
    }

    public ArrayList() {
        this(10);
    }

    public void add(T element) {
        this.add(element, this.size);
    }

    public void add(T element, int index) {
        validateIndex(index);

        //update array list size
        if (this.arr.length == this.size) {
            resize();
        }

        //shift elements
        System.arraycopy(this.arr, index, this.arr, index + 1, this.size - index);

        //add new element
        this.arr[index] = element;

        //update size
        this.size++;
    }

    public void addAll(ArrayList<T> array) {
        this.addAll(array, this.size);
    }

    public void addAll(ArrayList<T> array, int index) {
        validateIndex(index);

        //update array list size
        while (array.size() + this.size > this.arr.length) {
            resize();
        }

        //shift elements
        System.arraycopy(this.arr, index, this.arr, index + array.size(), this.size - index);

        //add new elements
        System.arraycopy(array.toArray(), 0, this.arr, index, array.size);

        //update size
        this.size += array.size();
    }

    public T get(int index) {
        validateIndex(index);

        return convertInstanceOfObject(this.arr[index]);
    }

    public int indexOf(T element) {
        for (int i = 0; i < this.size; i++) {
            if (element == this.arr[i]) {
                return i;
            }
        }

        return -1;
    }

    public void set(T element, int index) {
        validateIndex(index);

        this.arr[index] = element;
    }

    public T remove(int index) {
        validateIndex(index);

        this.size--;

        T removedElement = this.get(index);
        System.arraycopy(this.arr, index + 1, this.arr, index, this.size - index);

        return removedElement;
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        this.arr = convertInstanceOfObject(new Object[this.initialCapacity]);
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public T[] toArray() {
        T[] array = convertInstanceOfObject(new Object[this.size]);
        System.arraycopy(this.arr, 0, array, 0, this.size);
        return array;
    }

    public ArrayList<T> subList(int fromIndex, int toIndex) {
        ArrayList<T> newArray = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            newArray.add(convertInstanceOfObject(this.arr[i]));
        }

        return newArray;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("{");
        for (int i = 0; i < this.size; i++) {
            string.append(" ").append(this.arr[i]);

            if (i < this.size() - 1) {
                string.append(",");
            }

            string.append(" ");
        }
        string.append("}");

        return string.toString();
    }

    private void resize() {
        Object[] newArr = new Object[this.size * 2];
        System.arraycopy(this.arr, 0, newArr, 0, this.size);

        this.arr = newArr;
    }

    private void validateIndex(int index) {
        if (index > this.size || index < 0) {
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

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index != instance.size();
            }

            @Override
            public T next() {
                return instance.get(index++);
            }
        };
    }
}
