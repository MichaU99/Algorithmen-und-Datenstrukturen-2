import java.util.Comparator;

// Als Binomial-Halde implementierte Minimum-Vorrangwarteschlange
// mit Prioritäten eines beliebigen Typs P (der die Schnittstelle
// Comparable<P> oder Comparable<P'> für einen Obertyp P' von P
// implementieren muss) und zusätzlichen Daten eines beliebigen Typs D.
class P implements Comparable<P> {
	private P test;

	public P ( P prio){
		test = prio;
	}
	@Override
	public int compareTo(P that) {
		return test.compareTo(that.test);
	}
	}

class BinHeap <P extends Comparable<? super P>, D> {
	//Kann später entfert werden soll nur zum Test der Aufrufzahl von Methoden dienen
	static int Test;
	String Debug="";
	//

	private int size=0;
	private Entry<P,D> head; //Das Element mit dem niedrigsten Grad das den Baum "startet"

	public BinHeap(){head=null;} //Standartkonstruktor

	private BinHeap(Entry <P,D> e){ //Konstruktur für Baum mit einem Element
		head=e;
		size=1;
	}

	//Testmethoden können gelöscht werden sobald abgabe ansteht
	public Entry<P,D> test(P p, D d){
		Entry<P,D> e=new Entry<>(p,d);
		Node<P,D> n= new Node<>(e);
		return e;
	}

	public boolean priocontains (Entry<P, D> e){ //Iteriert über alle Wurzelknoten
		if(this.head==null) return false;
		for (Node <P,D> laufNode= head.node; laufNode!=null;laufNode=laufNode.sibling){
			Test++;
			if(laufNode.entry.prio.compareTo(e.prio) > 0) continue;
			else { //Durchsucht den Baum des Wurzelknotens rekursiv
				if (contains_rekursive(laufNode,e)!=null) return true;
			}
		}
		return false;
	}

	private Entry<P,D> contains_rekursive(Node<P,D> n,Entry<P,D> zusuchen){
		Node<P,D> tmpHead=n;
		Test++;
		do {
			if(tmpHead.entry.prio.equals(zusuchen.prio) && tmpHead.entry.data.equals(zusuchen.data)){ return tmpHead.entry;			} //sollte ein .equals sein

			if (tmpHead.child != null ){            //compare Befehl stimmt nicht
				Entry <P,D> rueckgabe=contains_rekursive(tmpHead.child,zusuchen);
				if (rueckgabe!=null) return rueckgabe;
			}
			tmpHead = tmpHead.sibling;
		} while (n != tmpHead && n.parent != null);//Sibling kann hier eigentlich nicht null sein!
		return null;
	}
	//Kann entfert werden, dient nur zum testen fehlerhafter Entries



	public Entry<P,D> insert(P p, D d) {
		if(p==null || d== null) return null; //Fängt Nullinserts ab
		Entry<P,D> e= new Entry<>(p,d);
		e.node=new Node<>(e);
 		this.head=mergeHeap(this,new BinHeap<>(e));
 		size++;
		return e;
	}

	public boolean isEmpty(){
		if (head==null) return true;
		else return false;
	}

	public Entry<P, D> minimum(){
		if(head==null) return null; //Fehlerabfang falls leerer BinHeap

		Entry <P,D > min=head;

		for( Node<P,D> laufnode=min.node;laufnode.sibling!=null;laufnode=laufnode.sibling){
			if(laufnode.entry.prio.compareTo(min.prio)< 0) min= laufnode.entry;
		}
		return min;
	}

	public Entry<P, D> extractMin (){
		Entry <P,D> E =minimum();

		if (head==null || !remove(E)) return null; //Falls es kein Element im Heap gibt

		return E;
    }

    public boolean contains (Entry<P,D> e){
		if(e==null || head==null) return false; //Fängt fehlerhafte Eingabe ab
		Node<P,D> hochlaufnode=e.node,wurzellaufnode=this.head.node;


		while(hochlaufnode.parent!=null){
			hochlaufnode=hochlaufnode.parent;
		}
		do{
			if(wurzellaufnode.equals(hochlaufnode)) return true;
			wurzellaufnode=wurzellaufnode.sibling;
		}while (wurzellaufnode.sibling!=null);

		return false;
	}

	public boolean remove (Entry<P, D> e){

		if (e==null || e.node==null || !contains(e)) return false;

		int tmpsize=size;


		while(e.node.parent!=null && e.node.parent.entry!= null) {
			Entry<P, D> child = e;
			Entry<P, D> parent = e.node.parent.entry;
			Node<P, D> childNode = e.node;
			Node<P, D> parentNode = e.node.parent;


			e.node.entry = e.node.parent.entry;
			child.node = parentNode;
			parent.node = childNode;

			childNode.entry = parent;
			parentNode.entry = child;
		}

		for(Node <P,D> laufnode=this.head.node;laufnode!=null;laufnode=laufnode.sibling) { //Sucht den Vorgänger des zu entfernden Elements
			if(laufnode==e.node) { //Falls das zu entfernende Element =head ist
				if(laufnode.sibling==null) head=null; //Falls das zu entfernende Element das einzige im Heap ist
				else this.head = e.node.sibling.entry;
				break;
			}
			else{
				if(laufnode.sibling==e.node) { //Falls der Vorgänger gefunden wurde
					laufnode.sibling = e.node.sibling; //Überspringt den Wurzelknoten des zu löschenden Elements damit er im Nachhinein wieder eingefügt werden kann
					break;
				}
			}
		}
		Node<P,D> laufnode=e.node.child;
		if(laufnode==null){//Abbruch falls e degree 0 hat
			size--;
			return true;
		}
		while(true) { //unsicher ob diese Schleife notwendig ist oder ob insert das erfüllt
			laufnode.parent=null;
			if(laufnode.sibling==e.node.child){
				e.node.child.sibling=null;
				break;
			}
			laufnode=laufnode.sibling;
		}
		System.out.println("Anfang");
		this.head=mergeHeap(this,new BinHeap<>(laufnode.entry));
		System.out.println("Ende");
		//changePrio(e,e.node.child.prio()); Sollte Praktisch damit umgesetzt werden, keine Ahnung wie
		//extractMin();


		//Warum sollte ich hier den Aufwand betreiben die Prio zu ändern anstatt es so zu machen
		size=tmpsize-1;

		return true;
	}

	public int size(){ //Vielleicht deckung auch innerhalb von Nodes?
		return size;
	}

	public void dump(){ //Läuft durch die Wurzelknoten
		if(this.head==null) return;
		for (Node<P,D> laufNode= head.node; laufNode!=null;laufNode=laufNode.sibling) dump(laufNode,0);
	}

	private void dump(Node<P,D> n,int Tiefe){ //Ruft Rekursiv die Children des übergebenen Wurzelknotens auf
		String platzhalterVorlage="  "; //Funktioniert das????
		String platzhalter;
		Node<P,D> tmpHead=n;
		int i=-1;
		if(tmpHead.parent!=null) { //Umständlich implementiert wegen unterschiedlichen ausgaben für Wurzeln und children
			while (n != tmpHead || i == -1) {
				tmpHead = tmpHead.sibling;
				if (tmpHead == null) break;
				platzhalter = ""; //Reset
				for (i = 0; i < Tiefe; i++)
					platzhalter = platzhalter + platzhalterVorlage; //Setzt die Leerzeichen für die Ausgabe auf die richtige Länge
				System.out.println(platzhalter + tmpHead.entry.prio + " " + tmpHead.entry.data);
				Debug=Debug+platzhalter + tmpHead.entry.prio + " " + tmpHead.entry.data+"\n"; //Extra Debug String für Vergleiche
				//if(tmpHead.parent!=null && tmpHead.degree<tmpHead.sibling.degree) System.out.println("ALAAARM ");
				if (tmpHead.child != null) dump(tmpHead.child, Tiefe + 1);

			}
		}
		else {
			do {
				platzhalter = ""; //Reset
				for (i = 0; i < Tiefe; i++)
					platzhalter = platzhalter + platzhalterVorlage; //Setzt die Leerzeichen für die Ausgabe auf die richtige Länge
				System.out.println(platzhalter + tmpHead.entry.prio + " " + tmpHead.entry.data);
				Debug=Debug+platzhalter + tmpHead.entry.prio + " " + tmpHead.entry.data+"\n"; //Extra Debug String für Vergleiche
				//if(tmpHead.parent!=null && tmpHead.degree<tmpHead.sibling.degree) System.out.println("ALAAARM ");
				if (tmpHead.child != null) dump(tmpHead.child, Tiefe + 1);
				tmpHead = tmpHead.sibling;
			} while (n != tmpHead && n.parent != null);//Sibling kann hier eigentlich nicht null sein!
		}
	}

	public Entry<P,D> mergeHeap(BinHeap<P,D> H1,BinHeap<P,D> H2){
		int i,k=0;
		int pos1=0,pos2=0;
		int filling_zwischensp=0; //Beschreibt wie viele Elemente in zwischensp enthalten sind
		Entry<P,D> tmp;
		BinHeap<P,D> buildH=new BinHeap<>();
		BinHeap<P,D>[] zwischensp=new BinHeap[3]; //Zwischenspeicher für bis zu drei Bäume

		while((H1.head!=null)||(H2.head!=null)||(filling_zwischensp!=0)){
			if(H1.head!=null && H1.head.node.degree==k) { //Codedopplung mit dem nächsten if-Statement, vielleicht Hilfsmethode?
				for (i = 0; i <= 2; i++) { //ISt es möglich das das Array volläuft??

					if (zwischensp[i] == null){
						tmp=H1.head;
						if(H1.head.node.sibling==null) H1.head=null;
						else H1.head = H1.head.node.sibling.entry; //Reicht das damit der Knoten von der Garbage Collection aufgesammelt wird?
						tmp.node.sibling=null;
						zwischensp[i] = new BinHeap<>(tmp);
						filling_zwischensp++;
						break;
					}
				}
			}
			// head rausnehmen aus H1 wenn es in den zwischenspeicher kommt
			if(H2.head!=null && H2.head.node.degree==k) {
				for (i = 0; i <= 2; i++) {
					if (zwischensp[i] == null){
						tmp=H2.head;
						if(H2.head.node.sibling==null) H2.head=null;
						else H2.head =H2.head.node.sibling.entry; //Reicht das damit der Knoten von der Garbage Collection aufgesammelt wird?
						tmp.node.sibling=null;
						zwischensp[i] = new BinHeap<>(tmp);
						filling_zwischensp++;
						break;
					}
				}

			}
			if(filling_zwischensp==1 || filling_zwischensp==3){
				if(filling_zwischensp==3) pos1=0;
				else{
					for(i=0;i<=2;i++){
						if(zwischensp[i]!=null) pos1=i;
						break;
					}
				}
				if(buildH.head==null) buildH.head=zwischensp[pos1].head;

				else { //Sehr fragwürdige umsetzung, laufnode sollte eigentlich eine Kopie von buildH.head sein ist aber eine Referenz weshalb workaround gemacht wurden pls fix
					Node<P,D> laufnode=buildH.head.node;
					while (laufnode.sibling != null) laufnode = laufnode.sibling; //Fehler
					laufnode.sibling = zwischensp[pos1].head.node;
					}
				zwischensp[pos1]=null;
				filling_zwischensp--;
			}
			if (filling_zwischensp==2) {
				if(zwischensp[0]==null){
					pos1=1;
					pos2=2;
				}
				if(zwischensp[1]==null){
					pos1=0;
					pos2=2;
				}
				else{
					pos1=0;
					pos2=1;
				}
				zwischensp[pos1]=mergeEqTree(zwischensp[pos1].head,zwischensp[pos2].head); //Ist das eine gute Idee? Kann man den entstehenden Tree besser übergeben?
				zwischensp[pos2]=null;
				filling_zwischensp--;
			}
			k++;
		}
		return buildH.head;
	}

		public BinHeap<P,D> mergeEqTree(Entry<P,D> H1, Entry<P,D> H2){ //Hilfsoperation zur Vereinigung zweier Bäume des gleichen Grads

			int degree=H1.node.degree;
			Entry<P,D> dom,sub;
			if(degree!=H2.node.degree) return null;
			if(H1.prio.compareTo(H2.prio)<0) { //compareTo muss im Typ P implementiert werden?
				dom = H1;
				sub = H2;
			}

			else{
				dom = H2;
				sub = H1;
			}
			dom.node.sibling=null;
			dom.node.degree=dom.node.degree+1;
			sub.node.parent=dom.node;
			if (dom.node.child==null) dom.node.child=sub.node.sibling=sub.node; //Funktioniert das?
			else {
				sub.node.sibling=dom.node.child.sibling;
				dom.node.child=dom.node.child.sibling=sub.node;
			}
			return new BinHeap<>(dom);
		}

	public boolean changePrio(Entry<P, D> entry, P s) { // muss noch boolena werden

		/*
		Entry<P,D> tam = new Entry<>(entry.prio(), entry.data());
		tam.node=new Node(tam);
		Node<P,D> parentNode= entry.node.parent;
		while (parentNode != null && entry.prio.compareTo(parentNode.entry.prio) < 0){
			tam = entry;
			entry = parentNode.entry;
			parentNode.entry = tam;
			entry.node = parentNode;
			parentNode = parentNode.parent;
		}
		return true;
		*/


		if( s.compareTo(entry.prio) <=0) {
			entry.prio = s;
			while (entry.node.parent != null && entry.node.parent.entry != null && entry.prio.compareTo(entry.node.parent.entry.prio)<0) {
				Entry<P, D> child = entry;
				Entry<P, D> parent = entry.node.parent.entry;
				Node<P, D> child_Node = entry.node;
				Node<P, D> parent_Node = entry.node.parent;


				entry.node.entry = entry.node.parent.entry;
				child.node = parent_Node;
				parent.node = child_Node;

				child_Node.entry = parent;
				parent_Node.entry = child;
			}
			return true;
		}

			/* war frühere whileschleife
			while( entry.node.parent != null && entry.prio.compareTo(entry.node.parent.entry.prio)<=0){
				if(entry.prio.compareTo(entry.node.parent.entry.prio) == 0){
					parentNode=entry.node.parent;
					entry.node.parent.entry=entry;
					entry.node.parent.entry.node=entry.node;

					entry.node.child.entry = parentNode.entry;
					entry.node.child=parentNode;
					return true;
				}
				parentNode=entry.node.parent;
				System.out.println("entry von entry: "+entry.prio());
				entry.node.parent.entry=entry;
				System.out.println("entry von entry: "+entry.prio());
				entry.node.parent.entry.node=entry.node;

				entry.node.child.entry = parentNode.entry;
				entry.node.child=parentNode;


			}
			return true;

			 */

		if ( s.compareTo(entry.prio) >= 1){
			if(entry.node.child == null) {
				entry.prio=s;
				return true;
			}
			else{
				Entry<P,D> test = entry;
				D b = test.data;
				remove(entry);
				insert(s,b);
			}
			return true;
		}
		return false;
	}


	// Eintrag einer solchen Warteschlange bzw. Halde, bestehend aus
    // einer Priorität prio mit Typ P und zusätzlichen Daten data mit
    // Typ D.
    // Wenn der Eintrag momentan tatsächlich zu einer Halde gehört,
    // verweist node auf den zugehörigen Knoten eines Binomialbaums
    // dieser Halde.
    public static class Entry <P, D> {
		// Priorität, zusätzliche Daten und zugehöriger Knoten.
		private P prio;
		private D data;
		private Node<P, D> node;

			// Eintrag mit Priorität p und zusätzlichen Daten d erzeugen.
		private Entry (P p, D d) {
	    	prio = p;
	    	data = d;
		}

		// Priorität bzw. zusätzliche Daten liefern.
		public P prio () { return prio; }
		public D data () { return data; }
    }

    // Knoten eines Binomialbaums innerhalb einer solchen Halde.
    // Neben den eigentlichen Knotendaten (degree, parent, child,
    // sibling), enthält der Knoten einen Verweis auf den zugehörigen
    // Eintrag.
    private static class Node <P, D> {
		// Zugehöriger Eintrag.
		private Entry<P, D> entry;

		// Grad des Knotens.
		private int degree;

		// Vorgänger (falls vorhanden; bei einem Wurzelknoten null).
		private Node<P, D> parent=null;

		// Nachfolger mit dem größten Grad
		// (falls vorhanden; bei einem Blattknoten null).
		private Node<P, D> child=null;

		// Zirkuläre Verkettung aller Nachfolger eines Knotens
		// bzw. einfache Verkettung aller Wurzelknoten einer Halde,
		// jeweils sortiert nach aufsteigendem Grad.
		private Node<P, D> sibling=null;

		// Knoten erzeugen, der auf den Eintrag e verweist
		// und umgekehrt.
		private Node (Entry<P, D> e) {
	    	entry = e;
	    	e.node = this;
		}

		// Priorität des Knotens, d. h. des zugehörigen Eintrags
		// liefern.
		private P prio () { return entry.prio; }
    }

}
//ERWEITERTES TESTPROGRAMM
//Befehle	Insert 				+ a
//			Remove				- 1
// 			Minimum				?
//			Extract Minimum		!
//			Change Prio			= 1 a
//			Is Emtpy			#
//			Contains			& 1

// a Steht für beliebige Prio in diesem Fall Buchstaben
// 1 Steht für beliebigen Eintrag, in diesem Fall Zahlen (ArrayIndizes)

// Interaktives Testprogramm für die Klasse BinHeap.
class BinHeapTest {
	public static void main (String [] args) throws java.io.IOException {
		// Leere Halde mit Prioritäten des Typs String und zugehörigen
		// Daten des Typs Integer erzeugen.
		// (Die Implementierung muss aber natürlich auch mit anderen
		// Typen funktionieren.)
		BinHeap<String, Integer> heap = new BinHeap<String, Integer>();

		// Feld mit allen eingefügten Einträgen, damit sie später
		// für remove und changePrio verwendet werden können.
		// Achtung: Obwohl die Klasse BinHeap ebenfalls Typparameter
		// besitzt, schreibt man "BinHeap.Entry<String, Integer>" und
		// nicht "BinHeap<String, Integer>.Entry<String, Integer>".
		// Achtung: "new BinHeap.Entry [100]" führt zu einem Hinweis
		// über "unchecked or unsafe operations"; die eigentlich "korrekte"
		// Formulierung "new BinHeap.Entry<String, Integer> [100]"
		// führt jedoch zu einem Übersetzungsfehler!
		BinHeap.Entry<String, Integer> [] entrys = new BinHeap.Entry [100];

		// Anzahl der bis jetzt eingefügten Einträge.
		int n = 0;

		// Standardeingabestrom System.in als InputStreamReader
		// und diesen wiederum als BufferedReader "verpacken",
		// damit man bequem zeilenweise lesen kann.
		java.io.BufferedReader r = new java.io.BufferedReader(
				new java.io.InputStreamReader(System.in));

		// Endlosschleife.
		while (true) {
			// Inhalt und Größe der Halde ausgeben.
			heap.dump();
			System.out.println(heap.size() + " entry(s)");

			// Eingabezeile vom Benutzer lesen, ggf. ausgeben (wenn das
			// Programm nicht interaktiv verwendet wird) und in einzelne
			// Wörter zerlegen.
			// Abbruch bei Ende der Eingabe oder leerer Eingabezeile.
			System.out.print(">>> ");
			String line = r.readLine();
			if (line == null || line.equals("")) return;
			if (System.console() == null) System.out.println(line);
			String [] cmd = line.split(" ");

			// Fallunterscheidung anhand des ersten Worts.
			switch (cmd[0]) {
				case "+": // insert prio
					// Die laufende Nummer n wird als zusätzliche Daten
					// verwendet.
					entrys[n] = heap.insert(cmd[1], n);
					n++;
					break;
				case "-": // remove entry
					System.out.println(heap.remove(entrys[Integer.parseInt(cmd[1])]));
					break;
				case "?": // minimum
					BinHeap.Entry<String, Integer> e = heap.minimum();
					//System.out.println("--> " + e.prio() + " " + e.data());
					System.out.println(e);
					break;
				case "!": // extractMin
					e = heap.extractMin();
					//System.out.println("--> " + e.prio() + " " + e.data());
					System.out.println(e);
					break;
				case "=": // changePrio entry prio
					System.out.println(heap.changePrio(entrys[Integer.parseInt(cmd[1])], cmd[2]));
					break;
				case "#":
					System.out.println(heap.isEmpty());
					break;
				case "&":
					System.out.println(heap.contains(entrys[Integer.parseInt(cmd[1])]));
					break;

			}
		}
	}
}

/*
// Interaktives Testprogramm für die Klasse BinHeap.
class BinHeapTest {
    public static void main (String [] args) throws java.io.IOException {
	// Leere Halde mit Prioritäten des Typs String und zugehörigen
	// Daten des Typs Integer erzeugen.
	// (Die Implementierung muss aber natürlich auch mit anderen
	// Typen funktionieren.)
	BinHeap<String, Integer> heap = new BinHeap<String, Integer>();

	// Feld mit allen eingefügten Einträgen, damit sie später
	// für remove und changePrio verwendet werden können.
	// Achtung: Obwohl die Klasse BinHeap ebenfalls Typparameter
	// besitzt, schreibt man "BinHeap.Entry<String, Integer>" und
	// nicht "BinHeap<String, Integer>.Entry<String, Integer>".
	// Achtung: "new BinHeap.Entry [100]" führt zu einem Hinweis
	// über "unchecked or unsafe operations"; die eigentlich "korrekte"
	// Formulierung "new BinHeap.Entry<String, Integer> [100]"
	// führt jedoch zu einem Übersetzungsfehler!
	BinHeap.Entry<String, Integer> [] entrys = new BinHeap.Entry [100];

	// Anzahl der bis jetzt eingefügten Einträge.
	int n = 0;

	// Standardeingabestrom System.in als InputStreamReader
	// und diesen wiederum als BufferedReader "verpacken",
	// damit man bequem zeilenweise lesen kann.
	java.io.BufferedReader r = new java.io.BufferedReader(
			    new java.io.InputStreamReader(System.in));

	// Endlosschleife.
	while (true) {
	    // Inhalt und Größe der Halde ausgeben.
	    heap.dump();
	    System.out.println(heap.size() + " entry(s)");

	    // Eingabezeile vom Benutzer lesen, ggf. ausgeben (wenn das
	    // Programm nicht interaktiv verwendet wird) und in einzelne
	    // Wörter zerlegen.
	    // Abbruch bei Ende der Eingabe oder leerer Eingabezeile.
	    System.out.print(">>> ");
	    String line = r.readLine();
	    if (line == null || line.equals("")) return;
	    if (System.console() == null) System.out.println(line);
	    String [] cmd = line.split(" ");

	    // Fallunterscheidung anhand des ersten Worts.
	    switch (cmd[0]) {
	    case "+": // insert prio
		// Die laufende Nummer n wird als zusätzliche Daten
		// verwendet.
		entrys[n] = heap.insert(cmd[1], n);
		n++;
		break;
	    case "-": // remove entry
		heap.remove(entrys[Integer.parseInt(cmd[1])]);
		break;
	    case "?": // minimum
		BinHeap.Entry<String, Integer> e = heap.minimum();
		System.out.println("--> " + e.prio() + " " + e.data());
		break;
	    case "!": // extractMin
		e = heap.extractMin();
		System.out.println("--> " + e.prio() + " " + e.data());
		break;
	    case "=": // changePrio entry prio
		heap.changePrio(entrys[Integer.parseInt(cmd[1])], cmd[2]);
		break;
	    }
	}
    }
}

 */
