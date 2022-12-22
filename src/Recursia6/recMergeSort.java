package Recursia6;
import java.util.Arrays;

import static Recursia6.mergeApp.merge;

public class recMergeSort {

    private int[] theArray;
    private int nItems;
    public recMergeSort(int max) {
        theArray = new int[max];
        nItems = 0;
    }
    public void insert(int value) {
        theArray[nItems++] = value;
    }

    public void mergeSort() {
        int[] workSpace = new int[]{2,1,6,3,4,9,6,2,100,99};
        recMergeSort(workSpace, 0, workSpace.length - 1);
        System.out.println(Arrays.toString(workSpace));
    }

    private void recMergeSort(int[] workSpace, int lowerBond, int upperBound) {
        if(lowerBond == upperBound)
            return;
        else {
            int mid = (upperBound + lowerBond) / 2;
            recMergeSort(workSpace, lowerBond, mid);
            recMergeSort(workSpace,mid + 1, upperBound);
            merge(workSpace, lowerBond, mid + 1, upperBound);
        }
    }

    public void merge(int[] workSpace, int lowPtr, int highPtr, int upperBound) {
        int j = 0;
        int lowerBound = lowPtr;
        int mid = highPtr - 1;
        int n = upperBound - lowerBound + 1;
        while(lowPtr <= mid && highPtr <= upperBound) {
            if (theArray[lowPtr] < theArray[highPtr])
                workSpace[j++] = theArray[lowPtr++];
            else
                workSpace[j++] = theArray[highPtr++];
        }
            while(lowPtr <= mid)
                workSpace[j++] = theArray[lowPtr++];

            while(highPtr <= upperBound)
                workSpace[j++] = theArray[highPtr++];

            for(j = 0; j < n; j++)
                theArray[lowerBound + j] = workSpace[j];
    }

    public static void main(String[] args) {
        recMergeSort recMergeSort = new recMergeSort(10);
        recMergeSort.insert(-4);
        recMergeSort.insert(0);
        recMergeSort.insert(7);
        recMergeSort.insert(57);
        recMergeSort.insert(56);
        recMergeSort.insert(78);
        recMergeSort.insert(90);
        recMergeSort.mergeSort();
    }
}
