public class SCCImpl implements SCC{
    int[] graph1=null;
    int[] graph2=null;

    @Override
    public void compute(Graph g) {
        DFS tiefensucheG=new DFSImpl();
        DFS tiefensucheGtlive=new DFSImpl();
        tiefensucheG.search(g);
        graph1=new int[g.size()];
        tiefensucheGtlive.search(g.transpose(),tiefensucheG); //Wie schaffe ich hier eine absteigende Reihenfolge in der äußeren Schleife?

    }

    @Override
    public int component(int v) {
        return 0;
    }
}
