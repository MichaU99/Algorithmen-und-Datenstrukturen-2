public class FIFOList {
    static FIFOList first=null;
    static FIFOList last=null;
    FIFOList next=null;
    Integer element;
    public FIFOList(Integer ele){ //Zweiten Konstruktor für reset hinzufügen
        if(first==null){
            first=this;
        }
        last=this;
        element=ele;
    }
    public void add(Integer ele){
        last.next=new FIFOList(ele);
    }
    public Integer extractFirst(){
        if(first==null) return null;

        Integer tmp=first.element;
        first=first.next;
        return tmp;
    }
}
