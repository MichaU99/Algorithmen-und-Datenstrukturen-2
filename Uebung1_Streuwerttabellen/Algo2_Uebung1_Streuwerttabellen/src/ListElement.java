import java.util.List;

public class ListElement {
    public ListElement last;
    public ListElement next;
    public Object key;
    public Object val;


    public ListElement(Object key,Object val,ListElement next){
        this.key=key;
        this.val=val;
        this.last=null;
        this.next=next;
        if(!(next==null)) next.last=this;
    }

   public ListElement search(Object key){
        if(this.key.equals(key)) return this;
        if(this.next==null)return null;
        else return next.search(key);
   }

   public boolean remove(Object key){
       if(this.key.equals(key)){
           next.last=last;
           last.next=next;
           this.last=null;
           this.next=null;
           return true;
       }
       if(this.next==null)return false;
       else return next.remove(key);
   }

   public void dump(int nr){
       System.out.println(nr+" "+key+" "+val);
       if(!(next==null)) next.dump(nr);
   }
}
