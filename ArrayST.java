import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ArrayST<Key, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] values;
    private int N;  // number of element in the table

    // Create a symbol table with INIT_CAPACITY.
    public ArrayST() {
        this(INIT_CAPACITY);
    }

    // Create a symbol table with given capacity.
    public ArrayST(int capacity) {
        this.keys = (Key[]) new Comparable[capacity];
        this.values = (Value[]) new Object[capacity];
        this.N = capacity;
    }

    // Return the number of key-value pairs in the table.
    public int size() {
        return this.N;
    }

    // Return true if the table is empty and false otherwise.
    public boolean isEmpty() {
        return size() == 0;
    }

    // Return true if the table contains key and false otherwise.
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        return get(key) != null;
    }

    // Return the value associated with key, or null.
    public Value get(Key key) {
        if (key == null) {
            return null;
        }
        for (int i = 0; i < this.N; i++) {
            if (key.equals(this.keys[i])) {
                return this.values[i];
            }
        }
        return null;
    }

    // Put the kev-value pair into the table; remove key from table
    // if value is null.
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }

        delete(key);    // delete duplicate.

        if (this.N >= this.keys.length) {
            resize(2 * this.N);
        }

        // We already hanlde out of bound
        // So we can add at the end.
        this.keys[this.N] = key;
        this.values[this.N] = value;
        this.N++;
    }

    // Remove key (and its value) from table.
    public void delete(Key key) {
        if (isEmpty()) {
            return;
        }
        for (int i = 0; i < this.N; i++) {
            if (key.equals(this.keys[i])) {
                // Found the target key
                // Swap the target key with the last key and delete the last key
                // Can do this because order doesn't matter in this data structure
                this.keys[i] = this.keys[this.N - 1];
                this.values[i] = this.values[this.N - 1];
                this.keys[this.N - 1] = null;
                this.values[this.N - 1] = null;
                this.N--;
                if (this.N > 0 && this.N == this.keys.length / 4) {
                    resize(this.keys.length / 2);
                }
                return;
            }
        }

    }

    // Return all the keys in the table.
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < this.keys.length; i++) {
            if (this.keys[i] != null) {
                queue.enqueue(this.keys[i]);
            }
        }
        return queue;
    }

    // Resize the internal arrays to capacity.
    private void resize(int capacity) {
        Key[] tempk = (Key[]) new Object[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            tempk[i] = keys[i];
            tempv[i] = values[i];
        }
        values = tempv;
        keys = tempk;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        ArrayST<String, Integer> st = new ArrayST<String, Integer>();
        int count = 0;
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            st.put(s, ++count);
        }
        for (String s : args) {
            st.delete(s);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
