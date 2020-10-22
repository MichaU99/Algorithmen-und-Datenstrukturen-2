// Schnittstelle einer Streuwertfunktion mit Einschränkung des
// Wertebereichs, die zu jedem Schlüssel einen gültigen Streuwert/Index
// liefert.
public interface HashFunction {
    // Größe N der Streuwerttabelle (Anzahl der Plätze).
    int size ();

    // Streuwert für Schlüssel key liefern, indem key.hashCode()
    // berechnet und geeignet in den Wertebereich von 0 bis N-1
    // abgebildet wird.
    int compute (Object key);
}
