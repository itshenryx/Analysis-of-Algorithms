//Experiment 1 ;

public class experimentOne {
    public int[] inputArray;

    /**
     * Constructor
     * @param inputArray : Array to be sorted
     */
    experimentOne(int[] inputArray) {
        this.inputArray = inputArray;
    }

    /**
     * Insertion sort
     * <p>Time Complexity: <b>O(n^2)</b><br>
     * Best Case: <b>O(n)</b></p>
     * @return : inputArray (Sorted Array)
     */
    public int[] insertionSort() {
        for (int x = 1; x < inputArray.length; x++) {
            int temp = inputArray[x];
            int y = x-1;


            while (y >= 0 && inputArray[y] > temp) {
                inputArray[y + 1] = inputArray[y];
                y--;
            }
            inputArray[y+1] = temp;
        }
        return inputArray;
    }

    /**
     * Selection sort
     * <p>Time Complexity: <b>O(n^2)</b></p>
     * @return : inputArray (Sorted Array)
     */
    public int[] selectionSort() {
        for (int i = 0; i < inputArray.length; i++) {
            int min = inputArray[i];

            for (int j = i; j < inputArray.length; j++) {
                if (inputArray[j] < min) {
                    min = inputArray[j];
                    int temp = inputArray[i];
                    inputArray[i] = min;
                    inputArray[j] = temp;
                }
            }
        }
        return inputArray;
    }
}
