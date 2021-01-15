import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TestMain {
    /*
    public static void main(String[] args) {
        char c='A';
        Integer i=null;
        i++;
        //String s=String.valueOf(((char) a));
        System.out.println(i);
    }
    */

    public static void main(String[] args) throws IOException {

        File file = new File("Uebung4_HummanCodierung/Test_Input");
        File fileout = new File("Uebung4_HummanCodierung/TestOutput");
        assert (!file.exists()) : "Input Datei existiert nicht!";
        if (!fileout.exists()) fileout.createNewFile();
        assert (!fileout.exists()) : "Output Datei existiert nicht!";

        Scanner scanFile = new Scanner(fileout);



        String choose = ;

        switch (choose) {
            case "enc0":
                break;
            case "enc1":
                break;
            case "prefixes":
                break;
            case "dec":
                break;
            case "decpref":
                break;
            case "dump":
                break;

        }
    }
}
