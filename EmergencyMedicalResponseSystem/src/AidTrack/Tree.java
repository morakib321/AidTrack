
package AidTrack;

public interface Tree<Incident> extends Iterable<Incident> {
	/** Return true if the id of an element is in the tree */
	public Incident search(int id);

	/**
	 * Insert element e into the binary search tree. Return true if the element is
	 * inserted successfully.
	 */
	public boolean insert(Incident e);

	/**
	 * Delete the specified element from the tree. Return true if the element is
	 * deleted successfully.
	 */
	public boolean delete(Incident e);

	public void update(Incident e);

	/** Inorder traversal from the root */
	public void reverseInorder();

	/** Postorder traversal from the root */
	public void postorder();

	/** Preorder traversal from the root */
	public void preorder();

	/** Get the number of nodes in the tree */
	public int getSize();

	/** Return true if the tree is empty */
	public boolean isEmpty(); 
}
