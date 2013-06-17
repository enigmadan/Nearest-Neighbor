public class Neighbor implements Comparable<Neighbor>{
	double dist;
	DataObject neighbor;

	public Neighbor(double d, DataObject o){
		dist= d;
		neighbor= o;
	}
	
	public int compareTo(Neighbor n){
		return this.dist > n.dist ? 1: this.dist < n.dist ? -1: 0;
	}
}
