import java.lang.reflect.Array;

// Implementierung von Streuwerttabellen mit Verkettung.
public class HashTableChaining implements HashTable {
    // Streuwerttabelle mit Streuwertfunktion f.

    //Java besitzt unter java.util.HashMap selbst eine Implementation einer Hashmap, können/ sollen wir die benutzen oder Eigenimplementation?

    int N=100; //groesse
    int verkettung_len=1; //Länge der max Verkettung im Array, zu Beginn immer 1


    Object[][] hashtable=new Object[N][verkettung_len];


    public HashTableChaining(HashFunction f) {

    }

    @Override
    public boolean put(Object key, Object val) {
        int index;
        int i,j,l,tiefe;  //Laufvariablen
        boolean verlaengern=true;
        Object tmp;

        index=key.hashCode(); //Unbekannt ob es stimmt, dummy implementation für weiteren code
        if(hashtable[index][0] instanceof Object){ //sollte testen ob bereits ein Objekt ins Array an der Stelle index eingefügt wurde
            tmp=hashtable[index][0];
            hashtable[index][0]=val;
            for (tiefe=1;tiefe<=verkettung_len;tiefe++){ //Checkt ob im aktuellen Array genug Platz in der zweiten Dimension für einen weiteren Wert ist
                if(!(hashtable[index][tiefe] instanceof Object)){
                    hashtable[index][tiefe]=tmp;
                    verlaengern=false;
                    break;
                }
            }
            if (verlaengern){
                array_tiefe_vergroessen(hashtable);
                hashtable[index][verkettung_len]=tmp;
            }
            verlaengern=true; //Reset

        }
        else{
            hashtable[index][0]=val;
            return true;
        }
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
    public void array_tiefe_vergroessen(Object[][] array){ //Vergroessert HashTable in zweiter Dimension (verkettung_len) um 1 und kopiert alle Elemente aus dem uebergebenen Array, speichert das Ergebnis in HashTable
        Object[][] tmp_arr=new Object[N][++verkettung_len];
        int i,a; //Laufvariablen

        for(i=0;i<=(array.length-1);i++){
            for (a=0;a<=1;a++){
                tmp_arr[i][a]=array[i][a];
            }
        }
        hashtable=tmp_arr;

    }

}
