/*
 * This file was submitted separately since it was too big.
 * Bharat Nema | 16010120089
 */

/**
 * Longest Common Subsequence calculated using recursion and dynamic programming/tabulation.
 */
public class experimentTen {
    public String sOne,sTwo;
    public int[][] lcsArray; //Array used for tabulation.
    public String lcs = "";

    /**
     * Constructor for the experimentTen class.
     * @param sOne String One
     * @param sTwo String Two
     */
    experimentTen(String sOne, String sTwo){
        this.sOne = sOne;
        this.sTwo = sTwo;

        System.out.println("\n<String I: '" + sOne + "'>\t;" + "\t<String II: '" + sTwo + "'>");
        System.out.println("|#|Length of the LCS(Recursively Calculated): " + recursiveLCS(0,0));
        System.out.println("\n|#|Length of the LCS(Dynamically Calculated[Bottom-Up approach]): " + dynamicLCS());
        printString(lcsArray.length-1,lcsArray[0].length-1);
        System.out.println("\t<LCS: '" + lcs + "'>");
        System.out.println("\n<#>Tabulation table used:\n");
        printArray();
    }

    /**
     * Algorithm for Finding the Longest Common Subsequence using recursion.
     * <p>
     *     Time Complexity : <b>O(2^n)</b> [ This can be improved using memoization to <b>O(m*n)</b> ]
     * </p>
     * The Worst Case occurs when LCS is 0.
     *
     *
     * @param i Starting index of the First String.
     * @param j Starting index of the Second String.
     * @return Length of the LCS.
     */
    int recursiveLCS(int i, int j){
        if (i > sOne.length()-1 || j > sTwo.length()-1) { //If you have exceeded the string length...
            return 0; //...End the recursion by returning 0.
        } else if (sOne.charAt(i) == sTwo.charAt(j)){ //If the characters match...
            return 1 + recursiveLCS(i+1,j+1); //...increment the value of LCS by one and continue recursion for the next characters
        } else{ //If they don't match...
            return Math.max(recursiveLCS(i+1,j),recursiveLCS(i,j+1));//...increase both index of string I and II by 1.
        }
    }

    /**
     * Finding LCS using tabulation.
     * <p>Time Complexity: <b>O(m*n)</b> ['m' and 'n' being the length of the strings]</p>
     * @return The length of the LCS
     */
    int dynamicLCS(){
        lcsArray = new int[sOne.length()+1][sTwo.length()+1];

        //Loop through each column and row.
        for (int i = 0; i <= sOne.length(); i++) {
            for (int j = 0; j <= sTwo.length(); j++) {

                if( i == 0 || j == 0){
                    lcsArray[i][j] = 0;
                } else if (sOne.charAt(i - 1) == sTwo.charAt(j - 1)) { //If characters match...
                    lcsArray[i][j] = lcsArray[i-1][j-1] + 1; //...increase the value of that row-column by one and add the diagonally left element.
                } else{
                    lcsArray[i][j] = Math.max(lcsArray[i][j-1],lcsArray[i-1][j]); //Pick the max element from the left or top from the current row-column.
                }
            }
        }
        //Return the LCS.
        return lcsArray[sOne.length()][sTwo.length()];
    }

    /**
     * Function to print the table used in the dynamic approach.
     */
    void printArray(){
        System.out.print("\t\t#");

        //Loop to print out the first row. (String-II)
        for (int i = 0; i < sTwo.length() ; i++) {
            System.out.print("\t" + sTwo.charAt(i));
        }
        System.out.println(); //End-line.

        //Loop to print out String-I and the table values.
        for (int i = 0; i <= sOne.length(); i++) {
            if (i != 0) {
                System.out.print("\t" + sOne.charAt(i-1) + ":"); //String-I characters...
            } else{
                System.out.print("\t#:");
            }
            for (int j = 0; j <= sTwo.length(); j++) { //...String-II characters...
                System.out.print("\t" + lcsArray[i][j]); //...Table values
            }
            System.out.println(); //End-line.
        }
    }

    /**
     * Function for printing out the lcs.
     * @param i x-coordinate for the last most cell from the right
     * @param j y-coordinate for the last most cell from the right
     */
    void printString(int i, int j){
        if (!(lcsArray[i][j] == 0)) //If the solution exists,
        {
            if(lcsArray[i][j] == lcsArray[i-1][j] && lcsArray[i][j] == lcsArray[i][j-1]) //If the left and top to the current cell cells are equal...
            {
                printString(i-1,j); //...Traverse one cell to the left.
            } else { //If they are not equal, meaning that the current cell got its value due to a match from a diagonal,
                lcs = sTwo.charAt(j-1) + lcs; //Add the current cell to the final string,
                printString(i-1,j-1); //...& traverse one back diagonally.
            }
        }
    }
}
