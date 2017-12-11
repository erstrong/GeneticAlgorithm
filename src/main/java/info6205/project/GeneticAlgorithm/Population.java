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
		
		if(oldmember.previous != null) {oldmember.previous.next = oldmember.next;}
		if(oldmember.next != null) {oldmember.next.previous = oldmember.previous;}
		
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
			// create array of 0 through n-1
			for (int j = 0; j < n; j++) { array[j] = j; }
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
	/*
	//Code for Debugging
	public static void main( String[] args )
    {
		 WeightedGraph g = new WeightedGraph(4);
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if(i!=j) {
						int w = 1;
						if(i==2 && j==3) { w=2; };
						WeightedEdge e = new WeightedEdge(i, j, w);
						g.addEdge(e);
						System.out.println(i + " " + j + " " + w);
					}
				}
			}
			
			Population p = new Population(g, true);
			 int[] a = {0, 1, 2, 3};
			 p.addMember(new Individual(a, null, null));
			 int[] b = {1,2,0,3};
			 p.addMember(new Individual(b, null, null));
			 System.out.println(p.size());
			 Individual[] members = p.sortOnFitness();
			 for(Individual c : members) {
				 System.out.println(c.toString());
			 }
    
		 
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
	     Population p = new Population(g, true);
	     Individual[] members = p.sortOnFitness();
	     System.out.println("Test 1");
	     for(Individual i : members) {
	    	 System.out.println(i.toString());
	     }
	     System.out.println("First: " + p.first.toString());
	     int[] array={0,1,2,3,4};
	     Individual i = new Individual(array,null,null);
	     
	     //verify addMember works
	     p.addMember(i);
	     System.out.println("Test 2");
	     Individual[] members2 = p.sortOnFitness();
	     for(Individual i2 : members2) {
	    	 System.out.println(i2.toString());
	     }
	     
	     p.removeMember(i);
	     System.out.println("Test 3");
	     Individual[] members3 = p.sortOnFitness();
	     for(Individual i3 : members3) {
	    	 System.out.println(i3.toString());
	     }
	        
    }
	*/
}
