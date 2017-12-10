package info6205.project.GeneticAlgorithm;

import java.util.Random;

/**
 * Traveling Salesman Problem using Genetic Algorithm
 * @author Emily Strong
 *
 * Requirements:
 * genetic code
 * gene expression
 * fitness function
 * sort function
 * evolution mechanism
 * logging function
 * unit tests
 * Bonus:
 * parallel computing for "colonies"
 *
 */
 
public class Main 
{
	
    public static void main( String[] args )
    {
        
        Random random = new Random(10);
        int n = 5;
        WeightedGraph g = new WeightedGraph(n);
        // generate a complete graph with n vertices
        for (int i = 0; i < n; i++) {
        	for(int j=i+1; j<n; j++) {
        		WeightedEdge e = new WeightedEdge(i, j, random.nextInt(5)+1); //create edge with weight between 1 and 5
        		g.addEdge(e);
        	}
        }
        
        GeneticAlgorithm darwin = new GeneticAlgorithm(g);
        darwin.run();
        
    }
}
