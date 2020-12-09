// Als Binomial-Halde implementierte Minimum-Vorrangwarteschlange
// mit Prioritäten eines beliebigen Typs P (der die Schnittstelle
// Comparable<P> oder Comparable<P'> für einen Obertyp P' von P
// implementieren muss) und zusätzlichen Daten eines beliebigen Typs D.




class BinHeap <P extends Comparable<? super P>, D> {

	private int size=0;
	private Node<P,D> head; //Das Element mit dem niedrigsten Grad das den Baum "startet"

	public BinHeap(){head=null;} //Standartkonstruktor

	private BinHeap(Node <P,D> n){ //Konstruktur für Baum mit einem Element
		head=n;
		size=1;
	}

	public Entry<P,D> insert(P p, D d) {

		if(p==null || d== null) return null; //Fängt Nullinserts ab
		Entry<P,D> e= new Entry<>(p,d);
		e.node=new Node<>(e);
 		this.head=mergeHeap(this,new BinHeap<>(e.node));
 		size++;

		return e;
	}

	public Entry<P,D> insertEntry(Entry<P,D> e) {

		e.node=new Node<>(e);
		this.head=mergeHeap(this,new BinHeap<>(e.node));
		size++;

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

		if(e==null || e.node==null || head==null ||head.entry==null) return false; //Fängt fehlerhafte Eingabe ab
		Node<P,D> hochlaufnode=e.node,wurzellaufnode=this.head;

		while(hochlaufnode.parent!=null){
			hochlaufnode=hochlaufnode.parent;

			j++;
			assert(j<100);
		}
		do{ //Verbesserungswürdig

			if(wurzellaufnode.equals(hochlaufnode)) return true;
			wurzellaufnode=wurzellaufnode.sibling;
			if(wurzellaufnode!=null && wurzellaufnode.equals(hochlaufnode)) return true;
		}while (wurzellaufnode!=null && wurzellaufnode.sibling!=null);

		return false;
	}

	public boolean remove (Entry<P, D> e){

		if (head==null||head.entry==null||e==null || e.node==null || !contains(e)) return false;

		int tmpsize=size;

		while(e.node.parent!=null && e.node.parent.entry!= null) {

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

			if(laufnode==e.node) { //Falls das zu entfernende Element =head ist

				head=head.sibling;
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
			e.node.entry=null;
			e.node=null;
			return true;
		}
		Node <P,D> neueHaldeStart=e.node.child.sibling;

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
		e.node.child.sibling=null;
		this.head=mergeHeap(this,new BinHeap<>(neueHaldeStart));
		//System.out.println("Ende");
		//changePrio(e,e.node.child.prio()); Sollte Praktisch damit umgesetzt werden, keine Ahnung wie
		//extractMin();


		size=tmpsize-1;

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

		int pos1=0,pos2=0;
		int filling_zwischensp=0; //Beschreibt wie viele Elemente in zwischensp enthalten sind
		Node<P,D> tmp;
		BinHeap<P,D> buildH=new BinHeap<>();
		BinHeap<P,D>[] zwischensp=new BinHeap[3]; //Zwischenspeicher für bis zu drei Bäume



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

				zwischensp[pos2]=null;
				filling_zwischensp--;
			}

			k++;

		}

		return buildH.head;

	}

		public BinHeap<P,D> mergeEqTree(Node<P,D> H1, Node<P,D> H2){ //Hilfsoperation zur Vereinigung zweier Bäume des gleichen Grads

			int degree=H1.degree;
			Node<P,D> dom,sub;


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

			return new BinHeap<>(dom);
		}

	public boolean changePrio(Entry<P, D> entry, P s) {
		if( head == null || entry == null|| s == null || !this.contains(entry)) return false;

		if( s.compareTo(entry.prio) <=0) {

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


			}
			else{

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
