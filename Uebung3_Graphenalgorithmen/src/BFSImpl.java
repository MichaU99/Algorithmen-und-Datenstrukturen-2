public class BFSImpl implements BFS {
    FIFOList fifo;
    Integer [] ergebnis=null;
    Integer[] vorgaenger=null;
    @Override
    public void search(Graph g, int s) {
        if(g==null ||g.size()-1<s|| s<0) return; // müssen abgefangen werden?

        int size=g.size();
        ergebnis=new Integer[g.size()];
        vorgaenger=new Integer[g.size()];
        fifo=new FIFOList(s);
        //Initialise ergebnis
        for(int i=0;i<size;i++){
            if(i!=s) ergebnis[i]=null;
            else ergebnis[i]=0;
            vorgaenger[i]=null;
        }

        for(Integer e=fifo.extractFirst();e!=null;e=fifo.extractFirst()){
            for (int i=0;i< g.deg(e);i++){
                int succ=g.succ(e,i);
                if(ergebnis[succ]==null){
                    ergebnis[succ]=ergebnis[e]+1;
                    vorgaenger[succ]=e;
                    fifo.add(succ);
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
