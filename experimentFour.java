//This file was submitted separately since it was too big.
//Bharat Nema
//16010120089

import java.util.Scanner;

public class experimentFour {
    public ItemCreator[] itemArray; //Array with all the items (as objects)
    public int capacity, numberOfItems;
    public ItemCreator[] finalArray;

    /**
     * Constructor for creating all the items and their weights and values.
     * @param capacity Total capacity of the knapsack
     * @param numberOfItems Number of items that exist
     */
    experimentFour(int capacity, int numberOfItems){
        this.capacity = capacity;
        this.numberOfItems = numberOfItems;

        itemArray = new ItemCreator[numberOfItems];
        finalArray = new ItemCreator[numberOfItems];

        Scanner bharatScanner = new Scanner(System.in);

        //Ask the user for each item value-weight pair and add them to the array.
        for (int i = 0; i < numberOfItems; i++) {
            System.out.println(">'" + (i+1) + "'th item: ");
            System.out.print("|#|Enter the value of the item: ");
            int value = bharatScanner.nextInt();

            System.out.print("|#|Enter the weight of the item: ");
            int weight = bharatScanner.nextInt();

            ItemCreator newItem = new ItemCreator(value,weight);
            itemArray[i] = newItem;
        }
    }

    /**
     * Greedy knapsack approach.
     */
    void GreedyKnapsack(){
        //Sort the array for cost (Highest to lowest).
        insertionSort();

        int remainingCapacity = capacity;
        double finalValue = 0d;
        int indexCounter = 0;

        //Go through every element and add them to the array if there is space.
        for (ItemCreator var: itemArray) {
            int currentWeight = (int)var.weight;
            int currentValue = (int)var.value;

            //If the knapsack has enough weight for the whole item.
            if (remainingCapacity - currentWeight >= 0){

                remainingCapacity = remainingCapacity - currentWeight; //Reduce the remaining capacity accordingly.
                finalValue = finalValue + currentValue; //Increase the final value of the knapsack.

                //Add the item and the included weight of the item to the knapsack.
                var.includedWeight = currentWeight;
                var.includedValue = currentValue;
                finalArray[indexCounter] = var;

                //Increase array index
                indexCounter++;

            } else{ //If the knapsack is empty but there isn't enough space full the full item

                double fractionalWeight = ((double)remainingCapacity/(double)currentWeight); //Calculate fractional weight that can be added.
                finalValue = finalValue + (currentValue*fractionalWeight); //Increase final value accordingly.
                remainingCapacity = (int)(remainingCapacity - (currentWeight * fractionalWeight)); //Reduce remaining capacity.

                //If the weight of the item added is more than 0, (which means if the bag is not full), add it to the array

                var.includedWeight = currentWeight*fractionalWeight;
                var.includedValue = (currentValue*fractionalWeight);
                finalArray[indexCounter] = var;
                indexCounter++;
                }
            }
        printFinalArray(finalValue,remainingCapacity);
    }

    /**
     * Insertion sort for sorting the array cost-wise.
     * @return : inputArray (Sorted Array)
     */
    public void insertionSort() {
        for (int x = 1; x < itemArray.length; x++) {
            ItemCreator temp = itemArray[x];
            int y = x-1;

            while (y >= 0 && itemArray[y].cost < temp.cost) {
                itemArray[y + 1] = itemArray[y];
                y--;
            }
            itemArray[y+1] = temp;
        }
    }

    /**
     * Method for printing the item Array.
     */
    void printItemArray(){
        System.out.println();
        System.out.println("|#|List of all items: ");
        for (ItemCreator var: itemArray) {
            System.out.print("{ Value: " + (int)var.value + ", Weight: "  + (int)var.weight + ", Cost: " + var.cost + " }");
        }
        System.out.println();
    }

    void printFinalArray(double finalValue,double remainingCapacity) {
        System.out.println();
        System.out.println("|#|Final Value: " + finalValue);
        System.out.println("|#|Final remaining weight: " + remainingCapacity);
        System.out.println("|#|Final List of added items: ");
        for (ItemCreator var: itemArray) {
            if (var.includedWeight > 0) {
                System.out.print("{ Included Value: " + var.includedValue + ", Included Weight: " + var.includedWeight +  " }");
            }
        }
    }
}


/**
 * Object creating class for the items.
 */
class ItemCreator {
    public double value, weight;
    public double cost;

    //Variable to be updated later, if the item is included in the knapsack.
    public double includedWeight;
    public double includedValue;

    /**
     * Constructor
     * @param value Value of the item
     * @param weight Weight of the item
     */
    ItemCreator (double value , double weight){
        this.value = value;
        this.weight = weight;

        cost = value/weight;
    }
}

