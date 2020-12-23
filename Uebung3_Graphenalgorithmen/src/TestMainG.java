public class TestMainG {
    public static void main(String[] args) {
        GraphImpl graph = new GraphImpl(new int[][]{
                {1, 3, 5},    // Knoten 0 hat als Nachfolger Knoten 1 und 2.
                {},    // Knoten 1 hat keine Nachfolger.
                {3, 6},
                {4, 5},
                {5, 0},
                {1},
                {2, 6},// Knoten 2 hat als Nachfolger sich selbst.
        });

        DFSImpl dfs = new DFSImpl();
        dfs.search(graph);
        String vergleich = null;

        for (int i = 0; i < graph.size(); i++) {
            vergleich = ((Integer) dfs.sequ(i)).toString() + " ";
        }
        assert (vergleich == "1 5 4 3 0 6 2 ");

        SCCImpl scc = new SCCImpl();
        scc.compute(graph);
        System.out.print("SCC :");
        for (int i = 0; i < graph.size(); i++) {
            System.out.print(scc.component(i) + " ");
        }
        System.out.println();

/*
        BFSImpl bsf=new BFSImpl();
        bsf.search(graph,0);

        graph=new GraphImpl(new int [][]{
                {5,2,6,7},
                {2},
                {0,1,3},
                {4,2},
                {3,5},
                {4,0,6},
                {5,0,7},
                {0,6,8},
                {7}
        });
        bsf.search(graph,4);
        for(int i=0;i<graph.size();i++) {
            System.out.println(bsf.dist(i));
        }
        System.out.println();
        for(int i=0;i<graph.size();i++) {
            System.out.println(bsf.pred(i));
        }
        DFSImpl dfs2 = new DFSImpl();
        dfs2.search(graph);
        dfs2.druckDFS(graph);
        System.out.println("Kann Tropologisch sortiert werden: "+dfs2.sort(graph));

    }
*/
    }
}
