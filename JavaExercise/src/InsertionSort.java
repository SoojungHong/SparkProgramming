import java.util.Arrays;

import static sun.misc.Version.println;

/**
 * Created by a613274 on 27.06.2017.
 */
public class InsertionSort {

    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.insertionSort();
    }

    public void insertionSort() { //insertion sort is sort only until current index position
        //get input array

        //find the min and replace it with the element in the front

        //move the current position (pointer, index) to next and search the next smallest value
        int[] input = {1, 3, 5, 7, 9, 2, 4};
        int length = input.length;
        int[] sortedOutput = new int[length];

        sortedOutput[0] = input[0]; //initialize
        for(int i = 1; i < length; i++) {

        }

        //print the sorted array
        for(int k = 0; k < length; k++) {
            System.out.println(sortedOutput[k]);
        }

    }



}
