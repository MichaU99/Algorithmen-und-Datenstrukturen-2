// Sondierungssequenz gemäß doppelter Streuung.

public class DoubleHashing extends AbstractHashSequence{
    Object key;
    int ausgangselement_1,ausgangselement_2,count;
    // Zweite Streuwertfunktion.
    private HashFunction func2;

    // Doppelte Streuung mit Streuwertfunktionen f1 und f2.
    public DoubleHashing (HashFunction f1, HashFunction f2) {
        super(f1);
        func2 = f2;
    }

    @Override
    public int first(Object key) {
        this.key=key;
        count=1;
        return ausgangselement_1=func.compute(key);
    }

    @Override
    public int next() {
        if(ausgangselement_1+(count++)*func2.compute(key)>=func.size()) return -1;
        return ausgangselement_2=ausgangselement_1+(count++)*func2.compute(key);
    }
}
