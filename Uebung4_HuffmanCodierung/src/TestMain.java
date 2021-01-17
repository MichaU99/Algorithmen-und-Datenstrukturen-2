import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) throws IOException {
        //File file = new File("Uebung4_HummanCodierung/Test_Input");
        //File fileout = new File("Uebung4_HummanCodierung/TestOutput");
        //assert (!file.exists()) : "Input Datei existiert nicht!";
        //if (!fileout.exists()) fileout.createNewFile();
        //assert (!fileout.exists()) : "Output Datei existiert nicht!";

        //Scanner scanFile = new Scanner(fileout);


        String choose = "";
        //assert(false);
        try {
            assert (false);
            System.out.println("Asserts nicht aktiviert");
        } catch (AssertionError e) {
            System.out.println("Test startet");
        }


        Huffman test = new Huffman();

        String text1 = "Chr. Die chinesische Schrift (chinesisch 中文字, Pinyin zhōngwénzì, Zhuyin ㄓㄨㄥ ㄨ";
        String text2 = "12";
        String text3 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwx ,";
        String text4 = "Ein Toller text";
        String text5 = "Ein anderer toller Text mit allen MOEGLICHEN Zeichen, die in drei enhalten sind";
        String text6 = "ETto";
	/*	String text3 = "ab1cdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ().:;,&$ßäöüielen wie Federn vom Himmel herab. Da saß eine Königin an einem Fenster, das ÄÖÜe Es war einmal mitten im Winter, und die Schneeflocken fielen wie Federn vom Himmel herab. Da saß eine Königin an einem Fenster, das einen Rahmen von schwarzem Ebenholz hatte, und nähte. Und wie sie so nähte und nach dem Schnee aufblickte, stach sie sich mit der Nadel in den Finger, und es fielen drei Tropfen Blut in den Schnee. Und weil das Rote im weißen Schnee so schön aussah, dachte sie bei sich: Hätt' ich ein Kind, so weiß wie Schnee, so rot wie Blut und so schwarz wie das Holz an dem Rahmen! Bald darauf bekam sie ein Töchterlein, das war so weiß wie Schnee, so rot wie Blut und so schwarzhaarig wie Ebenholz und ward darum Schneewittchen (Schneeweißchen) genannt. Und wie das Kind geboren war, starb die Königin. Über ein Jahr nahm sich der König eine andere Gemahlin. Es war eine schöne Frau, aber sie war stolz und übermütig und konnte nicht leiden, daß sie an Schönheit von jemand sollte übertroffen werden. Sie hatte einen wunderbaren Spiegel wenn sie vor den trat und sich darin beschaute, sprach sie:";
		String text4 = "Die chinesische Schrift oder Hanzì fixiert die chinesischen Sprachen, vor allem das Hochchinesische, mit chinesischen Schriftzeichen. Sie ist damit ein zentraler Träger der chinesischen Kultur und diente als Grundlage der japanischen Schriften, einer der koreanischen Schriften und einer der vietnamesischen Schriften.";
		String text5 = "Es war einmal mitten im Winter, und die Schneeflocken fielen wie Federn vom Himmel herab. Da saß eine Königin an einem Fenster, das einen Rahmen von schwarzem Ebenholz hatte, und nähte. Und wie sie so nähte und nach dem Schnee aufblickte, stach sie sich mit der Nadel in den Finger, und es fielen drei Tropfen Blut in den Schnee. Und weil das Rote im weißen Schnee so schön aussah, dachte sie bei sich: Hätt' ich ein Kind, so weiß wie Schnee, so rot wie Blut und so schwarz wie das Holz an dem Rahmen! Bald darauf bekam sie ein Töchterlein, das war so weiß wie Schnee, so rot wie Blut und so schwarzhaarig wie Ebenholz und ward darum Schneewittchen (Schneeweißchen) genannt. Und wie das Kind geboren war, starb die Königin. Über ein Jahr nahm sich der König eine andere Gemahlin. Es war eine schöne Frau, aber sie war stolz und übermütig und konnte nicht leiden, daß sie an Schönheit von jemand sollte übertroffen werden. Sie hatte einen wunderbaren Spiegel wenn sie vor den trat und sich darin beschaute, sprach sie:";
*/
        String result1;
        String result2;
        String result3;
        String result4;
        String result5;

        HNode root1;
        HNode root2;


        assert (!test.canEncode(text1)) : "Kein Präfixcode vorhanden, kann nicht codiert werden";

        result1 = test.encode(text1, false);
        assert (result1 == null) : "Result muss NULL sein, kein Präfixcode vorhanden";

        result2 = test.encode(text1, true);
        assert (result2 == null) : "Text kann nicht in der ASCII 256er Tabelle kodiert werden";

        result1 = test.encode(text2, false);
        assert (result1 == null) : "Text kann nicht kodiert werden, bisher kein Präfixbaum erstellt";

        result2 = test.encode(text2, true);
        assert (result2 != null && result2.length() == 2) : "Text muss kodierbar sein";

        assert (test.canEncode(text2)) : "Text kann nicht codiert werden, wurde aber bereits codiert!?";

        result1 = test.encode(text3, true);
        result2 = test.decode(result1);

        assert (text3.equals(result2)) : "Texte müssen gleich sein";


        Integer[] freq = test.calculateFrequencies(text3);
        root1 = test.constructPrefixCode(freq);
        assert (freq != null && root1 != null) : "Freq oder Wurzel ist null";

        result1 = test.encode(text3, false);
        result2 = test.encode(text3, true);
        assert ((result1.equals(result2))) : "Codierte Texte müssen gleich sein, da gleiche Präfixbäume (neu erstellt aber gleich) verwendet wurden";

        result4 = test.decode(result2);
        result5 = test.decode(result1, root1);
        assert (result4.equals(result5)) : "Texte müssen gleich sein, unterschiedliche Kodierung";

        root1 = test.constructPrefixCode(test.calculateFrequencies(text4));
        //Präfixcode erstellen
        root2 = test.constructPrefixCode(test.calculateFrequencies(text3));

        assert ((test.decode(text3, root2)) == null) : "Test Dekodierung eines nicht kodierten Textes";

        test.encode(text3, true);


        assert ((test.decode(test.encode(text4, false)).equals(text4))) : "Texte nicht gleich1";

        assert (test.decode(test.encode(text5, false)).equals(text5)) : "Texte nicht gleich2";

        //Testfall: Präfixbaum -> encode Text -> Decodieren mit root -> Decode Text
        result1 = test.encode(text3, true);
        assert (test.decode(test.encode(text4, false), root2).equals(text4)) : "Texte nicht gleich3";
        result2 = test.decode(result1);
        assert (result2.equals(text3)) : "Kodierung + Dekodierung != gleicher Text";


        /*
        char c='A';
        Integer i=null;
        i++;
        //String s=String.valueOf(((char) a));
        System.out.println(i);



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

        Huffman neu = new Huffman();
        neu.encode(text4, true);
        //Präfixbaum vom Text 4
        HNode neuerPraefixbaum= neu.constructPrefixCode(neu.calculateFrequencies(text4));

        String encWithPra = neu.encode(text6, false);

        //neuen Präfixbaum erstellen
        String encWithPraeT3 =neu.encode(text3, true);
        String Texttt = neu.decode(encWithPraeT3);
        String neuerText = neu.decode(encWithPra, neuerPraefixbaum);
        assert(neuerText.equals(text6));
        assert (Texttt.equals(text3));


        System.out.println( "\n\n\n Baumausgabe");

        test.dumpPrefixCodes(true);
        test.dumpPrefixCodes(false);

    }
}
    */

    }
}