// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).
    // While inserting into the list, only call insert at the head of the list
    // Please note that ALL insertions in the DLL (used either in A1DynamicMem or used independently as the dictionary class implementation) are to be made at the HEAD (from the front).
    // Also, the find-first should start searching from the head (irrespective of the use for A1DynamicMem). Similar arguments will follow with regards to the ROOT in the case of trees (specifying this in case it was not so trivial to anyone of you earlier)
    public int Allocate(int blockSize) {
        if (blockSize<1){
            return -1;
        }
        Dictionary headalloc=allocBlk.Find(-1, true);
        Dictionary headfree=freeBlk.Find(-1,true);
        Dictionary block=freeBlk.Find(blockSize, false);
        if (block!=null){
            if (block.size==blockSize){
                headalloc.Insert(block.address, block.size, block.address);
                freeBlk.Delete(block);
            }else{
                headalloc.Insert(block.address, blockSize, block.address);
                headfree.Insert(block.address + blockSize, block.size- blockSize, block.size - blockSize);
                freeBlk.Delete(block);
            }
            return block.address;
        }
        return -1;
    } 
    // return 0 if successful, -1 otherwise
    public int Free(int startAddr) {
        Dictionary headfree=freeBlk.Find(-1, true);
        Dictionary block=allocBlk.Find(startAddr, true);
        if (block!=null){
            headfree.Insert(block.address, block.size, block.size);
            allocBlk.Delete(block);
            return 0;
        }
        return -1;
    }
}