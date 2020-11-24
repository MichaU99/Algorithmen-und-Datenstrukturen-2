import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class DefaultTest {
    public static void main(String[] args) throws IOException {
        BinHeap H =new BinHeap();
        Random rand=new Random();
        String filedata="";

        File file=new File("C:\\Users\\Michael Ulrich\\IdeaProjects\\Algorithmen-und-Datenstrukturen-2\\Uebung2_Vorrangswarteschlangen\\src\\Testfile");
        File fileout=new File("C:\\Users\\Michael Ulrich\\IdeaProjects\\Algorithmen-und-Datenstrukturen-2\\Uebung2_Vorrangswarteschlangen\\src\\DefaultTestFile");
        Scanner scanFile=new Scanner(fileout);
        BufferedWriter write=new BufferedWriter(new FileWriter(fileout.getAbsoluteFile()));


        if(!fileout.exists()) fileout.createNewFile();

        for (int i=0;i<=10;i++){
            Comparable prio= rand.nextInt();
            Comparable data= rand.nextInt();
            write.write( prio.toString()+" "+ data.toString()+" ");
            H.insert(prio,data);
        }

        while(scanFile.hasNext()){
            Comparable prio= scanFile.next();
            Comparable data=scanFile.next();

            if(!H.contains(H.test(prio,data))) System.out.println("ALAAARM prio= "+prio+" data= "+data+" ");
        //System.out.println("   |||   "+prio+" | "+data.toString()+"  |||  ");
        }

        H.dump();
        System.out.println(H.contains(H.test(0,1000)));

        while (scanFile.hasNextLine()){
            filedata=filedata+ scanFile.nextLine()+"\n";
        }
        System.out.println(H.Test);
        System.out.println("Stimmt die Testfile mit dump() überein? "+filedata.equals(H.Debug));


        write.close();
        fileout.delete();


    }
}
