public class TestMainG {
    public static void main(String[] args) {
        GraphImpl graph = new GraphImpl(new int[][]{
                {1, 2},    // Knoten 0 hat als Nachfolger Knoten 1 und 2.
                {},    // Knoten 1 hat keine Nachfolger.
                {2}    // Knoten 2 hat als Nachfolger sich selbst.
        });
        graph.printAdjazenzMatrix();
        //graph=graph.transpose();
        System.out.println();

        graph.printAdjazenzMatrix();

        FIFOList fifo = new FIFOList(5);
        fifo.add(6);
        fifo.add(7);

        DFSImpl dfs = new DFSImpl();
        dfs.search(graph);
        dfs.druckDFS(graph);
    }
}
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
    }

}
*/