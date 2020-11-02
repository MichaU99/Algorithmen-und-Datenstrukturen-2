// Sondierungssequenz gemäß quadratischer Sondierung
// (Implementierung nur mit Ganzzahlarithmetik).

import org.w3c.dom.ls.LSOutput;

import java.lang.Math;

public class QuadraticProbing extends AbstractHashSequence{
    // Quadratische Sondierung mit Streuwertfunktion f.
    Object key;
    int ausgangselement,count;
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
        int quadratischer_sprung= (int) ((count+Math.pow(count,2))/2);
        if(count>=(size()-1)) return -1; //Muss extra getested werden
        ausgangselement=(ausgangselement+(int) ((count+Math.pow(count,2))/2)) % size();
        count++;
        return ausgangselement;

    }
}
