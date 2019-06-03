package graph;

import java.io.*;
import java.util.*;

/**
 * @author Jandos Iskakov
 * problem: Shortest Path Problem
 */
public class ShortestPathProblem {

    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();

        dijkstra(in, out, n, m);

        out.flush();

        in.close();
        out.close();
    }

    private static void bellmanFord(FastReader in, PrintWriter out, int n, int m)
            throws IOException {
        int[] dis = new int[n];

        int[][] edges = new int[n][3];

        for (int i = 0; i < m; i++) {
            edges[i][0] = in.nextInt() - 1;
            edges[i][1] = in.nextInt() - 1;
            edges[i][2] = in.nextInt();
        }

        int INFINITE = (int) Math.pow(10, 9);

        Arrays.fill(dis, INFINITE);
        dis[0] = 0;

        for (int i = 1; i <= n - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int weight = edge[2];

                if (dis[v] > dis[u] + weight) {
                    dis[v] = dis[u] + weight;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            out.print(dis[i] + " ");
        }
    }

    private static void dijkstra(FastReader in, PrintWriter out, int n, int m)
            throws IOException {
        List<Pair>[] adjList = new ArrayList[n];

        try {
            for (int i = 0; i < m; i++) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                int w = in.nextInt();

                if (adjList[u] == null) {
                    adjList[u] = new ArrayList<>();
                }

                adjList[u].add(new Pair(v, w));

                if (adjList[v] == null) {
                    adjList[v] = new ArrayList<>();
                }

                adjList[v].add(new Pair(u, w));
            }
        } catch (Exception e) {
            ;
        }

        boolean[] visited = new boolean[n];

        PriorityQueue<Pair> heap = new PriorityQueue<>(Comparator.comparingInt(p -> p.value2));
        heap.add(new Pair(0, 0));

        int[] dis = new int[n];
        int INFINITE = (int) Math.pow(10, 9);

        Arrays.fill(dis, INFINITE);
        dis[0] = 0;

        while (!heap.isEmpty()) {
            Pair node = heap.poll();
            int u = node.value1;

            if (visited[u]) {
                continue;
            }

            visited[u] = true;
            dis[u] = node.value2;

            if (adjList[u] != null) {
                for (Pair adj : adjList[u]) {
                    int v = adj.value1;
                    int w = adj.value2;

                    if (dis[v] > dis[u] + w) {
                        heap.add(new Pair(v, dis[u] + w));
                    }
                }
            }
        }

        for (int i = 1; i < n; i++) {
            out.print(dis[i] + " ");
        }
    }

    private static class Pair {
        int value1, value2;

        public Pair(int value1, int value2) {
            this.value1 = value1;
            this.value2 = value2;
        }
    }

    private static class FastReader {
        private BufferedReader br;
        private StringTokenizer tok = new StringTokenizer("");

        public FastReader() throws FileNotFoundException {
            br = new BufferedReader(new FileReader("688e4d6e-0-input-688dc46.txt"));
        }

        public String next() throws IOException {
            String token = null;
            if (tok == null) {
                return null;
            }

            while (!tok.hasMoreElements()) {
                String line = br.readLine();
                if (line != null) {
                    tok = new StringTokenizer(line);
                    //token = tok.nextToken();
                } else {
                    tok = null;
                }
            }

            return tok != null? tok.nextToken(): null;
        }

        public Integer nextInt() throws IOException {
            String text = next();
            if (text != null) {
                return Integer.parseInt(text);
            }
            return null;
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
