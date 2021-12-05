public class Hw2_p4 {

	/**
	 * Calls helper method initializing predecessor to the header element
	 * and successor to the first element
	 * @param intList
	 */
	public static void reverse(DoublyLinkedList<Integer> intList) {
		DoubleLinkNode<Integer> pred = intList.getHeader();
		DoubleLinkNode<Integer> succ = pred.getNext();
		reverse(intList, pred, succ);
	}

	/**
	 * Reverses a linked list using the methods provided
	 * @param intList
	 * @param pred
	 * @param succ
	 */
	private static void reverse(DoublyLinkedList<Integer> intList,
								DoubleLinkNode<Integer> pred, DoubleLinkNode<Integer> succ) {

		Integer node = intList.removeLast(); // Node to add

		if(node.equals(pred.getElement())){
			return;  // Stop when the element removed is equal to the first element before reversal
		}

		// Add element between pred and succ
		intList.addBetween(node, pred, succ);

		pred = succ.getPrev(); // prev advances to the next element in the reversed list

		reverse(intList, pred, succ); // Call reverse recursively
	}
	
	// use the main method for testing
	// test with arrays of different lenghts
	public static void main(String[] args) {

		
		DoublyLinkedList<Integer> intList = new DoublyLinkedList<>();
		
		int[] a = {10, 20, 30, 40, 50};
		for (int i=0; i<a.length; i++) {
			intList.addLast(a[i]);
		}
		System.out.println("Initial list: size = " + intList.size() + ", " + intList.toString());
		
		// Here, invoke the reverse method you implemented above
		reverse(intList);
		
		System.out.println("After reverse: " + intList.toString());
		
		intList = new DoublyLinkedList<>();
		int[] b = {10, 20, 30, 40, 50, 60};
		for (int i=0; i<b.length; i++) {
			intList.addLast(b[i]);
		}
		System.out.println();
		System.out.println("Initial list: size = " + intList.size() + ", " + intList.toString());
		
		// Here, invoke the reverse method you implemented above
		reverse(intList);
		
		System.out.println("After reverse: " + intList.toString());

	}

}