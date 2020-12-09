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
        int testdurchfuehrungen=1;
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

            for (i = 0; i < 5; i++) {
                getNewRand();
                System.out.println(prio + " " + data + " ");
                write.write(prio + " " + data + " ");
                System.out.println("Eingefügt wird "+prio + " " + data + " ");
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



            H.dump();
            // Test changePrio
            for (int k = 0; k < 100; k++) {
                getNewRand();
                System.out.println("Change Prio beginnt, Initialinsert: prio:"+prio+" data:"+data);
                e = H.insert(prio, data);
                getNewRand();
                System.out.println("Changed to prio:"+prio);
                H.changePrio(e,prio);
                assert((int)e.prio()==prio):"changePrio ist fehlerhaft";
                System.out.println("Change Prio endet\n");
            }



            write.close();
            scanFile.close();
            //System.out.println(fileout.delete());
            }
            System.out.println("Es wurden keine Fehler gefunden");
    }
}
