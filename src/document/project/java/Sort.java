package document.project.java;

import java.util.Arrays;

public class Sort {

    private Sort(){}

    @SuppressWarnings("unchecked")
    public static void heapSort(Comparable[] array) throws ClassCastException{
        recursiveHeapSort(array, array.length);
    }

    private static void recursiveHeapSort (Comparable[] array, int length){
        if(length > 1){
            Comparable element;
            heapify(array, length);
            length--;
            element = array[0];
            array[0] = array[length];
            array[length] = element;
            recursiveHeapSort(array, length);
        }
    }

    private static void heapify (Comparable[] array, int length){
        int parent = length/2;
        int child;
        Comparable element;
        while (parent > 0){
            parent --;
            element = array[parent];
            child = parent * 2 + 1;
            while (child < length) {
                if ((child + 1 < length) && (array[child + 1].compareTo(array[child]) > 0))
                    child++;
                if (array[child].compareTo(element) > 0) {
                    array[parent] = array[child];
                    parent = child;
                    child = parent * 2 + 1;
                } else {
                    break;
                }
            }
            array[parent] = element;
        }
    }

}
