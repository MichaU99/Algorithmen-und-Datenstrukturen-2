import java.lang.Math;
import java.util.Random;

public class Simple_Test {
    public static void main(String[] args){
        Random random= new Random();
        int i;
    /*MultiplicationMethod h = new MultiplicationMethod(10, (int) (((Math.sqrt(5)-1)/2)*Math.pow(2,32)));
        System.out.println((h.compute(500))) ;
        DivisionMethod d=new DivisionMethod((int) (((Math.sqrt(5)-1)/2)*Math.pow(2,32)));
        System.out.println(d.compute(500));*/
        HashTableChaining hashtable=new HashTableChaining(new HashFunction() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public int compute(Object key) {
                return 0;
            }
        });
        hashtable.put("hallo","bruder");

    hashtable.dump();
    }
}
