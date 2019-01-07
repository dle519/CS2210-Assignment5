/**
 * GraphNode class for assignment 5 in CS2210
 * 
 * @author David Le 250849011
 */
public class GraphNode {
	boolean mark;
	int name;

	/**
	 * Creates a GraphNode object with the given name and unmarked.
	 * 
	 * @param name value from 0 to n-1 where n ins number of nodes in the graph
	 */
	public GraphNode(int name) {
		this.mark = false; // Unmarked
		this.name = name;
	}

	/**
	 * Changes the mark of the object node
	 * 
	 * @param mark True or False
	 */
	public void setMark(boolean mark) {
		this.mark = mark; // Changing the mark of the object node
	}

	/**
	 * @return The current mark of the GraphNode object
	 */
	public boolean getMark() {
		return this.mark;
	}

	/**
	 * @return The current name of the GraphNode object
	 */
	public int getName() {
		return this.name;
	}

}
