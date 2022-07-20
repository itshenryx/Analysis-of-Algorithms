/*
 * This file was submitted separately since it was too big.
 */


import java.util.Scanner;

/**
 * Floyd Warshall Algorithm
 * <p>
 *     Time Complexity : O(n^3)
 * </p> <p>
 *     n: Number of vertices.
 * </p>
 */
public class experimentSix {
    public int numberOfVertices;
    public int[][] distanceSet;

    /**
     * Constructor for the graph & calls the algorithm after the graph is made.
     * @param numberOfVertices Total number of vertices in the graph
     */
    experimentSix(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        directedGraph graphObject = new directedGraph(numberOfVertices);

        //Taking the user inputs for the graph.
        Scanner bharatScanner = new Scanner(System.in);
        System.out.print("|#|Enter number of edges: ");
        int numberOfEdges = bharatScanner.nextInt();

        for (int i = 0; i < numberOfEdges; i++) {
            System.out.print("|#|Enter vertex pair for edge '" + i + "': ");
            int x = bharatScanner.nextInt();
            int y = bharatScanner.nextInt();
            System.out.print("|#|Enter distance edge '" + i + "': ");
            int dist = bharatScanner.nextInt();
            graphObject.addEdge(x, y, dist);
        }

        floydWarshall(graphObject.adjMatrix); //Calculate the shortest path.
        printGraph(graphObject.adjMatrix);
        printArray(); //Print the final Solution.
    }

    /**
     * Floyd Warshall Algorithm.
     * @param directedGraph The user inputted graph.
     */
    void floydWarshall(int[][] directedGraph){
        //Final array that has the shortest paths for all pairs of vertices.
        distanceSet = new int[numberOfVertices][numberOfVertices];

        //Copy the original array to the final array.
        for (int i = 0; i < numberOfVertices; i++) {
            System.arraycopy(directedGraph[i], 0, distanceSet[i], 0, numberOfVertices);
        }

        //Compare all pairs and distances.
        for (int k = 0; k < numberOfVertices; k++) {
            for (int i = 0; i < numberOfVertices; i++) {
                for (int j = 0; j < numberOfVertices; j++) {

                    /* Check the following:
                    *     1. If the minimum distance in the minimum distance array is less or more than the newly calculated distance
                    *     2. Also, if any of the distances that are being used to calculate the new distance are the max integer value,
                    *        because if they are, adding even 1 to them, will reset them to the lowest integer value.
                    */
                    if((distanceSet[i][j] > distanceSet[i][k] + distanceSet[k][j]) &&
                            (distanceSet[k][j] != Integer.MAX_VALUE && distanceSet[i][k] != Integer.MAX_VALUE) ){
                        distanceSet[i][j] = distanceSet[i][k] + distanceSet[k][j]; //Update the array for the new minimum distance.
                    }
                }
            }
        }
    }

    /**
     * Function for printing the final solution.
     */
    void printArray(){
        System.out.println("\n|#|The final solution is: \n");
        for (int i = 0; i < numberOfVertices; i++) {
            System.out.print("\t" + i); //Print top row.
        }
        System.out.println(); //End-line.
        for (int i = 0; i < numberOfVertices; i++) {
            System.out.print(i + ":");
            for (int j = 0; j < numberOfVertices; j++) {
                System.out.print("\t" + distanceSet[i][j]);
            }
            System.out.println(); //End-line.
        }
        System.out.println("\n");
    }


    /**
     * Function for printing the initial graph.
     */
    void printGraph(int[][] adjMatrix){
        System.out.println("\n|#|The original graph: \n");
        for (int i = 0; i < numberOfVertices; i++) {
            System.out.print("\t" + i); //Print top row.
        }
        System.out.println(); //End-line.
        for (int i = 0; i < numberOfVertices; i++) {
            System.out.print(i + ":");
            for (int j = 0; j < numberOfVertices; j++) {
                if (adjMatrix[i][j] != Integer.MAX_VALUE){
                    System.out.print("\t" + adjMatrix[i][j]);
                } else{
                    System.out.print("\t∞");
                }

            }
            System.out.println(); //End-line.
        }
    }
}

class directedGraph {
    public int[][] adjMatrix;
    public int numVertices;

    /**
     * Constructor for the graph
     * @param numVertices Number of vertices in the graph
     */
    directedGraph(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new int[numVertices][numVertices];

        //Initialize the graph.
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i==j){
                    adjMatrix[i][j] = 0;
                } else{
                    adjMatrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }

    /**
     * Function to add edges
     * @param i Vertex One
     * @param j Vertex Two
     * @param dist Distance between the two vertices
     */
    public void addEdge(int i, int j, int dist) {
        adjMatrix[i][j] = dist;
    }

}

