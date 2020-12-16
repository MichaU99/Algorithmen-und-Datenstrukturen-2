public class BFSImpl implements BFS {
    Graph graph;
    int start;
    FIFOList fifo;
    @Override
    public void search(Graph g, int s) {
        this.graph=g;
        this.start=s;
        fifo=new FIFOList(s);
    }

    @Override
    public int dist(int v) {
        return 0;
    }

    @Override
    public int pred(int v) {
        return 0;
    }
}
