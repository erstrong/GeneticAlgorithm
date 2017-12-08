package info6205.project.GeneticAlgorithm;

public class Individual {
	// Properties:
	int[] gene;
	float score;
	Individual next;
	
	// Constructor
	public Individual(int[] gene, Individual next) {
		this.gene = gene;
		this.score=0;
		this.next=next;
	}
	
	// Getter and Setters
	public int getBase(int i) { return gene[i]; }
	public void setBase(int i, int j) { gene[i] = j; }
	public int size()  { return gene.length; }
	public float score()  { return score; }

	public void calculateFitness() {
		// calculate fitness score TODO
	}
	
	
	// Other Methods
	// toString method TODO
	
}
