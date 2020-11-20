// Als Binomial-Halde implementierte Minimum-Vorrangwarteschlange
// mit Prioritäten eines beliebigen Typs P (der die Schnittstelle
// Comparable<P> oder Comparable<P'> für einen Obertyp P' von P
// implementieren muss) und zusätzlichen Daten eines beliebigen Typs D.
class BinHeap <P extends Comparable<? super P>, D> {
	private int size=0;
	private Entry head=null; //Das Element mit der niedrifsten Priorität das den Baum "startet"

	public BinHeap(){
		versuch = new P (1);
	} //Standartkonstruktor
	public BinHeap(Entry e){ //Konstruktur für Baum mit einem Element
		head=e;
		versuch = new P (1);
	}

	public int size() {
		return size;
	}

	public Entry<P, D> insert(P p, D d) {
		Entry e= new Entry(p,d);
 		Node n=new Node(e);
 		BinHeap tmpheap= new BinHeap(e);

 		mergeHeap(this,tmpheap);
		return e;
	}
	public int compareTo(){
		return 0; //nicht implementiert
	}
	public boolean mergeHeap(BinHeap H1,BinHeap H2){
		int i,k=0;
		int filling_zwischensp=0; //Beschreibt wie viele Elemente in tmpHeap enthalten sind
		BinHeap[] zwischensp=new BinHeap[3]; //Zwischenspeicher für bis zu drei Bäume

		while((H1.head!=null)||(H2.head!=null)||(filling_zwischensp!=0)){
			if(H1.head.node.degree==k) { //Codedopplung mit dem nächsten if-Statement, vielleicht Hilfsmethode?
				for (i = 0; i <= 2; i++) {
					if (zwischensp[i] == null){
						zwischensp[i] = new BinHeap(H1.head);
						filling_zwischensp++;
						break; // bis wohin geht brak raus
					}
				}
			}
			// head rausnehmen aus H1 wenn es in den zwischenspeicher kommt
			if(H2.head.node.degree==k) {
				for (i = 0; i <= 2; i++) {
					if (zwischensp[i] == null){
						zwischensp[i] = new BinHeap(H2.head);
						filling_zwischensp++;
						break;
					}
				}
			}
			if(filling_zwischensp==1 || filling_zwischensp==3){

			}
			if (filling_zwischensp==2) {

			}
		}
		return false; //Löschen
	}

		public BinHeap mergeEqTree(Entry H1, Entry H2){ //Hilfsoperation zur Vereinigung zweier Bäume des gleichen Grads

			int degree=H1.node.degree;
			Entry dom,sub;
			if(degree!=H2.node.degree) return false;
			if(H1.prio().compareTo(H2.prio())<0) { //compareTo muss im Typ P implementiert werden?
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
			return new BinHeap(dom);
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
