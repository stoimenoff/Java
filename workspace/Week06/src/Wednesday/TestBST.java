package Wednesday;

public class TestBST {
	
	public static void main(String[] args) {
		BST<Integer> tree = new BST<Integer>();
		tree.add(200);
		tree.add(300);
		tree.add(250);
		tree.add(400);
		tree.add(199);
		tree.add(3);
		tree.add(1);
		tree.add(0);
		tree.add(2);
		tree.add(6);
		tree.add(5);
		tree.add(189);
		tree.add(100);
		tree.add(50);
		tree.add(25);
		tree.add(75);
		tree.add(150);
		tree.add(140);
		tree.add(175);
		tree.add(188);
		//tree.print();
		tree.remove(199);
		tree.print();
		System.out.println("Done");
	}
	
}
