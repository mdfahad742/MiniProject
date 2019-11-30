import java.util.Scanner;


public class NetworkEavesDropper {

    public static boolean RED = true;
    public static boolean BLACK = false;

    public static void secure(EdgeWeightedDigraph G, DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        e.colour = RED;
        if (G.adj_to[w].containsKey(v)) {
            int ind = G.adj_to[w].get(v);
            DirectedEdge e1 = G.adj[w].get(ind);
            e1.colour = RED;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(V);
        for (int i = 0; i < E; i++) {
            int v = sc.nextInt();
            int w = sc.nextInt();
            double weight = sc.nextDouble();
            DirectedEdge e1 = new DirectedEdge(v, w, weight, BLACK);
            G.addEdge(e1);
        }

        System.out.print("Enter the source vertex : ");
        int s = sc.nextInt();
        System.out.print("Enter the destination vertex : ");
        int d = sc.nextInt();

        System.out.println("Enter the vertices to block & -1 to quit");
        int block = 0;
        while (block != -1) {
            block = sc.nextInt();
            if (block < 0 || block > V - 1)
                continue;
            G.block(block);
        }

        DijkstraSP sp = new DijkstraSP(G, s);
        System.out.print(s + " to " + d + " (" + sp.distTo(d) + "): ");
        for (DirectedEdge e : sp.pathTo(d)) {
            System.out.print(e + " ");
            secure(G, e);
        }
        System.out.println();
    }

}
