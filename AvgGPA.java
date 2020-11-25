// AvgGPA.java: Reads from standard input a list of letter grades and computes
// and prints the average GPA (the average of the numbers corresponding to
// the grades).

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class AvgGPA {
    public static void main(String[] args) {
        float average = 0.0f;
        float total = 0.0f;
        int count = 0;
        ArrayST<String, Float> st = new ArrayST<String, Float>();
        st.put("A+", 4.33f);
        st.put("A", 4.0f);
        st.put("A-", 3.67f);
        st.put("B+", 3.33f);
        st.put("B", 3.0f);
        st.put("B-", 2.67f);
        st.put("C+", 2.33f);
        st.put("C", 2.0f);
        st.put("C-", 1.67f);
        st.put("D", 1.0f);
        st.put("F", 0.0f);

        while (!StdIn.isEmpty()) {
            String grade = StdIn.readString();
            //StdOut.println(grade + " " + st.get(grade));
            count++;
            total += st.get(grade);
        }

        average = total / count;
        StdOut.printf("%.4f", average);
    }
}
