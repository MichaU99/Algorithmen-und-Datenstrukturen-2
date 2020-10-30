// Implementierung von Streuwerttabellen mit offener Adressierung.
public class HashTableOpenAddressing implements HashTable{
    // Streuwerttabelle mit Sondierungsfunktion s.
    HashSequence HSequence;
    int N; //Groesse der Tabelle
    Speicher[] hashtable;

    public HashTableOpenAddressing (HashSequence s) {
        this.HSequence=s;
        this.N=HSequence.size();
        hashtable=new Speicher[N];
    }

    @Override
    public boolean put(Object key, Object val) {
        int index;

        if(key==null && val==null) return false; //Fehlerhafte Eingabe abfangen

        index=HSequence.first(key); //Ersten Index berechnen & testen
        if(hashtable[index]==null) hashtable[index]=new Speicher(key, val);
        else{ //Sucht alternativen Speicherpunkt
            while (true){

            }
        }

        return false;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public boolean remove(Object key) {
        return false;
    }

    @Override
    public void dump() {

    }
}
