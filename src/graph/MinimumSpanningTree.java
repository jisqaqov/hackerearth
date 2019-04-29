package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Jandos Iskakov
 * problem: Graph > Minimum Spanning Tree > TEST YOUR UNDERSTANDING
 * algorithm: Kruskal's algorithm
 */
public class MinimumSpanningTree {

    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        out.println(kruskal(in));

        out.flush();

        in.close();
        out.close();
    }

    private static int kruskal(FastReader in) throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();

        Integer[][] list = new Integer[m][3];

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int weight = in.nextInt();

            list[i] = new Integer[3];
            list[i][0] = u;
            list[i][1] = v;
            list[i][2] = weight;
        }

        Arrays.sort(list, Comparator.comparingInt(o -> o[2]));

        int[] ds = new int[n];
        for (int i = 0; i < n; i++) {
            ds[i] = i;
        }

        int mst = 0;
        for (int i = 0; i < m; i++) {
            int u = list[i][0];
            int v = list[i][1];
            int weight = list[i][2];

            if (!find(u, v, ds)) {
                mst += weight;
                union(u, v, ds);
            }
        }

        return mst;
    }

    private static boolean find(int x, int y, int[] ds) {
        return root(ds, x) == root(ds, y);
    }

    private static int root(int[] ds, int x) {
        while (x != ds[x]) {
            ds[x] = ds[ds[x]];
            x = ds[x];
        }

        return x;
    }

    private static void union(int x, int y, int[] ds) {
        int rootX = root(ds, x);
        int rootY = root(ds, y);

        ds[rootX] = rootY;
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
