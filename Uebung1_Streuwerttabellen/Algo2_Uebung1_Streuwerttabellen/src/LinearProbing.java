// Sondierungssequenz gemäß linearer Sondierung.
public class LinearProbing extends AbstractHashSequence{
    int count,ausgangselement;
    Object key;
    // Lineare Sondierung mit Streuwertfunktion f.
    public LinearProbing (HashFunction f) {
        super(f);
    }

    @Override
    public int first(Object key) {
        this.key=key;
        count=1;
        return ausgangselement=func.compute(key);
    }

    @Override
    public int next() {
        //assert key==null: "Fehlerhafter next() Aufruf ohne key";
        if(count>=(size()-1)) return -1; //Muss extra getested werden
        else return (ausgangselement+count++ % size());
    }
}
