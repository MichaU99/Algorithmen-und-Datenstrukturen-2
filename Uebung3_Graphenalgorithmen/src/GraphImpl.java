public class GraphImpl implements Graph{
    private int[][] Graph;
    private boolean[][] Adjazenzmatrix;
    private int size;
    public GraphImpl(int[][] ints) {
        this.Graph=ints;
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
    //Testfunktion
    public void printAdjazenzMatrix(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.print(Adjazenzmatrix[j][i]+" ");
            }
            System.out.println();
        }
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
    public GraphImpl transpose() {
        int[][] intarr=new int [size()][size()];

        return new GraphImpl(intarr);
    }
}
