import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) throws FileNotFoundException {
        BinHeap H =new BinHeap();
        String filedata="";
        File file=new File("C:\\Users\\Michael Ulrich\\IdeaProjects\\Algorithmen-und-Datenstrukturen-2\\Uebung2_Vorrangswarteschlangen\\src\\Testfile");
        Scanner scanFile=new Scanner(file);
        //Scanner scanDebug=new Scanner()
        //for (int i=0;i<=100;i++) H.insert(i,i);
        H.insert("a",0);
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
        H.dump();
        while (scanFile.hasNextLine()){
            filedata=filedata+ scanFile.nextLine()+"\n";
        }

        System.out.println();
        System.out.println(filedata.equals(H.Debug));

        //System.out.println(H.minimum().prio());

    }
}
