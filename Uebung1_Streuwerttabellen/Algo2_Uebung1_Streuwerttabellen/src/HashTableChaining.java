import org.w3c.dom.html.HTMLDListElement;

// Implementierung von Streuwerttabellen mit Verkettung.
public class HashTableChaining implements HashTable {
    // Streuwerttabelle mit Streuwertfunktion f.

    //Java besitzt unter java.util.HashMap selbst eine Implementation einer Hashmap, können/ sollen wir die benutzen oder Eigenimplementation?
    HashFunction HFunction;
    int N;
    ListElement[] hashtable;

    public HashTableChaining(HashFunction f) {
        HFunction=f;
        N=HFunction.size();
        hashtable= new ListElement[N];
    }

    @Override
    public boolean put(Object key, Object val) {
        int index;

        if (key==null && val==null) return false;
        index= HFunction.compute(key);

        if(hashtable[index]!=null){ //sollte testen ob bereits ein Objekt ins Array an der Stelle index eingefügt wurde

            if(!(hashtable[index].search(key)==null)){ //Testet innerhalb der Liste nach key übereinstimmungen
                hashtable[index].search(key).val=val;
                return true;
            }
            hashtable[index]=new ListElement(key,val,hashtable[index]);
            return true;
        }
        else{
            hashtable[index]=new ListElement(key,val,null);
            return true;
        }
        //Kann hier ein False rauskommen?
    }



    @Override
    public Object get(Object key) {
        Object val;
        int index= HFunction.compute(key);
        if(hashtable[index]==null) return null;
        if(hashtable[index].search(key)==null) return null;
        else return hashtable[index].search(key).val;
    }

    @Override
    public boolean remove(Object key) {
        int index= HFunction.compute(key);
        if(hashtable[index]==null) return false;
        switch (hashtable[index].remove(key)) {
            case -1:                //Element wurde gefunden, ist aber das einzige in der Liste
                hashtable[index]=null;
            case 1:                 //Element wurde gefunden
                return true;
            default:                //Element wurde nicht gefunden
                return false;

        }
    }


    public void dump() {
        int i,j,k=0; //lauf
        for (i=0;i<=N-1;i++){
            if(!(hashtable[i]==null)) hashtable[i].dump(i);
    }



        //Alte Implementierungen sollten bei Abgabe und Sicherheit der Antworten gelöscht werden
    /*
    public void array_tiefe_vergroessen(Object[][] array){ //Vergroessert HashTable in zweiter Dimension (verkettung_len) um 1 und kopiert alle Elemente aus dem uebergebenen Array, speichert das Ergebnis in HashTable
        Object[][] tmp_arr=new Object[N][++verkettung_len];
        int i,a; //Laufvariablen
        for(i=0;i<=N-1;i++){
            for (a=0;a<=verkettung_len-2;a++){
                tmp_arr[i][a]=array[i][a];
            }
        }
        hashtable=tmp_arr;    }*/
/*

/* @Override //Implementierung stimmt nicht!!!! Muss vor abgabe noch geändert werden, wie müssen die gechainten Elemente nummeriert werden???

    /*public void dump() {

         int i,j; //lauf
         for (i=0;i<=N-1;i++){
             for(j=0;j<=verkettung_len-1;j++){
                 if(hashtable[i][j]==null) break;
                 else System.out.println("Stelle im Table key:"+i+" chaindeapth:"+j+" val:"+hashtable[i][j]);
             }
         }
     }


    @Override
    public boolean put(Object key, Object val) {
        int index;
        int i,j,l,tiefe;  //Laufvariablen
        boolean verlaengern=true;
        Object tmp;

        if (key==null && val==null) return false;
        index= platz.compute(key); //Vermutlich braucht man hier eine Implementation der HashFunction aktuell inkompatible N, dummy implementation für weiteren code

        if(hashtable[index][0] instanceof Object){ //sollte testen ob bereits ein Objekt ins Array an der Stelle index eingefügt wurde
            tmp=hashtable[index][0];
            hashtable[index][0]=val;
             for (tiefe = 1; tiefe <= verkettung_len-1; tiefe++) { //Checkt ob im aktuellen Array genug Platz in der zweiten Dimension für einen weiteren Wert ist
                if (!(hashtable[index][tiefe] instanceof Object)) {
                    hashtable[index][tiefe] = tmp;
                    verlaengern = false;
                    break;
                }
            }
            if (verlaengern){
                array_tiefe_vergroessen(hashtable);
                hashtable[index][verkettung_len-1]=tmp;
            }
            verlaengern=true; //Reset
            return true;
        }
        else{
            hashtable[index][0]=val;
            return true;
        }

    }*/



}
}
