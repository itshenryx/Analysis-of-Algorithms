//Experiment 2 ; 16010120089 ; Bharat Nema

public class experimentTwo {
    public int[] inputArray;

    /**
     * @param inputArray : Array containing the element
     */
    experimentTwo(int[] inputArray){
        this.inputArray = inputArray;
    }

    /**
     * @param key : Element to be searched
     * @return : The position of the element or -1 if the element was not found
     */
    public int binarySearchIterative(int key){
        int keyPosition, leftHalf = 0;
        int rightHalf = inputArray.length - 1;

        while (rightHalf >= leftHalf) {
            int middle = (leftHalf + rightHalf)/2;

            if (inputArray[middle] == key) {
                keyPosition = middle;
                return keyPosition;
            }

            if (inputArray[middle] < key) {
                leftHalf = middle + 1;
            }

            else {
                rightHalf = middle-1;
            }
        }
        return -1;
    }

    /**
     * Caller for recursive binary search.
     * @param key Key to be searched.
     * @return Position of the key.
     */
    public int recursiveBinarySearchCaller(int key){
        return recursiveBinarySearch(inputArray,key,0,inputArray.length);
    }

    /**
     * To perform binary search recursively.
     * @param inputArray Sorted Array provided.
     * @param key The key to be searched for.
     * @param imin Left most index of the array.
     * @param imax Right most index of the array.
     * @return Position of the Key.
     */
    public int recursiveBinarySearch(int[] inputArray,int key, int imin, int imax) {
        if (imax < imin){
            return 0;
        }
        else{
            int imid = (imin + imax)/2;
            if (inputArray[imid] > key){
                return recursiveBinarySearch(inputArray,key,imin,imid-1);
            }
            else if (inputArray[imid] < key){
                return recursiveBinarySearch(inputArray, key, imid + 1, imax);
            }
            else {
                return imid;
            }
        }
    }

    /**
     * Iterative algorithm for finding minimum and maximum.
     */
    public void iterativeMinMax() {
        int minimum = inputArray[0];
        int maximum = inputArray[0];

        for (int i = 0; i < inputArray.length; i++) {
            if(inputArray[i]>maximum){
                maximum = inputArray[i];
            }
            else if(inputArray[i]<minimum){
                minimum = inputArray[i];
            }
        }
        System.out.println("|#|Minimum: " + minimum + "\n|#|Maximum: " + maximum);
    }

    /**
     * Function to call the recursive min-max algorithm
     */
    public void recursiveMinMaxCaller() {
        MinMax output = recursiveMinMax(inputArray,0,inputArray.length-1);
        System.out.println("|#|Minimum(Using Divide and Conquer): " + output.min + "\n|#|Maximum(Using Divide and Conquer): " + output.max);
    }

    /**
     * Recursive algorithm for finding minimum and maximum.
     * @param array Array to be searched.
     * @param low First index of the array.
     * @param high Last index of the array.
     * @return MinMax object which stores 2 integers
     */
    public MinMax recursiveMinMax(int[] array,int low, int high) {
        if (high - low == 0){
            return new MinMax(array[low],array[low]);
        } else if (high - low == 1) {
            return new MinMax(Math.min(array[low],array[high]),Math.max(array[low],array[high]));
        }
        else {
            int mid = (low + high) / 2;
            MinMax leftArray = recursiveMinMax(array, low, mid - 1);
            MinMax rightArray = recursiveMinMax(array, mid, high);

            int min,max;
            max = Math.max(leftArray.max, rightArray.max);
            min = Math.min(leftArray.min, rightArray.min);

            return new MinMax(min,max);
        }
    }

    class MinMax{
        public int min,max;
        MinMax(int min,int max){
            this.min = min;
            this.max = max;
        }
    }
}

