import java.lang.Math;
public class Simple_Test {
    public static void main(String[] args){
    MultiplicationMethod h = new MultiplicationMethod(10, (int) (((Math.sqrt(5)-1)/2)*Math.pow(2,32)));
        System.out.println((h.compute(500))) ;
    }
}
