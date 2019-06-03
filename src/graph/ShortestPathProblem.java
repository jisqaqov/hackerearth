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

        int[][] edgesTemp = new int[m][3];

        int edgeSize = 0;

        try {
            for (int i = 0; i < m; i++) {
                edgesTemp[i][0] = in.nextInt() - 1;
                edgesTemp[i][1] = in.nextInt() - 1;
                edgesTemp[i][2] = in.nextInt();

                edgeSize++;
            }
        } catch (Exception e) {
            //
        }

        int[][] edges = Arrays.copyOf(edgesTemp, edgeSize);

        int INFINITE = (int) Math.pow(10, 9);

        int[] parent = new int[n];

        Arrays.fill(dis, INFINITE);
        dis[0] = 0;

        for (int i = 1; i <= n - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int weight = edge[2];

                if (dis[v] > dis[u] + weight) {
                    dis[v] = dis[u] + weight;
                    parent[v] = u;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            out.print(dis[i] + " ");
        }

        System.out.println();

        for (int i = 1; i < n; i++) {
            out.print(parent[i] + " ");
        }
    }

    private static void dijkstra(FastReader in, PrintWriter out, int n, int m) {
        List<Pair>[] adjList = new ArrayList[n];

        try {
            for (int i = 0; i < m; i++) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                int w = in.nextInt();

                if (adjList[u] == null) {
                    adjList[u] = new ArrayList<>();
                }

                if (adjList[v] == null) {
                    adjList[v] = new ArrayList<>();
                }

                adjList[u].add(new Pair(v, w));
                adjList[v].add(new Pair(u, w));
            }
        } catch (Exception e) {
            //
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

            if (adjList[u] != null) {
                for (Pair adj : adjList[u]) {
                    int v = adj.value1;
                    int weight = adj.value2;

                    if (dis[v] > dis[u] + weight) {
                        dis[v] = dis[u] + weight;
                        heap.add(new Pair(v, dis[u] + weight));
                    }
                }
            }
        }

        for (int t = 1; t < n; t++) {
            out.print(dis[t] + " ");
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
            while (tok != null && !tok.hasMoreElements()) {
                String line = br.readLine();
                if (line != null) {
                    tok = new StringTokenizer(line);
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
