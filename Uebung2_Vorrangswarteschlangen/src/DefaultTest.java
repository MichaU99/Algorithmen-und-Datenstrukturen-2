import java.io.*;
import java.nio.IntBuffer;
import java.util.Random;
import java.util.Scanner;

public class DefaultTest {

    public static void main(String[] args) throws IOException {
        int prio=0,data=0;
        BinHeap H =new BinHeap();
        int alarm=0;
        Random rand=new Random();
        String filedata="";

        File file=new File("C:\\Users\\Michael\\IdeaProjects\\Algorithmen-und-Datenstrukturen-2\\Uebung2_Vorrangswarteschlangen\\src\\Testfile");
        File fileout=new File("C:\\Users\\Michael\\IdeaProjects\\Algorithmen-und-Datenstrukturen-2\\Uebung2_Vorrangswarteschlangen\\src\\DefaultTestFile");
        if (!fileout.exists()) fileout.createNewFile();
        Scanner scanFile=new Scanner(fileout);
        BufferedWriter write=new BufferedWriter(new FileWriter(fileout.getAbsoluteFile()));




        for(int j=0;j<1;j++) { //male wie oft der Test durchgerführt wird
            H=new BinHeap();
            if (!fileout.exists()) fileout.createNewFile();
            for (int i = 0; i < 10000; i++) {
                prio = rand.nextInt();
                data = rand.nextInt();
                write.write(prio + " " + data + " ");
                H.insert(prio, data);
            }
            write.flush();
            while (scanFile.hasNext()) {

                prio = scanFile.nextInt();
                data = scanFile.nextInt();

                if (!H.contains(H.test(prio, data))) {
                    System.out.println("ALAAARM prio= " + prio + " data= " + data + " "+ alarm++);
                }
                //System.out.println("   |||   "+prio+" | "+data.toString()+"  |||  ");
            }

            //Vergleich mit
            int ä=0;
            int i=rand.nextInt();
            int k=rand.nextInt();
            BinHeap.Entry e=H.insert(i,k);
            H.remove(e);
            if(H.contains(e)) System.out.println("FEHLER Nr."+ä++);


            H.dump();System.out.println(H.size+" "+alarm);
            fileout.delete();
        }



        /*while (scanFile.hasNextLine()){
            filedata=filedata+ scanFile.nextLine()+"\n";
        }*/
        //System.out.println(H.Test);
        //System.out.println("Stimmt die Testfile mit dump() überein? "+filedata.equals(H.Debug));
        write.close();
        fileout.delete();


    }
}
