// Sondierungssequenz gemäß linearer Sondierung.
public class LinearProbing extends AbstractHashSequence{
    // Lineare Sondierung mit Streuwertfunktion f.
    public LinearProbing (HashFunction f) {
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
