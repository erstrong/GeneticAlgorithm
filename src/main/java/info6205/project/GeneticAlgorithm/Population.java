package info6205.project.GeneticAlgorithm;

import java.util.Random;

public class Population {
	// Properties
	Individual first;
	int size;
	
	// Constructors
	public Population() {
		this.first = null;
		this.size=0;
	}
	
	public Population(int n) {
		this.size=0;
		seed(n);
	}
	
	
	// Getters and Setters
	
	public Individual getFirst() { return first; }
	public int size() { return size; }
	
	public void addMember(Individual newmember) {
		newmember.next = first;
		this.first = newmember;
		this.size++;
	}
	
	// get the fittest individual  TODO
	
	// Private Methods
	
	private void seed(int n) {
		// we seed the population with 4 members
		for(int i=0; i<4; i++) {
			int[] array = new int[n];
			// create array of 1 through n
			for (int j = 0; j < n; j++) { array[j-1] = j; }
			// shuffle the array using the Fisher-Yates shuffle
			Random rnd = new Random();
			for (int j = array.length-1; j >0; j--) {
				int index = rnd.nextInt(j+1);
				int a = array[index];
				array[index] = array[j];
				array[j] = a;
			}
			this.first = new Individual(array, first);		
			this.size++;
		}
	}
	
}
