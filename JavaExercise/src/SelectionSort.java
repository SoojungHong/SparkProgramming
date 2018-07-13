/**
 * Created by a613274 on 27.06.2017.
 */
public class SelectionSort {

    public static void main(String[] args) {
        SelectionSort sort = new SelectionSort();
        sort.selectionSort();
    }

    public void selectionSort() { // this is actually selection sort. selection sort is look for all elements, insertion sort is sort only until current index position
        //get input array

        //find the min and replace it with the element in the front

        //move the current position (pointer, index) to next and search the next smallest value
        int[] input = {1, 3, 5, 7, 9, 2, 4};
        int length = input.length;
        int[] sortedOutput = new int[length];

        for(int i = 0; i < length; i++) {
            int smallest = input[i];
            int currentMinIndex = 0;
            for(int j = i+1; j < length; j++) {
                if(input[j] < smallest) {
                    smallest = input[j];
                    currentMinIndex = j;
                }
            }
            input[currentMinIndex] = input[i];
            sortedOutput[i] = smallest;
        }

        for(int k = 0; k < length; k++) {
            System.out.println(sortedOutput[k]);
        }

    }

}
