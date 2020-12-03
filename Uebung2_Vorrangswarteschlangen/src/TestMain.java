import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) throws FileNotFoundException {
        BinHeap H =new BinHeap();
        String filedata="";

        File file=new File("Uebung2_Vorrangswarteschlangen/Testfile");
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
        BinHeap.Entry e=null;
        while (scanFile.hasNextLine()){

            int prio=scanFile.nextInt();
            int data=scanFile.nextInt();
            //filedata=filedata+ scanFile.nextLine()+"\n";
            if(prio==-633449019){
                e=H.insert(prio,data);
            }
            else H.insert(prio,data);
        }

        H.dump();
        Scanner scan2nd=new Scanner(file);
        System.out.println(H.remove(e));
        if(H.contains(H.test(-633449019,-330117133))){
            System.out.println("Fehler");
            H.dump();
            while(scan2nd.hasNext()) {
                int prio = scan2nd.nextInt();
                int data = scan2nd.nextInt();
                if (!H.contains(H.test(prio, data))) {
                    System.out.println("Fehler Prio: " + prio + " Data: " + data);
                }
            }

        }



        //System.out.println(H.Test);
       // System.out.println("Stimmt die Testfile mit dump() überein? "+filedata.equals(H.Debug));

        //System.out.println(H.minimum().prio());

    }
}
