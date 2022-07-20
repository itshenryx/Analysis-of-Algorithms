/*
 * This file was submitted separately since it was too big.
 */

import java.util.Arrays;

/**
 * Sum of subsets using backtracking.
 */
public class experimentEight {
    public int totalSum;
    public int[] subset, includedSubset;
    public int count = 0; //Used for printing the proper array.

    /**
     * Constructor and function caller for finding the possible subset.
     * @param subset The original set
     * @param totalSum Total sum to be found
     */
    experimentEight(int[] subset, int totalSum) {
        this.subset = subset;
        this.totalSum = totalSum;

        int totalRemaining = 0; //Total remaining sum of remaining integers in the original subset.

        for (int value : subset) {
            totalRemaining += value;
        }

        includedSubset = new int[subset.length];

        if (subsetSum(totalSum, totalRemaining,0, 0)){
            //Print the result.
            System.out.println("|#|Subset for the sum: " + totalSum);

            for (int var: includedSubset) {
                if (var != 0){
                    count++;
                }
            }

            System.out.print("\t<<Original Subset>> " + Arrays.toString(subset) + "\n");
            System.out.print("\t<<Final Subset>> [");
            for (int i = 0; i < includedSubset.length; i++) {
                if (i == count-1){
                    System.out.print(includedSubset[i] + "]");
                    break;
                } else{
                    System.out.print(includedSubset[i] + ", ");
                }
            }
        } else{
            System.out.println("|#|No suitable subset was found for the sum: " + totalSum);
        }
    }

    /**
     * Sum of subsets using backtracking
     * <p>Time Complexity: <b>O(2^n) <i>(exponential)</i></b></p>
     * @param sum Remaining sum, starts from total sum , is slowly deducted
     * @param remainingWeight Remaining total sum of unused integers from the subset
     * @param mainIndex Index pointer for main array
     * @param subSetIndex Index pointer for secondary array
     * @return True or false if the solution was found or not
     */
    boolean subsetSum(int sum, int remainingWeight, int mainIndex, int subSetIndex){
        //Run out of integers to use, backtrack.
        if (mainIndex == subset.length){
            return false;
        }

        //Sum has been found , return true.
        if (subset[mainIndex] == sum){
            includedSubset[subSetIndex] = subset[mainIndex]; //Include value in secondary subset.
            remainingWeight = remainingWeight - includedSubset[subSetIndex];
            return true;
        }

        //No possible way to achieve the sum since not enough integers are present to reach it.
        if (remainingWeight < sum){
            return false;
        }

        //Loop through the array from the main index pointer.
        for (int i = mainIndex; i < subset.length; i++) {

            //If including current integer exceeds the sum, skip to the next iteration.
            if (subset[i] > sum){
                continue;
            }

            //If including the current integer achieves the sum, return true.
            if (subset[i] == sum){
                includedSubset[subSetIndex] = subset[i];
                remainingWeight = remainingWeight - includedSubset[subSetIndex];
                return true;
            }

            //If it's neither of the above...
            includedSubset[subSetIndex] = subset[i]; //...first include the integer
            //...and then pass it on by recursion by increase the index by one,
            if(subsetSum(sum - subset[i],remainingWeight-subset[i],i+1,subSetIndex+1)){
                //...if it is able to achieve the sum in future trees, it will keep returning true...
                return true;
            }
        }
        return false; //Other-wise that node is killed and backtracked from.
    }
}
