import java.lang.reflect.Array;

public class DefaultTestG {
    public static void main(String[] args) {
        //Testet ob Inserts aktiv sind
        int [] ArrayList;
        Graph graph;
        try {
            assert (false);
            System.out.println("Fehler: Asserts nicht aktiv");
            return;
        } catch (AssertionError f) {
            System.out.println("Test beginnt");
        }

        String testsToDo = "GraphImpl";//Put in the tests you want to perform (test1 test2 test3 usw)
        for (String list : testsToDo.split(" ")) {

            switch (list) {

                case "GraphImpl":
                    int [][] Muster = new int [][] {
                            {2},
                            {0,2,4},
                            {},
                            {0,1},
                            {0,3},
                            {4,6},
                            {3,5,6},
                    };
                    graph = new GraphImpl(Muster);
                    assert (graph.size() == 7) : "Size sollte 7 sein, ist aber " + graph.size();
                    ArrayList = new int [] {1,3,0,2,2,2,3};
                    for (int i = 0; i < ArrayList.length; i++) {
                        assert (graph.deg(i) == ArrayList[i]) : "Der Knoten " + i + " sollte " + ArrayList[i] + " Nachfolger haben, ist aber " + graph.deg(i);
                    }
                    for (int i = 0; i < graph.size(); i++) {
                        for (int k = 0; k < graph.deg(i); k++) {
                            for (int j = 0; j < graph.deg(i) ;j++) {
                                if (graph.succ(i, j) == Muster[i][k]) break;
                            }
                            assert (true) : "Der Knoten " + i + " sollte " + ArrayList[i] + " als direkten Nachfolger haben, ist aber " + graph.succ(i, i);
                        }
                    }
                    int [][] Muster2 = new int[][] {
                            {1,3,4},
                            {3},
                            {0,1},
                            {4},
                            {1,5,6},
                            {6},
                            {5,6},
                    };
                    Graph graph2 = new GraphImpl(Muster2);
                    assert (graph2.equals(graph.transpose())) : "transpose ist nicht korrekt";
                    break;

                // Gerichteter gewichteter Graph
                case "WeightedGraph":
                    WeightedGraph Wgraph = new WeightedGraphImpl(new int [][] {
                            {2},
                            {0,2,4},
                            {},
                            {0,1},
                            {0,3},
                            {4,6},
                            {3,5,6},
                    },new double[][]{});
                    // Hier ist noch Baustelle
                    ArrayList = new int [] {};
                    for (int i = 0; i < ArrayList.length; i++) {
                        assert (Wgraph.weight(i, 0) == -2) : " "; // TODO: 28.12.2020

                    }
                    break;

                // Breitensuche
                case "Breitensuche":
                    graph = new GraphImpl(new int [][] {
                            {2},
                            {0,2,4},
                            {},
                            {0,1},
                            {0,3},
                            {4,6},
                            {3,5,6},
                    });
                    BFS graphBFS = new BFSImpl();
                    graphBFS.search(graph, 1);
                    ArrayList = new int [] {1,0,1,2,1,-1,-1};
                    for (int i = 0; i < ArrayList.length; i++) {
                        assert (graphBFS.dist(i) == ArrayList[i]) : "Distance des Knotens " + i + " sollte " + ArrayList[i] + " sein, ist aber " + graphBFS.dist(i);
                    }
                    ArrayList = new int [] {1,-1,1,4,1,-1,-1};
                    for (int i = 0; i < ArrayList.length; i++) {
                        assert (graphBFS.pred(i) == ArrayList[i]) : "Vorgänger des Knotens " + i + " sollte " + ArrayList[i] + " sein, ist aber " + graphBFS.dist(i);
                    }
                    break;

                // Tiefensuche einschließlich topologischer Sortierung
                case "Tiefensuche":
                    graph = new GraphImpl(new int [][] {
                            {2},
                            {0,2,4},
                            {},
                            {0,1},
                            {0,3},
                            {4,6},
                            {3,5,6},
                    });
                    DFS graphDFS = new DFSImpl();
                    graphDFS.sort(graph);
                    if (graphDFS.sort(graph)) {
                        assert (true) : "Topologische Sortierung ist möglich";
                    } else {
                        assert (false) : "Graph enthält einen Zyklus";
                    }
                    ArrayList = new int[] {1,2,2,4,3,0,0,3,4,1,5,6,6,5};
                    for (int i = 0; i < ArrayList.length; i++) {
                        assert (graphDFS.det(i) == ArrayList[i]) : " ";
                    }
                    for (int i = 0; i < ArrayList.length; i++) {
                        assert (graphDFS.fin(i) == ArrayList[i]) : " ";
                    }
                    // Zwei Methoden?

                    for (int i = 0; i < ArrayList.length; i++) {
                        assert (graphDFS.sequ(i) == ArrayList[i]) : "Die Eingabe stimmt nicht mit dem Knoten überein.";
                    }
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

                default:
                    System.out.println("Fehlerhafte Eingabe bei der Testfallwahl");
                    }
        }
    }
}
