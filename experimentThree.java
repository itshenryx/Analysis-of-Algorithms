public class experimentThree {
    public int[] inputArray;

    experimentThree(int[] inputArray){
        this.inputArray = inputArray;
    }

    /**
     * Function to swap two elements in an array.
     * @param arr The array in which the swap is to be performed.
     * @param i Element 1
     * @param j Element 2
     */
    static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Function to partition the arrays into sub-arrays.
     * @param array Array to be partitioned.
     * @param left Left-most index.
     * @param right Right-most index.
     * @return Sorted partitioned array.
     */
    int partition(int array[],int left, int right){
        int pivot = array[right];
        int low = left - 1;

        for (int i = left; i < right; i++) {
            if(array[i] < pivot){
                low++;
                swap(array,low,i);
            }
        }
        swap(array,low+1,right);
        return low+1;
    }

    /**
     * Recursive quick sort algorithm.
     * @param inputArray Array to be sorted.
     * @param left Left-most index of the array.
     * @param right Right-most index of the array.
     * @return The final sorted array
     */
    int[] quickSort(int[] inputArray, int left, int right){
        if(left<right){
            int q = partition(inputArray,left,right);
            quickSort(inputArray,left,q-1);
            quickSort(inputArray,q+1,right);
        }
        return inputArray;
    }

    /**
     * Function to call quick-sort from main.
     * @return Sorted Array
     */
    public int[] quickSortCaller(){
        return quickSort(inputArray,0,inputArray.length-1);
    }

    /**
     * Function to split , then sort and then merge arrays.
     * @param array
     * @param left Left-most index
     * @param middle Middle index
     * @param right Right-most index
     */
    void merge(int[] array,int left,int middle,int right){
        //Find sizes of two sub-arrays to be merged.
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        //Create left and right temp arrays.
        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];

        //Copying data into left array...
        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = array[left + i];
        }

        //Copying data into right array...
        for (int i = 0; i < rightSize; i++) {
            rightArray[i] = array[middle + 1 + i];
        }

        int firstIndex = 0, secondIndex = 0;
        int mainIndex = left;

        while (firstIndex < leftSize && secondIndex < rightSize) {
            if (leftArray[firstIndex] <= rightArray[secondIndex]) {
                array[mainIndex] = leftArray[firstIndex];
                firstIndex++;
            }
            else {
                array[mainIndex] = rightArray[secondIndex];
                secondIndex++;
            }
            mainIndex++;
        }

        //Copying remaining elements of leftArray...
        while (firstIndex < leftSize) {
            array[mainIndex] = leftArray[firstIndex];
            firstIndex++;
            mainIndex++;
        }

        //Copying remaining elements of rightArray...
        while (secondIndex < rightSize) {
            array[mainIndex] = rightArray[secondIndex];
            secondIndex++;
            mainIndex++;
        }
    }

    /**
     * Recursive function of merge sort
     * @param array Array to be sorted
     * @param left Left-most index
     * @param right Right-most index
     * @return Sorted Array
     */
    int[] mergeSort(int[] array, int left, int right){
        if (left < right) {
            // Find the middle point
            int m =(right+left)/2;

            // Sort first and second halves
            mergeSort(array, left, m);
            mergeSort(array, m + 1, right);

            // Merge the sorted halves
            merge(array, left, m, right);
        }
        return array;
    }

    int[] mergeSortCaller(){
        return mergeSort(inputArray,0,inputArray.length-1);
    }
}
