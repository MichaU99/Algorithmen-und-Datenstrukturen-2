public class TestMainG {
    public static void main(String[] args) {
        GraphImpl graph = new GraphImpl(new int[][]{
                {1, 2},    // Knoten 0 hat als Nachfolger Knoten 1 und 2.
                {},    // Knoten 1 hat keine Nachfolger.
                {2}    // Knoten 2 hat als Nachfolger sich selbst.
        });
        graph.printAdjazenzMatrix();
        graph=graph.transpose();
        System.out.println();
        graph.printAdjazenzMatrix();
        FIFOList fifo=new FIFOList(5);
        fifo.add(6);
        fifo.add(7);
        for(Object i=fifo.extractFirst();i!=null;i= fifo.extractFirst()){
            System.out.println(i);
        }
    }

}