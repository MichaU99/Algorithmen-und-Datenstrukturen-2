// Implementierung von Streuwerttabellen mit offener Adressierung.
public class HashTableOpenAddressing implements HashTable{
    // Streuwerttabelle mit Sondierungsfunktion s.
    HashSequence HSequence;
    int N; //Groesse der Tabelle
    Speicher[] hashtable;
    int ges_anzahl_Objekte=0;

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
        while (index>=0){

            if(hashtable[index]==null) {
                hashtable[index] = new Speicher(key, val);
                ges_anzahl_Objekte++;
                return true;
            }
            if (hashtable[index].key.equals(key)){
                hashtable[index].val=val;
                return true;
            }
            index=HSequence.next();
        }
        return false;
    }

    @Override
    public Object get(Object key) {
        int index=HSequence.first(key);

        while(index>0){
            if(hashtable[index]==null) return null;
            if(hashtable[index].key.equals(key)) return hashtable[index].val;
            index=HSequence.next();
        }
        return null;
    }

    @Override
    public boolean remove(Object key) {
        int index=HSequence.first(key);
        while(index>0){
            if(hashtable[index].key.equals(key)){
                hashtable[index]=null;
                return true;
            }
            index=HSequence.next();
        }
        return false;
    }

    @Override
    public void dump() {
        int i;
        for (i=0;i<N;i++){
            if(hashtable[i]!=null) System.out.println(i+" "+hashtable[i].key+" "+hashtable[i].val);
        }

    }
}
