import java.util.*;

public class DijkstraSP {
    public DirectedEdge[] edgeTo;
    public double[] distTo;
    public PriorityQueue<Node> pq;
    public Set<Integer> inTree;

    class Node implements Comparable<Node> {
        int v;
        double cost;

        public Node(int v, double cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public boolean equals(Object o) {
            Node node = (Node) o;
            return this.v == node.v;
        }


        @Override
        public int compareTo(Node that) {
            if (this.cost > that.cost)
                return -1;
            else if (this.cost < that.cost)
                return 1;
            else
                return 0;
        }
    }

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V];
        distTo = new double[G.V];
        pq = new PriorityQueue<Node>();
        inTree = new HashSet<>();

        for (int v = 0; v < G.V; v++) {
            distTo[v] = Double.MAX_VALUE;
        }
        distTo[s] = 0.0;
        pq.add(new Node(s, 0.0));
        inTree.add(s);

        while (!pq.isEmpty()) {
            int v = pq.poll().v;
            for (DirectedEdge e : G.adj(v)) {
                relax(e);
            }
        }
    }

    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight) {
            distTo[w] = distTo[v] + e.weight;
            edgeTo[w] = e;
            if (inTree.contains(w)) {
                pq.remove(new Node(w, distTo[w]));
                pq.add(new Node(w, distTo[w]));
            } else {
                pq.add(new Node(w, distTo[w]));
                inTree.add(w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        ArrayList<DirectedEdge> path = new ArrayList<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.add(e);
        Collections.reverse(path);
        return path;
    }
}