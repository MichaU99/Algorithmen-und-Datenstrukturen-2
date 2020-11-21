public class TestMain {
    public static void main(String[] args) {
        BinHeap H =new BinHeap();
        int i,j;
        for (i=0;i<=100;i++) {
            System.out.println(">>>>>"+H.insert(i,i));
        }
        H.dump();

    }
}
