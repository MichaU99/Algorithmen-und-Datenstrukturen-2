public class DefaultTestG {
    public static void main(String[] args) {
        //Testet ob Inserts aktiv sind
        try {
            assert (false);
            System.out.println("Fehler: Asserts nicht aktiv");
            return;
        } catch (AssertionError f) {
            System.out.println("Test beginnt");
        }

        String testsToDo = "graphImpl test";//Put in the tests you want to perform (test1 test2 test3 usw)
        for (String list : testsToDo.split(" ")) {

            switch (list) {

                case "GraphImpl":
                    Graph graph = new GraphImpl(new int[][]{
                            {1, 2},    // Knoten 0 hat als Nachfolger Knoten 1 und 2.
                            {},    // Knoten 1 hat keine Nachfolger.
                            {2}    // Knoten 2 hat als Nachfolger sich selbst.
                    });
                    break;

                // Breitensuche
                case "Breitensuche":
                    BFS graphBFS = new BFSImpl();
                    graphBFS.search(Graph g, int s);
                    graphBFS.dist(int v);
                    graphBFS.pred(int v);
                    assert (graphBFS.dist(int v) == null) : "Gleich null";
                    assert (graphBFS.pred(int v) == null) : "Gleich null";
                    break;

                // Tiefensuche einschließlich topologischer Sortierung
                case "Tiefensuche":
                    DFS graphDFS = new DFSImpl();
                    graph.size();
                    graphDFS.search(Graph g);
                    graphDFS.sort(Graph g);
                    graphDFS.det(int v);
                    graphDFS.fin(int v);
                    graphDFS.sequ(int i);
                    assert (graphDFS.sort(Graph g) == true) : "Sortierung ist möglich.";
                    assert (graphDFS.det(int v) < 0 || graph.size()) : " ";
                    assert (graphDFS.fin(int v) < 0 || graph.size()) : " ";
                    assert (graphDFS.sequ(int i) < 0 || graph.size()) : " ";
                    break;

                // Bestimmung starker Zusammenhangskomponenten
                case "Zusammenhangskomponenten":
                    SCC graphSCC = new SCCImpl();
                    break;

                // Bestimmung minimaler Gerüste nach Prim
                case "minPrim":
                    MSF graphMSF = new MSFImpl();
                    break;

                // Bestimmung kürzester Wege nach Bellman-Ford und Dijkstra
                case "BellmanFordDijkstra":
                    SP graphSP = new SPImpl();
                    break;

                // Test
                case "test":
                    System.out.println("Test");


/*
        Graph graph=new GraphImpl(new int [] [] {
                { 1, 2 },	// Knoten 0 hat als Nachfolger Knoten 1 und 2.
                { },	// Knoten 1 hat keine Nachfolger.
                { 2 }	// Knoten 2 hat als Nachfolger sich selbst.
        });

        assert (graph.size()==3):"Die size des Graphen stimmt nicht. Wirkliche Größe ist 3 vs."+graph.size();

        assert (graph.deg(0)==2):"Deg von v=0 sollte 2 sein, ist aber "+graph.deg(0);
        assert (graph.deg(1)==0):"Deg von v=1 sollte 0 sein, ist aber "+graph.deg(1);
        assert (graph.deg(2)==1):"Deg von v=2 sollte 1 sein, ist aber "+graph.deg(2);

        //assert (graph.succ(1,5)==0):"Fehler in succ, sollte 0 sein, ist aber "+graph.succ(1,0);
       // assert (graph.succ(2,0)==2):"Fehler in succ, sollte 2 sein, ist aber "+graph.succ(2,0);
        System.out.println("Alle Tests wurden bestanden");
         */

                    }
        }
    }
}
