import java.lang.reflect.Array;
import java.util.ArrayList;

public class DefaultTestG {
    public static void main(String[] args) {
        //Testet ob Inserts aktiv sind
        int [] ArrayList;
        int [] [] ArrayList2;
        Graph graph;
        WeightedGraph Wgraph;
        try {
            assert (false);
            System.out.println("Fehler: Asserts nicht aktiv");
            return;
        } catch (AssertionError f) {
            System.out.println("Test beginnt");
        }

        String testsToDo = "WeightedGraph";//Put in the tests you want to perform (test1 test2 test3 usw)
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
                    Wgraph = new WeightedGraphImpl(new int [][] {
                            {1,3,4},
                            {0,2,3,4,5},
                            {1,4,5},
                            {0,1,4},
                            {0,1,2,3,5},
                            {1,2,4},
                    }, new double[][]{
                            {3,5,1},
                            {3,8,5,2,7},
                            {8,7,5},
                            {5,5,4},
                            {1,2,7,4,8},
                            {7,5,8},
                    });
                    double [][] DListe = new double [][] {
                            {3,5,1},
                            {3,8,5,2,7},
                            {8,7,5},
                            {5,5,4},
                            {1,2,7,4,8},
                            {7,5,8},
                    };
                    for (int i = 0; i < DListe.length; i++) {
                        for (int j = 0; j < DListe[1].length; j++) {
                            assert (Wgraph.weight(i, j) == DListe.length) : "Das Gewicht von " + Wgraph.weight(i, j) + " beträgt " + DListe.length;
                        }
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
                    SCC SCC = new SCCImpl();
                    SCC.compute(graph);
                    graph = new GraphImpl((new int [][] {
                            {2},
                            {0,2,4},
                            {},
                            {0,1},
                            {0,3},
                            {4,6},
                            {3,5,6},
                    }));
                    ArrayList2 = new int [] [] {
                            {0,1,2,3,4},
                            {5,6},
                    };
                    ArrayList = new int [2];
                    for (i = 0; i < ArrayList.length; i++) {
                        kakau:
                        for (j = 0; j < ArrayList2.length; j++) {
                            for (k = 0; k < ArrayList2[1].length; k++) {
                                if (i == ArrayList2[j][k] && ArrayList[i] == null) {
                                    ArrayList[i] = SCC.component(i);
                                    break kakau;
                                }
                                else if (i == ArrayList2[j][k]) {
                                    assert (SCC.component(i) == ArrayList[i]) : "component stimmen nicht überein";
                                }
                            }
                        }
                    }
                    break;

                // Bestimmung minimaler Gerüste nach Prim
                case "minPrim":
                    MSF graphMSF = new MSFImpl();
                    Wgraph = new WeightedGraphImpl((new int [][] {
                            {1,3},
                            {0,2,3,4},
                            {1,4},
                            {0,1,4,5,6},
                            {1,2,3,6},
                            {3,6},
                            {3,4,5},
                    }, new double[][]{
                            {14,10},
                            {14,16,18,13},
                            {16,9},
                            {10,18,30,17,12},
                            {13,9,30,16},
                            {17,22},
                            {12,16,22};
                    }));
                    graphMSF.compute(Wgraph, 0);
                    ArrayList = new int [] {-1,0,4,0,1,3,3};
                    for (int i = 0; i < ArrayList.length; i++) {
                        assert (graphMSF.pred(i) == ArrayList[i]) : "Vorgängerknoten stimmt nicht";
                    }
                    break;

                // Bestimmung kürzester Wege nach Bellman-Ford und Dijkstra
                case "BellmanFordDijkstra":
                    SP SPgraph = new SPImpl();
                    Wgraph = new WeightedGraphImpl((new int [][] {
                            {1},
                            {2},
                            {4},
                            {3,5},
                            {1},
                            {},
                    }, new double[][]{
                            {50},
                            {-10},
                            {-5},
                            {-1,5},
                            {-1},
                            {},
                    }));
                    double [][] SPListe = new double [][] {
                            {50},
                            {-10},
                            {-5},
                            {-1,5},
                            {-1},
                            {},
                    };
                    ArrayList = new int [] {};
                    assert (!SPgraph.bellmanFord(Wgraph, 0)) : "BellmenFord liefert true zurück liefer sollte aber false zurück";
                    Wgraph = new WeightedGraphImpl((new int [][] {
                            {1},
                            {2,3},
                            {4},
                            {4},
                            {5},
                            {},
                    }, new double[][]{
                            {50},
                            {-10,-1},
                            {-5},
                            {-1},
                            {5},
                            {},
                    }));
                    double [][] SPNListe = new double [][] {
                            {50},
                            {-10,-1},
                            {-5},
                            {-1},
                            {5},
                            {},
                    };
                    assert (SPgraph.bellmanFord(Wgraph, 0)) : "BellmenFord liefert false zurück";
                    SPgraph = new SPImpl();
                    SPgraph.dijkstra(Wgraph, 0);
                    for (int i = 0; i < SPNListe.length; i++) {
                        assert (SPgraph.dist(i) == ArrayList[i]) : "Der Abstand " + i + " sollte " + ArrayList[i] + " sein, ist aber " + SPgraph.dist(i);
                    }
                    for (int i = 0; i < SPListe.length; i++) {
                        assert (SPgraph.pred(i) == ArrayList[i]) : "Der Abstand " + i + " sollte " + ArrayList[i] + " sein, ist aber " + SPgraph.dist(i);
                    }
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
