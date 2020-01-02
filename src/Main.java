import structures.ArrayList;
import structures.LinkedList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("A");
//        arrayList.add("B");
//        arrayList.clear();
//        arrayList.add("C");
//        arrayList.add("D");
//        arrayList.add("E");
//        arrayList.remove(1);
//        System.out.println();
//        System.out.println(arrayList);
//        System.out.println(Arrays.toString(arrayList.toArray()));

        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);
        linkedList.add(121, 4);
        for (int element : linkedList) {
            System.out.println(element);
        }
    }
}
