import java.io.IOException;
import java.lang.Math;
import java.util.Random;

public class Simple_Test {
    public static void main(String[] args) throws IOException {
        java.io.BufferedReader r = new java.io.BufferedReader(
                new java.io.InputStreamReader(System.in));
        Random random = new Random();
        int i,j,k,size=1000000;
        MultiplicationMethod m = new MultiplicationMethod(10, (int) (((Math.sqrt(5) - 1) / 2) * Math.pow(2, 32)));
        DivisionMethod d = new DivisionMethod(10);
        String[] HashFunction = new String[]{"d","m"};
        String[] HashTable = new String[]{"c","open"};
        String[] HashSequence = new String[]{"l","q","d"};

            HashTable hashtable = new HashTableChaining(d);
            Random rand = new Random();
            hashtable.put(1, 10);
            hashtable.put(2, 11);
            hashtable.put(3, 12);
            hashtable.put(13, 13);
            hashtable.put(4, 20);
        while (true) {
            for(i=0;i<=1;i++){ //d/m
                for(j=0;j<=1;j++){ //c/open
                    for(k=0;k<=2;k++){ //linear/quad/double
                        //if(HashFunction[i]=="d") hashtable=
                        while (true) {
                            String word = r.readLine();
                            if (word == null) break;
                            Integer count = (Integer) hashtable.get(word);
                            if (count == null) count = 0;
                            hashtable.put(word, count + 1);
                        }
                    }
                }
            }
        }

            //hashtable.dump();
    }
}
