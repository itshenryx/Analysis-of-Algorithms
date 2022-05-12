/**
 * This file was submitted separately since it was too big.
 * Bharat Nema | 16010120089
 */

import java.util.Scanner;

/**
 * Dijkstra's Algorithm.
 * <p>
 *     Worst Case: n^2 (When all vertices are connected to each other [Complete Graph])
 * </p>
 */
public class experimentFive {
    public int numberOfVertices;
    public int[] distanceSet;

    /**
     * Constructor for the graph.
     * @param numberOfVertices Total number of vertices in the graph
     */
    experimentFive(int numberOfVertices){
        this.numberOfVertices = numberOfVertices;

        Graph graphObject = new Graph(numberOfVertices);

        Scanner bharatScanner = new Scanner(System.in);
        System.out.print("|#|Enter number of edges: ");
        int numberOfEdges = bharatScanner.nextInt();

        for (int i = 0; i < numberOfEdges; i++) {
            System.out.print("|#|Enter vertex pair for edge '" + i + "': ");
            int x = bharatScanner.nextInt();
            int y = bharatScanner.nextInt();
            System.out.print("|#|Enter distance edge '" + i + "': ");
            int dist = bharatScanner.nextInt();
            graphObject.addEdge(x,y,dist);
        }

        System.out.print("|#|Enter the source vertex: ");
        int source = bharatScanner.nextInt();

        dijkstraAlgo(graphObject.adjMatrix, source);
        //Debugging code for the graph
        //System.out.print(graphObject.toString());

        System.out.println("<!>Distance from all vertices for source Vertex: <" + source + ">");
        for (int i = 0; i < distanceSet.length; i++) {
            System.out.println("<" + i + "> : " + distanceSet[i]);
        }
    }

    /**
     * Dijkstra's Algorithm
     * @param graph 2d-Matrix for the graph
     * @param source Starting vertex
     */
    void dijkstraAlgo(int[][] graph, int source){
        //Set of distances for all the vertices from the source vertex.
        distanceSet = new int[numberOfVertices];

        //Set of included vertices, if the vertex is included its co-responding index value will be true.
        Boolean[] includedSet = new Boolean[numberOfVertices];
        
        //Initialize the array. 
        distanceSet[source] = 0;
        includedSet[source] = false;
        for (int i = 1; i < numberOfVertices; i++) {
            distanceSet[i] = Integer.MAX_VALUE;
            includedSet[i] = false;
        }

        for (int count = 0; count < numberOfVertices-1; count++) {
            //Pick the minimum distance vertex from non-visited vertices
            int x = minDistance(distanceSet,includedSet);
            //Mark the selected vertex as visited.
            includedSet[x] = true;

            for (int y = 0; y < numberOfVertices ; y++) {
                /**
                 * Perform the following checks...
                 *   1. Check if the vertex 'y' is already in the included set or not.
                 *   2. Check if there is an edge between vertex 'y' and 'x'.
                 *   3. Check if the minimum distance to 'x' is infinity or not.
                 *   4. And then finally, check if the sum of the distance of 'x' + cost of the vertex between 'x' and 'y'
                 *      is less than the minimum distance to 'y' in the distance array.
                 */
                if (!includedSet[y] && graph[x][y] != 0 && distanceSet[x] != Integer.MAX_VALUE && distanceSet[x] + graph[x][y] < distanceSet[y]){
                    distanceSet[y] = distanceSet[x] + graph[x][y];
                    //If all the checks pass, update the minimum distance for vertex 'y'.
                }
            }
        }
    }

    /**
     * Find the vertex with the smallest distance from the set of vertices not yet included in the shortest
     * @param distanceSet Array with the minimum distances of all vertices from the source
     * @param includedSet Array for tracking which vertices have already been processed.
     * @return The index of the closest connected vertex that has not been included.
     */
    int minDistance(int[] distanceSet, Boolean[] includedSet){
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int vertex = 0; vertex < numberOfVertices; vertex++){
            if (!includedSet[vertex] && distanceSet[vertex] <= min){
                min = distanceSet[vertex];
                min_index = vertex;
            }
        }

        return min_index;
    }
}

class Graph {
    public int[][] adjMatrix;
    public int numVertices;

    /**
     * Constructor for the graph
     * @param numVertices Number of vertices in the graph
     */
    Graph(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new int[numVertices][numVertices];
    }

    /**
     * Function to add edges
     * @param i Vertex One
     * @param j Vertex Two
     */
    public void addEdge(int i, int j, int dist) {
        adjMatrix[i][j] = dist;
        adjMatrix[j][i] = dist;
    }

}