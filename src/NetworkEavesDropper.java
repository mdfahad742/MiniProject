import java.util.ArrayList;
import java.util.HashSet;

class NetworkEavesDropper {
    boolean[] visited;
    int s;
    ArrayList<Integer> trails;
    int count;

    public NetworkEavesDropper(EdgeWeightedDigraph G, int s, HashSet<Integer> RED, HashSet<Integer> block_vertices, int count) {
        this.s = s;
        visited = new boolean[G.V];
        this.count = 0;
        trails = new ArrayList<>();

        dfs(G, s, RED, block_vertices);

    }

    public void dfs(EdgeWeightedDigraph G, int v, HashSet<Integer> RED, HashSet<Integer> block_vertices) {
        visited[v] = true;
        for (DirectedEdge e : G.adj[v]) {
            int w = e.to();

            if (!visited[w] && !block_vertices.contains(w)) {
                if (RED.contains(w)) {
                    visited[w] = true;
                    count++;
                    trails.add(w);
                }
                else
                    dfs(G, w, RED, block_vertices);
            }
        }
    }

    public Iterable<Integer> trails() {
        return trails;
    }
}
