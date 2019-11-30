public class DirectedEdge {
    public int v, w;
    public double weight;
    public boolean colour;

    public DirectedEdge(int v, int w, double weight, boolean colour) {
        this.v = v;
        this.w = w;
        this.weight = weight;
        this.colour = colour;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double weight() {
        return weight;
    }

    public boolean isRed() { return  (colour == true); }
    public boolean isBlack() { return  (colour == false); }


    @Override
    public String toString() {
        return v + "->" + w + " " + weight;
    }
}
