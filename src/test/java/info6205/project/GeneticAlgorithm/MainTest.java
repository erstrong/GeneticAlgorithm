package info6205.project.GeneticAlgorithm;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for genetic algorithm
 */
public class MainTest {
	/**
	 * Test the graph data structures
	 */
	@Test public void testWeightedEdge() {
		WeightedEdge e = new WeightedEdge(1, 2, 3);
		assertTrue(e.v() == 1);
		assertTrue(e.w()==2);
		assertTrue(e.weight()==3);
	}
	
	@Test public void testWeightedGraph() {
		WeightedGraph g = new WeightedGraph(7);
		WeightedEdge e = new WeightedEdge(1, 2, 3);
		g.addEdge(e);
		assertTrue(g.V()==7);
		assertTrue(g.E()==1);
		Iterable<WeightedEdge> a = g.adj(1);
		Iterable<WeightedEdge> b = g.adj(2);
		assertTrue(a.iterator().next() == e);
		assertTrue(b.iterator().next() == e);
	}
	
	/**
	 * Test The Fitness Function
	 */
	@Test public void testFitnessFunction() {
		WeightedGraph g = new WeightedGraph(4);
		for (int i = 0; i < 4; i++) {
			for (int j = i+1; j < 4; j++) {
				WeightedEdge e = new WeightedEdge(i, j, 1);
				g.addEdge(e);
			}
		}
		int[] a = {0, 1, 2, 3};
		Individual i = new Individual(a, null, null);
		i.calculateFitness(g);
		assertTrue(i.score == (float) 0.25);	
	}
	
	/**
	 * Test Individual Compare To
	 */
	@Test public void testCompareTo() {
		int[] a = {0, 1, 2, 3};
		Individual i = new Individual(a, null, null);
		i.score = (float) 0.20;
		Individual j = new Individual(a, null, null);
		j.score = (float) 0.50;
		assertTrue(i.compareTo(j) == -1);
		assertTrue(j.compareTo(i) == 1);
	}
	
	
	/**
	 * Test The Fitness Score Sort
	 */
	@Test public void testSortOnFitness() {
		WeightedGraph g = new WeightedGraph(4);
		for (int i = 0; i < 4; i++) {
			for (int j = i+1; j < 4; j++) {
				int w = 1;
				if(i==2 && j==3) { w=2; }
				WeightedEdge e = new WeightedEdge(i, j, w);
				g.addEdge(e);
			}
		}
		Population p = new Population(g, true);
		int[] a = {0, 1, 2, 3};
		p.addMember(new Individual(a, null, null));
		int[] b = {1,2,0,3};
		p.addMember(new Individual(b, null, null));
		assertTrue(p.size() == 6);
		Individual[] members = p.sortOnFitness();
		assertTrue(members.length==6);
		assertTrue(members[0].score == (float) 0.20);
		assertTrue(members[5].score == (float) 0.25);
		
	}
	

	
	
	
    
}
