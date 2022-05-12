/*
 * This file was submitted separately since it was too big.
 * Bharat Nema | 16010120089
 */

import java.util.Arrays;

/**
 * Matrix chain multiplication
 */
public class experimentSeven {
    public int[] dimensions; //Inputted table.
    public int[][] kTable, cost; //Tables used to store the least amount of cost and the k-values used.


    /**
     * Constructor for the MCM algorithm, calls the algorithm as well.
     * @param dimensions Input table which contains the dimensions of the matrices.
     */
    experimentSeven(int[] dimensions){
        this.dimensions = dimensions;

        cost = new int[dimensions.length][dimensions.length]; //Giving 1-extra row and column for personal ease.
        kTable = new int[dimensions.length][dimensions.length];

        matrixChainMultiplication();
        System.out.println("\n|#|The minimum cost of multiplication is : " + cost[1][dimensions.length-1]);

        System.out.println("\n\t<k-Table>");
        for (int[] row : kTable) {
            System.out.println("\t\t" + Arrays.toString(row));
        }
        System.out.println("\n\t<cost-Table>");
        for (int[] row : cost) {
            System.out.println("\t\t" + Arrays.toString(row));
        }
    }

    /**
     * Dynamic Matrix Chain multiplication, using tabulation.
     * <p>Time Complexity: <b>O(n^3)</b></p>
     */
    void matrixChainMultiplication() {

        //Set all the values in the middle diagonal to be zero, Since multiplication of a matrix to itself costs nothing.
        for (int i = 1; i < dimensions.length; i++) {
            cost[i][i] = 0;
        }

        //@param Diagonal is used here to travel the top right half of the matrix, diagonally.
        //We start from 2, as to skip the middle diagonal (Which we just set to zero).
        for (int diagonal = 2; diagonal < dimensions.length; diagonal++) {

            //We start from 1, since we have a filler rows and columns at indexes 0 and 0.
            for (int i = 1; i < dimensions.length - diagonal + 1; i++) {
                int j = i + diagonal - 1; //Calculate j such that we move one diagonal at a time.


                cost[i][j] = Integer.MAX_VALUE; //Set the current spot as infinite for later comparisons.
                // (Since it's set to '0' originally)

                //Calculate k depending on i and j...
                //... i >= k > j , k can never be greater than j.
                for (int k = i; k <= j - 1 ; k++) {

                    //Calculate cost for the multiplication of 2 or more matrices in that order.
                    int q = cost[i][k] + cost[k + 1][j] + dimensions[i - 1] * dimensions[k] * dimensions[j];
                    if (q < cost[i][j]){
                        cost[i][j] = q; //If there is more than one k value (i.e. more than 2 matrices involved, this will update more than once.
                        kTable[i][j] = k; //Same with this, the k-table is used to find the order of partitioning.
                    }
                }
            }
        }
    }

}
