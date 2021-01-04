import java.util.ArrayList;
import java.util.List;

public class SCCImpl implements SCC{

    List<List<Integer>> woodOfTrees;
    Graph g;
    ArrayList<Integer> nochNichtZugeteilt=null;

    @Override
    public void compute(Graph g) {
        if(g==null) return;

        this.g=g;
        int i,j,tmp=-1; //laufvariabeln
        woodOfTrees = new ArrayList<>();
        int[] seq = new int[g.size()];
        DFS tiefensucheG = new DFSImpl();
        DFS search;

        tiefensucheG.search(g);
        int lastSeq=tiefensucheG.sequ(g.size()-1);
        tiefensucheG.search(g.transpose(),tiefensucheG);

        search = tiefensucheG; //search muss auf die Scueh gesezt werden die die richtigen Inforamtionen enthält
        for (i = 0; i < g.size(); i++) {
            seq[i] = search.sequ(i);
        }

        int rootKnoten=lastSeq; //Knoten mit dem ein Baum beginnt und endet , am Anfang immer der Startknoten
        int baumzaehler=0; //Anzahl der components im Baum
        int startknoten=0;//Laufvariable
        nochNichtZugeteilt=new ArrayList<>();
        for(int a=0;a< g.size();a++) nochNichtZugeteilt.add(a);
        do{
            woodOfTrees.add(new ArrayList<>());
            while(startknoten< seq.length){
                if(seq[startknoten]==rootKnoten){ //Abbruchsfall
                    woodOfTrees.get(baumzaehler).add(seq[startknoten]);
                    if(!searchDelete(seq[startknoten++])) assert (false):"Fehler, Element im seq ist kein Knoten des Graphen";
                    break;
                }
                woodOfTrees.get(baumzaehler).add(seq[startknoten]);
                if(!searchDelete(seq[startknoten++])) assert (false):"Fehler, Element im seq ist kein Knoten des Graphen";
            }

            baumzaehler++;
            for (int e:nochNichtZugeteilt){
                if(search.det(e)==(search.fin(rootKnoten)+1)){
                    rootKnoten=e;
                    break;
                }
            }
        }while(!nochNichtZugeteilt.isEmpty());
    }
    private boolean searchDelete(int toDel){
        int left=-1;
        int right=nochNichtZugeteilt.size();
        int middle;
        while(right>left+1){
            middle=(left+right)/2;
            if(nochNichtZugeteilt.get(middle)>=toDel) right=middle;
            else left=middle;
        }
        if(right<nochNichtZugeteilt.size() && nochNichtZugeteilt.get(right)==toDel){
            nochNichtZugeteilt.remove(right);
            return true;
        }
        return false;
    }

    @Override
    public int component(int v) {
        for(int i=0;i<woodOfTrees.size();i++){
            if(woodOfTrees.get(i).contains(v)) return i;
        }
        return -1; //Wäre eine Fehlerausgabe
    }
}
