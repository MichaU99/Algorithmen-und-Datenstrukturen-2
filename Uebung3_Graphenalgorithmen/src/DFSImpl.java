public class DFSImpl implements DFS{
    Integer [] entdeckungszeit = null;
    int entdeckungszeitzahl =1;
    Integer [] abschlusszeit = null;
    Integer[] vorgaenger = null;
    String [] Farbe = null;
    Integer size;
    int Knoten;
    Graph g;
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

    @Override
    public void search(Graph g, DFS d) {

    }

    @Override
    public boolean sort(Graph g) {
        return false;
    }

    @Override
    public int det(int v) {
        return 0;
    }

    @Override
    public int fin(int v) {
        return 0;
    }

    @Override
    public int sequ(int i) {
        return 0;
    }
}


