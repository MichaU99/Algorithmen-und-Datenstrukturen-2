import java.util.ArrayList;
import java.util.List;

public class SCCImpl implements SCC{

    List<List<Integer>> woodOfTrees;
    Graph g;

    @Override
    public void compute(Graph g) {
        this.g=g;
        int i,j; //laufvariabeln
        woodOfTrees = new ArrayList<>();
        int[] seq = new int[g.size()];
        DFS tiefensucheG = new DFSImpl();
        DFS tiefensucheGtlive = new DFSImpl();
        DFS search;

        tiefensucheG.search(g);
        tiefensucheG.search(g.transpose(),tiefensucheG); //Wie muss hier das zweite search aufgerufen werden? Mit demselben DFS oder einem anderen?
        //tiefensucheGtlive.search(g.transpose(), tiefensucheG); //Wie schaffe ich hier eine absteigende Reihenfolge in der äußeren Schleife?

        search = tiefensucheG; //search muss auf die Scueh gesezt werden die die richtigen Inforamtionen enthält
        for (i = 0; i < g.size(); i++) {
            seq[i] = search.sequ(i);
        }
        for(int k=i=0;i<g.size();k++){ //k=anzahl der bäume,i ist lauf, j ist die begrenzung von i
            woodOfTrees.add(new ArrayList<>());
            for(j=getEndofTree(i,search);i<=j;i++){
                woodOfTrees.get(k).add(seq[i]);
            }
            i=j+1;
            if(i>=g.size()) break;
        }
    }
    private int getEndofTree(int i,DFS searched){//give one element of the tree, and get the beginning of that tree back
        while(true) {
            for (int k = 0; k < g.deg(i); k++) {
                if (searched.det(g.succ(i, k)) == searched.det(i)-1) {
                    i = g.succ(i, k);
                    break;
                }
            }
            return i;
        }
    }


    @Override
    public int component(int v) {
        for(int i=0;i<woodOfTrees.size();i++){
            if(woodOfTrees.get(i).contains(v)) return i;
        }
        return -1; //Wäre eine Fehlerausgabe
    }
}
