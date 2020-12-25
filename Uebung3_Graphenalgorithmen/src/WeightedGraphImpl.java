public class WeightedGraphImpl implements WeightedGraph{
    private int[][] Graph;
    private boolean[][] Adjazenzmatrix;
    private int size;
    public int [][] tranTemp;
    private double[][] weight;

    public WeightedGraphImpl(int[][] ints, double[][] doubles) {
        this.Graph=ints;
        this.weight=doubles;
        size=size();
        Adjazenzmatrix=new boolean[size()][size()];
        makeAdj();
    }

    private void makeAdj(){
        for (int adv=0;adv<size;adv++){
            for(int adh=0;adh<size;adh++){
                for(int intv=0;intv<deg(adv);intv++){
                    if(Graph[adv][intv]==adh) Adjazenzmatrix[adh][adv]=true;
                }
            }
        }
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
        return tranTemp;
    }

    @Override
    public int size() {
        if(Graph==null) return 0;
        return Graph.length;
    }

    @Override
    public int deg(int v) {
        if(Graph==null || v<0|| v>size()) return 0;
        return Graph[v].length;
    }

    @Override
    public int succ(int v, int i) {
        if(Graph==null || v<0|| v>size() || i<0 || i>deg(v)) return -1; //Frage
        return Graph[v][i];
    }

    @Override
    public Graph transpose() {
        return new GraphImpl(transGraph());
    }

    @Override
    public double weight(int v, int i) {
        return 0;
    }
}
