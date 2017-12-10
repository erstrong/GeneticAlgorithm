package info6205.project.GeneticAlgorithm;

import java.util.Random;

public class Population {
	// Properties
	// Population is a doubly linked list
	Individual first;
	Individual last;
	int size;
	WeightedGraph g;
	
	// Constructors
	public Population(WeightedGraph g) {
		this.g = g;
		this.first = null;
		this.last=null;
		this.size=0;
	}
	
	public Population(WeightedGraph g, boolean seed) {
		this.g=g;
		this.first = null;
		this.last=null;
		this.size=0;
		if (seed) { seed(g.V()); } // creates 4 individuals to start the population
	}
	
	
	// Getters and Setters
	public Individual getFirst() { return first; }
	public int size() { return size; }
	
	public void addMember(Individual newmember) {
		newmember.previous = last;
		last.next = newmember;
		this.last = newmember;
		this.size++;
	}
	
	public void removeMember(Individual oldmember) {
		
		oldmember.previous.next = oldmember.next;
		oldmember.next.previous = oldmember.previous;
		
		if(oldmember==this.last) { this.last = oldmember.previous; }
		if(oldmember==this.first) { this.first = oldmember.next; }
		
		oldmember.next = null;
		oldmember.previous = null;
		this.size--;
	}
	
	// Sort
	public Individual[] sortOnFitness(){
		Individual[] sortlist = new Individual[this.size];
		Individual in = last;
		for (int i =0;i < this.size; i++) {
			in.calculateFitness(this.g);
			sortlist[i] = in;
			in=in.previous;
		}
		sort(sortlist);
		return sortlist;
	}
	
	// Private Methods
	private static void sort(Individual[] list) {
		int n = list.length;
		for (int i = 1; i < n; i++) 
			for (int j = i; j > 0 && list[j].compareTo(list[j-1]) == -1; j--) {
				exch(list, j, j-1);
			}
	}
	
	private static void exch(Individual[] array, int i, int j) {
		Individual a = array[i];
		array[i] = array[j];
		array[j] = a;
	}
	
	private void seed(int n) {
		// we seed the population with 4 members
		for(int i=0; i<4; i++) {
			int[] array = new int[n];
			// create array of 1 through n
			for (int j = 0; j < n; j++) { array[j-1] = j; }
			// shuffle the array using the Fisher-Yates method
			Random rnd = new Random();
			for (int j = array.length-1; j >0; j--) {
				int index = rnd.nextInt(j+1);
				int a = array[index];
				array[index] = array[j];
				array[j] = a;
			}
			Individual x = new Individual(array, null, last); 
			if(last!= null) {last.next = x;} // put new individual as the "next" for the last link
			last = x; // set new individual as the last link
			if (i == 0) this.first = x; // set the very first individual created as the first link
			this.size++;
		}
	}
	
}
