import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class DefaultTest {

    public static void main(String[] args) throws IOException {
        BinHeap H =new BinHeap();
        int alarm=0;
        Random rand=new Random();
        String filedata="";

        File file=new File("C:\\Users\\Michael Ulrich\\IdeaProjects\\Algorithmen-und-Datenstrukturen-2\\Uebung2_Vorrangswarteschlangen\\src\\Testfile");
        File fileout=new File("C:\\Users\\Michael Ulrich\\IdeaProjects\\Algorithmen-und-Datenstrukturen-2\\Uebung2_Vorrangswarteschlangen\\src\\DefaultTestFile");
        Scanner scanFile=new Scanner(fileout);
        BufferedWriter write=new BufferedWriter(new FileWriter(fileout.getAbsoluteFile()));




        for(int j=0;j<1;j++) {
            H=new BinHeap();
            if (!fileout.exists()) fileout.createNewFile();
            for (int i = 0; i < 1000; i++) {
                int prio = rand.nextInt();
                int data = rand.nextInt();
                write.write(prio + " " + data + " ");
                H.insert(prio, data);
            }
            while (scanFile.hasNext()) {
                int prio = scanFile.nextInt();
                int data = scanFile.nextInt();

                if (!H.contains(H.test(prio, data))) {
                    alarm++;
                    System.out.println("ALAAARM prio= " + prio + " data= " + data + " "+ alarm);

                }
                //System.out.println("   |||   "+prio+" | "+data.toString()+"  |||  ");
            }



            H.dump();System.out.println(H.size+" "+alarm);
            fileout.delete();
        }



        while (scanFile.hasNextLine()){
            filedata=filedata+ scanFile.nextLine()+"\n";
        }
        //System.out.println(H.Test);
        System.out.println("Stimmt die Testfile mit dump() überein? "+filedata.equals(H.Debug));


        write.close();
        fileout.delete();


    }
}
