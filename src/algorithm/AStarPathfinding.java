package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class AStarPathfinding {

	int width, height;
	int map[][];
//	Node[][] nodes;

	public class Node implements Comparable<Node> {
		public int row, col;
		public int gCost, hCost;
		Node parent;

		public Node(int row, int col) {
			this.col = col;
			this.row = row;
		}

		public int getFCost() {
			return this.gCost + this.hCost;
		}

		@Override
		public int compareTo(Node that) {
			int result = Integer.compare(this.getFCost(), that.getFCost());
			return result;
		}

	}

	public List<Node> findPath(int[][] map, int startRow, int startCol, int destRow, int destCol) {
		width = map[0].length;
		height = map.length;
		this.map = map;

		Node startNode = new Node(startRow, startCol);

		PriorityQueue<Node> openNodes = new PriorityQueue<>();
		openNodes.add(startNode);
		HashMap<String, Node> nodes = new HashMap<>();
		nodes.put(startNode.row+","+startNode.col, startNode);

		while (!openNodes.isEmpty()) {
			Node currentNode = openNodes.poll();
			if (currentNode.row == destRow && currentNode.col == destCol) {
				List<Node> path = new ArrayList<>();
				while (currentNode != startNode) {
					path.add(currentNode);
					currentNode = currentNode.parent;
				}
				Collections.reverse(path);
				return path;
			}
			for (Node node : getChildrenOf(currentNode)) {
				if(nodes.containsKey(node.row+","+node.col)) continue;
				node.gCost = currentNode.gCost + 1;
                node.hCost =  Math.abs(node.row - destRow) + Math.abs(node.col - destCol);
				openNodes.add(node);
				nodes.put(node.row+","+node.col, node);
			}
		}
		return Collections.emptyList();
	}

	private List<Node> getChildrenOf(Node node) {
		List<Node> result = new ArrayList<>();
		int rowDir[] = { 0, 1, 0, -1 };
		int colDir[] = { 1, 0, -1, 0 };
		for (int i = 0; i < 4; i++) {
			int childRow = node.row + rowDir[i];
			int childCol = node.col + colDir[i];
			if (childRow >= 0 && childRow < height && childCol >= 0 && childCol < width && map[childRow][childCol] == 0) {
				Node childNode = new Node(childRow, childCol);
				childNode.parent = node;
				result.add(childNode);
			}
		}
		return result;
	}

}
