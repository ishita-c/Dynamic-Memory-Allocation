// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 
    //Your BST (and AVL tree) implementations should obey the property that keys in the left subtree <= root.key < keys in the right subtree. 
    // How is this total order between blocks defined? It shouldn't be a problem when using key=address since those are unique 
    // (this is an important invariant for the entire assignment123 module). When using key=size, use address to break ties i.e. 
    // if there are multiple blocks of the same size, order them by address. Now think outside the scope of the allocation problem 
    //  and think of handling tiebreaking in blocks, in case key is neither of the two. 


    //over-writing Free function with adding the condition if startAddr<0 return -1
    public int Free(int startAddr) {
        if (startAddr<0)
            return -1;
        Dictionary headfree=freeBlk.Find(-1, true);
        Dictionary block=allocBlk.Find(startAddr, true);
        if (block!=null){
            headfree.Insert(block.address, block.size, block.size);
            allocBlk.Delete(block);
            return 0;
        }
        return -1;
    }

    public void Defragment() {
        BSTree defrag = new BSTree();
        for (Dictionary d=freeBlk.getFirst(); d!=null; d=d.getNext()){
            defrag.Insert(d.address, d.size, d.address);
        }
        Dictionary first=defrag.getFirst();
        if (first==null) return;
        Dictionary next=first.getNext();
        while (next!=null){
            if (first.key+first.size==next.key){
                BSTree ffree =new BSTree(first.address, first.size, first.size);
                BSTree fnext =new BSTree(next.address, next.size, next.size);
                defrag.Delete(first);
                defrag.Delete(next);
                freeBlk.Delete(ffree);
                freeBlk.Delete(fnext);
                defrag.Insert(first.address, first.size+next.size, first.address);
                freeBlk.Insert(first.address, first.size+next.size, first.size+next.size);
                first=defrag.Insert(first.address, first.size+next.size, first.address);
                next=first.getNext();
            }else{
                if (first.getNext()==null )
                    break;
                first=first.getNext();
                next=first.getNext();
            }
        }
        return ;
    }
}