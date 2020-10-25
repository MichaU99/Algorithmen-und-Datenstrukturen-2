import java.lang.Math;
import java.util.Random;

public class Simple_Test {
    public static void main(String[] args){
        Random random= new Random();
        int i;
        MultiplicationMethod m = new MultiplicationMethod(10, (int) (((Math.sqrt(5)-1)/2)*Math.pow(2,32)));
        DivisionMethod d=new DivisionMethod((int) (Math.pow(2,10)));
        for(i=0;i<100;i++) {
            System.out.println("Mit Input i="+i+" M="+m.compute(i)+" D="+d.compute(i));
        }
        /*HashTableChaining hashtable=new HashTableChaining(new HashFunction() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public int compute(Object key) {
                return 0;
            }
        });
        Random rand=new Random();
        for(i=0;i<10000;i++) {
            hashtable.put(i, rand.nextInt(9999999));
        }
    hashtable.dump();*/
    }
}
