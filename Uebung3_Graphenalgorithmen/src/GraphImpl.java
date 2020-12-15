public class GraphImpl implements Graph{
    private int[][] Graph;
    public GraphImpl(int[][] ints) {
        this.Graph=ints;
    }

    @Override
    public int size() {
        if(Graph==null) return 0;
        return Graph.length-1; //Unsicher ob -1 nötig oder nicht
    }

    @Override
    public int deg(int v) {
        if(Graph==null || v<0|| v>size()) return 0;
        return Graph[1].length;
    }

    @Override
    public int succ(int v, int i) {
        if(Graph==null || v<0|| v>size() || i<0 || i>deg(v)) return 0;
        return Graph[v][i];
    }

    @Override
    public Graph transpose() {
        return null;
    }
}
