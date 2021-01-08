import java.sql.SQLOutput;

public class SPImpl implements SP{
    Integer size;
    Integer Size;
    double [] AbstandZuStartknoten = null;
    Integer[] vorgaenger = null;
    int strartknoten;
    int Startknoten;
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

                    for (int nachfolger = 0; nachfolger < g.deg(Knoten); nachfolger++) {
                        int NachFolgerKnoten = g.succ(Knoten, nachfolger);
                        if (AbstandZuStartknoten[Knoten] + g.weight(Knoten, nachfolger) < AbstandZuStartknoten[NachFolgerKnoten]) {
                            AbstandZuStartknoten[NachFolgerKnoten] = AbstandZuStartknoten[Knoten] + g.weight(Knoten, nachfolger);
                            vorgaenger[NachFolgerKnoten] = Knoten;
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
        Startknoten=s;
        AbstandZuStartknoten = new double[Size];
        vorgaenger = new Integer[Size];

        BinHeap<Double, Integer> heap=new BinHeap();
        BinHeap.Entry[] entryArray=new BinHeap.Entry[Size];
        for (int i = 0; i < Size; i++) {
            AbstandZuStartknoten[i] = INF;
            vorgaenger[i] = NIL;
        }
        AbstandZuStartknoten[Startknoten] = 0;

        for(int i=0;i<Size;i++){
            if(i == Startknoten){
                entryArray[i]=heap.insert(0.0,i);
            }
            else{
                entryArray[i]=heap.insert(Double.POSITIVE_INFINITY,i);
            }

        }
        do{
            BinHeap.Entry<Double,Integer> knoten=heap.extractMin();
            int Knoten =knoten.data();
            int succnr=g.deg(Knoten);

            for(int i=0;i<succnr;i++){

                if(heap.contains(entryArray[g.succ(Knoten,i)]) && (AbstandZuStartknoten[Knoten] + g.weight(Knoten,i) )< AbstandZuStartknoten[g.succ(Knoten,i)]){
                    vorgaenger[ g.succ(Knoten,i)]=Knoten;
                    AbstandZuStartknoten[g.succ(Knoten,i)]=AbstandZuStartknoten[Knoten] + g.weight(Knoten,i);
                    heap.changePrio(entryArray[g.succ(Knoten,i)],  AbstandZuStartknoten[g.succ(Knoten,i)]);


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
