import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class representing the city with buslines. Graph is used to model the map and
 * has a method to find a path from the starting point to the destination point
 * 
 * @author David Le
 *
 */
public class BusLines {
	private int width;
	private int length;
	private int busLineChanges;
	private int nodes;
	private int startingPoint;
	private int destinationPoint;
	private Graph graph;

	/**
	 * Creates a BusLines object by reading in the textfile and creating the graph
	 * 
	 * @param inputFile Name of the text file
	 * @throws MapException   If there is no file given
	 * @throws GraphException
	 */
	public BusLines(String inputFile) throws MapException, IOException, GraphException {
		// Reading file name
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		if (in.equals(null)) { // If there is no file
			throw new MapException("File does not exist!");
		} else {
			// Reading the first line
			nodes = 0;
			String line = in.readLine();
			String[] wordList = line.split(" "); // Used to get the total number of nodes
			ArrayList<String> tempCharList = new ArrayList<String>(); // Will be used to insert graphedges into the
																		// graph
																		// object
			// Storing given values from text file into global variables
			width = Integer.parseInt(wordList[1]);
			length = Integer.parseInt(wordList[2]);
			busLineChanges = Integer.parseInt(wordList[3]);
			// Skip to the next line
			line = in.readLine();
			tempCharList.add(line);
			// Traversing the text file until an empty line
			while (line != null) {
				char[] charList = line.toCharArray();
				int counter = 0;
				// Read each character in a single line and counting the number of nodes in the
				// text file
				while (counter < charList.length) {
					char readChar = charList[counter];
					// If the characters are a node
					if (readChar == 'S') {
						startingPoint = nodes;
						nodes++;
					} else if (readChar == 'D') {
						destinationPoint = nodes;
						nodes++;
					} else if (readChar == '.') {
						nodes++;
					}
					counter++;
				}
				// Reads the next line
				line = in.readLine();
				// Adding each input line into the tempList --> Used later for adding graphedges
				// into the graph object
				if (line != null) {
					tempCharList.add(line);
				}
			}
			graph = new Graph(nodes);
			// Adding all the edges into the graph from textfile
			int counter2 = 0;
			while (counter2 < tempCharList.size()) {
				int index = 0;
				char[] list = tempCharList.get(index).toCharArray();
				// Traversing the character list
				while (index < list.length) {
					char currentChar = list[index];
					// If index is even
					if ((index % 2) == 0) {
						// If current letter is a busline; add the edge to graph object
						if ((currentChar != 'S' || currentChar != 'D' || currentChar != '.' || currentChar != ' ')
								&& (Character.isLetter(currentChar) || Character.isDigit(currentChar))) {
							graph.insertEdge(graph.getNode(counter2), graph.getNode(counter2 + width), currentChar);
						} else {
							throw new GraphException("Problem inserting edge!");
						}
					}
					// If index is odd
					else {
						if ((currentChar != 'S' || currentChar != 'D' || currentChar != '.' || currentChar != ' ')
								&& (Character.isLetter(currentChar) || Character.isDigit(currentChar))) {
							graph.insertEdge(graph.getNode(counter2), graph.getNode(counter2 + 1), currentChar);
						} else {
							throw new GraphException("Problem inserting edge!");
						}
					}
					counter2++;
				}
			}
		}

	}

	/**
	 * 
	 * @return The graph created from the constructor
	 * @throws MapException If the graph does not exist
	 */
	public Graph getGraph() throws MapException {
		if (this.graph.equals(null)) {
			throw new MapException("Graph cannot be created");
		} else {
			return this.graph;
		}
	}

	/**
	 * Helper method to recursively call on the incident edges for the current node
	 * 
	 * @param current         Current Node
	 * @param destinationNode End Node
	 * @param busChanges      Number of Bus Changes left
	 * @param currentPath     List of the path from start to the destination
	 */
	private void tripRecursive(GraphNode current, GraphNode destinationNode, int busChanges,
			ArrayList<GraphNode> currentPath) throws GraphException {
		char currentBusLine;
		GraphEdge currentEdge;
		GraphNode nextNode;
		if (current != null) {
			// Base Case --> If current node is the destination
			if (current.equals(destinationNode)) {
				currentPath.add(destinationNode);
			}
			// Recursive Case
			else {
				Iterator<GraphEdge> list = graph.incidentEdges(current);
				// Iterating through incidentEdge list of the current node
				while (list.hasNext()) {
					currentEdge = list.next();
					nextNode = currentEdge.secondEndpoint();
					currentBusLine = currentEdge.getBusLine();
					// Add next node to the list
					if (nextNode.getMark() == false) {
						currentPath.add(nextNode);
						nextNode.setMark(true);
						tripRecursive(nextNode, destinationNode, currentBusLine, currentPath);
					}
					// If the current array does not have the destination node, remove the last node
					if (!currentPath.contains(destinationNode)) {
						currentPath.remove(currentPath.size() - 1);
					}
				}
			}
		}
	}

	/**
	 * Finds a path from the starting point to the destination point given in the
	 * text file
	 * 
	 * @return An iterator for the path from starting point to destination point
	 * @throws GraphException
	 */
	Iterator<GraphNode> trip() throws GraphException {
		GraphNode start = graph.getNode(startingPoint);
		GraphNode destination = graph.getNode(destinationPoint);
		ArrayList<GraphNode> path = new ArrayList<GraphNode>();
		path.add(start);
		start.setMark(true);
		tripRecursive(start, destination, this.busLineChanges, path);

		return path.iterator();
	}
}
