package info6205.project.GeneticAlgorithm;

public class Individual {
	// Properties:
	int[] gene;
	float score;
	Individual next;
	Individual previous;
	
	// Constructor
	public Individual(int[] gene, Individual next, Individual previous) {
		this.gene = gene;
		this.score=0;
		this.next=next;
		this.previous=previous;
	}
	
	// Getter and Setters
	public int getBase(int i) { return gene[i]; }
	public void setBase(int i, int j) { gene[i] = j; }
	public int size()  { return gene.length; }
	
	public int getIndex(int vertex) {
		int index = -1;
		for(int i = 0; i < this.gene.length; i++) {
			if (gene[i] == vertex) { 
				index = i;
				break;
			}
		}
		return index;
	}

	// Other Methods
	public void calculateFitness(WeightedGraph g) {
		
		Iterable<WeightedEdge> b = g.edges();
		int sum = 0;
		// add the edge weights between elements in the array
		for( int i = 0; i < gene.length-1; i++) {
			for(WeightedEdge e : b){
				if ((e.v()==gene[i] && e.w()==gene[i+1]) || (e.w()==gene[i] && e.v()==gene[i+1])){
					sum+= e.weight();
					break; // vertex i has been found, end the iteration through bag for i
				}
			}
		}
		// add the weight for the final edge
		for(WeightedEdge e : b){
			if ((e.v()==gene[gene.length-1] && e.w()==gene[0]) || (e.w()==gene[0] && e.v()==gene[gene.length-1])){
				sum+= e.weight();
				break; // vertex has been found, end the iteration through bag
			}
		}
		this.score = 1/sum;  // fitness score is inverse of the sum of the weights
	}

	public int compareTo(Individual that) {
		if (this.score < that.score) return -1;
		else if (this.score > that.score) return 1;
		else return 0;
	}
	
	// toString method TODO
	public String toString() {
		String s = "";
		for (int i : gene) {
			s += i +", ";
		}
		return "Order of Cities: " + s + "Fitness: " + this.score;
	}
	
	
}
