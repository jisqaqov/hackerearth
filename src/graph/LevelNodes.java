package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Jandos Iskakov
 * problem: Graph > BFS > TEST YOUR UNDERSTANDING
 */
public class LevelNodes {

    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();

        List<Integer>[] nodes = new LinkedList[n];

        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1, v = in.nextInt() - 1;

            if (nodes[u] == null) {
                nodes[u] = new LinkedList<>();
            }

            if (nodes[v] == null) {
                nodes[v] = new LinkedList<>();
            }

            nodes[u].add(v);
            nodes[v].add(u);
        }

        int x = in.nextInt() - 1;

        int[] levels = new int[n];
        levels[0] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        boolean[] visited = new boolean[n];
        visited[0] = true;

        while (!queue.isEmpty()) {
            int parent = queue.poll();

            for (int node : nodes[parent]) {
                if (visited[node]) {
                    continue;
                }

                visited[node] = true;
                levels[node] = levels[parent] + 1;
                queue.add(node);
            }
        }

        long items = Arrays.stream(levels).filter(value -> value == x).count();
        out.println(items);

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
