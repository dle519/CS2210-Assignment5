import java.util.Iterator;
import java.util.Vector;
import java.util.ArrayList;

/**
 * Creates a Graph class using an adjacent list representation.
 * 
 * @author David Le
 *
 */
public class Graph implements GraphADT {
	Vector<GraphNode> nodeList;
	Vector<ArrayList<GraphEdge>> edgeList;

	/**
	 * Creates a Graph object with a nodeList consisting with all the nodes and an
	 * edgeList consisting of empty ArrayList for each node
	 * 
	 * @param n
	 */
	public Graph(int n) {
		// Creating the nodeList and edgeList
		nodeList = new Vector<GraphNode>(n);
		edgeList = new Vector<ArrayList<GraphEdge>>(n);
		// Creating all the node objects into the nodeList
		for (int i = 0; i < n; i++) {
			nodeList.add(i, new GraphNode(i));
			edgeList.add(i, new ArrayList<GraphEdge>(i));
		}
	}

	@Override
	public void insertEdge(GraphNode u, GraphNode v, char busLine) throws GraphException {
		// Throw exception for node not existing or there is a edge connecting the nodes
		if (u.equals(null) || v.equals(null)) {
			throw new GraphException("Node does not exist");
		} else {
			// Declaration of the nodes
			int first = u.getName();
			int second = v.getName();
			// Creating references to the ArrayList of each node index
			ArrayList<GraphEdge> firstList = edgeList.get(first);
			ArrayList<GraphEdge> secondList = edgeList.get(second);
			GraphEdge edge = new GraphEdge(u, v, busLine);
			// Check if the vector already has this edge.
			if (firstList.contains(edge) || secondList.contains(edge)) {
				throw new GraphException("Edge already exists!");
			} else {
				// Add the edge to both of the ArrayLists in the edgeList.
				firstList.add(edge);
				secondList.add(edge);
			}
		}

	}

	@Override
	public GraphNode getNode(int name) throws GraphException {
		// Throw exception if node is not within the graph.
		if (name > this.nodeList.size() || name < 0) {
			throw new GraphException("Node is not within the graph");
		} else {
			return this.nodeList.elementAt(name);
		}
	}

	@Override
	public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException {
		// If given node does not exist
		if (u.equals(null)) {
			throw new GraphException("Node does not exist");
		}
		int index = u.getName(); // Index of the given node in the lists
		ArrayList<GraphEdge> incidentList = edgeList.get(index);
		// If there are no edges, return null
		if (incidentList.isEmpty()) {
			return null;
		}
		// Returns iterator of the incidentList
		else {
			return incidentList.iterator();
		}
	}

	@Override
	public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {
		// If given nodes does not exist
		if (u.equals(null) || v.equals(null)) {
			throw new GraphException("Node does not exist");
		}
		int index = u.getName(); // Index
		ArrayList<GraphEdge> currentList = edgeList.get(index);
		// Traversing the edgelist at the index of node u
		for (int i = 0; i < currentList.size(); i++) {
			GraphEdge currentEdge = currentList.get(i); // Current edge
			// If the current edge has the endpoints equal to the nodes given as parameters
			if (currentEdge.firstEndpoint().equals(u) && currentEdge.secondEndpoint().equals(v)) {
				return currentEdge;
			}
		}
		// If there was no edge connecting the 2 nodes together
		throw new GraphException("There is no edge connected!");
	}

	@Override
	public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {
		// If the given nodes do not exist
		if (u.equals(null) || v.equals(null)) {
			throw new GraphException("Node does not exist");
		} else {
			int index = u.getName(); // Index
			ArrayList<GraphEdge> currentList = edgeList.get(index);
			// Traversing the edgelist at the index of first node u
			for (int i = 0; i < currentList.size(); i++) {
				GraphEdge currentEdge = currentList.get(i); // Current edge
				// If the current edge has the second endpoint equal to the one given
				if (currentEdge.secondEndpoint().equals(v)) {
					return true;
				}
			}
			return false;
		}
	}

}
