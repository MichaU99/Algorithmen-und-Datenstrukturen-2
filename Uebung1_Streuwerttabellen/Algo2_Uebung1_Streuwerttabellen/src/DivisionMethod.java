// Streuwertfunktion gemäß Divisionsrestmethode.
public class DivisionMethod extends AbstractHashFunction{
    // Divisionsrestmethode für Tabellengröße N.
    public DivisionMethod (int N) {
        super(N);
    }

    @Override
    public int compute(Object key) {
        int h=key.hashCode();
        return (h%size+size)%size; //??? Abgeschrieben aus dem Skript
    }
}
