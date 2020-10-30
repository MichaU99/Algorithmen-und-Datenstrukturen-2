import java.lang.Math;
import java.util.Random;

public class Simple_Test {
    public static void main(String[] args) {
        Random random = new Random();
        int i;
        MultiplicationMethod m = new MultiplicationMethod(10, (int) (((Math.sqrt(5) - 1) / 2) * Math.pow(2, 32)));
        DivisionMethod d = new DivisionMethod(10);

            HashTableChaining hashtable = new HashTableChaining(d);
            Random rand = new Random();
            hashtable.put(1, 10);
            hashtable.put(2, 11);
            hashtable.put(3, 12);
            hashtable.put(13, 13);
            hashtable.put(4, 20);

        //System.out.println(hashtable.remove(3));
            hashtable.dump();
    }
}
