import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) throws IOException {
        String prio=null;
        Integer data=null;
        BinHeap H =new BinHeap();
        String filedata="";

        File file=new File("Uebung2_Vorrangswarteschlangen/Testfile");
        Scanner scanFile=new Scanner(file);
        if(!file.exists()) file.createNewFile();
        //Scanner scanDebug=new Scanner()
         /*
        System.out.println(H.contains(H.insert("a",0)));
        System.out.println(H.contains(H.insert("b",1)));
        System.out.println(H.contains(H.insert("c",2)));
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


        BinHeap.Entry<Integer,Integer> e=null;
        while (scanFile.hasNextLine()){

            prio=scanFile.next();
            data=scanFile.nextInt();
            //filedata=filedata+ scanFile.nextLine()+"\n";
            H.insert(prio,data);
        }
        H.dump();
        System.out.println(H.extractMin());



        //System.out.println(H.Test);
       // System.out.println("Stimmt die Testfile mit dump() überein? "+filedata.equals(H.Debug));

        //System.out.println(H.minimum().prio());

    }
}
