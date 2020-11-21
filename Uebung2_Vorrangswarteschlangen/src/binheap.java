// Als Binomial-Halde implementierte Minimum-Vorrangwarteschlange
// mit Prioritäten eines beliebigen Typs P (der die Schnittstelle
// Comparable<P> oder Comparable<P'> für einen Obertyp P' von P
// implementieren muss) und zusätzlichen Daten eines beliebigen Typs D.
class BinHeap <P extends Comparable<? super P>, D> {
	public int size=0;
	private Entry head=null; //Das Element mit der niedrifsten Priorität das den Baum "startet"

	public BinHeap(){} //Standartkonstruktor
	private BinHeap(Entry e){ //Konstruktur für Baum mit einem Element
		head=e;
		size=1;
	}

	public Entry<P, D> insert(P p, D d) {
		Entry e= new Entry(p,d);
		e.node=new Node(e);
 		this.head=mergeHeap(this,new BinHeap(e));
 		size++;
		return e;
	}

	public boolean isEmpty(){
		//assert ((head==null && size!=0) || head!=null && size==0);
		if (head==null) return true;
		return false;
	}

	public Entry<P, D> minimum(){
		return head;
	}

	public Entry<P, D> extractMin (){
		Entry E =head;
		remove(head);
		return E;
	}
	public boolean contains (Entry<P, D> e){
		return false ;//dummy Implemetation
	}

	private Entry<P, D> contains_with_element (Entry<P, D> e){
		if(false){
			return e;
		}
		else return null;
	}

	public boolean remove (Entry<P, D> e){
		Entry<P, D> zulöschen=contains_with_element(e);
		if(zulöschen==null) {
			return false;
		}
		//Warum sollte ich hier den Aufwand betreiben die Prio zu ändern anstatt es so zu machen
		size--;
		return true;
	}

	public int size(){
		return size;
	}
	/*public void dump(){
		Entry arbeitsentry =head;
		while (arbeitsentry.node.parent==null && arbeitsentry.node.sibling!=null){ //Bedingung unvollständig, würde bei abstieg in den Baum abbrechen

		}
	} */

	public void dump(){ //Läuft durch die Wurzelknoten
		Node laufNode=head.node;
		while (laufNode!=null ) { //Ist ist die node von head.node= null auch null?
			dump(laufNode,0);
			laufNode=laufNode.sibling;
		}
	}

	private void dump(Node n,int Tiefe){ //Ruft Rekursiv die Children des übergebenen Wurzelknotens auf
		char platzhalterVorlage=' '; //Funktioniert das????
		String platzhalter;
		Node tmpHead=n;
		int i;

		do{
			platzhalter=""; //Reset
			for(i=0;i<Tiefe;i++)	platzhalter=platzhalter+platzhalterVorlage; //Setzt die Leerzeichen für die Ausgabe auf die richtige Länge
			System.out.println(platzhalter+tmpHead.entry.prio+" "+tmpHead.entry.data);
			if(tmpHead.child!=null) dump(tmpHead.child,Tiefe+1);
			tmpHead=tmpHead.sibling;
		}while (n!=tmpHead && n.parent!=null);//Sibling kann hier eigentlich nicht null sein!
	}

	public Entry mergeHeap(BinHeap H1,BinHeap H2){
		int i,k=0;
		int pos1=0,pos2=0;
		int filling_zwischensp=0; //Beschreibt wie viele Elemente in zwischensp enthalten sind
		Entry tmp;
		BinHeap buildH=new BinHeap();
		BinHeap[] zwischensp=new BinHeap[3]; //Zwischenspeicher für bis zu drei Bäume

		while((H1.head!=null)||(H2.head!=null)||(filling_zwischensp!=0)){
			if(H1.head!=null && H1.head.node.degree==k) { //Codedopplung mit dem nächsten if-Statement, vielleicht Hilfsmethode?
				for (i = 0; i <= 2; i++) { //ISt es möglich das das Array volläuft??

					if (zwischensp[i] == null){
						tmp=H1.head;
						if(H1.head.node.sibling==null) H1.head=null;
						else H1.head = H1.head.node.sibling.entry; //Reicht das damit der Knoten von der Garbage Collection aufgesammelt wird?
						tmp.node.sibling=null;
						zwischensp[i] = new BinHeap(tmp);
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
						zwischensp[i] = new BinHeap(tmp);
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
				else {
					Entry arbeitsentry=buildH.head;
					while (arbeitsentry.node.sibling != null) arbeitsentry.node = arbeitsentry.node.sibling;
					arbeitsentry.node.sibling = zwischensp[pos1].head.node;
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

		public BinHeap mergeEqTree(Entry H1, Entry H2){ //Hilfsoperation zur Vereinigung zweier Bäume des gleichen Grads

			int degree=H1.node.degree;
			Entry dom,sub;
			if(degree!=H2.node.degree) return null;
			if(H1.prio().toString().compareTo(H2.prio().toString())<0) { //compareTo muss im Typ P implementiert werden?
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

	public void changePrio(Entry<P, D> entry, P s) {
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
