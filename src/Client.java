import java.util.HashSet;
import java.util.Scanner;

public class Client {

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

    public static void main(String[] args)throws Exception {


//        8
//        16
//        0 1 5.0
//        0 4 9.0
//        0 7 8.0
//        1 2 12.0
//        1 3 15.0
//        1 7 4.0
//        2 3 3.0
//        2 6 11.0
//        3 6 9.0
//        4 5 4.0
//        4 6 20.0
//        4 7 5.0
//        5 2 1.0
//        5 6 13.0
//        7 5 6.0
//        7 2 7.0

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
        HashSet<Integer> secure_vertices = new HashSet<>();
        System.out.print("Secure path from source to destination is : " + s + " to " + d + " (" + sp.distTo(d) + "): ");
        for (DirectedEdge e : sp.pathTo(d)) {
            System.out.print(e + " ");
            secure(G, e);
            secure_vertices.add(e.to());
            secure_vertices.add(e.from());
        }
        System.out.println();


        int p;
        do {
            System.out.print("Enter a valid position of eavesdropper : ");
            p = sc.nextInt();
            if (secure_vertices.contains(p))
                System.out.println("Eavesdropping from that secured position not possible");
        } while (secure_vertices.contains(p));

        NetworkEavesDropper ed = new NetworkEavesDropper(G, p, secure_vertices, 0);

        if (ed.count > 0)
            System.out.println("Eavesdropper couldn't succeed even after breaking " + ed.count + " positions " + ed.trails);

    }
}
