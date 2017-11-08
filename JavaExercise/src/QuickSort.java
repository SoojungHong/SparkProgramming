import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by a613274 on 28.06.2017.
 */
public class QuickSort {

    public static void main(String[] args) {
        QuickSort qsort = new QuickSort();
        Integer[] inputArr = {2, 3, 4, 5, 1, 7};
        List<Integer> input = new ArrayList<Integer>();
        input = Arrays.asList(inputArr);

        qsort.quickSort(input);
    }

    public void quickSort(List<Integer> input) {

        int midIndex = (int)(input.size()/2);
        int leftArraySize = midIndex + 1;
        int rightArraySize = input.size() - leftArraySize;
        int midValue = input.get(midIndex); //pick index in the middle

        //using the mid value, divide the array into two sub arrays
        //int[] leftArray = new int[leftArraySize];
        //int[] rightArray = new int[rightArraySize];
        ArrayList<Integer> leftArray = new ArrayList<Integer>();
        ArrayList<Integer> rightArray = new ArrayList<Integer>();

        int leftArrayIndex = 0;
        int rightArrayIndex = midIndex + 1;
        for(int i = 0; i < leftArraySize; i++) {
            while(input.get(leftArrayIndex) < midValue) {
                leftArrayIndex++;
            }

            while(input.get(rightArrayIndex) > midValue) {
                rightArrayIndex--;
            }
        }

/*
        quickSort(leftArray.toArray()); //do quicksort on left array
        quickSort(rightArray.toArray()); //do quicksort on right array
        combineArray(leftArray, rightArray);
*/
    }

    public void combineArray(ArrayList<Integer> left, ArrayList<Integer> right) {
        left.addAll(right);
    }
}
