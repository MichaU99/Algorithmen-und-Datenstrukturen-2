// Implementierung von Streuwerttabellen mit Verkettung.
public class HashTableChaining implements HashTable {
    // Streuwerttabelle mit Streuwertfunktion f.

    //Java besitzt unter java.util.HashMap selbst eine Implementation einer Hashmap, können/ sollen wir die benutzen oder Eigenimplementation?
    int p=20;
    int N= (1 << p); //groesse
    int verkettung_len=1; //Länge der max Verkettung im Array, zu Beginn immer 1
    MultiplicationMethod platz=new MultiplicationMethod(p,(int) (((Math.sqrt(5)-1)/2)*Math.pow(2,32)));
    DivisionMethod platz_d=new DivisionMethod(N);

    Object[][][] hashtable =new Object[N][2][verkettung_len]; //[N]=Laenge [2]={0:key},{1:val} verkettung_len=Länge der größten Verkettung

    public HashTableChaining(HashFunction f) {

    }
    @Override
    public boolean put(Object key, Object val) {
        int index;
        int i,j,l,tiefe;  //Laufvariablen
        boolean verlaengern=true;
        Object tmp_key,tmp_val;

        if (key==null && val==null) return false;
        index= platz.compute(key); //Vermutlich braucht man hier eine Implementation der HashFunction aktuell inkompatible N, dummy implementation für weiteren code

        if(hashtable[index][0][0] !=null){ //sollte testen ob bereits ein Objekt ins Array an der Stelle index eingefügt wurde
            if(key== hashtable[index][0][0]){ //Bei Objekten mit demselben Key muss val überspeichert werden
                 hashtable[index][1][0]=val;
                 return true; }

            tmp_key= hashtable[index][0][0];
            tmp_val= hashtable[index][1][0];
            hashtable[index][0][0]=key;
            hashtable[index][1][0]=val;
            for (tiefe = 1; tiefe <= verkettung_len-1; tiefe++) { //Checkt ob im aktuellen Array genug Platz in der zweiten Dimension für einen weiteren Wert ist
                if (key.equals(hashtable[index][0][tiefe])){ //For true, two Objects with same key have to be overwritten
                    hashtable[index][1][tiefe] = val;
                    verlaengern = false;
                    break;
                }
                if ((hashtable[index][0][tiefe] ==null)) {
                    hashtable[index][0][tiefe] = tmp_key;
                    hashtable[index][1][tiefe] = tmp_val;
                    verlaengern = false;
                    break;
                }
            }
            if (verlaengern){
                array_tiefe_vergroessen_3d(hashtable);
                hashtable[index][0][verkettung_len-1]=tmp_key;
                hashtable[index][1][verkettung_len-1]=tmp_val;
            }
            verlaengern=true; //Reset
            return true;
        }
        else{
            hashtable[index][0][0]=key;
            hashtable[index][1][0]=val;
            return true;
        }

    }



    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public boolean remove(Object key) {
        int index= platz.compute(key);

        return false;
    }


    public void dump() {
        int i,j,k=0; //lauf
        for (i=0;i<=N-1;i++){
            for(j=0;j<=verkettung_len-1;j++){
                if(hashtable[i][0][j]==null) break;
                else {
                    System.out.println("Stelle im Table:" + i + " chaindeapth:" + j + " key:" + hashtable[i][0][j] + "  val:" + hashtable[i][1][j]);
                    k++;
                }
            }
        }
        System.out.println();
        System.out.println("Anzahl der Werte im Hashtable: "+k);
    }

    public void array_tiefe_vergroessen_3d(Object[][][] array){ //Vergroessert HashTable in zweiter Dimension (verkettung_len) um 1 und kopiert alle Elemente aus dem uebergebenen Array, speichert das Ergebnis in HashTable
        Object[][][] tmp_arr=new Object[N][2][++verkettung_len];
        int i,a,b; //Laufvariablen
        for(i=0;i<=N-1;i++){
            for (a=0;a<=1;a++){
                for (b=0;b<=verkettung_len-2;b++) {
                    tmp_arr[i][a][b] = array[i][a][b];
                }
            }
        }
        hashtable =tmp_arr;    }






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
