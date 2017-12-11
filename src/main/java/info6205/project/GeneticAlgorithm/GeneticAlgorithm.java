package info6205.project.GeneticAlgorithm;

import java.util.Random;
import org.apache.log4j.Logger;

public class GeneticAlgorithm {
	Population p;
	int generations;
	Individual[] lastgeneration;
	float[] mostfit;
	final static Logger logger = Logger.getLogger(GeneticAlgorithm.class);
	
	
	// Constructor
	public GeneticAlgorithm(WeightedGraph g) {
		this.p = new Population(g, true);
		this.generations=0;
		logger.info("Vertices: " + g.V());
	}
	
	// Run the algorithm
	public void run() {
		this.mostfit = new float[100];
		// terminate after 100 generations or when the maximum fitness score hasn't changed in 5 generations
		// minimum generations 10
		while(generations < 100) {
			if(generations > 10) {
				
				if(mostfit[generations-1]==mostfit[generations-2] 
						&& mostfit[generations-1]==mostfit[generations-3]
						&& mostfit[generations-1]==mostfit[generations-4]
						&& mostfit[generations-1]==mostfit[generations-5]
						&& mostfit[generations-1]==mostfit[generations-6]) { break; }
			}
			evolve();
			logger.info("Generation: " + generations + " Individuals: " + p.size + " Best Score: " + mostfit[generations]);
			generations++;
		}
		this.lastgeneration = p.sortOnFitness();
		Individual fittest = lastgeneration[lastgeneration.length-1];
		logger.info("Shortest Path" + fittest.toString());
		
	}
	
	// Private Methods
	private void evolve() {
		if(generations>0) {
			cull(p);
		}
		
		Individual[] children = new Individual[p.size]; // with every generation the population doubles after culling
		Individual in = p.last;
		for(int i=0; i <p.size; i++) {
			int[] copygene = new int[in.gene.length];
			for (int j=0; j < in.gene.length; j++) {
				copygene[j] = in.gene[j];
			}
			children[i] = new Individual(copygene, null, null);
			in = in.previous;
		}
		
		Random rand1 = new Random();
		for(int i=0; i<children.length-1; i+=2) {
			crossOver(rand1.nextInt(p.g.V()), children[i], children[i+1]);
		}
		
		for(int i=0; i < children.length; i++) {
			mutate(children[i]);
		}
		
		// Genetic modifications of children complete
		// Add children to population
		for(Individual c : children) {
			p.addMember(c);
		}
		
		// Get the best fitness score
		Individual[] members = p.sortOnFitness();
		mostfit[generations] = members[members.length-1].score; // the most fit individual of the survivors

	}
	
	private void cull(Population pop) {
		// Get the number of individuals to cull
		Random rand2 = new Random();
		double n = 1 / (rand2.nextInt(8) + 1); // generate a number between .1 and .9
		int keep = 0;
		// population needs at least 2 individuals for sexual reproduction
		if(Math.floor(n * pop.size) > 2) { keep = (int) Math.floor(n * pop.size); }
		else { keep = 2; } 
		// sort the population
		Individual[] members = pop.sortOnFitness();
		// the array is sorted weakest to fittest
		for(int i = 0; i < (pop.size - keep); i++) {
			pop.removeMember(members[i]);
		}
		
	}

	private void crossOver(int nextInt, Individual individual, Individual individual2) {
		Random rand4 = new Random();
		int m = rand4.nextInt(individual.gene.length);
		// get the value in each gene at m
		int i1v1 = individual.gene[m]; 
		int i2v1 = individual2.gene[m]; 
		
		// get the index of the other individual's gene[m] value
		int i1v2 = individual.getIndex(i2v1);
		int i2v2 = individual2.getIndex(i1v1);
		
		// cross over and repair
		if(i1v2 != -1) {mutation1(individual, i1v1, i1v2);}
		if(i2v2 != -1) {mutation1(individual2, i2v1, i2v2);}
		
	}
	
	private void mutate(Individual individual) {
		Random rand3 = new Random();
		int t = rand3.nextInt(2);
		int m1 = rand3.nextInt(individual.gene.length);
		int m2 = rand3.nextInt(individual.gene.length);
		while(m1==m2) { m2 = rand3.nextInt(individual.gene.length); } // make sure they aren't the same
		if (t==0) {
			mutation1(individual, m1, m2);
		} else {
			mutation2(individual, m1, m2);
		}
		
	}
	
	// simple swap mutation
	private void mutation1(Individual individual, int m1, int m2) {
		int a = individual.gene[m1];
		individual.gene[m1] = individual.gene[m2];
		individual.gene[m2] = a;
	}

	private void mutation2(Individual individual, int m1, int m2) {
		if((m1+2 < individual.gene.length && m1 > m2+2) || (m2+2 < individual.gene.length && m2 > m1+2)) {
			int[] aux = new int[individual.gene.length];
			for (int i = 0; i < aux.length; i++) {
				aux[i] = individual.gene[i];
			}
			
			if (m1 > m2) {
				// insert m1 - m1+2 at m2 and shift everything right
				individual.gene[m2] = aux[m1];
				individual.gene[m2+1] = aux[m1+1];
				individual.gene[m2+2] = aux[m1+2];
				for (int i = m2+3; i < m1 + 2; i++) {
					individual.gene[i] = aux[m2++];
				}
			} else {
				// insert m2 - m2+2 at m1 and shift everything right
				individual.gene[m1] = aux[m2];
				individual.gene[m1+1] = aux[m2+1];
				individual.gene[m1+2] = aux[m2+2];
				for (int i = m1+3; i < m2 + 2; i++) {
					individual.gene[i] = aux[m1++];
				}
				
			}
		} 
		
		else {
			mutation1(individual, m1, m2);  // vertices selected were not optimal for mutation2
		}
	}

	
	/* Code for Debugging
	public static void main( String[] args )
    {
		 Random random = new Random(10);
	     int n = 5;
	     WeightedGraph g = new WeightedGraph(n);
	     // generate a complete graph with n vertices
	     for (int i = 0; i < n; i++) {
	    	 for(int j=i+1; j<n; j++) {
	        	int weight = random.nextInt(5)+1;
	        	WeightedEdge e = new WeightedEdge(i, j, weight); //create edge with weight between 1 and 5
	        	g.addEdge(e);
	        }
	     }
	     GeneticAlgorithm gam = new GeneticAlgorithm(g);
	     gam.evolve();
	     Individual[] members = gam.p.sortOnFitness();
	     for(Individual i : members) {
	    	 System.out.println(i.toString());
	     }
	     System.out.println("Run 2");
	     gam.evolve();
	     Individual[] members2 = gam.p.sortOnFitness();
	     for(Individual i : members2) {
	    	 System.out.println(i.toString());
	     }
	     
	     System.out.println("Run 3");
	     gam.evolve();
	     Individual[] members3 = gam.p.sortOnFitness();
	     for(Individual i : members3) {
	    	 System.out.println(i.toString());
	     }
	     //gam.evolve();
	     //gam.evolve();
	     //gam.evolve();
	     
	     
	     Individual[] children = new Individual[gam.p.size]; // with every generation the population doubles after culling
		 Individual in = gam.p.last;
		 for(int i=0; i < gam.p.size; i++) {
			children[i] = new Individual(in.gene, null, null);
			in = in.previous;
		}
		System.out.println("Test 1");
		for (Individual c : children) {
			System.out.println(c.toString());
		}
		
		Random rand1 = new Random();
		for(int i=0; i<children.length-1; i+=2) {
			gam.crossOver(rand1.nextInt(gam.p.g.V()), children[i], children[i+1]);
		}
		
		System.out.println("Test 2");
		for (Individual c : children) {
			c.calculateFitness(g);
			System.out.println(c.toString());
		}
		
		for(int i=0; i < children.length; i++) {
			gam.mutate(children[i]);
		}
		
		System.out.println("Test 3");
		for (Individual c : children) {
			c.calculateFitness(g);
			System.out.println(c.toString());
		}
		
		System.out.println("Test 4");
		for(Individual c : children) {
			gam.p.addMember(c);
		}
		Individual[] members1 = gam.p.sortOnFitness();
		for (Individual ind : members1) {
			System.out.println(ind.toString());
		}
		
		System.out.println("Test 5");
		 // the most fit individual of the survivors
		gam.cull(gam.p);
		Individual[] members2 = gam.p.sortOnFitness();
		for (Individual ind : members2) {
			System.out.println(ind.toString());
		}
		
	    
    }*/

}
