// Abstrakte Oberklasse für Implementierungen von Streuwertfunktionen.
public abstract class AbstractHashFunction implements HashFunction{
    // Größe der Streuwerttabelle (Anzahl der Plätze).
    protected int size;

    // Größe mit N initialisieren.
    protected AbstractHashFunction (int N) {
        size = N;
    }

    // Größe liefern.
    public int size () {
        return size;
    }
}
