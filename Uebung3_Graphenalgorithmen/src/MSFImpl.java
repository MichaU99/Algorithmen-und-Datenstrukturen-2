public class MSFImpl implements MSF{
    WeightedGraph graph=null;
    int size;
    int startknoten;
    Integer[] vorganger;
    @Override
    public void compute(WeightedGraph g, int s) {
        graph=g;
        startknoten=s;
        size=graph.size();
        vorganger=new Integer[g.size()];
        BinHeap<Integer,Integer> heap=new BinHeap();
        BinHeap.Entry[] entryArray=new BinHeap.Entry[size]; //Ist diese Umsetzung sinnvoll/möglich?

        if(g==null || g.size()==0) return;
        for(int i=0;i<size;i++){
            entryArray[i]=heap.insert(Integer.MAX_VALUE,i);

        }
        //Test
        for (int i=0;i< size;i++){
            assert (heap.contains(entryArray[i])) ;
        }
        //
        do{
            BinHeap.Entry knoten=heap.extractMin();
            int succnr=graph.deg((Integer) knoten.data());

            for(int i=0;i<succnr;i++){
                if(heap.contains(entryArray[graph.succ((Integer) knoten.data(),i)]) && graph.weight((Integer)knoten.data(),i)<(Integer) entryArray[graph.succ((Integer) knoten.data(),i)].prio()){
                    heap.changePrio(entryArray[graph.succ((Integer) knoten.data(),i)],((Double)graph.weight((Integer)knoten.data(),i)).intValue()); // TODO: 29.12.2020  Help
                    vorganger[(Integer) graph.succ((Integer) knoten.data(),i)]=(Integer) knoten.data();//

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
