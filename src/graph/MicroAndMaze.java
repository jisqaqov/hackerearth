package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Jandos Iskakov
 * problem: Graph > Flood fill > TEST YOUR UNDERSTANDING: Micro and Maze
 */
public class MicroAndMaze {

    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();

        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.nextInt();
            }
        }

        boolean[][] visited = new boolean[n][m];
        out.println(dfs(0, 0, a, visited, n, m)? "Yes": "No");

        out.flush();

        in.close();
        out.close();
    }

    private static boolean dfs(int x, int y, int[][] a, boolean[][] visited, int n, int m) {
        if (x == n - 1 && y == m - 1) {
            return true;
        }

        if (x < 0 || y < 0 || x >= n || y >= m || visited[x][y] || a[x][y] == 0) {
            return false;
        }

        visited[x][y] = true;

        return dfs(x - 1, y, a, visited, n, m) ||
               dfs(x + 1, y, a, visited, n, m) ||
               dfs(x, y - 1, a, visited, n, m) ||
               dfs(x, y + 1, a, visited, n, m);
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
