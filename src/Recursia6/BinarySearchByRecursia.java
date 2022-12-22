package Recursia6;

import java.util.Collections;

public class BinarySearchByRecursia {
    static int[] array;
    static int nItems;
    public BinarySearchByRecursia(int size) {
        nItems = 0;
        array = new int[size];
    }

    public static int findRecursia(int target, int minEdge, int maxEdge) {
        int middle = (maxEdge - minEdge) / 2;
        if(minEdge > maxEdge) return nItems;
        if(array[middle] == target) return middle;
        else {
            if (target < array[middle])
                findRecursia(target, minEdge, middle - 1);
            else
                findRecursia(target, middle + 1, maxEdge);
        }
        return middle;
    }

    public static int find(int target) {
        return findRecursia(target, 0, nItems);
    }

    public static void main(String[] args) {
        BinarySearchByRecursia bi = new BinarySearchByRecursia(10);
    }

}
