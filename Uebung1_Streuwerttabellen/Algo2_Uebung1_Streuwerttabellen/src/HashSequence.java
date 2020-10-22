// Schnittstelle einer Sondierungsfunktion für offene Adressierung,
// die zu jedem Schlüssel eine gültige Sondierungssequenz liefert.
public interface HashSequence {
    // Größe N der Streuwerttabelle (Anzahl der Plätze).
    int size ();

    // Ersten bzw. nächsten Streuwert für Schlüssel key liefern.
    // Alle Werte liegen zwischen 0 und N-1.
    // Ein Aufruf von first und N-1 nachfolgende Aufrufe von next
    // liefern jeden Wert zwischen 0 und N-1 jeweils genau einmal.
    int first (Object key);
    int next ();
}
