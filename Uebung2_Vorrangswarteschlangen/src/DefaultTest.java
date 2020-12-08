import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.nio.file.*;

public class DefaultTest {

    static Integer prio = 0, data = 0;
    static Random rand = new Random();

    static void getNewRand() {
        prio = rand.nextInt();
        data = rand.nextInt();
    }

    public static void main(String[] args) throws IOException {
        int testdurchfuehrungen=1;
        int completeRandTestDurchfuehrungen=20;
        int initialbaumgroeße=10;
        int methodenanzahl=6;
        int i;

        Integer tmp=null;
        BinHeap.Entry<Integer,Integer> e = null;
        BinHeap<Integer,Integer> H = new BinHeap();
        BinHeap<Integer,Integer> K = new BinHeap();
        String filedata = "";

        File fileout = new File("DefaultTestFile");
        File fileout2=new File("DefaultTestFile2");
        if (!fileout.exists()) fileout.createNewFile();
        if(!fileout2.exists()) fileout2.createNewFile();
        Scanner scanFile;
        BufferedWriter write;

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

            for (i = 0; i < initialbaumgroeße; i++) {
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



            // Test extractMin - Einen Eintrag mit minimaler Priorität liefern und aus der Halde entfernen.
            e = H.minimum();
            H.extractMin();
            assert (!H.contains(e)) : "ExtractMin enthealt";


/*
            // Test changePrio
            for (int k = 0; k < 5; k++) {
                getNewRand();
                System.out.println("Change Prio beginnt, Initialinsert: prio:"+prio+" data:"+data);
                e = H.insert(prio, data);
                System.out.println("Inserted");
                getNewRand();
                System.out.println("Changed to prio:"+prio);
                H.changePrio(e,prio);
                assert((int)e.prio()==prio):"changePrio ist fehlerhaft";
                System.out.println("Change Prio endet\n");
            }
            write.close();
            scanFile.close();
            }
*/
        //Vorbereitung für komplettes Randomtesting
        scanFile = new Scanner(fileout2);
        write = new BufferedWriter(new FileWriter(fileout2.getAbsoluteFile()));
        H = new BinHeap();
        if (!fileout2.exists()) fileout2.createNewFile();
        //
        for(int o=0;o<completeRandTestDurchfuehrungen;o++) {
            switch (rand.nextInt((methodenanzahl - 1) + 1) + 1) {
                case 1://Testet isEmpty()
                    scanFile = new Scanner(fileout2);
                    //Testet
                    if(scanFile.hasNext() && !H.isEmpty()){
                        H.dump();
                        assert(!H.isEmpty()) : "IsEmpty gibt true zurück obwohl Elemente im Baum sind";
                    }
                    else{
                        H.dump();
                        assert (H.isEmpty()) : "IsEmpty gibt false zurück obwohl der Baum leer sein sollte";
                    }
                    System.out.println("1");
                    break;

                case 2://Testet insert und fügt richtige neue Elemente hinzu
                    getNewRand();
                    write.write(prio + " " + data + " ");
                    if(!H.contains(H.insert(prio, data))) {
                        H.dump();
                        assert (false):"Ein hinzugefügtes Element mit prio:"+prio+" data:"+data+",wird nicht im Baum gefunden";
                    }
                    System.out.println("2");
                    break;

                case 3://Testet size()
                    write.flush();
                    scanFile=new Scanner(fileout2);
                    i=0;
                    while (scanFile.hasNext()){
                        scanFile.nextInt();
                        scanFile.nextInt();
                        i++;
                    }
                    if(i != H.size()){
                        H.dump();
                        assert (false) : "size stimmt nicht mit Anzahl Elemente im Baum überein! size=" + H.size() + " wirkliche anzahl" + i;
                    }
                    System.out.println("3");
                    break;

                case 4://Testet fehlerhafte inserts
                    e=null;
                    if(H.contains(e)) {
                        H.dump();
                        assert(false): "Der Binheap enthealt einen null-Entry";
                    }
                    getNewRand();
                    e=K.insert(prio,data);
                    if(H.contains(e)) {
                        H.dump();
                        assert(false): "Der Binheap enthealt einen fehlerhaften Entry mit prio:"+prio+" data:"+data;
                    }
                    System.out.println("4");
                    break;

                case 5://Testet remove()
                    getNewRand();
                    e = H.insert(prio, data);
                    H.remove(e);
                    if(H.contains(e)) {
                        H.dump();
                        assert (false) : "Fehler in remove, Heap enthält entferntes Element noch";
                    }
                    System.out.println("5");
                    break;

                case 6://Testet minimum
                    scanFile=new Scanner(fileout2);
                    if(scanFile.hasNext()) {
                        tmp = scanFile.nextInt();
                        scanFile.nextInt();
                    }
                    else tmp=null;
                    while (scanFile.hasNext()) {
                        prio = scanFile.nextInt();
                        scanFile.nextInt();
                        if (tmp.compareTo(prio)>0) {
                            tmp = prio;
                        }
                    }
                    if(tmp==null && H.minimum()!=null){
                        H.dump();
                        assert(false): "Minimum liefert einen Entry("+H.minimum()+"), obwohl sich kein Element im Baum befindet";
                    }
                    else if(H.minimum().prio().compareTo(tmp)!=0) {
                        H.dump();
                        assert (false) : "minimum ist gleich " + tmp + " ,prio liefert " + H.minimum().prio();
                    }
                    System.out.println("6");
                    break;
            }
        }
            System.out.println("Es wurden keine Fehler gefunden");
    }
}
}
