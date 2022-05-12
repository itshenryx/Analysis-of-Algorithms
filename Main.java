/*
 * This file was submitted separately since it was too big.
 * Bharat Nema | 16010120089
 */

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
//        Main.experimentOneTester();
//        Main.experimentTwoTester();
//        Main.experimentThreeTester();
//        Main.experimentFourTester();
//        Main.experimentFiveTester();
//        Main.experimentSixTester();
//        Main.experimentSevenTester();
        Main.experimentEightTester();
//        Main.experimentNineTester();
//        Main.experimentTenTester();
    }

    public static void experimentOneTester() {
        int X = 100000; //Number of elements in the Array
        int[] testArray = new int[X];

        //Generating random numbers and adding them to the Array
        Random random = new Random();
        for (int i = 0; i < X; i++){
            testArray[i] = random.nextInt(10000) + 1;
        }

        //Array used for testing insertion sort
        int[] testArray2 = testArray.clone();

        //Printing the unsorted array...
        System.out.println(Arrays.toString(testArray));

        experimentOne insertionSort = new experimentOne(testArray);
        experimentOne selectionSort = new experimentOne(testArray2);

        //Testing selection sort...
        long startTime = System.nanoTime();
        int[] selectionSortArray = selectionSort.selectionSort();
        long endTime = System.nanoTime();
        System.out.println("|#|Selection sort took : " + (endTime-startTime)/1000000 + "ms");

        //Testing insertion sort...
        startTime = System.nanoTime();
        int[] insertionSortArray = insertionSort.insertionSort();
        endTime = System.nanoTime();
        System.out.println("|#|Insertion sort took : " + (endTime-startTime)/1000000 + "ms");

        System.out.println("\n↓ Sorted Array ↓");
        System.out.println(Arrays.toString(selectionSortArray));
//        System.out.println(Arrays.toString(insertionSortArray));
    }

    public static void experimentTwoTester() {
        //Number of elements in the array.
        int numberOfElements = 20;
        int[] sortedTestArray = new int[numberOfElements];
        for (int i = 0; i < numberOfElements; i++) {
            sortedTestArray[i] = i;
        }
        int searchElement = 1;

        experimentTwo cursor = new experimentTwo(sortedTestArray);

        //Testing iterative binary sort...
        System.out.println("|#|Element searched: " + searchElement);
        System.out.println(Arrays.toString(sortedTestArray));
        System.out.println("|#|Element found at: " +  cursor.binarySearchIterative(searchElement));

        //Testing recursive binary sort...
        System.out.println("|#|Element searched: " + searchElement);
        System.out.println(Arrays.toString(sortedTestArray));
        System.out.println("|#|Element found at: " +  cursor.recursiveBinarySearchCaller(searchElement));

        //Testing iterative min-max
        cursor.iterativeMinMax();

        //Testing recursive min-max;
        cursor.recursiveMinMaxCaller();

    }

    public static void experimentThreeTester(){
        int X = 10000; //Number of elements in the Array
        int[] testArray = new int[X];

        //Generating random numbers and adding them to the Array
        Random random = new Random();
        for (int i = 0; i < X; i++){
            testArray[i] = random.nextInt(10000) + 1;
        }

        System.out.println(Arrays.toString(testArray));
        experimentThree quickSort = new experimentThree(testArray);
        experimentThree mergeSort = new experimentThree(testArray);

        long startTime = System.nanoTime();
        System.out.println(Arrays.toString(quickSort.quickSortCaller()));
        long endTime = System.nanoTime();

        System.out.println("|#|Quicksort took : " + (endTime-startTime)/1000000 + "ms");

        startTime = System.nanoTime();
        System.out.println(Arrays.toString(mergeSort.mergeSortCaller()));
        endTime = System.nanoTime();

        System.out.println("|#|Mergesort took : " + (endTime-startTime)/1000000 + "ms");
    }

    public static void experimentFourTester(){
        experimentFour knapsack = new experimentFour(25,4);
        knapsack.printItemArray();
        knapsack.GreedyKnapsack();
    }

    public static void experimentFiveTester(){
        experimentFive DijkstraAlgo = new experimentFive(5);
    }

    public static void experimentSixTester(){
        experimentSix FloydWarshall = new experimentSix(4);
    }

    public static void experimentSevenTester() {
        int[] dimensions = new int[] { 2,3,4,1 };
        experimentSeven MCM = new experimentSeven(dimensions);
    }

    public static void experimentEightTester(){
        int[] subset = new int[] {8,4,6,1,3,7};
        experimentEight subsetSum = new experimentEight(subset, 15);
    }

    public static void experimentNineTester(){
        int[][] graph = {
                { 0, 1, 1, 1 },
                { 1, 0, 1, 0 },
                { 1, 1, 0, 1 },
                { 1, 0, 1, 0 },
        };
        char[] colors = {'R','G','B'};
        experimentNine graphColoring = new experimentNine(graph,colors);
    }

    public static void experimentTenTester(){
        experimentTen LCS = new experimentTen("sdajdfgfsjw", "dahwqd");
    }
}












