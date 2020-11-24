import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) throws FileNotFoundException {
        BinHeap H =new BinHeap();
        String filedata="";

        File file=new File("C:\\Users\\Michael Ulrich\\IdeaProjects\\Algorithmen-und-Datenstrukturen-2\\Uebung2_Vorrangswarteschlangen\\src\\Testfile");
        Scanner scanFile=new Scanner(file);
        //Scanner scanDebug=new Scanner()
        /*H.insert("a",0);
        H.insert("b",1);
        H.insert("c",2);
        H.insert("d",3);
        H.insert("e",4);
        H.insert("f",5);
        H.insert("g",6);
        H.insert("h",7);
        H.insert("i",8);
        H.insert("j",9);
        H.insert("k",10);
        */
        //for (int i=0;i<=100;i++) H.insert(i,100);

        while (scanFile.hasNextLine()){
            //filedata=filedata+ scanFile.nextLine()+"\n";
            H.insert(scanFile.nextInt(),scanFile.nextInt());
        }
        H.dump();
        Scanner scan2nd=new Scanner(file);
        while(scan2nd.hasNext()){
            int prio=scan2nd.nextInt();
            int data=scan2nd.nextInt();
            if(!H.contains(H.test(prio,data))){
                System.out.println("Fehler Prio: "+prio+" Data: "+data);
            };
        }



        //System.out.println(H.Test);
       // System.out.println("Stimmt die Testfile mit dump() überein? "+filedata.equals(H.Debug));

        //System.out.println(H.minimum().prio());

    }
}
