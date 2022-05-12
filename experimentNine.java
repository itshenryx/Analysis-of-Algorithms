/*
 * This file was submitted separately since it was too big.
 * Bharat Nema | 16010120089
 */

import java.util.Arrays;

/**
 * Graph coloring algorithm using backtracking.
 */
public class experimentNine {
    public int[][] graph;
    public char[] colors, finalColors;

    /**
     * Constructor and function caller for experiment 9
     * @param graph Input graph which is to be colors
     * @param colors Array of colors
     */
    experimentNine(int[][] graph, char[] colors){
        this.graph = graph;
        this.colors = colors;

        finalColors = new char[graph.length]; //Initializing the array used for storing the final colors.
        Arrays.fill(finalColors, 'N');

        //Print the solution.
        if (graphColoring(0,finalColors)) {
            System.out.println("<!>Success, a solution was found: ");
            System.out.println(Arrays.toString(finalColors));
        }else {
            System.out.println("<!>No solution found.");
        }
    }

    /**
     * Graph coloring algorithm using backtracking.
     * <p>Time Complexity: <b>O(C^v)</b> </p>
     * <p>Where c: number of colors,v: number of vertices</p>
     * @param vertex Index for the current vertex being colored.
     * @param finalColors Array used for storing the list of final coloring order.
     * @return True or False accordingly.
     */
    boolean graphColoring(int vertex,char[] finalColors){
        //If we have reached the final vertex+1, it means we have colored all the other vertices.
        if (vertex == graph.length){
            return true;
        }

        //Iterate through all the colors...
        for (char color : colors) {
            //Call this function to check if that color is allowed here or not...
            if(isLegal(vertex, color) && finalColors[vertex] == 'N'){
                //...If it is, then assign it in the array,
                finalColors[vertex] = color;

                //And then move onto the next vertex...
                if(graphColoring(vertex+1,finalColors)){
                    return true;
                }

                //If the last vertex has no possible solutions, then backtrack.
                finalColors[vertex] = 'N'; //Remove it from this vertex as-well.
            }
        }
        return false; //Return false if no solutions were found.
    }

    /**
     * Function to check if the passed color is allowed for the passed vertex.
     * @param vertex Vertex to be checked
     * @param color Color to be assigned
     * @return True or false
     */
    boolean isLegal(int vertex,char color){
        //Iterate through all vertices
        for (int i = 0; i < graph.length; i++) {
            //Check: 1. If the vertex is connected to the passed vertex,
            //       2. If the two vertices have the same color or not.
            if (graph[vertex][i] != 0 && finalColors[i] == color){
                return false; //If both statements are true, then that color is not possible for the passed vertex.
            }
        }
        return true; //The color is possible for the passed vertex.
    }
}
