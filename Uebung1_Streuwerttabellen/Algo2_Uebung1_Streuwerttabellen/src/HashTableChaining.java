// Implementierung von Streuwerttabellen mit Verkettung.
import java.util.ArrayList;
public class HashTableChaining implements HashTable {
    // Streuwerttabelle mit Streuwertfunktion f.

    int N=5; //groesse

    public HashTableChaining(HashFunction f) {

    }

    @Override
    public boolean put(Object key, Object val) {
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
