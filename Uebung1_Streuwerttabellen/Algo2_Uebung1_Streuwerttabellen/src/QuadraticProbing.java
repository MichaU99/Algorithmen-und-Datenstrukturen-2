// Sondierungssequenz gemäß quadratischer Sondierung
// (Implementierung nur mit Ganzzahlarithmetik).
public class QuadraticProbing extends AbstractHashSequence{
    // Quadratische Sondierung mit Streuwertfunktion f.
    public QuadraticProbing (HashFunction f) {
        super(f);
    }

    @Override
    public int first(Object key) {
        return 0;
    }

    @Override
    public int next() {
        return 0;
    }
}
