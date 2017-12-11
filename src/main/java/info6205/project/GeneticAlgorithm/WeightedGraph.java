package info6205.project.GeneticAlgorithm;

//This code is based on code provided in the textbook, pp 610-611
public class WeightedGraph {
	private final int V; // number of vertices
	private int E; // number of edges
	private Bag<WeightedEdge>[] adj; // adjacency list
	
	@SuppressWarnings("unchecked")
	public WeightedGraph(int v) {
		this.V = v;
		this.E=0;
		adj = (Bag<WeightedEdge>[]) new Bag[V];
		for (int i = 0; i < V; i++) 
			adj[i] = new Bag<WeightedEdge>();
	}
	
	public int V() { return V; }
	public int E() { return E; }
	
	public void addEdge(WeightedEdge e) {
		int v = e.v();
		int w = e.w();
		adj[v].add(e);
		//System.out.println("v edge added" + v);
		adj[w].add(e);
		//System.out.println("w edge added" + w);
		E++;
	}
	
	public Iterable<WeightedEdge> adj(int v) { return adj[v]; }
	public Iterable<WeightedEdge> edges() {
		Bag<WeightedEdge> b = new Bag<WeightedEdge>();
		for (int v = 0; v < V; v++) 
			for (WeightedEdge e : adj[v])
				if (e.other(v) > v) b.add(e);
		return b;
	}

}
