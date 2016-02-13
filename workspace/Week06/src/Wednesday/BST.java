package Wednesday;

public class BST<T extends Comparable<T>> {
	private class Node implements Comparable<Node> {
		public T value;
		public int depth;
		public int height;
		public Node left;
		public Node right;
		public Node parent;

		Node(T val) {
			this.value = val;
			this.depth = 0;
			this.height = 0;
			this.left = null;
			this.right = null;
			this.parent = null;
		}

		public void print() {
			System.out.println("LEFT: ");
			if (this.left != null) {
				this.left.print();
			} else {
				System.out.println("NONE");
			}
			System.out.println("Value" + this.value + "Depth, height:" + 
			this.depth + " " + this.height);
			System.out.println("RIGHT: ");
			if (this.right != null) {
				this.right.print();
			} else {
				System.out.println("NONE");
			}

		}

		@Override
		public int compareTo(BST<T>.Node other) {
			return this.value.compareTo(other.value);
		}

		public boolean isLeftChild() {
			if (this == this.parent.left) {
				return true;
			}
			return false;
		}
	};

	private Node root;

	BST() {
		root = null;
	}

	public void print() {
		if (root != null) {
			root.print();
		}
	}

	public void add(T element) {
		if (root == null) {
			root = new Node(element);
			root.height = 0;
			root.depth = 0;
		} else {
			addTo(new Node(element), root);
		}
	}
	
	private Node findElement(T element) {
		Node curr = root;
		while (curr != null) {
			if (curr.value.equals(element)) {
				return curr;
			} else {
				if (curr.value.compareTo(element) == 1) {
					curr = curr.left;
				} else {
					curr = curr.right;
				}
			}

		}
		return null;
	}
	
	public void remove(T element) {
		Node deleted = findElement(element);
		if (deleted != null) {
			removeNode(deleted);
		}
	}
	
	public boolean contains(T element) {
		return findElement(element) != null;
	}
	
	public int getHeight(T element) {
		Node elementNode = findElement(element); 
		if (elementNode == null) {
			return -1;
		}
		return elementNode.height;
	}
	
	public int getdepth(T element) {
		Node elementNode = findElement(element); 
		if (elementNode == null) {
			return -1;
		}
		return elementNode.depth;
	}
	
	private void removeNode(Node deleted) {
		Node swap = findSwap(deleted);
		if (swap == null) {
			if (deleted == root) {
				root = null;
			}
			else {
				if (deleted.isLeftChild()) {
					deleted.parent.left = null;
				} else {
					deleted.parent.right = null;
				}
			}
		}
		// swap the elements
		deleted.value = swap.value;
		//remove swap from tree
		if (swap.isLeftChild()) {
			swap.parent.left = swap.left;
		} else {
			swap.parent.right = swap.left;
		}
		if (swap.left != null) {
			swap.left.parent = swap.parent;
			// update height
			updateHeight(swap.left);
		} else {
			//swap is leaf, update height
			if (swap.parent.right != null) {
				swap.parent.height = swap.parent.right.height + 1; 
			} else {
				swap.parent.height = 0;
			}
			updateHeight(swap.parent);
		}
	}

	private void addTo(Node newNode, Node parent) {
		if (parent.compareTo(newNode) == -1) {
			if (parent.right == null) {
				parent.right = newNode;
				newNode.parent = parent;
				newNode.depth = parent.depth + 1;
				updateHeight(newNode);
			} else {
				addTo(newNode, parent.right);
			}
		} else {
			if (parent.left == null) {
				parent.left = newNode;
				newNode.parent = parent;
				newNode.depth = parent.depth + 1;
				updateHeight(newNode);
			} else {
				addTo(newNode, parent.left);
			}
		}
	}

	private void updateHeight(Node current) {
		if (current.parent != null) {
			if (current.parent.left == null || current.parent.right == null) {
				current.parent.height = current.height + 1;
			} else {
				int lh = current.parent.left.height;
				int rh = current.parent.right.height;
				current.parent.height = Math.max(lh, rh) + 1;
			}
			updateHeight(current.parent);
		}
	}

	private Node findSwap(Node deleted) {
		Node curr = deleted.left;
		if (curr == null) {
			return deleted.right;
		} else {
			while (curr.right != null) {
				curr = curr.right;
			}
			return curr;
		}
	}

}
