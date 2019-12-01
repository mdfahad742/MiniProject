import java.util.ArrayList;
import java.util.HashSet;

class NetworkEavesDropper {
    boolean[] visited;
    int s;
    ArrayList<Integer> trails;
    int count;

    public NetworkEavesDropper(EdgeWeightedDigraph G, int s, HashSet<Integer> RED, int count) {
        this.s = s;
        visited = new boolean[G.V];
        this.count = 0;
        trails = new ArrayList<>();

        dfs(G, s, RED);

    }

    public void dfs(EdgeWeightedDigraph G, int v, HashSet<Integer> RED) {
        visited[v] = true;
        for (DirectedEdge e : G.adj[v]) {
            int w = e.to();

            if (!visited[w]) {
                if (RED.contains(w)) {
                    visited[w] = true;
                    count++;
                    trails.add(w);

                }
                else
                    dfs(G, w, RED);
            }
        }
    }

    public Iterable<Integer> trails() {
        return trails;
    }
}
