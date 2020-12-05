import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class DefaultTest {

    static int prio = 0, data = 0;

    static void getNewRand() {
        Random rand = new Random();
        prio = rand.nextInt();
        data = rand.nextInt();
    }

    public static void main(String[] args) throws IOException {
        int i;
        BinHeap.Entry<Integer,Integer> e = null;
        BinHeap<Integer,Integer> H = new BinHeap();
        int alarm = 0;
        String filedata = "";
        int tmp = 0;

        File file = new File("Uebung2_Vorrangswarteschlangen/Testfile");
        File fileout = new File("DefaultTestFile");
        if (!fileout.exists()) fileout.createNewFile();
        Scanner scanFile = new Scanner(fileout);
        BufferedWriter write = new BufferedWriter(new FileWriter(fileout.getAbsoluteFile()));


        //Schreibt zufällige prio und data in Textdatei und insert'ed
        for (int j = 0; j < 1; j++) { //male wie oft der Test durchgeführt wird
            H = new BinHeap();
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
                if (!H.priocontains(H.test(prio, data))) {
                    H.dump();
                }
                assert (H.priocontains(H.test(prio, data))) : "Elemente p:" + prio + " & d:" + data + " fehlen im Heap";
            }


            //Testet remove mit zufälligen Werten

            for (int k = 0; k < 5; k++) {
                getNewRand();
                e = H.insert(prio, data);
                H.remove(e);
                assert (!H.contains(e)) : "Fehler in remove, Heap enthält entferntes Element noch";
            }

            /* Tut nicht weil compareTo kein numerischer Vergleich ist
            //Testet minimum - Einen Eintrag mit minimaler Priorität liefern.
            if(!scanFile.hasNext()){
                scanFile=new Scanner(fileout);
            }
            while (scanFile.hasNext()) {
                prio = scanFile.nextInt();
                scanFile.nextInt();
                if (tmp < prio) {
                    tmp = prio;
                }
            }
            assert ((int) H.minimum().prio() == tmp) : "minimum ist gleich";
             */

            // Test extractMin - Einen Eintrag mit minimaler Priorität liefern und aus der Halde entfernen.
            e = H.minimum();
            H.extractMin();
            assert (!H.contains(e)) : "Ist nicht gleich.";



            // Test changePrio
            for (int k = 0; k < 5; k++) {
                getNewRand();
                e = H.insert(prio, data);
                getNewRand();
                H.changePrio(e,prio);
                assert((int)e.prio()==prio):"changePrio ist fehlerhaft";
            }


                fileout.delete();
            }




        /*while (scanFile.hasNextLine()){
            filedata=filedata+ scanFile.nextLine()+"\n";
        }*/
            //System.out.println(H.Test);
            //System.out.println("Stimmt die Testfile mit dump() überein? "+filedata.equals(H.Debug));
            System.out.println("Es wurden keine Fehler gefunden");
            write.close();
            fileout.delete();



    }
}
