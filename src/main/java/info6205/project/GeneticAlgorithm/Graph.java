package info6205.project.GeneticAlgorithm;

import java.util.Iterator;


public class Graph {
	private final int V; 			// vertices
	private int E; 					// edges
	private Bag<Integer>[] adj; 	// adjacency lists
	
	public Graph(int V) {
		this.V = V;
		this.E= 0;
		this.adj = (Bag<Integer>[]) new Bag[V]; // array of lists
		for (int v = 0; v < V; v++)
			adj[v] = new Bag<Integer>();	
	}
	
	public int V() { return V; }
	public int E() { return E; }
	
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	
	public Iterable<Integer> adj(int v) {
		return (Iterable<Integer>) adj[v];
	}
	
	
	
	private class Bag<Item> {
		private Node first; 		// top of bag
		private int n; 				// number of items
		
		private class Node {
			// nested class to define nodes
			Item item;
			Node next;
		}
		
		public boolean isEmpty() { return first == null; }
		public int size() { return n; }
		
		public void add(Item item) {
			Node oldfirst = first;
			first = new Node();
			first.item = item;
			first.next = oldfirst;
		}
		
		public Iterator<Item> iterator() {
			return new ListIterator();
		}
		
		public class ListIterator implements Iterator<Item> {
			private Node current = first;
			public boolean hasNext() { return current != null; }
			public void remove() {}
			public Item next() {
				Item item = current.item;
				current = current.next;
				return item;
			}
		}

		
	}
	
	
}
