// Abstrakte Oberklasse für Implementierungen von Sondierungsfunktionen.
public abstract class AbstractHashSequence implements HashSequence{
    // Zugrundeliegende Streuwertfunktion.
    protected HashFunction func;

    // Letzter von first oder next gelieferter Streuwert.
    protected int prev;

    // Streuwertfunktion mit f initialisieren.
    protected AbstractHashSequence (HashFunction f) {
        func = f;
    }

    // Tabellengröße liefern.
    public int size () {
        return func.size();
    }
}
