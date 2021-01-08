

public class DFSImpl implements DFS{
    int[] seq=null;
    Integer [] entdeckungszeit = null;
    int entdeckungszeitzahl;
    Integer [] abschlusszeit = null;
    Integer[] vorgaenger = null;
    String [] Farbe = null;
    Integer size;

    int knot;
    Graph g;
    boolean trop=true;

    // TODO: 23.12.2020
    @Override
    public void search(Graph g) {
        entdeckungszeitzahl =1;
        entdeckungszeit= new Integer[g.size()];
        abschlusszeit= new Integer[g.size()];
        Farbe= new String[g.size()];
        this.g=g;
        size = g.size();
        Farbe= new String [size];
        for(int Knoten=0;Knoten<size;Knoten++){
                Farbe[Knoten]="white";
            }
        vorgaenger = new Integer[size];

        for( int Knoten=0;Knoten < size;Knoten++){ // wird schleife gebraucht überhaupt?
            if(Farbe[Knoten].equals("white")) {

               vorgaenger[Knoten]=null;

                durchsuche(g,Knoten);
            }
        }
    calculateSort();
    }
        private void durchsuche(Graph g,int knoten) {
            entdeckungszeit[knoten] = entdeckungszeitzahl++;
            Farbe[knoten] = "grey";
            for (int Nachfolger = 0; Nachfolger < g.deg(knoten); Nachfolger++) {
                int succ = g.succ(knoten, Nachfolger);
                if (Farbe[succ].equals("white")) {
                    vorgaenger[succ] = knoten;
                    durchsuche(g,succ);
                }
                else if (Farbe[succ].equals("grey")){ //für topolpgische sortierung
                    trop=false;
                }
            }
            abschlusszeit[knoten] = entdeckungszeitzahl++;
            Farbe[knoten] = "black";
        }



    @Override
    public void search(Graph g, DFS d) { // erstmal keine neuen Entdeckungs und abschlussArrays gemacht

        entdeckungszeitzahl =1;
        for( knot=0;knot<size;knot++){
            Farbe[knot]="white";
        }

        vorgaenger = new Integer[size];
       //d.sequ(g.size() - 1) bis d.sequ(0)
        for( int abstAbschl=(g.size()-1);abstAbschl > -1 ;abstAbschl--){ // wird schleife gebraucht überhaupt?
            knot=d.sequ(abstAbschl);
            if(Farbe[knot].equals("white")) {

                vorgaenger[knot]=null;

                durchsuche(g,knot);
            }
        }
        calculateSort();
    }

    @Override
    public boolean sort(Graph g) {
        search(g);
        return trop;
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
        Integer[] nochEinzufuegen=new Integer[abschlusszeit.length];
        for(int i=0;i<abschlusszeit.length;i++) nochEinzufuegen[i]=abschlusszeit[i];
        seq=new int[abschlusszeit.length];

        for(int k=0;k<seq.length;k++){
            q_place=q=null;
            for(int lauf=0;lauf<nochEinzufuegen.length;lauf++){
                if((nochEinzufuegen[lauf]!=null && q==null) || (nochEinzufuegen[lauf]!=null && q>nochEinzufuegen[lauf])){
                    q=nochEinzufuegen[lauf];
                    q_place=lauf;
                }
            }
            if(q==null || q_place==null) break;
            nochEinzufuegen[q_place]=null;
            seq[k]=q_place;
        }
    }

}


