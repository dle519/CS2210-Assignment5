/**
 * Creates GraphEdge object using 2 GraphNodes for each end of the edge with a
 * specific type of busline given
 * 
 * @author David Le
 *
 */
public class GraphEdge {
	private GraphNode first;
	private GraphNode second;
	char busLine;

	/**
	 * Creates GraphEdge object given 2 GraphNodes for each end and the type of
	 * busline
	 * 
	 * @param u       First endpoint of the GraphEdge
	 * @param v       Last endpoint of the GraphEdge
	 * @param busLine Type of busline the GraphEdge is
	 */
	public GraphEdge(GraphNode u, GraphNode v, char busLine) {
		this.first = u;
		this.second = v;
		this.busLine = busLine;

	}

	/**
	 * 
	 * @return The first GraphNode endpoint of the current GraphEdge object
	 */
	public GraphNode firstEndpoint() {
		return this.first;
	}

	/**
	 * 
	 * @return The last GraphNode endpoint of the current GraphEdge object
	 */
	public GraphNode secondEndpoint() {
		return this.second;
	}

	/**
	 * 
	 * @return The busline type of the current GraphEdge object
	 */
	public char getBusLine() {
		return this.busLine;

	}

}
