public class FIFOList {
    header head;
    FIFOList next=null;
    Integer element;
    public FIFOList(Integer ele){ //Zweiten Konstruktor für reset hinzufügen
        head=new header(this);
        element=ele;
    }

    private FIFOList(Integer ele,header head){ //Zweiten Konstruktor für reset hinzufügen
        this.head=head;
        if(head.first==null){
            head.first=this;
        }
        head.last=this;
        element=ele;
    }
    public void add(Integer ele){
        head.last.next=new FIFOList(ele,head);
    }
    public Integer extractFirst(){
        if(head.first==null) return null;

        Integer tmp=head.first.element;
        head.first=head.first.next;
        return tmp;
    }
    public class header{
        FIFOList first;
        FIFOList last;
        private header(FIFOList obj){
            first=last=obj;
        }
    }
}
