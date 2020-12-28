public class MSFImpl implements MSF{
    WeightedGraph graph;
    int size;
    int startknoten;
    @Override
    public void compute(WeightedGraph g, int s) {
        graph=g;
        startknoten=s;
        size=graph.size();
        BinHeap<Integer,Integer> heap=new BinHeap();
        BinHeap.Entry[] entryArray=new BinHeap.Entry[size]; //Ist diese Umsetzung sinnvoll/möglich?
        if(g==null || g.size()==0) return;
        for(int i=0;i<size;i++){
            entryArray[i]=heap.insert(Integer.MAX_VALUE,i);
            // TODO: 29.12.2020 Setze π(v)=⊥.
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
                if(heap.contains(entryArray[graph.succ((Integer) knoten.data(),i)]) && graph.weight((Integer)knoten.data(),i)<(Integer) knoten.prio()){
                    heap.changePrio(knoten,((Double)graph.weight((Integer)knoten.data(),i)).intValue()); // TODO: 29.12.2020  Help
                    // TODO: 29.12.2020  Setze π(v)=u.
                }
            }
        }while (!heap.isEmpty());
    }

    @Override
    public int pred(int v) {
        return 0;
    }
}
