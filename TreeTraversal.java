import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class TreeTraversal {
    private Node root; // root of the binary search tree

    // Representation of a binary search tree
    private class Node {
        private String item;      // node item
        private Node left, right; // left and right subtrees

        // Construct a Node given its item.
        Node(String item) {
            this.item = item;
        }

        // Return a string representation of the node.
        public String toString() {
            return item;
        }
    }

    // Put the item into the tree.
    public void put(String item) {
        root = put(root, item);
    }

    // Helper for put(String item).
    private Node put(Node x, String item) {
        if (x == null) return new Node(item);
        int cmp = item.compareTo(x.item);
        if (cmp < 0) x.left = put(x.left, item);
        else if (cmp > 0) x.right = put(x.right, item);
        return x;
    }

    // Return the nodes of the tree traversed pre-order.
    public Iterable<Node> preOrder() {
        Queue<Node> queue = new Queue<Node>();
        preOrder(this.root, queue);
        return queue;
    }

    // Helper for preOrder().
    // Root Left Right
    private void preOrder(Node x, Queue<Node> q) {
        if (x != null) {
            q.enqueue(x);
            preOrder(x.left, q);
            preOrder(x.right, q);
        }
    }

    // Return the nodes of the tree traversed in-order.
    public Iterable<Node> inOrder() {
        Queue<Node> queue = new Queue<Node>();
        inOrder(this.root, queue);
        return queue;
    }

    // Helper for inOrder().
    // Left Root Right
    private void inOrder(Node x, Queue<Node> q) {
        if (x != null) {
            inOrder(x.left, q);
            q.enqueue(x);
            inOrder(x.right, q);
        }
    }

    // Return the nodes of the tree traversed post-order.
    public Iterable<Node> postOrder() {
        Queue<Node> queue = new Queue<Node>();
        postOrder(this.root, queue);
        return queue;
    }

    // Helper for postOrder().
    // Left Right Root
    private void postOrder(Node x, Queue<Node> q) {
        if (x != null) {
            postOrder(x.left, q);
            postOrder(x.right, q);
            q.enqueue(x);
        }
    }

    // Return the nodes of the tree traversed level-order.
    public Iterable<Node> levelOrder() {
        Queue<Node> queue = new Queue<Node>();  // use for traversing through the tree
        Queue<Node> result = new Queue<Node>(); // result to return
        queue.enqueue(this.root);
        while (!queue.isEmpty()) {
            Node current = queue.dequeue();
            result.enqueue(current);
            if (current.left != null) {
                queue.enqueue(current.left);
            }
            if (current.right != null) {
                queue.enqueue(current.right);
            }
        }
        return result;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String[] items = StdIn.readAllStrings();
        TreeTraversal tree = new TreeTraversal();
        for (String item : items) {
            tree.put(item);
        }
        StdOut.println("Pre-order:   " + tree.preOrder());
        StdOut.println("In-order:    " + tree.inOrder());
        StdOut.println("Post-order:  " + tree.postOrder());
        StdOut.println("Level-order: " + tree.levelOrder());
    }
}
