import document.project.java.Sort;

import java.util.Arrays;

public class heapSortTest {
    public static void main (String[] args) {
        Integer[] array = {3, 5, 5, 2, 1, 7, 6, 8};
        System.out.println(Arrays.toString(array));
        Sort.heapSort(array);
        System.out.println(Arrays.toString(array));
    }
}
