import java.io.*;
import java.nio.IntBuffer;
import java.util.Random;
import java.util.Scanner;

public class DefaultTest {

    static int prio=0,data=0;

    static void getNewRand(){
        Random rand=new Random();
        prio = rand.nextInt();
        data = rand.nextInt();
    }

    public static void main(String[] args) throws IOException {
        int i;
        BinHeap H =new BinHeap();
        int alarm=0;
        String filedata="";

        File file=new File("C:\\Users\\Veronika\\Documents\\GitHub\\Algorithmen-und-Datenstrukturen-2\\Uebung2_Vorrangswarteschlangen\\src\\Testfile");
        File fileout=new File("C:\\Users\\Veronika\\Documents\\GitHub\\Algorithmen-und-Datenstrukturen-2\\Uebung2_Vorrangswarteschlangen\\src\\DefaultTestFile");
        if (!fileout.exists()) fileout.createNewFile();
        Scanner scanFile=new Scanner(fileout);
        BufferedWriter write=new BufferedWriter(new FileWriter(fileout.getAbsoluteFile()));


        //Schreibt zufällige prio und data in Textdatei und insert'ed
        for(int j=0;j<1;j++) { //male wie oft der Test durchgeführt wird
            H=new BinHeap();
            assert(H.isEmpty()):"Heap sollte leer sein";
            if (!fileout.exists()) fileout.createNewFile();
            for (i = 0; i < 100; i++) {
                getNewRand();
                write.write(prio + " " + data + " ");
                H.insert(prio, data);
                assert(!H.isEmpty()):"Heap sollte Elemente enthalten";
            }
            assert(i==H.size):"size stimmt nicht mit Anzahl Elemente im Baum ueberein| size="+H.size+" anzahl"+i;

            write.flush();
            //Checkt mithilfe der Textfile ob alle inserts auch im Baum wiedergefunden werden können
            while (scanFile.hasNext()) {
                prio = scanFile.nextInt();
                data = scanFile.nextInt();
                if (!H.contains(H.test(prio, data))) {
                    H.dump();
                    //System.out.println("ALAAARM prio= " + prio + " data= " + data + " "+ alarm++);
                }
                assert(H.contains(H.test(prio, data))):"Elemente p:"+prio+" & d:"+data+" fehlen im Heap";
                //System.out.println("   |||   "+prio+" | "+data.toString()+"  |||  ");
            }


            //Testet remove mit zufälligen Werten

            for(int k=0;k<5;k++) {
                getNewRand();
                BinHeap.Entry e = H.insert(prio, data);
                H.remove(e);
                assert (!H.contains(e)) : "Fehler in remove, Heap enthält entferntes Element noch";
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
