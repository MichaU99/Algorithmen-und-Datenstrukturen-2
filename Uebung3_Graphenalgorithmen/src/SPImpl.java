public class SPImpl implements SP{
    Integer size;
    double [] AbstandZuStartknoten = null;
    Integer[] vorgaenger = null;
    int strartknoten;
    @Override
    public boolean bellmanFord(WeightedGraph g, int s) {
        size = g.size();
        strartknoten = s;
        AbstandZuStartknoten= new double[size];
        vorgaenger = new Integer[size];
        for(int i=0; i<size;i++){
            AbstandZuStartknoten[i]=INF;
            vorgaenger[i]=NIL;
        }
        AbstandZuStartknoten[s]=0;
        for(int i=0; i<size-1;i++){

            for(int Knoten=0;Knoten < size; Knoten++){
                if (Knoten == strartknoten){ // wie ist der vergleich hier? oder equals?
                    for(int nachfolger=0; nachfolger<g.deg(s); nachfolger++){
                        int NachfolgerKnoten=g.succ(s,nachfolger);
                        AbstandZuStartknoten[NachfolgerKnoten]= g.weight(s,NachfolgerKnoten);
                        vorgaenger[NachfolgerKnoten]=s;
                    }
                }
                else{
                    for(int nachfolger=0; nachfolger<g.deg(Knoten); nachfolger++){
                        int NachFolgerKnoten=g.succ(Knoten,nachfolger);
                        if( AbstandZuStartknoten [Knoten] == INF){

                        }
                    }
                }
            }
        }








        return false;
    }

    @Override
    public void dijkstra(WeightedGraph g, int s) {

    }

    @Override
    public double dist(int v) {
        return 0;
    }

    @Override
    public int pred(int v) {
        return 0;
    }
}
