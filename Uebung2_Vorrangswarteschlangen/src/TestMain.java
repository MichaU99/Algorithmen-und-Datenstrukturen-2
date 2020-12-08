import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) throws IOException {
        Integer prio=null;
        Integer data=null;
        BinHeap H =new BinHeap();
        String filedata="";

        File file=new File("C:\\Users\\Veronika\\Documents\\GitHub\\Algorithmen-und-Datenstrukturen-2\\Uebung2_Vorrangswarteschlangen\\Testfile");
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
        BinHeap.Entry<Integer,Integer> f=null;
        BinHeap.Entry<Integer,Integer> g=null;
       Integer a,b,c;
        while (scanFile.hasNext()){

            prio=scanFile.nextInt();
            data=scanFile.nextInt();
            //filedata=filedata+ scanFile.nextLine()+"\n";
            f=H.insert(prio,data);

        }
        e = H.insert(616805175 ,-286782772);
        H.dump();
        H.changePrio(e,1314289278);

        System.out.println("hier das dump: ");
        H.dump();
        System.out.println("Hier das min"+H.extractMin().prio());



        //System.out.println(H.Test);
       // System.out.println("Stimmt die Testfile mit dump() überein? "+filedata.equals(H.Debug));

        //System.out.println(H.minimum().prio());

    }
}
