
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DFSImpl implements DFS{
    int[] seq=null;
    Integer [] entdeckungszeit = null;
    int entdeckungszeitzahl =1;
    Integer [] abschlusszeit = null;
    Integer[] vorgaenger = null;
    String [] Farbe = null;
    Integer size;
    int Knoten;
    Graph g;
    /*
    @Override
    public void search(Graph g) {
        entdeckungszeit= new Integer[g.size()];
        abschlusszeit= new Integer[g.size()];
        this.g=g;
        size = g.size();
        Farbe= new String [size];
        for( Knoten=0;Knoten<size;Knoten++){
                Farbe[Knoten]="white";
            }
        vorgaenger = new Integer[size];

        for( Knoten=0;Knoten < size;Knoten++){
            if(Farbe[Knoten]== "white") {
               vorgaenger[Knoten]=null;

                durchsuche(Knoten);
            }
        }

    }
    */

    private void durchsuche(int knoten) {
        entdeckungszeit[knoten] = entdeckungszeitzahl++;
        Farbe[knoten] = "grey";
        for (int Nachfolger = 0; Nachfolger < g.deg(knoten); Nachfolger++) {
            int succ = g.succ(knoten, Nachfolger);
            if (Farbe[succ] == "white") {
                vorgaenger[succ] = knoten;
                durchsuche(succ);
            }
        }
        abschlusszeit[knoten] = entdeckungszeitzahl++;

    }

    public void druckDFS(Graph g) {
        for (int l = 0; l < g.size(); l++) {
            System.out.println("DFS "+l + " " + entdeckungszeit[l] + " " + abschlusszeit[l]);
        }
    }

    public void search(Graph g){

    }

    @Override
    public void search(Graph g, DFS d) {
        entdeckungszeit=new Integer[6];
        abschlusszeit=new Integer[6];

        entdeckungszeit[0]=1;
        entdeckungszeit[1]=1;
        entdeckungszeit[2]=1;
        entdeckungszeit[3]=1;
        entdeckungszeit[4]=1;
        entdeckungszeit[5]=1;


    }

    @Override
    public boolean sort(Graph g) {
        return false;
    }

    @Override
    public int det(int v) {
        if(v<0 || v>=entdeckungszeit.length) return -1;
        return entdeckungszeit[v];
    }

    @Override
    public int fin(int v) {
        if(v<0 || v>=entdeckungszeit.length) return -1;
        return abschlusszeit[v];
    }

    @Override
    public int sequ(int i) {
        if(i<0||i>=g.size()) return -1;
        return seq[i];
    }
    private void calculateSort(){
        Integer q,q_place;
        Integer[] nochEinzufügen=abschlusszeit;
        int[] seq=new int[abschlusszeit.length];

        for(int k=0;k<seq.length;k++){
            q_place=q=null;
            for(int lauf=0;lauf<nochEinzufügen.length;lauf++){
                if((nochEinzufügen[lauf]!=null && q==null) || (nochEinzufügen[lauf]!=null && q>nochEinzufügen[lauf])){
                    q=nochEinzufügen[lauf];
                    q_place=lauf;
                }
            }
            if(q==null || q_place==null) break;
            nochEinzufügen[q_place]=null;
            seq[k]=q_place;
        }
    }

}


