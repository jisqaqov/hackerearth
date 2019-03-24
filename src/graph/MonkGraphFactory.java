package graph;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Jandos Iskakov
 * problem: problem: Graph > Graph Representation > Monk at the Graph Factory
 * algorithm: tree has n-1 edges, sum all degrees,
 * hence every edge counted twice get devision by two to calculate number of edges
 */
public class MonkGraphFactory {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();

        int edges = 0;
        for (int i = 0; i < n; i++) {
            int degree = in.nextInt();
            edges += degree;
        }

        out.println(edges/2 == n - 1? "Yes": "No");

        out.flush();

        in.close();
        out.close();
    }

}
