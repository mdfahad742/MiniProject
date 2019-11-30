import java.util.ArrayList;
import java.util.HashMap;

public class EdgeWeightedDigraph {
    public int V;
    public int E = 0;
    public ArrayList<DirectedEdge>[] adj;
    public HashMap<Integer, Integer>[] adj_to;
    public int[] size;


    public EdgeWeightedDigraph(int V) {
        this.V = V;
        adj = new ArrayList[V];
        adj_to = new HashMap[V];
        size = new int[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
            adj_to[i] = new HashMap<>();
        }
    }

    public void addEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        DirectedEdge e2 = new DirectedEdge(w, v, e.weight, false);
        adj[v].add(e);
        adj[w].add(e2);
        size[v]++;
        size[w]++;
        adj_to[v].put(w, size[v] - 1);
        adj_to[w].put(v, size[w] - 1);
        E++;
    }

    public void block(int v) {
        for (DirectedEdge e : adj(v)) {
            e.weight = Double.MAX_VALUE;
            int w = e.to();
            if (adj_to[w].containsKey(v)) {
                int ind = adj_to[w].get(v);
                DirectedEdge e1 = adj[w].get(ind);
                e1.weight = Double.MAX_VALUE;
            }
        }
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        ArrayList<DirectedEdge> list = new ArrayList<>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    }
}
