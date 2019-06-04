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
        List<Node>[] adjList = new ArrayList[n];

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

                adjList[u].add(new Node(v, w));
                adjList[v].add(new Node(u, w));
            }
        } catch (Exception e) {
            //
        }

        Set<Integer> visited = new HashSet<>();

        HeapMap<Node> heap = new HeapMap<>();
        heap.add(new Node(0, 0));

        int INFINITE = (int) Math.pow(10, 9);

        int[] dis = new int[n];
        Arrays.fill(dis, INFINITE);
        dis[0] = 0;

        while (heap.getSize() > 0) {
            Node node = heap.poll();

            visited.add(node.vertex);

            if (adjList[node.vertex] == null) {
                continue;
            }

            for (Node adj : adjList[node.vertex]) {
                if (visited.contains(adj.vertex)) {
                    continue;
                }

                int newDis = dis[node.vertex] + adj.cost;
                if (dis[adj.vertex] > newDis) {
                    dis[adj.vertex] = newDis;
                }

                heap.add(new Node(adj.vertex, dis[adj.vertex]));
            }
        }

        for (int t = 1; t < n; t++) {
            out.print(dis[t] + " ");
        }
    }

    private static class HeapMap<T extends Comparable> {
        private static final int INITIAL_CAPACITY = 10;
        private Node[] heap = new Node[INITIAL_CAPACITY];
        private int size = 0;
        Map<Integer, Node> map = new HashMap<>();

        private int getLeftChildIndex(int parentIndex) {
            return 2 * parentIndex + 1;
        }

        private int getRightChildIndex(int parentIndex) {
            return 2 * parentIndex + 2;
        }

        private int getParentIndex(int index) {
            return (index - 1) / 2;
        }

        @SuppressWarnings("unchecked")
        private T getParent(int index) {
            return (T) heap[getParentIndex(index)];
        }

        @SuppressWarnings("unchecked")
        private T leftChild(int parentIndex) {
            return (T) heap[getLeftChildIndex(parentIndex)];
        }

        @SuppressWarnings("unchecked")
        private T rightChild(int parentIndex) {
            return (T) heap[getRightChildIndex(parentIndex)];
        }

        private boolean hasParent(int childIndex) {
            return getParentIndex(childIndex) >= 0;
        }

        private boolean hasLeftChild(int parentIndex) {
            return size > getLeftChildIndex(parentIndex);
        }

        private boolean hasRightChild(int parentIndex) {
            return size > getRightChildIndex(parentIndex);
        }

        @SuppressWarnings("unchecked")
        public Node peek() {
            if (size == 0)
                throw new IllegalArgumentException("Empty Heap");

            return heap[0];
        }

        @SuppressWarnings("unchecked")
        public Node poll() {
            if (size == 0)
                throw new IllegalArgumentException("Empty Heap");

            Node poll = heap[0];

            map.remove(poll.vertex);

            heap[0] = heap[size - 1];
            size--;
            siftDown();

            return poll;
        }

        public void add(Node item) {
            if (size >= this.heap.length)
                this.growHeap(size + 1);

            heap[size] = item;

            map.put(item.vertex, item);

            size++;

            siftUp();
        }

        private void growHeap(int rate) {
            int capacity = this.heap.length;
            int newCapacity = capacity + (capacity < 64 ? capacity + 2 : capacity >> 1);
            if (newCapacity - 2147483639 > 0) {
                newCapacity = hugeCapacity(rate);
            }

            this.heap = Arrays.copyOf(this.heap, newCapacity);
        }

        private int hugeCapacity(int capacity) {
            if (capacity < 0) {
                throw new OutOfMemoryError();
            } else {
                return capacity > 2147483639 ? 2147483647 : 2147483639;
            }
        }

        @SuppressWarnings("unchecked")
        private void siftDown() {
            int currentIndex = 0;
            Node current = heap[0];

            while (hasLeftChild(currentIndex)) {
                int nextIndex = getLeftChildIndex(currentIndex);
                int rightChildIndex = getRightChildIndex(currentIndex);

                if (hasRightChild(currentIndex) && rightChild(currentIndex).compareTo(heap[nextIndex]) > 0)
                    nextIndex = rightChildIndex;

                if (current.compareTo(heap[nextIndex]) < 0) {
                    swap(currentIndex, nextIndex);

                    currentIndex = nextIndex;
                    current = heap[currentIndex];
                }
                else
                    break;
            }
        }

        @SuppressWarnings("unchecked")
        private void siftUp() {
            Node current = heap[size - 1];
            int currentIndex = size - 1;
            while (hasParent(currentIndex) && current.compareTo(getParent(currentIndex)) > 0) {
                int parentIndex = getParentIndex(currentIndex);
                swap(currentIndex, parentIndex);

                currentIndex = parentIndex;
                current = heap[parentIndex];
            }
        }

        private void swap(int index1, int index2) {
            Node temp = heap[index2];
            heap[index2] = heap[index1];
            heap[index1] = temp;
        }

        public int getSize() {
            return size;
        }
    }

    private static class Node implements Comparable {
        int vertex, cost;

        public Node() {
        }

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Object o) {
            return cost - ((Node)o).cost;
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
