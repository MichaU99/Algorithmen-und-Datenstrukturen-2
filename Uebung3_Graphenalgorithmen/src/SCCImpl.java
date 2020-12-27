import java.util.ArrayList;
import java.util.List;

public class SCCImpl implements SCC{

    List<List<Integer>> woodOfTrees;
    Graph g;

    @Override
    public void compute(Graph g) {
        this.g=g;
        int i,j,tmp=-1; //laufvariabeln
        woodOfTrees = new ArrayList<>();
        int[] seq = new int[g.size()];
        DFSImpl tiefensucheG = new DFSImpl();
        DFS tiefensucheGtlive = new DFSImpl();
        DFSImpl search;

        tiefensucheG.search(g);
        tiefensucheG.search(g.transpose(),tiefensucheG); //Wie muss hier das zweite search aufgerufen werden? Mit demselben DFS oder einem anderen?
        //tiefensucheGtlive.search(g.transpose(), tiefensucheG); //Wie schaffe ich hier eine absteigende Reihenfolge in der äußeren Schleife?

        search = tiefensucheG; //search muss auf die Scueh gesezt werden die die richtigen Inforamtionen enthält
        for (i = 0; i < g.size(); i++) {
            seq[i] = search.sequ(i);
        }
        for(int k=i=0;k<search.roots.size();k++){ //k=anzahl der bäume,i ist lauf, j ist die begrenzung von i
            woodOfTrees.add(new ArrayList<>());
            for(int q=0;q<seq.length;q++){
                if(search.roots.get(k)==seq[q]) tmp=q;
            }
            for(j=tmp;i<=j;i++){
                woodOfTrees.get(k).add(seq[i]);
            }
            i=j+1;
            if(i>=g.size()) break;
        }
    }
    //Diese Methode könnte durch eine Liste der Wurzelknoten ersetzt werden, die von DFS übergeben wird

// TODO: 27.12.2020  wir haben immernoch keine Möglichkeit die components aus der Rückgaben der Tiefensuche zu berechnen
    @Override
    public int component(int v) {
        for(int i=0;i<woodOfTrees.size();i++){
            if(woodOfTrees.get(i).contains(v)) return i;
        }
        return -1; //Wäre eine Fehlerausgabe
    }
}
