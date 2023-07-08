A1List has the following functions:
A1DynamicMem has the following functions:
Find: starts from head
1.) Allocate: finds the head of allocBlock and freeBlock for insert function (inserts at head). Finds the size of block according to first fit algorithm. 
If the size of block is larger than required, a split is performed. Insertions and deletions are made accordingly.
2.) Free: finds head of freeBlock and block to be freed. Insertion at head of freeBlock followed by del from allocblock.

Change of Key is made according to the type of dictionary.