// FrequencyCounter.java: Reads in a command-line integer and sequence of words
// from standard input and prints out all the words (whose length exceeds the
// threshold) that occur most frequently to standard output. It also prints out
// the number of words whose length exceeds the threshold and the number of
// distinct such words.

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FrequencyCounter {
    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int threshold = Integer.parseInt(args[0]);
        ArrayST<String, Integer> st = new ArrayST<String, Integer>();

        //This loop is for putting the cmd input into the st
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();

            //Ignore words length < threshold
            if (key.length() < threshold) {
                continue;
            }
            words++;    // counting the total amount of letters

            if (st.contains(key)) {
                //Updating the frequency of appearance.
                //st.put() will delete the key and recreate it
                //so we have to get the value of the current key before deleting the key.
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
                distinct++;
            }
        }

        //Find the longest length of the string.
        int maxlength = 0;
        for (String word : st.keys()) {
            if (st.get(word) > maxlength) {
                maxlength = st.get(word);
            }
        }

        //Print the longest strings.
        for (String word : st.keys()) {
            if (st.get(word) == maxlength) {
                StdOut.print(word + " ");
            }
        }
        StdOut.println(maxlength);
        StdOut.println("distinct = " + distinct);
        StdOut.println("words = " + words);
    }
}
