// Knoten für den Huffman-Trie
class HNode{
	// chars enthält bei Blattknoten ein Zeichen, ansonsten alle Zeichen der darunterliegenden Knoten
	// Beispiel:
	// 			ab
	//         /  \
	//        a    b
	public String chars;
	// Linkes Kind
	public HNode leftChild;
	// Rechtes Kind
	public HNode rightChild;

	public HNode(String c){
		this.chars=c;
	}
	public HNode(HNode leftChild,HNode rightChild){
		this.leftChild=leftChild;
		this.rightChild=rightChild;
	}
	public HNode(String c,HNode leftChild,HNode rightChild){
		this.chars=c;
		this.leftChild=leftChild;
		this.rightChild=rightChild;
	}

}
class Huffman {
	// Feld mit Huffman-Codes zu den einzelnen Zeichen.
	// Wenn char c = 'a', dann ist codes[c] ein Code, der aus Nullen und Einsen besteht, mit dem das Zeichen a kodiert werden soll.
	private String[] codes;

	// Wurzelknoten des Präfix-Codebaums
	private HNode root;

	// Konstruktor
	public Huffman() {
	}

	private void calcCodes(){
		codes=new String[256];
		searchAllCodes(root,"");
	}
	private void searchAllCodes(HNode node,String code){
		if(node.leftChild==null && node.rightChild==null) {
			codes[(node.chars.charAt(0))] = code;
			return;
		}
		if(node.leftChild!=null){
			searchAllCodes(node.leftChild,code+"0");
		}
		if(node.rightChild!=null){
			searchAllCodes(node.rightChild,code+"1");
		}
	}
	// Prüfen, ob ein Text mit dem aktuell erstellten Huffman-Code kodiert werden kann, ob also alle Zeichen einen Präfix-Code besitzen. Wenn ja, return true, wenn nein, return false. 
	// Prüfen, ob ein Text mit dem aktuell erstellten Huffman-Code kodiert werden kann, ob also alle Zeichen einen Präfix-Code besitzen. Wenn ja, return true, wenn nein, return false.
	public boolean canEncode(String text){
		if(text==null){
			System.out.println("FEHLER canEncode: Kein Text beim CanEncode übergeben");
			return false;
		}
		if(root==null){
			System.out.println("FEHLER canEncode: Text kann nicht codiert werden");
			return false;
		}
		calcCodes();
		char[] sollArray=text.toCharArray();

		for(char c: sollArray){
			if(c>=256){
				System.out.println("Text kann mit dem aktuellen Baum nicht verschlüsselt werden weil ASCII Zeichen >256 verwendet werden");
				return false;
			}
			if(codes[c]==null) return false;
		}
		return true;
	}

	// Vor dem eigentlichen Algorithmus kann mit dieser Funktion die Häufigkeit der einzelnen Zeichen aus dem übergebenen Text errechnet werden.
	// Hierzu kann die Anzahl des Vorkommens eines Zeichens berechnet werden und in einem Array gespeichert werden.
	// Für jedes Zeichen c enthält das Array f an Stelle c die Häufigkeit (also f['a'] ist die Häufigkeit von a im Text. Kommt das Zeichen nicht vor, ist die Häufigkeit 0.)
	// Zur Erinnerung: ein char kann wie eine Ganzzahl verwendet werden, daher funktioniert f[c] für jedes char c.

	/**
	 * Laufzeit für Textlänge n,von O(2n), wenn null=0, dann O(n)
	 * @param text
	 * @return
	 */
	public Integer[] calculateFrequencies(String text){
		if(text==null){
			System.out.println("Fehler calcFreq: calculate Frequencies wurde ohne Text aufgerufen");
			return null;
		}
		Integer[] f = new Integer[256];
		for(char c: text.toCharArray()){
			try {
				if (f[c] == null) f[c] = 1;
				else f[c]++;
			}
			catch (IndexOutOfBoundsException e){
				System.out.println("Fehler calcFreq: Character ist nicht im ASCII Bereich enthalten");
				return null;
			}
		}
		for (int i=0;i<f.length;i++){ //Setzt alle null Felder im Feld auf 0
			if(f[i]==null) f[i]=0;
		}
		return f;
	}

	// Iterativer Algorithmus zur Erstellung des Präfix-Codes (Skript S.115) mithilfe von BinHeap.
	// frequencies enthält die Häufigkeiten (siehe calculateFrequencies). Häufigkeit von 0 bedeutet, das entsprechende Zeichen ist nicht im Text vorhanden und wir brauchen keinen Präfixcode dafür.
	// Die Funktion setzt den Knoten root auf den Wurzelknoten des PräfixCode-Baums und gibt diesen Wurzelknoten außerdem zurück

	/**
	 * Laufzeit ~O(n), erste Schleife O(n)-> n=frequencies.length, zweite Schleife O(d), d->Anzahl !0 Felder in frequencies
	 * @param frequencies
	 * @return
	 */
	public HNode constructPrefixCode(Integer[] frequencies){
		if(frequencies==null){
			System.out.println("Fehler: constructPrefixCode wurde ohne frequencies aufgerufen");
			return null;
		}
		BinHeap<Integer,HNode> heap=new BinHeap<>();
		for(int i=0;i<frequencies.length;i++){
			if(frequencies[i]==0) continue;
			heap.insert(frequencies[i],new HNode(String.valueOf(((char)i))));
		}
		while (heap.size()>=2){
			BinHeap.Entry<Integer,HNode> X= heap.extractMin();
			BinHeap.Entry<Integer,HNode> Y= heap.extractMin();

			heap.insert(X.prio()+Y.prio(),new HNode(X.data().chars+Y.data().chars,X.data(), Y.data()));
		}
		root=heap.extractMin().data();
		return root;
	}

	// Kodierung einer Zeichenkette text (Skript S.108)
	// Die Ergebnis-Zeichenkette enthält nur Nullen und Einsen
	// (der Einfachheit halber wird dennoch ein String-Objekt verwendet)
	// Kodierung: linker Teilbaum -> 0, rechter Teilbaum -> 1
	// Erster Parameter: Zu kodierender Text
	// Zweiter Parameter zeigt an, ob ein neuer Präfixcode erzeugt werden soll (true) oder mit dem aktuellen Präfixcode gearbeitet werden soll (false)

	/**
	 * Laufzeit O(n*log(n))
	 */
	public String encode(String text, boolean newPrefixCode){
		if(text==null){
			System.out.println("FEHLER: Kein Text zum Encode übergeben");
			return null;
		}
		if(!newPrefixCode && !canEncode(text) ||(!newPrefixCode && root==null)){
			System.out.println("FEHLER: Es soll kein neuer Prefixcode generiert werden, aber der bisherige ist mit dem Text nicht kompatibel");
			return null;
		}

		String result = "";
		if(newPrefixCode){
			root=constructPrefixCode(calculateFrequencies(text));
			if(root==null){
				System.out.println("FEHLER ENCODE: Fehler in der Präfixcodekonstruktion");
				return null;
			}
			calcCodes();
		}
		if(codes==null){
			System.out.println("FEHLER encode: codes konnten nicht generiert werden");
			return null;
		}
		for(char c: text.toCharArray()){
			//Testen
			if(c>=codes.length) return null;
			if(codes[c]==null) continue;
			result=result+codes[c];
			//result=result+searchCharInTree(c,root);
		}

		return result;
	}
	

	// Dekodierung eines Huffman-Kodierten Textes. (Skipt S.107)
	// Die Ergebnis-Zeichenkette ist der ursprüngliche Text vor der Huffman-Kodierung
	public String decode(String huffmanEncoded){
		if(huffmanEncoded==null){
			System.out.println("Fehler: decode wurde ohne Text aufgerufen");
		}
		return decode(huffmanEncoded,root);
	}

	// Dekodierung eines Huffman-Kodierten Textes mithilfe des übergebenen Präfix-Codebaums. (Skipt S.107) Der aktuelle Baum soll dabei nicht überschrieben werden.
	// Die Ergebnis-Zeichenkette ist der ursprüngliche Text vor der Huffman-Kodierung

	/**
	 * Laufzeit O(n)
	 * @param huffmanEncoded
	 * @param rootNode
	 * @return
	 */
	public String decode(String huffmanEncoded, HNode rootNode){
		if(huffmanEncoded==null || rootNode==null){
			System.out.println("Fehler: Decode wurde mit einem null-String oder null-HNode aufgerufen");
			return null;
		}
		String resultString="";
		HNode laufNode=rootNode;
		char[] stringAsChar=huffmanEncoded.toCharArray();

		for(char c:stringAsChar){
			if(laufNode==null){
				System.out.println("FEHLER: LaufNode ist null, Baum ohne Kinder");
				break;
			}
			if(c!='0' && c!='1'){
				System.out.println("FEHLER: Decode Sequenz darf nur 1 und 0 enthalten");
				return null;
			}
			if(c=='0'){
				laufNode= laufNode.leftChild;
			}
			else laufNode=laufNode.rightChild;
			if(laufNode!=null && laufNode.leftChild==null && laufNode.rightChild==null){
				resultString=resultString+laufNode.chars;
				laufNode=rootNode;
			}
		}

		return resultString;
	}

	// Präfixcodes ausgeben
	// Reihenfolge: preOrder, also WLR, zuerst Wurzel, dann linker Teilbaum, dann rechter Teilbaum
	public void dumpPrefixCodes(boolean modus){
		if(root==null) return;
		if(modus){
			dumpPrefixCodeRecursiveCodes(root,"");
		}
		else {
			dumpPrefixCodesRecursive(root);
		}

	}

	private void dumpPrefixCodesRecursive(HNode node){
		System.out.println(node.chars);
		if(node.leftChild!=null) dumpPrefixCodesRecursive(node.leftChild);
		if(node.rightChild!=null) dumpPrefixCodesRecursive(node.rightChild);
	}

	private void dumpPrefixCodeRecursiveCodes(HNode node,String code){
		if(node.leftChild==null && node.rightChild==null) {
			System.out.println(node.chars.charAt(0)+": "+code);
			return;
		}
		if(node.leftChild!=null){
			dumpPrefixCodeRecursiveCodes(node.leftChild,code+"0");
		}
		if(node.rightChild!=null){
			dumpPrefixCodeRecursiveCodes(node.rightChild,code+"1");
		}
	}


}

