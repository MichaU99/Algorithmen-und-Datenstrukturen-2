import java.util.Map;

public class BFSImpl implements BFS {
    Integer [] ergebnis=null;
    Integer[] vorgaenger=null;
    @Override
    public void search(Graph g, int s) {
        int lauf=0;
        BinHeap<Integer,Integer> heap=new BinHeap<>();
        if(g==null ||g.size()-1<s|| s<0) return; // müssen abgefangen werden?

        int size=g.size();
        ergebnis=new Integer[g.size()];
        vorgaenger=new Integer[g.size()];
        heap.insert(lauf++,s);
        //Initialise ergebnis
        for(int i=0;i<size;i++){
            if(i!=s) ergebnis[i]=null;
            else ergebnis[i]=0;
            vorgaenger[i]=null;
        }

        for(BinHeap.Entry<Integer,Integer>e=heap.minimum(); !heap.isEmpty(); e=heap.minimum()){
            heap.remove(e);
            for (int i=0;i< g.deg(e.data());i++){
                int succ=g.succ(e.data(),i);
                if(ergebnis[succ]==null){
                    ergebnis[succ]=ergebnis[e.data()]+1;
                    vorgaenger[succ]=e.data();
                    heap.insert(lauf++,succ);
                }

            }

        }
    }

    @Override
    public int dist(int v) {
        if(ergebnis==null ||ergebnis[v]==null) return NIL;
        return ergebnis[v];
    }

    @Override
    public int pred(int v) {
        if(vorgaenger==null|| vorgaenger[v]==null) return INF;
        return vorgaenger[v];
    }
}
