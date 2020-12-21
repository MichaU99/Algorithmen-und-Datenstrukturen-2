public class DFSImpl implements DFS{
    Integer [] entdeckungszeit = null;
    int entdeckungszeitzahl =1;
    Integer [] abschlusszeit = null;
    int abschlusszeitzahl=1;
    Integer[] vorgaenger = null;
    String [] Farbe = null;
    Integer size;
    Integer Knoten;
    Graph graph;
    @Override
    public void search(Graph g) {
        graph=g;
        size = g.size();
        Farbe= new String [size];
        for( Knoten=0;Knoten<size;Knoten++){
                Farbe[Knoten]="white";
            }
        vorgaenger = new Integer[size];

        for( Knoten=0;Knoten < size;Knoten++){
            if(Farbe[Knoten]== "white") {
               vorgaenger[Knoten]=null;

                durchsuche(graph, 0);
            }
        }

    }
        private void durchsuche(Graph g,Integer knoten) {
            entdeckungszeit[knoten] = entdeckungszeitzahl;
            if (entdeckungszeitzahl < size) entdeckungszeitzahl++;
            Farbe[knoten] = "grey";
            for (int Nachfolger = 0; Nachfolger < g.deg(knoten); Nachfolger++) {
                int succ = g.succ(knoten, Nachfolger);
                if (Farbe[succ] == "white") {
                    vorgaenger[succ] = knoten;
                    durchsuche(g,succ);
                }
            }
            abschlusszeit[knoten] = abschlusszeitzahl;
            if (abschlusszeitzahl < size) abschlusszeitzahl++;
        }

        public void druckDFS(Graph g) {
            for (int l = 0; l < g.size(); l++) {
                System.out.print(l + " " + entdeckungszeit[l] + " " + abschlusszeit);
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


