// Als Binomial-Halde implementierte Minimum-Vorrangwarteschlange
// mit Prioritäten eines beliebigen Typs P (der die Schnittstelle
// Comparable<P> oder Comparable<P'> für einen Obertyp P' von P
// implementieren muss) und zusätzlichen Daten eines beliebigen Typs D.


import java.sql.SQLOutput;

class BinHeap <P extends Comparable<? super P>, D> {

	private int size=0;
	private Node<P,D> head; //Das Element mit dem niedrigsten Grad das den Baum "startet"

	public BinHeap(){head=null;} //Standartkonstruktor

	private BinHeap(Node <P,D> n){ //Konstruktur für Baum mit einem Element
		head=n;
		size=1;
	}

	public Entry<P,D> insert(P p, D d) {
		System.out.println("--------------------Insertbeginn "+p);
		if(p==null || d== null) return null; //Fängt Nullinserts ab
		Entry<P,D> e= new Entry<>(p,d);
		e.node=new Node<>(e);
 		this.head=mergeHeap(this,new BinHeap<>(e.node));
 		size++;
		System.out.println("--------------------Insertende");
		return e;
	}

	public Entry<P,D> insertEntry(Entry<P,D> e) {
		System.out.println("--------------------Insert_changePrio_beginn");
		e.node=new Node<>(e);
		this.head=mergeHeap(this,new BinHeap<>(e.node));
		size++;
		System.out.println("--------------------Insert_changePrio_ende");
		return e;
	}

	public boolean isEmpty(){
		if (head==null) return true;
		else return false;
	}

	public Entry<P, D> minimum(){
		if(head==null) return null; //Fehlerabfang falls leerer BinHeap

		Entry <P,D > min=head.entry;

		for( Node<P,D> laufnode= head;laufnode!=null;laufnode=laufnode.sibling){
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
		int j=0; //Debug
		System.out.println("Anfang Contains");
		if(e==null || e.node==null || head==null ||head.entry==null) return false; //Fängt fehlerhafte Eingabe ab
		Node<P,D> hochlaufnode=e.node,wurzellaufnode=this.head;

		while(hochlaufnode.parent!=null){
			hochlaufnode=hochlaufnode.parent;
			System.out.println("Contains hochlaufnode läuft");
			j++;
			assert(j<100);
		}
		do{ //Verbesserungswürdig
			System.out.println("Contains wurzellaufnode läuft");
			if(wurzellaufnode.equals(hochlaufnode)) return true;
			wurzellaufnode=wurzellaufnode.sibling;
			if(wurzellaufnode!=null && wurzellaufnode.equals(hochlaufnode)) return true;
		}while (wurzellaufnode!=null && wurzellaufnode.sibling!=null);

		return false;
	}

	public boolean remove (Entry<P, D> e){
		System.out.println("-----anfang Remove");
		if (head==null||head.entry==null||e==null || e.node==null || !contains(e)) return false;

		int tmpsize=size;

		while(e.node.parent!=null && e.node.parent.entry!= null) {
			System.out.println("-----Remove: Hochschieben der Wurzel");
			Entry<P, D> child = e;
			Entry<P, D> parent = e.node.parent.entry;
			Node<P, D> childNode = e.node;
			Node<P, D> parentNode = e.node.parent;

			child.node = parentNode;
			parent.node = childNode;

			childNode.entry = parent;
			parentNode.entry = child;
		}

		for(Node <P,D> laufnode=head;laufnode!=null;laufnode=laufnode.sibling) { //Sucht den Vorgänger des zu entfernden Elements
			System.out.println("-----Remove: Sucht Vorgänger");
			if(laufnode==e.node) { //Falls das zu entfernende Element =head ist
				System.out.println("-----Remove: sucht Vorgänger: Falls das zu entfernende Element =head ist");
				head.entry.node=head.sibling;
				break;
			}
			else{
				if(laufnode.sibling==e.node) { //Falls der Vorgänger gefunden wurde
					laufnode.sibling = e.node.sibling; //Überspringt den Wurzelknoten des zu löschenden Elements damit er im Nachhinein wieder eingefügt werden kann
					System.out.println("-----Remove: gefunden Vorgänger");
					break;
				}
			}
		}

		Node<P,D> laufnode=e.node.child;
		if(laufnode==null){//Abbruch falls e degree 0 hat
			System.out.println("-----Remove: nur von einem blattknoten");
			size--;
			e.node.entry=null;
			e.node=null;
			return true;
		}
		Node <P,D> neueHaldeStart=e.node.child.sibling;
		System.out.println("-----Remove: anfang von unterbrechung der siblingkliste");
		/*
		while(true) { //unsicher ob diese Schleife notwendig ist oder ob insert das erfüllt
			laufnode.parent=null;
			if(laufnode.sibling==e.node.child){
				e.node.child.sibling=null;
				System.out.println("-----Remove: unterbrechung der siblingkliste erfolgt");
				break;
			}
			laufnode=laufnode.sibling;
		}
		 */
		//System.out.println("Anfang");
		this.head=mergeHeap(new BinHeap<>(head),new BinHeap<>(neueHaldeStart));
		//System.out.println("Ende");
		//changePrio(e,e.node.child.prio()); Sollte Praktisch damit umgesetzt werden, keine Ahnung wie
		//extractMin();


		size=tmpsize-1;
		System.out.println("-----ende Remove");
		e.node.entry=null;
		e.node=null;
		return true;
	}

	public int size(){ //Vielleicht deckung auch innerhalb von Nodes?
		return size;
	}

	public void dump(){ //Läuft durch die Wurzelknoten
		if(this.head==null) return;
		for (Node<P,D> laufNode= head; laufNode!=null;laufNode=laufNode.sibling) dump(laufNode,0);
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
				//if(tmpHead.parent!=null && tmpHead.degree<tmpHead.sibling.degree) System.out.println("ALAAARM ");
				if (tmpHead.child != null) dump(tmpHead.child, Tiefe + 1);
				tmpHead = tmpHead.sibling;
			} while (n != tmpHead && n.parent != null);//Sibling kann hier eigentlich nicht null sein!
		}
	}

	public Node<P,D> mergeHeap(BinHeap<P,D> H1,BinHeap<P,D> H2){
		int i,k=0;
		int p=0;
		int pos1=0,pos2=0;
		int filling_zwischensp=0; //Beschreibt wie viele Elemente in zwischensp enthalten sind
		Node<P,D> tmp;
		BinHeap<P,D> buildH=new BinHeap<>();
		BinHeap<P,D>[] zwischensp=new BinHeap[3]; //Zwischenspeicher für bis zu drei Bäume

		System.out.println("---------------beginn_mergeHeap mit Head");

		while((H1.head!=null)||(H2.head!=null)||(filling_zwischensp!=0)){
			assert(k<1000):" filling="+filling_zwischensp+" H1.head="+H1.head+" H2.head="+H2.head+" H1.head.sibling+"+H1.head.sibling;
			assert(H1.head==null|| H1.head.sibling==null||!(H1.head.degree==0 && H1.head.sibling.degree==0)):"H1.head.node.deg:"+H1.head.degree+" H1.head.node.sib.deg:"+H1.head.sibling.degree;
			if(k>=900) System.out.println("H1.head deg:"+H1.head.degree+" H1.head.sibling:"+H1.head.sibling);
			if(H1.head!=null && H1.head.degree==k) { //Codedopplung mit dem nächsten if-Statement, vielleicht Hilfsmethode?
				for (i = 0; i <= 2; i++) { //ISt es möglich das das Array volläuft??

					if (zwischensp[i] == null){
						tmp=H1.head;
						if(H1.head.sibling==null) H1.head=null;
						else H1.head = H1.head.sibling; //Reicht das damit der Knoten von der Garbage Collection aufgesammelt wird?
						tmp.sibling=null;
						tmp.parent=null;
						zwischensp[i] = new BinHeap<>(tmp);
						filling_zwischensp++;

						break;
					}

				}
			}
			// head rausnehmen aus H1 wenn es in den zwischenspeicher kommt
			if(H2.head!=null && H2.head.degree==k) {
				for (i = 0; i <= 2; i++) {
					if (zwischensp[i] == null){
						tmp=H2.head;
						//if(H2.head.sibling==null ) H2.head=null;
						 H2.head =H2.head.sibling; //Reicht das damit der Knoten von der Garbage Collection aufgesammelt wird?
						tmp.sibling=null;
						tmp.parent=null;
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
						if(zwischensp[i]!=null){
							pos1=i;
							break;
						}
					}
				}
				if(buildH.head==null) buildH.head=zwischensp[pos1].head;

				else { //Sehr fragwürdige umsetzung, laufnode sollte eigentlich eine Kopie von buildH.head sein ist aber eine Referenz weshalb workaround gemacht wurden pls fix
					Node<P,D> laufnode=buildH.head;
					while (laufnode.sibling != null) laufnode = laufnode.sibling; //Fehler
					laufnode.sibling = zwischensp[pos1].head;
					}
				zwischensp[pos1]=null;
				filling_zwischensp--;
			}
			if (filling_zwischensp==2) {
				if(zwischensp[0]==null){
					pos1=1;
					pos2=2;
				}
				else if(zwischensp[1]==null){
					pos1=0;
					pos2=2;
				}
				else{
					pos1=0;
					pos2=1;
				}
				zwischensp[pos1]=mergeEqTree(zwischensp[pos1].head,zwischensp[pos2].head); //Ist das eine gute Idee? Kann man den entstehenden Tree besser übergeben?
				System.out.println("---------------MergeHeap: Equalstree rausgehen");
				zwischensp[pos2]=null;
				filling_zwischensp--;
			}

			k++;
			System.out.println("---------------MergeHeap: k wird erhöht auf"+k);
		}
		System.out.println("---------------ende_mergeHeap mit Head");
		return buildH.head;

	}

		public BinHeap<P,D> mergeEqTree(Node<P,D> H1, Node<P,D> H2){ //Hilfsoperation zur Vereinigung zweier Bäume des gleichen Grads
			System.out.println("----------Anfang mergeEqTree");
			int degree=H1.degree;
			Node<P,D> dom,sub;
			if(degree!=H2.degree) assert(false):"Fehler in den Nodes";
			assert(H1.entry!=null && H2.entry!=null): "H1.entry:"+H1.entry+" H2.entry:"+H2.entry;
			if(H1.entry.prio.compareTo(H2.entry.prio)<0) { //compareTo muss im Typ P implementiert werden?
				dom = H1;
				sub = H2;
			}

			else{
				dom = H2;
				sub = H1;
			}
			dom.sibling=null;
			dom.degree=dom.degree+1;
			sub.parent=dom;
			if (dom.child==null) dom.child=sub.sibling=sub; //Funktioniert das?
			else {
				sub.sibling=dom.child.sibling;
				dom.child=dom.child.sibling=sub;
			}
			System.out.println("----------Ende mergeEqTree");
			return new BinHeap<>(dom);
		}

	public boolean changePrio(Entry<P, D> entry, P s) {
		if( head == null || entry == null|| s == null || !this.contains(entry)) return false;
		System.out.println("changePrio:reingekommen");
		if( s.compareTo(entry.prio) <=0) {
			System.out.println("changePrio: kleinergleich neue Prio");
			entry.prio = s;
			while (entry.node.parent != null && entry.node.parent.entry != null && entry.prio.compareTo(entry.node.parent.prio())<0) {
				Entry<P, D> child = entry;
				Entry<P, D> parent = entry.node.parent.entry;
				Node<P, D> child_Node = entry.node;
				Node<P, D> parent_Node = entry.node.parent;

				child_Node.entry = parent;
				parent_Node.entry = child;

				child.node = parent_Node;
				parent.node = child_Node;


			}
			return true;
		}

			if(entry.node.child == null) {
				entry.prio=s;
				System.out.println("changePrio: Blattknoten");

			}
			else{
				System.out.println("changePrio: muss removt werden");
				remove(entry);
				entry.prio = s;

				insertEntry(entry);
			}
		return true;
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

//Erweitertes Testprogramm, welches 3 verschiedene Halden erzeugt
class BinHeapTest {
	public static void main (String [] args) throws java.io.IOException {
		// Leere Halde mit Prioritäten des Typs String und zugehörigen
		// Daten des Typs Integer erzeugen.
		// (Die Implementierung muss aber natürlich auch mit anderen
		// Typen funktionieren.)
		BinHeap<String, Integer> heap1 = new BinHeap<String, Integer>();
		BinHeap<String, Integer> heap2 = new BinHeap<String, Integer>();
		BinHeap<String, Integer> heap3 = new BinHeap<String, Integer>();
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
			heap1.dump();
			System.out.println(heap1.size() + " entry(s)");

			heap2.dump();
			System.out.println(heap2.size() + " entry(s)");

			heap3.dump();
			System.out.println(heap3.size() + " entry(s)");

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
				//-------------------------------------------------Heap 1 --------------------------------------------------
				case "+1": // insert prio
					// Die laufende Nummer n wird als zusätzliche Daten
					// verwendet.
					entrys[n] = heap1.insert(cmd[1], n);
					n++;
					break;
				case "-1": // remove entry
					heap1.remove(entrys[Integer.parseInt(cmd[1])]);
					break;
				case "?1": // minimum
					BinHeap.Entry<String, Integer> e1 = heap1.minimum();
					if (e1 != null) System.out.println("--> " + e1.prio() + " " + e1.data());
					break;
				case "!1": // extractMin
					e1 = heap1.extractMin();
					//System.out.println("--> " + e.prio() + " " + e.data());
					break;
				case "=1": // changePrio entry prio
					heap1.changePrio(entrys[Integer.parseInt(cmd[1])], cmd[2]);
					break;
				case "#1": //is empty
					System.out.println(heap1.isEmpty());
					break;
				case "&1": // contains
					System.out.println(heap1.contains(entrys[Integer.parseInt(cmd[1])]));
					break;
				//------------------------------------------------Heap 2---------------------------------------
				case "+2": // insert prio
					// Die laufende Nummer n wird als zusätzliche Daten
					// verwendet.
					entrys[n] = heap2.insert(cmd[1], n);
					n++;
					break;
				case "-2": // remove entry
					heap2.remove(entrys[Integer.parseInt(cmd[1])]);
					break;
				case "?2": // minimum
					BinHeap.Entry<String, Integer> e2 = heap2.minimum();
					System.out.println("--> " + e2.prio() + " " + e2.data());
					break;
				case "!2": // extractMin
					e2 = heap1.extractMin();
					System.out.println("--> " + e2.prio() + " " + e2.data());
					break;
				case "=2": // changePrio entry prio
					heap2.changePrio(entrys[Integer.parseInt(cmd[1])], cmd[2]);
					break;
				case "#2": //is empty
					System.out.println(heap2.isEmpty());
					break;
				case "&2": // contains
					System.out.println(heap2.contains(entrys[Integer.parseInt(cmd[1])]));
					break;
				//-------------------------------------------------Heap 3 ------------------------------------------------
				case "+3": // insert prio
					// Die laufende Nummer n wird als zusätzliche Daten
					// verwendet.
					entrys[n] = heap3.insert(cmd[1], n);
					n++;
					break;
				case "-3": // remove entry
					heap3.remove(entrys[Integer.parseInt(cmd[1])]);
					break;
				case "?3": // minimum
					BinHeap.Entry<String, Integer> e3 = heap3.minimum();
					System.out.println("--> " + e3.prio() + " " + e3.data());
					break;
				case "!3": // extractMin
					e3 = heap1.extractMin();
					if(e3 != null) System.out.println("--> " + e3.prio() + " " + e3.data());
					break;
				case "=3": // changePrio entry prio
					heap3.changePrio(entrys[Integer.parseInt(cmd[1])], cmd[2]);
					break;
				case "#3": //is empty
					System.out.println(heap3.isEmpty());
					break;
				case "&3": // contains
					System.out.println(heap3.contains(entrys[Integer.parseInt(cmd[1])]));
					break;

			}
		}
	}
}



