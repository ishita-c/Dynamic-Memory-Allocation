// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

//import java.util for sanity
import java.util.*;
public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }
    
    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    //returns Sentinel node of tree. Not the actual root.
    private BSTree Root(){
    	if (this.parent==null){
    		return this;
    	}else{
    		BSTree current=this;
    		while (current.parent!=null){
    			current=current.parent;
    		}
    		return current;
    	}
    }

    public BSTree Insert(int address, int size, int key)
    {
    	BSTree current=this.Root();
    	BSTree new_node=new BSTree(address, size, key);
    	if (current.right==null){
    		current.right=new_node;
            new_node.parent=current;
            new_node.left=null;
            new_node.right=null;
    		return new_node;
    	}
    	else{
            current=current.right;
            BSTree par=current.parent;
	    	while (current!=null){
	    		if (current.key<key){
                    par=current;
                    current=current.right;
                }	
                else if (current.key==key){
                    if(current.address<address){
                        par=current;
                        current=current.right;
                    }
                    else{
                        par=current;
                        current=current.left;
                    }
                }   
	    		else{
                    par=current;
	    			current=current.left;
                }
	    	}
            new_node.parent=par;
            new_node.left=null;
            new_node.right=null;
            if (par.right==null && par.key<=key)
                par.right=new_node;
            else
                par.left=new_node;
	    	return new_node;
	    }
        // return null;
    }

    public boolean Delete(Dictionary e)
    {
    	if (this.Find(e.key, true)==null || e.key==-1){
    		return false;
    	}
    	else{
    		BSTree current=this.getFirst();
            if (current==null) return false;
    		while(current.key<=e.key){
        		if(current.key==e.key){
        			if (current.address==e.address && current.size==e.size){
        				if (current.left==null && current.right==null){
                            if (current.parent.left==current){
                                current.parent.left=null;
                            }else{
                                current.parent.right=null;
                            }
        					return true;
        				}
        				else if (current.left==null || current.right==null){
        					if (current.parent.left==current){
        						if (current.left!=null){
        							current.parent.left=current.left;
                                    current.left.parent=current.parent;
        							return true;
        						}
        						else{
        							current.parent.left=current.right;
                                    current.right.parent=current.parent;
        							return true;
        						}
        							
        					}else{
    							if (current.left!=null){
    								current.parent.right=current.left;
                                    current.left.parent=current.parent;
        							return true;
    							}
    							else{
    								current.parent.right=current.right;
                                    current.right.parent=current.parent;
        							return true;
    							}	
        					}
        				}
        				else{
        					BSTree succ=current.getNext();
        					current.address=succ.address;
    	    				current.size=succ.size;
    	    				current.key=succ.key;
        					if (succ.left==null && succ.right==null){
        						if (succ.parent.left==succ){
                                    succ.parent.left=null;
                                }else{
                                    succ.parent.right=null;
                                }
    	    				}
    	    				else{
    	    					if (succ.parent.left==succ){
    	    						if (succ.left!=null){
    	    							succ.parent.left=succ.left;
                                        succ.left.parent=succ.parent;
                                    }
    	    						else{
    	    							succ.parent.left=succ.right;
                                        succ.right.parent=succ.parent;
                                    }
    	    					}else{
    	    						if (succ.left!=null){
    	    							succ.parent.right=succ.left;
                                        succ.left.parent=succ.parent;
                                    }
    	    						else{
    	    							succ.parent.right=succ.right;
                                        succ.right.parent=succ.parent;
                                    }
                                    
    	    					}
    	    				}
    	    				return true;
        				}
        			}
        		}
                current=current.getNext();
            }
    	}
        return false;
    }
        
    public BSTree Find(int key, boolean exact)
    {
        if (key==-1)
            return this.Root();
    	BSTree current=this.getFirst();
    	if (current==null)
    		return null;
    	if (exact==true){
    		while (current!=null && current.key!=key){
    				current = current.getNext();
    		}
    		return current;
    	}else{
    		while (current!=null && current.key<key){
    			current=current.getNext();
    		}
    		return current;
    	}
    }

    public BSTree getFirst()
    { 
    	BSTree current=this.Root();
    	if (current.right==null)
    		return null;
    	else
            current=current.right;
    		while (current.left!=null){
    			current=current.left;
    		}
    		return current;
    }

    public BSTree getNext()
    {
        if (this.parent==null){
            return null;
        }
        if(this.Root().right==null){
            return null;
        }
    	if (this.right!=null){
    		BSTree next=this.right;
    		while (next.left!=null){
    			next=next.left;
    		}
    		return next;
    	}else{
    		BSTree next=this.parent;
    		BSTree curr=this;
    		while (next!=null && curr==next.right){
    			curr=next;
    			next=next.parent;
    		}
            if (curr==this.Root())
    		  return null;
            else
                return next;
    	}
    }
    

    Vector<Integer> v = new Vector<Integer>();
    private Vector<Integer> inorder(BSTree root) {
        if (root==null){
            return null;
        }
        else{
            inorder(root.left);
            v.add(root.key);
            inorder(root.right);
        }
        return v;
    }
    public boolean sanity()
    {
        Boolean ans=true;
        if (this.Root().right==null){
            return true;
        }
        inorder(this.Root().right);
        for(int i = 0 ; i < v.size()- 2; i++ ){
            if(v.get(i) > v.get(i+1)){
               ans=false;
               break;
           }               
        }
        return ans;
        
    }

}


 


