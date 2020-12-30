public class SPImpl implements SP{
    Integer size;
    Integer Size;
    double [] AbstandZuStartknoten = null;
    Integer[] vorgaenger = null;
    int strartknoten;
    @Override
    public boolean bellmanFord(WeightedGraph g, int s) {
        size = g.size();
        strartknoten = s;
        AbstandZuStartknoten = new double[size];
        vorgaenger = new Integer[size];
        for (int i = 0; i < size; i++) {
            AbstandZuStartknoten[i] = INF;
            vorgaenger[i] = NIL;
        }
        AbstandZuStartknoten[s] = 0;

        for (int i = 0; i < size - 1; i++) {

            for (int Knoten = 0; Knoten < size; Knoten++) {
                if (Knoten == strartknoten) { // wie ist der vergleich hier? oder equals?
                    for (int nachfolger = 0; nachfolger < g.deg(strartknoten); nachfolger++) {
                        int NachfolgerKnoten = g.succ(strartknoten, nachfolger);
                        double Test = g.weight(strartknoten, nachfolger);
                        AbstandZuStartknoten[NachfolgerKnoten] = Test;
                        vorgaenger[NachfolgerKnoten] = strartknoten;
                    }
                } else {
                    for (int nachfolger = 0; nachfolger < g.deg(Knoten); nachfolger++) {
                        int NachFolgerKnoten = g.succ(Knoten, nachfolger);
                        if (AbstandZuStartknoten[Knoten] + g.weight(Knoten, nachfolger) < AbstandZuStartknoten[NachFolgerKnoten]) {
                            AbstandZuStartknoten[NachFolgerKnoten] = AbstandZuStartknoten[Knoten] + g.weight(Knoten, nachfolger);
                            vorgaenger[NachFolgerKnoten] = Knoten;
                        }
                    }
                }
            }
        }
        for (int Knoten = 0; Knoten < size; Knoten++) {
            for (int nachfolger = 0; nachfolger < g.deg(Knoten); nachfolger++) {
                int NachFolgerKnoten = g.succ(Knoten, nachfolger);
                if (AbstandZuStartknoten[Knoten] + g.weight(Knoten, nachfolger) < AbstandZuStartknoten[NachFolgerKnoten]) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void dijkstra(WeightedGraph g, int s) {
        Size=g.size();
        AbstandZuStartknoten = new double[Size];
        vorgaenger = new Integer[Size];

        BinHeap<Integer,Integer> heap=new BinHeap();// übernommen aus MSF
        BinHeap.Entry[] entryArray=new BinHeap.Entry[Size];
        for (int i = 0; i < Size; i++) {
            AbstandZuStartknoten[i] = INF;
            vorgaenger[i] = NIL;
        }
        AbstandZuStartknoten[s] = 0;

        for(int i=0;i<Size;i++){
            entryArray[i]=heap.insert(Integer.MAX_VALUE,i);
        }
        do{
            BinHeap.Entry knoten=heap.extractMin();
            int succnr=g.deg((Integer) knoten.data());

            for(int i=0;i<succnr;i++){
                if(heap.contains(entryArray[g.succ((Integer) knoten.data(),i)]) && g.weight((Integer)knoten.data(),i)<(Integer) entryArray[g.succ((Integer) knoten.data(),i)].prio()){
                    heap.changePrio(entryArray[g.succ((Integer) knoten.data(),i)],((Double)g.weight((Integer)knoten.data(),i)).intValue());
                    vorgaenger[(Integer) g.succ((Integer) knoten.data(),i)]=(Integer) knoten.data();//

                }
            }
        }while (!heap.isEmpty());


    }

    @Override
    public double dist(int v) {
        return AbstandZuStartknoten[v];
    }

    @Override
    public int pred(int v) {
        return vorgaenger[v];
    }
}
