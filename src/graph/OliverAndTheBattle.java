package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Jandos Iskakov
 * problem: Graph > Breadth First Search > Oliver and the battle
 */
public class OliverAndTheBattle {

    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt(), m = in.nextInt();

            boolean[][] groud = new boolean[n][m];

            for (int  i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    groud[i][j] = in.nextInt() == 1;
                }
            }

            boolean[][] visited = new boolean[n][m];

            int troops = 0, max = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!groud[i][j] || visited[i][j]) {
                        continue;
                    }

                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[] {i, j});

                    visited[i][j] = true;

                    troops++;
                    
                    int soldiers = 1;
                    
                    while (!queue.isEmpty()) {
                        int[] coords = queue.poll();
                        int row = coords[0], col = coords[1];

                        for (int x = row == 0? 0: row - 1; x < n && x >= 0 && x <= row + 1; x++) {
                            for (int y = col == 0? 0: col - 1; y < m && y >= 0 && y <= col + 1; y++) {
                                if (!visited[x][y] && groud[x][y]) {
                                    visited[x][y] = true;
                                    queue.add(new int[] {x, y});
                                    soldiers++;
                                }
                            }
                        }
                    }

                    max = Math.max(soldiers, max);
                }
            }

            out.println(troops + " " + max);
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
