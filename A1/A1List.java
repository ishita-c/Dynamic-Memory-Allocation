// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        if (this.next!=null){
            A1List new_node=new A1List(address, size, key);
            A1List next_node=this.next;
            this.next=new_node;
            new_node.next=next_node;
            next_node.prev=new_node;
            new_node.prev=this;
            return new_node;
        }
        return null;
    }

    public boolean Delete(Dictionary d) 
    {
        if (this.getFirst()==null){
            return false;
        }
        if (d.key==-1){
            return false;
        }
        A1List current =this;
        while (current.prev!=null){
            if (current.key==d.key){
                if (current.address==d.address && current.size==d.size){
                    A1List p=current.next;
                    A1List q=current.prev;
                    q.next=p;
                    p.prev=q;
                    return true;
                }
            }
            current=current.prev;
        }
        current=this;
        while(current.next!=null){
            if (current.key==d.key){
                if (current.address==d.address && current.size==d.size){
                    A1List p=current.next;
                    A1List q=current.prev;
                    q.next=p;
                    p.prev=q;
                    return true;
                }
            }
            current=current.next;
        }
        return false;
        
    }

    public A1List Find(int k, boolean exact)
    { 
        A1List current=this.getFirst();
        if (current!=null){
            if (k==-1){
                return current.prev;
            }
            if(exact==true){
            while(current.key!=-1){
                if (current.key==k){
                    return current;
                }
                current=current.next;
            }
            }
            else{
                while(current.key!=-1){
                    if(current.key>=k){
                        return current;
                    }
                    current=current.next;
                }
            }
        }else{
            if (k==-1){
                if (this.prev==null){
                    return this;
                }else{
                    return this.prev;
                }
            }
        }
        return null;
    }

    public A1List getFirst()
    {
        A1List current=this;
        if (current.key==-1){
            if (current.prev==null && current.next.key==-1){
                return null;
            }
            else if (current.next==null && current.prev.key==-1){
                return null;
            }
            else if (current.prev==null){
                return current.next;
            }else{
                while(current.prev.key!=-1){
                    current=current.prev;
                }
                return current;
            }
            }
        else{
            while(current.prev.key!=-1){
                current=current.prev;
            }
            return current;
        }
    }
    
    public A1List getNext() 
    {
        A1List current=this;
        if (current.key==-1){
            if (current.prev==null && current.next.key==-1){
                return null;
            }
            else if (current.next==null && current.prev.key==-1){
                return null;
            }
            else if (current.prev==null){
                return current.next;
            }else{
                return null;
            }
            }
        else{
            if(current.next!=null){
                return current.next;
            }
        }
        return null;
    }

    public boolean sanity()
    {
        A1List current=this.getFirst();
        if (current!=null){
            current=current.prev;
            if (current.prev!=null || current.next.prev!=current || current.key!=-1){
                return false;
            }
            while (current.next!=null){
                if (current.prev==null || current.next==null || current.next.prev!=current || current.prev.next!=current || current.key==-1){
                    return false;
                }
                current=current.next;
            }
            if (current.next!=null || current.prev.next!=current || current.key!=-1){
                return false;
            }
        }else{
            if (this.key!=-1){
                return false;
            }
            if (this.prev==null && (this.next==null || this.next.key!=-1 || this.next.next!=null || this.next.prev!=current)){
                return false;
            }
            if (this.next==null && (this.prev==null || this.prev.key!=-1 || this.prev.prev!=null || this.prev.next!=current)){
                return false;
            }

        }
        return true;
    }
}


