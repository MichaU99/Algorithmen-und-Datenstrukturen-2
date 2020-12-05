import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) throws FileNotFoundException {
        int prio=0,data=0;
        BinHeap H =new BinHeap();
        String filedata="";

        File file=new File("Uebung2_Vorrangswarteschlangen/Testfile");
        Scanner scanFile=new Scanner(file);
        //Scanner scanDebug=new Scanner()
        System.out.println(H.contains(H.insert("a",0)));
        System.out.println(H.contains(H.insert("b",1)));
        System.out.println(H.contains(H.insert("c",2)));
        /*

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

        /*
        BinHeap.Entry<Integer,Integer> e=null;
        while (scanFile.hasNextLine()){

            prio=scanFile.nextInt();
            data=scanFile.nextInt();
            //filedata=filedata+ scanFile.nextLine()+"\n";
            System.out.println(H.contains(H.insert(prio,data)));
        }

        H.dump();
        Scanner scan2nd=new Scanner(file);
        System.out.println(H.remove(e));
        System.out.println(H.remove(H.insert(prio,data)));
        if(H.priocontains(H.test(-633449019,-330117133))){
            System.out.println("Fehler");
            while(scan2nd.hasNext()) {
                prio = scan2nd.nextInt();
                data = scan2nd.nextInt();
                if (!H.priocontains(H.test(prio, data))) {
                    System.out.println("Fehler Prio: " + prio + " Data: " + data);
                }
            }

        }
        H.dump();



        //System.out.println(H.Test);
       // System.out.println("Stimmt die Testfile mit dump() überein? "+filedata.equals(H.Debug));

        //System.out.println(H.minimum().prio());
*/
    }
}
