// Sondierungssequenz gemäß quadratischer Sondierung
// (Implementierung nur mit Ganzzahlarithmetik).

import java.lang.Math;

public class QuadraticProbing extends AbstractHashSequence{
    // Quadratische Sondierung mit Streuwertfunktion f.
    Object key;
    int ausgangselement,count,ausgabeelement;
    public QuadraticProbing (HashFunction f) {
        super(f);
    }

    @Override
    public int first(Object key) {
        this.key=key;
        count=1;
        return ausgangselement=func.compute(key);
    }

    @Override
    public int next() {
        if(count>=(size()-1)) return -1; //Muss extra getested werden
        ausgabeelement=(ausgangselement+(int) ((count+Math.pow(count,2))/2)) % size();
        count++;
        return ausgabeelement;

    }
}
