package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Jandos Iskakov
 * problem: Learning Graph
 */
public class LearningGraph {

    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt() - 1;

        Map<Integer, Integer> vals = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int value = in.nextInt();
            vals.put(i, value);
        }

        List<Integer>[] nodes = new ArrayList[n];

        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;

            if (nodes[x] == null)
                nodes[x] = new ArrayList<>();

            if (nodes[y] == null)
                nodes[y] = new ArrayList<>();

            nodes[x].add(y);
            nodes[y].add(x);
        }

        for (int i = 0; i < n; i++) {
            if (nodes[i] == null || nodes[i].size() < k) {
                out.println(-1);
            } else {
                nodes[i].sort((i1, i2) -> vals.get(i1).equals(vals.get(i2)) ? i2 - i1: vals.get(i2) - vals.get(i1));

                out.println(nodes[i].get(k) + 1);
            }
        }

        out.flush();

        in.close();
        out.close();
    }


    private static class FastReader {
        private BufferedReader br;
        private StringTokenizer tok = new StringTokenizer("");

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(br.readLine());
            return tok.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        public boolean nextBoolean() throws IOException {
            return Boolean.valueOf(next());
        }

        public String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        public void close() throws IOException {
            br.close();
        }
    }

}
