public class FIFOList {
    static FIFOList first=null;
    static FIFOList last=null;
    FIFOList next=null;
    Object element;
    public FIFOList(Object ele){
        if(first==null){
            first=this;
        }
        last=this;
        element=ele;
    }
    public void add(Object ele){
        last.next=new FIFOList(ele);
    }
    public Object extractFirst(){
        if(first==null) return null;

        Object tmp=first.element;
        first=first.next;
        return tmp;
    }
}
