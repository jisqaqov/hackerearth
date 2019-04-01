package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Jandos Iskakov
 * problem: Graph > Topological Sort > TEST YOUR UNDERSTANDING
 */
public class TopologicalSort {

    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();

        List<Integer>[] adj = new LinkedList[n];

        boolean[] visited = new boolean[n];

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;

            if (adj[u] == null) {
                adj[u] = new LinkedList<>();
            }

            adj[u].add(v);
        }

        for (int i = 0; i < n; i++) {
            if (adj[i] != null) {
                adj[i].sort((t1, t2) -> t2 - t1);
            }
        }

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = n - 1; i >= 0; i--) {
            if (!visited[i]) {
                tsort(i, adj, visited, deque);
            }
        }

        while (!deque.isEmpty()) {
            out.print((deque.pop() + 1) + " ");
        }

        out.flush();

        in.close();
        out.close();
    }

    private static void tsort(int s, List<Integer>[] adj, boolean[] visited, Deque<Integer> deque) {
        visited[s] = true;

        if (adj[s] == null) {
            deque.offerFirst(s);
            return;
        }

        for (int vertice : adj[s]) {
            if (!visited[vertice]) {
                tsort(vertice, adj, visited, deque);
            }
        }

        deque.offerFirst(s);
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
