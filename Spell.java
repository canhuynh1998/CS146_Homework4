// Spell.java: Takes a command-line argument specifying the name of the file
// containing common misspellings (a line-oriented file with each
// comma-separated line containing a misspelled word and the correct spelling),
// then reads text from standard input and prints out the misspelled words in
// the text along with the line numbers where they occurred and their correct
// spellings.

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;

public class Spell {
    public static void main(String[] args) {
        SeparateChainingHashST<String, String> st = new SeparateChainingHashST<String, String>();
        In in1 = new In(args[0]);
        int count = 0;
        while (in1.hasNextLine()) {
            String lines = in1.readLine();
            String[] line = lines.split(",");
            String word = line[0];
            String correct = line[1];
            st.put(word, correct);
            //StdOut.println(word + "2" + correct + "1");
            // StdOut.println(line[0] + " " + line[1] + " ");
            count++;
        }
        StdOut.println(st.contains("Ukranian"));
        In in2 = new In(args[1]);
        int line2 = 1;
        while (in2.hasNextLine()) {

            String line = in2.readLine();
            String[] words = line.split(" ");
            for (String letter : words) {
                if (st.contains(letter.toLowerCase())) {
                    // StdOut.println(st.get(letter));
                    StdOut.println(letter + " : " + line2 + " ->" + st.get(letter));
                }
                //StdOut.println(letter + "$" + line2);
            }
            //StdOut.println();
            //StdOut.println(line2);
            line2++;
        }

    }
}

