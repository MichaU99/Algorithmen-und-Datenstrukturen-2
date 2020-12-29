import java.util.ArrayList;

public class GraphImpl implements Graph{
    private int[][] Graph;
    private int size;
    public int [][] tranTemp;
    ArrayList<Integer> anzNachfolger;
    public GraphImpl(int[][] ints) {
        this.Graph=ints;
        size=size();
    }

    public int[][] transGraph(){ // retunrstatment??
        tranTemp = new int[size()][size()];
        for(int Ho=0;Ho<size();Ho++){
            for (int Ve=0; Ve<size();Ve++){
                tranTemp[Ho][Ve]=-1;
            }
        }
        for(int listHo=0;listHo < size();listHo++){
            for(int listVe=0;listVe < deg(listHo);listVe++){
                Integer a = Graph[listHo][listVe];
                for(int tranVe=0;tranVe < size();tranVe++){
                    Integer b=tranTemp[a][tranVe];
                        if( b == -1) {
                            tranTemp[a][tranVe] = listHo;
                            break;
                        }
                    }
            }
        }
        for(int i=0;i<size;i++){
            anzNachfolger=new ArrayList();
            for(int k=0;k<size;k++){
                if(tranTemp[i][k]!=-1) anzNachfolger.add(tranTemp[i][k]);
            }
            int[] array=new int[anzNachfolger.size()];
            for(int l=0;l<array.length;l++){
                array[l]=anzNachfolger.get(l);
            }

            tranTemp[i]= array;
        }

        return tranTemp;
    }

    @Override
    public int size() {
        if(Graph==null) return 0;
        return Graph.length;
    }

    @Override
    public int deg(int v) { //Returned den die Anzahl der Nachfolger jedes Knotens
        if(Graph==null || v<0|| v>size()) return 0;
        return Graph[v].length;
    }

    @Override
    public int succ(int v, int i) {//v=Knoten i=nr des Nachfolgers (0->erster,1->zweiter,usw)
        if(Graph==null || v<0|| v>size() || i<0 || i>deg(v)) return -1; //Frage
        return Graph[v][i];
    }

    @Override
    public Graph transpose() {
        return new GraphImpl(transGraph());
    }

    public int[][] getGraph() {
        return Graph;
    }
}
