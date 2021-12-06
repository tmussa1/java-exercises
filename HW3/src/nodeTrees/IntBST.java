package nodeTrees;

import java.util.ArrayList;
import java.util.List;

// binary search tree storing integers
public class IntBST extends NodeBinaryTree<Integer> {

	private int size = 0;
	
	public IntBST() {	}

	public int size() {
		return size;
	}

	public void setSize(int s) { size = s; }
	
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Places element e at the root of an empty tree and returns its new Node.
	 *
	 * @param e the new element
	 * @return the Node of the new element
	 * @throws IllegalStateException if the tree is not empty
	 */

	public Node<Integer> addRoot(Integer e) throws IllegalStateException {
		if (size != 0)
			throw new IllegalStateException("Tree is not empty");
		root = createNode(e, null, null, null);
		size = 1;
		return root;
	}

	/**
	 * Print a binary tree horizontally Modified version of
	 * https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
	 * Modified by Keith Gutfreund
	 * 
	 * @param n Node in tree to start printing from
	 */
	
	  public void print(Node<Integer> n){ print ("", n); }
	  
	  public void print(String prefix, Node<Integer> n){
		  if (n != null){
			  print(prefix + "       ", right(n));
			  System.out.println (prefix + ("|-- ") + n.getElement());
			  print(prefix + "       ", left(n));
		  }
	  }
	  
	  
	  public void inorderPrint(Node<Integer> n) {
		if (n == null)
			return;
		inorderPrint(n.getLeft());
		System.out.print(n.getElement() + "  ");
		inorderPrint(n.getRight());
	}

	
	public Iterable<Node<Integer>> children(Node<Integer> n) {
		List<Node<Integer>> snapshot = new ArrayList<>(2); // max capacity of 2 
		if (left(n) != null) 
			snapshot.add(left(n)); 
		if (right(n) != null)
			snapshot.add(right(n)); return snapshot; 
	}
	
	public int height(Node<Integer> n) throws IllegalArgumentException { 
		if (isExternal(n)) { return 0; } 
		int h = 0; // base case if p is external
		for (Node<Integer> c : children(n)) h = Math.max(h, height(c)); return h + 1; 
	}

	/**
	 * Constructs a binary tree from a sorted list
	 * @param a
	 * @return
	 */
	public IntBST makeBinaryTree(int[] a){

	    IntBST intBST = new IntBST();

		// Helper that drives the recursion
		Node<Integer> root = makeBinaryTree(a,0, a.length);

		intBST.root = root; // Set the root and size
		intBST.setSize(a.length);

		return intBST;
	}

	/**
	 * Helper function to construct a binary tree from a sorted array
	 * @param a
	 * @param start
	 * @param end
	 * @return
	 */
	private Node<Integer> makeBinaryTree(int[] a, int start, int end) {

	    if(start >= end){
		  return null; // Base case
	    }

	    int mid = start + ((end - start) / 2); // Calculates mid point

		Node<Integer> root = createNode(a[mid], null, null, null);

		// Call the left side recursively
		Node<Integer> left = makeBinaryTree(a, start, mid);
		if(left != null) {
			left.setParent(root); // Set parent/child relationship
			root.setLeft(left);
		}

		// Call the right side recursively
		Node<Integer> right = makeBinaryTree(a, mid + 1, end);
		if(right != null){
			right.setParent(root); // Set parent/child relationship
			root.setRight(right);
		}

		return root;
	}
}
