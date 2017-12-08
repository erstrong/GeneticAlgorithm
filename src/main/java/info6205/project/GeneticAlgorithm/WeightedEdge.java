package info6205.project.GeneticAlgorithm;

// This code is based on code provided in the textbook, pp 610-611
public class WeightedEdge {
	private final int v;
	private final int w;
	private final double weight;
	
	public WeightedEdge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	// Getters
	public double weight() { return weight; }
	public int v() { return v; }
	public int w() { return w; }
	
	public int other(int vertex) {
		if (vertex==v) return w;
		else if (vertex==w) return v;
		else throw new RuntimeException("Inconsistent edge");
	}
	
	// Other Methods
	public int compareTo(WeightedEdge that) {
		if (this.weight() < that.weight()) return -1;
		else if (this.weight() > that.weight()) return 1;
		else return 0;
	}
}
