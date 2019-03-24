import java.io.PrintWriter;
import java.util.*;

/**
 * @author Jandos Iskakov
 * problem: Graph Representation: TEST YOUR UNDERSTANDING
 */
public class EdgeExistence {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt(), m = in.nextInt();

        Set<Integer>[] list = new HashSet[n + 1];

        for (int i = 0; i < m; i++) {
            int a = in.nextInt(), b = in.nextInt();

            if (list[a] == null)
                list[a] = new HashSet<>();

            if (list[b] == null)
                list[b] = new HashSet<>();

            list[a].add(b);
            list[b].add(a);
        }

        int q = in.nextInt();

        for (int i = 0; i < q; i++) {
            int a = in.nextInt(), b = in.nextInt();

            if (list[a] == null || !list[a].contains(b))
                out.println("NO");
            else
                out.println("YES");
        }

        out.flush();

        in.close();
        out.close();
    }

}
