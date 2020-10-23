// Streuwertfunktion gemäß Multiplikationsmethode
// (Implementierung mit 32-Bit-Ganzzahlarithmetik).
import java.lang.Math;
public class MultiplicationMethod extends AbstractHashFunction{

    // Anzahl p von Bits.
    private int bits;

    // Parameter s = A'=A*2^32.
    private int seed;

    // Multiplikationsmethode für Tabellengröße N = 2 hoch p
    // mit Parameter s.
    public MultiplicationMethod (int p, int s) {
        super(1 << p);	// 1 << p entspricht 2 hoch p.
        bits = p;
        seed = s;
    }

    @Override
    public int compute(Object key) {
        int h=key.hashCode(); //Hashcode ist der Streuwert?
        double i,v,u;

        u=(h*seed);
        v=u%Math.pow(2.0,bits);
        i=v/(Math.pow(2,32-bits));
        return (int) i;
    }
}
