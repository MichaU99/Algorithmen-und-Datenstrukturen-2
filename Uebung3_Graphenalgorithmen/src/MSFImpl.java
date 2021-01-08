public class MSFImpl implements MSF{
    WeightedGraph graph=null;
    int size;
    int startknoten;
    Integer[] vorganger;
    @Override
    public void compute(WeightedGraph g, int s) {
        if(g==null || g.size()==0) return;
        graph=g;
        startknoten=s;
        size=graph.size();
        vorganger=new Integer[g.size()];
        BinHeap<Double,Integer> heap=new BinHeap<>();
        BinHeap.Entry[] entryArray=new BinHeap.Entry[size]; //Ist diese Umsetzung sinnvoll/möglich?

        for(int i=0;i<size;i++){
            if(i!=startknoten)entryArray[i]=heap.insert(Double.POSITIVE_INFINITY,i);
            else entryArray[i]=heap.insert(0.0,i);
        }
        do{
            BinHeap.Entry<Double,Integer> knoten=heap.extractMin();
            int succnr=graph.deg( knoten.data());
            for(int i=0;i<succnr;i++){
                if(heap.contains(entryArray[graph.succ( knoten.data(),i)]) && ( (graph.weight(knoten.data(),i)) < (Double) entryArray[graph.succ( knoten.data(),i)].prio())){
                    heap.changePrio(entryArray[graph.succ( knoten.data(),i)],graph.weight(knoten.data(),i));
                    vorganger[graph.succ( knoten.data(),i)]= knoten.data();
                }
            }
        }while (!heap.isEmpty());
    }

    @Override
    public int pred(int v) {
        if(v<0 ||graph==null || vorganger==null||v>=graph.size()) return -1;
        if(vorganger[v]==null) return -1;
        return vorganger[v];
    }
}
