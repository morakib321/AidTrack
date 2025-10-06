
package AidTrack;

import java.io.Serializable;

import java.util.*;

public class TreeNode implements Serializable {
	//serialVersionUID is given to this class as a special ID when reading or writing into the file
	private static final long serialVersionUID = 3L;
	//field of ArrayList
	ArrayList<Incident> data;
	//Tree node field of left and right
	TreeNode left;
	TreeNode right;
	
	//Constructor
	public TreeNode(Incident incident) {
		//Initialize the ArrayList
		data = new ArrayList<>();
		//adding the incident to the arraylist of the node
		data.add(incident);
		//initializing the right and left
		left = null;
		right = null;
	}

	public boolean hasLeft() {
		return (left != null && right == null);
	}

	public boolean hasRight() {
		return (right != null && left == null);
	}

	public boolean hasTwoChildren() {
		return left != null && right != null;
	}

	public boolean isLeaf() {
		return left == null && right == null;
	}

}
