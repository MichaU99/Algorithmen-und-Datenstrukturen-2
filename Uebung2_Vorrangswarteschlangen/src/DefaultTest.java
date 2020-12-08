import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.nio.file.*;

public class DefaultTest {

    static Integer prio = 0, data = 0;

    static void getNewRand() {
        Random rand = new Random();
        prio = rand.nextInt();
        data = rand.nextInt();
    }

    public static void main(String[] args) throws IOException {
        int testdurchfuehrungen=2;
        int i;
        Integer tmp=null;
        BinHeap.Entry<Integer,Integer> e = null;
        BinHeap<Integer,Integer> H = new BinHeap();
        BinHeap<Integer,Integer> K = new BinHeap();
        String filedata = "";

        File fileout = new File("DefaultTestFile");
        if (!fileout.exists()) fileout.createNewFile();
        Scanner scanFile = new Scanner(fileout);
        BufferedWriter write = new BufferedWriter(new FileWriter(fileout.getAbsoluteFile()));


        //Testet ob Inserts aktiv sind
        try{
            assert(false);
            System.out.println("Fehler: Asserts nicht aktiv");
            return ;
        }
        catch(AssertionError f){
            System.out.println("Test beginnt");
        }

        //Schreibt zufällige prio und data in Textdatei und insert'ed
        for (int j = 0; j < testdurchfuehrungen; j++) { //male wie oft der Test durchgeführt wird
            H = new BinHeap();
            scanFile = new Scanner(fileout);
            write = new BufferedWriter(new FileWriter(fileout.getAbsoluteFile()));

            assert (H.isEmpty()) : "Heap sollte leer sein";
            if (!fileout.exists()) fileout.createNewFile();

            for (i = 0; i < 100; i++) {
                getNewRand();
                write.write(prio + " " + data + " ");
                assert (H.contains(H.insert(prio, data)));
                assert (!H.isEmpty()) : "Heap sollte Elemente enthalten";
            }
            assert (i == H.size()) : "size stimmt nicht mit Anzahl Elemente im Baum ueberein| size=" + H.size() + " anzahl" + i;

            write.flush();

            //Checkt mithilfe der Textfile ob alle inserts auch im Baum wiedergefunden werden können
            while (scanFile.hasNext()) {
                prio = scanFile.nextInt();
                data = scanFile.nextInt();
                e= H.insert(prio,data);
                if (!H.contains(e)) {
                    H.dump();
                }
                getNewRand();

                assert (H.contains(e)) : "Elemente p:" + prio + " & d:" + data + " fehlen im Heap";
                assert(!H.contains(K.insert(prio,data))) : "H enthealt einen entry den es nicht im Heap gibt";
            }

            e=null;
            assert (!H.contains(e)): "Der Binheap enthealt einen null-Entry";


            //Testet remove mit zufälligen Werten
            for (int k = 0; k < 5; k++) {
                getNewRand();
                e = H.insert(prio, data);
                H.remove(e);
                assert (!H.contains(e)) : "Fehler in remove, Heap enthält entferntes Element noch";
            }

            // Tut nicht weil die Datei nicht gelöscht werden kann
            //Testet minimum - Einen Eintrag mit minimaler Priorität liefern.
/*
            if(!scanFile.hasNext()){
                scanFile=new Scanner(fileout);
            }
            tmp = scanFile.nextInt();
            scanFile.nextInt();

            while (scanFile.hasNext()) {
                prio = scanFile.nextInt();
                scanFile.nextInt();
                if (tmp.compareTo(prio)>0) {
                    tmp = prio;
                }
            }
            assert ((Integer) H.minimum().prio().compareTo(tmp)==0 ): "minimum ist gleich "+tmp+" ,prio liefert "+H.minimum().prio();
*/


            // Test extractMin - Einen Eintrag mit minimaler Priorität liefern und aus der Halde entfernen.
            e = H.minimum();
            H.extractMin();
            assert (!H.contains(e)) : "ExtractMin enthealt";


/*
            // Test changePrio
            for (int k = 0; k < 5; k++) {
                System.out.println("Change Prio beginnt");
                getNewRand();
                e = H.insert(prio, data);
                getNewRand();
                H.changePrio(e,prio);
                assert((int)e.prio()==prio):"changePrio ist fehlerhaft";
                System.out.println("Change Prio endet");
            }
*/


            write.close();
            scanFile.close();
            //System.out.println(fileout.delete());
            }
            System.out.println("Es wurden keine Fehler gefunden");
    }
}
