package graph;

import java.io.PrintWriter;
import java.util.*;

/**
 * @author Jandos Iskakov
 * problem: Monk in the real estate
 */
public class MonkRealEstate {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int t = in.nextInt();

        while (t-- > 0) {
            int e = in.nextInt();

            Set<Integer> counter = new HashSet<>();

            for (int i = 0; i < e; i++) {
                int x = in.nextInt(), y = in.nextInt();

                counter.add(x);
                counter.add(y);
            }

            out.println(counter.size());
        }

        out.flush();

        in.close();
        out.close();
    }

}
