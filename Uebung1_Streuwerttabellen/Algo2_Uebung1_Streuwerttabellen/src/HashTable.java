// Schnittstelle einer Streuwerttabelle.
public interface HashTable {
    // Eintrag mit Schlüssel key und Wert val einfügen, sofern key und
    // val nicht null sind und die Tabelle bei offener Adressierung
    // noch nicht voll ist.
    // Wenn es bereits einen Eintrag mit dem gleichen Schlüssel gibt,
    // wird er durch diesen neuen Eintrag ersetzt.
    // Resultatwert true, falls erfolgreich (Eintrag hinzugefügt oder
    // ersetzt), sonst false (Tabelle unverändert).
    // Bei Verkettung muss am Anfang der jeweiligen Liste eingefügt
    // werden.
    // Die als Schlüssel verwendeten Objekte müssen konsistente
    // Implementierungen von hashCode und equals besitzen, d. h.
    // Objekte, die gemäß equals "gleich" sind, müssen den gleichen
    // hashCode-Wert besitzen. Außerdem darf sich der hashCode-Wert
    // eines Schlüssels nicht ändern, solange es einen Tabelleneintrag
    // mit diesem Schlüssel gibt.
    // key und val können jeweils einen beliebigen dynamischen Typ
    // besitzen.
    boolean put (Object key, Object val);

    // Wert zum Schlüssel key liefern, falls vorhanden, sonst null.
    // (Resultatwert null, wenn key gleich null ist.)
    Object get (Object key);

    // Eintrag mit Schlüssel key entfernen, falls vorhanden.
    // Resultatwert true, falls erfolgreich (Eintrag war vorhanden),
    // sonst false (Eintrag war nicht vorhanden).
    // (Resultatwert false, wenn key gleich null ist.)
    boolean remove (Object key);

    // Inhalt der Tabelle zu Testzwecken ausgeben:
    // Pro Eintrag eine Zeile bestehend aus der Nummer des Platzes,
    // Schlüssel und Wert, jeweils getrennt durch genau ein Leerzeichen.
    // Dieses Ausgabeformat muss exakt eingehalten werden.
    // Leere Plätze und Plätze mit Löschmarkierung werden nicht
    // ausgegeben.
    void dump ();
}
