package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Jandos Iskakov
 * problem: Graph > Depth First Search > TEST YOUR UNDERSTANDING
 */
public class UnreachableNodes {

    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();

        List<Integer>[] vertex = new LinkedList[n];

        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1, y = in.nextInt() - 1;

            if (vertex[x] == null) {
                vertex[x] = new LinkedList<>();
            }

            if (vertex[y] == null) {
                vertex[y] = new LinkedList<>();
            }

            vertex[x].add(y);
            vertex[y].add(x);
        }

        int x = in.nextInt() - 1;

        boolean[] visited = new boolean[n];
        visited[x] = true;

        dfs(visited, x, vertex);

        int unreachable = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                unreachable++;
            }
        }

        out.println(unreachable);

        out.flush();

        in.close();
        out.close();
    }

    private static void dfs(boolean[] visited, int x, List<Integer>[] vertex) {
        visited[x] = true;

        if (vertex[x] == null) {
            return;
        }

        for (int node : vertex[x]) {
            if (!visited[node]) {
                dfs(visited, node, vertex);
            }
        }
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
