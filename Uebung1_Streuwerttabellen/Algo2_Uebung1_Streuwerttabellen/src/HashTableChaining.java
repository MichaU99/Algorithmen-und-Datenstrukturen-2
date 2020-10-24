import java.lang.reflect.Array;

// Implementierung von Streuwerttabellen mit Verkettung.
public class HashTableChaining implements HashTable {
    // Streuwerttabelle mit Streuwertfunktion f.

    //Java besitzt unter java.util.HashMap selbst eine Implementation einer Hashmap, können/ sollen wir die benutzen oder Eigenimplementation?

    int N=100; //groesse
    int[] hashtable=new int[N];

    public HashTableChaining(HashFunction f) {

    }

    @Override
    public boolean put(Object key, Object val) {
        int index;

        index=key.hashCode(); //Unbekannt ob es stimmt, dummy implementation für weiteren code
        if(hashtable[index]) //Man benötigt Information ob im Array an Stelle x bereits ein Objekt gespeichert ist, mögliche Implementation über zweidimensionales Array mit Info in der zweiten Dimension
            //Zum Chainen muss an einer Stelle x ein Objekt einen Verweis auf eine Stelle y haben können die nicht im Bereich des normalen Arrays liegt, mögliche Implementation mithilfe von sich selbst vergrößernden mehrdimensionalen Arrays oder Zeigern?
        if (key==null && val==null) return false;
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
