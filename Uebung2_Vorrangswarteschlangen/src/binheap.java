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
	static int Test; //Kann später entfert werden soll nur zum Test der Aufrufzahl von Methoden dienen
	String Debug="";
	public int size=0;
	private Entry <P,D>head; //Das Element mit der niedrifsten Priorität das den Baum "startet"

	public BinHeap(){head=null;} //Standartkonstruktor
	private BinHeap(Entry <P,D> e){ //Konstruktur für Baum mit einem Element
		head=e;
		size=1;
	}

	//Testmethode
	public Entry<P,D> test(P p, D d){
		Entry e=new Entry<>(p,d);
		Node n= new Node(e);
		return e;
	} //Kann entfert werden, dient nur zum testen fehlerhafter Entries



	public Entry<P,D> insert(P p, D d) {
		//Nullinsert abfrage?
		Entry<P,D> e= new Entry(p,d);
		e.node=new Node(e);
 		this.head=mergeHeap(this,new BinHeap<>(e));
 		size++;

 		/*
		Entry a= new Entry(55555,5);
		a.node=new Node(a);

		System.out.println("davor a: "+a.prio);
 		changePrio(a, (P) e.prio());
		System.out.println("a: "+a.prio);
		*/
		return e;
	}

	public boolean isEmpty(){
		//assert ((head==null && size!=0) || head!=null && size==0);
		if (head==null) return true;
		return false;
	}

	public Entry<P, D> minimum(){
		Entry <P,D > min=head;

		for( Node<P,D> laufnode=min.node;laufnode.sibling!=null;laufnode=laufnode.sibling){
			if(laufnode.entry.prio.compareTo(min.prio)< 0) min= laufnode.entry; //(Irgendwas stimmt mit Prios noch nicht) habe gefixt // Doppelklammer benötigz?
		}

		return min; // wird nur die Referenz übergeben, richtig so?
	}

	public Entry<P, D> extractMin (){ // was muss dern return werden?
		Entry <P,D> E =minimum();
		Entry <P,D> Vorgänger = head;

        for( Node<P,D> laufnode=Vorgänger.node;laufnode.sibling!=null;laufnode=laufnode.sibling){ // sollte nach dem Voränger suchen
            if(laufnode.sibling.prio().compareTo(E.prio) == 0) break;
        }
        Vorgänger.node.sibling = null;

        if (E.node.sibling != null) {

            this.head = mergeHeap(this, new BinHeap<>(E.node.child.sibling.entry));
        }
        return E;

    }
	public boolean contains (Entry<P, D> e){ //Iteriert über alle Wurzelknoten
		if(this.head==null) return false;
		for (Node <P,D> laufNode= head.node; laufNode!=null;laufNode=laufNode.sibling){
			Test++;
			if(laufNode.entry.prio.compareTo(e.prio) > 0) continue;
			else { //Durchsucht den Baum des Wurzelknotens rekursiv
				if (contains_rekursive(laufNode,e)!=null) return true;
			}
		}
		return false;
	} //Wie in guter Laufzeit lösen??????

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

	/* Unbekannt ob benötigt, würde testen und gleich element bei existenz liefern
	private Entry<P, D> contains_with_element (Entry<P, D> e){ //Theoretisch gleich zu
		Entry rueckgabe=null,contains=null;
		Test++;
		if(this.head==null) return null;
		for (Node laufNode2= head.node; laufNode2!=null;laufNode2=laufNode2.sibling){
			if(laufNode2.prio().toString().compareTo(e.prio.toString())>0) continue;
			else { //Durchsucht den Baum des Wurzelknotens rekursiv
				rueckgabe=contains_rekursive(laufNode2,e);
				if(rueckgabe!=null) contains=rueckgabe;
				if (rueckgabe!=null) return rueckgabe;
			}
		}
		return null;
	}
	 */


	public boolean remove (Entry<P, D> e){

		if (e==null || e.node==null) return false;
		// Entry<P, D> zulöschen=contains_with_element(e); //Schaut ob das Element existiert muss glaub nicht gemacht werden
		int tmpsize=size;

		if(true){
			boolean b = changePrio(e, minimum().prio );
			dump();
			extractMin();
			return true;
		}


		while(e.node.parent!=null){
			Node<P,D> parent=new Node(e.node.parent.entry);
			parent.sibling=e.node.parent.sibling;
			parent.child=e.node.parent.child;
			parent.child.child=e.node.child;
			parent.parent=e.node.parent.parent;


			//Mein Versuch:
			e.node.parent.entry=e;
			e.node.child.entry=parent.entry;

			e.node.parent=parent.parent;
			e.node.child.child=parent.child.child;






			/* Michaels Versuch 2
			e.node.parent=e.node;
			e.node.parent.entry=parent.entry;

			e.node=parent;
			e.node.entry=e;
			*/
			/* Michaels Versuch 1
			e.node.parent.entry=e;
			e.node.parent.entry.node=e.node;

			e.node=parent;
			e.node.entry=e;
			//e.node.entry=parent.entry;
			e.node.entry.node=e.node;
			e.node.parent=e.node;
			//e.node.parent.entry=parent.entry;

			//e.node.entry=e;
			 */
		return true;
		}

		/*
		Entry<P,D> min=e.node.child.entry;
		Node<P,D> tmpHead=e.node.child;
		do {
			if(min.prio.compareTo(tmpHead.entry.prio)>0) min=tmpHead.entry;
			tmpHead = tmpHead.sibling;
		} while (e.node.child != tmpHead );

		min.node.child=e.node.child;

		e=min;
		 */

		for(Node <P,D> laufnode=this.head.node;laufnode!=null;laufnode=laufnode.sibling) {
			if(laufnode==e.node) {
				this.head = e.node.sibling.entry;
				break;
			}
			else{
				if(laufnode.sibling==e.node) {
					laufnode.sibling = e.node.sibling;
					break;
				}
			}
		}
		dump();
		Node<P,D> laufnode=e.node.child;
		if(laufnode==null){//Abbruch falls e degree 0 hat
			size--;
			return true;
		}
		while(true) { //unsicher ob diese Schleife notwendig ist oder ob insert das erfüllt
			laufnode.parent=null;
			if(laufnode.sibling==e.node.child){
				laufnode.sibling=null;
				break;
			}
			laufnode=laufnode.sibling;
		}

		this.head=mergeHeap(this,new BinHeap<P,D>(e.node.child.entry));
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
		for (Node laufNode= head.node; laufNode!=null;laufNode=laufNode.sibling) dump(laufNode,0);
	}

	private void dump(Node n,int Tiefe){ //Ruft Rekursiv die Children des übergebenen Wurzelknotens auf
		String platzhalterVorlage="  "; //Funktioniert das????
		String platzhalter;
		Node tmpHead=n;
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

		public BinHeap mergeEqTree(Entry<P,D> H1, Entry<P,D> H2){ //Hilfsoperation zur Vereinigung zweier Bäume des gleichen Grads

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
			return new BinHeap<P,D>(dom);
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
		Entry<P,D> tam;
		Node parentNode,childnode;

		if( s.compareTo(entry.prio) <=0){
			entry.prio = s;
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
		}
		if ( s.compareTo(entry.prio) > 1){
			if(entry.node.child == null) {
				entry.prio=s;
				return true;
			}
			else{
				Entry test = entry;
				remove(entry);
				D b = (D) test.data;
				insert(s,b);
				size--;
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
