// Streuwertfunktion gemäß Multiplikationsmethode
// (Implementierung mit 32-Bit-Ganzzahlarithmetik).
import java.lang.Math;
import java.math.BigDecimal;

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
        boolean klaus=false;
        int h=key.hashCode(),j; //Hashcode ist der Streuwert?


        if(klaus) {
            BigDecimal v,u,i;
            u = new BigDecimal(seed).multiply(new BigDecimal(h));
            v = u.remainder(new BigDecimal(Math.pow(2, 32)));
            i = v.divide(new BigDecimal(Math.pow(2, 32 - bits)));
            return i.intValue();
        }
        else {
            j = h * seed >>> 32 - bits; // = (h * seed) >>> (32 -bits);
            return j;
        }
    }
}
