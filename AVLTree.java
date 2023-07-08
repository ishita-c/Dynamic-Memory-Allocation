// Class: Height balanced AVL Tree
// Binary Search Tree

//import java.util for sanity
import java.util.*;
public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.


    //return the sentinel node. Not actual root
    private AVLTree Root(){
        if (this.parent==null){
            return this;
        }else{
            AVLTree current=this;
            while (current.parent!=null){
                current=current.parent;
            }
            return current;
        }
    }
    
    private void rightRotate( AVLTree node){
        AVLTree b = node.left;
		node.left = b.right;
		if(b.right != null) {
			b.right.parent = node;
		}
		b.parent = node.parent;
		if(node.parent == null) {
			this.Root().right = b;
		}
		else if(node.parent.left == node){
			node.parent.left = b;
		}
		else {
			node.parent.right = b;
		}
		b.right = node;
		node.parent = b;

		b.height = 1 + max(getHeight(b.left), getHeight(b.right));
		node.height = 1 + max(getHeight(node.left), getHeight(node.right));
    }


    private void leftRotate( AVLTree node){
    	AVLTree b = node.right;
  		node.right = b.left;
  		if(b.left != null) {
   			b.left.parent = node;
  		}
  		b.parent = node.parent;
  		if(node.parent == null) {
    		this.Root().right = b;
  		}
  		else if(node.parent.right == node){ 
    		node.parent.right = b;
  		}
  		else{
    		node.parent.left = b;
  		}
  		b.left = node;
  		node.parent = b;
  		
  		b.height = 1 + max(getHeight(b.left), getHeight(b.right));
  		node.height = 1 + max(getHeight(node.left), getHeight(node.right));
    }
    private int balfac(AVLTree node){
    	if (node==null) return 0;
    	return getHeight(node.left)-getHeight(node.right);
    }

    private int max(int a, int b){
	  if(a>=b)
	    return a;
	  return b;
	}

    private int getHeight(AVLTree node){
        if (node==null) return 0;
        return node.height;
    }

    public AVLTree Insert(int address, int size, int key) 
    {
    	AVLTree new_node= new AVLTree(address, size, key);
		AVLTree par = this.Root();
		AVLTree current = this.Root().right;
		while(current != null) {
			par = current;
		    if(key < current.key)
		    	current=current.left;
		    else if(key==current.key)
		    	if(address>current.address)
		    		current=current.right;
		    	else
		    		current=current.left;
		    else
		    	current=current.right;
		}
		new_node.parent = par;
		if(par == this.Root())
			this.Root().right = new_node;
		else if(key < par.key)
			par.left = new_node;
		else if (key==par.key)
			if (address<par.address)
				par.left=new_node;
			else
				par.right=new_node;
		else
		    par.right = new_node;
		new_node.height=1;

		while(par.key != -1) {
			AVLTree gpar = par.parent;
		    par.height = 1 + max(getHeight(par.left), getHeight(par.right));

		    if(gpar.key!=-1 && (balfac(gpar) <= -2 || balfac(gpar) >= 2)) {
		      	if(par == gpar.left) {
		        	if(new_node == gpar.left.left)
		          		rightRotate(gpar);
		        	else{
		          		leftRotate(par);
		          		rightRotate(gpar);
		        	}
		    	}
		      	else{
		        	if(new_node == gpar.right.right)
		          		leftRotate(gpar);

		        	else{
		          		rightRotate(par);
		          		leftRotate(gpar);
		        	}
		      	}
		    }
		    new_node=par;
		    par = par.parent;
		}
		return new_node;
    }
    private void updateHeight(AVLTree node){
    	while(node.key!=-1){
    		node.height=1+max(getHeight(node.left),getHeight(node.right));
    		node=node.parent;
    	}
    }

    public boolean Delete(Dictionary e)
    {
    	if (this.Find(e.key, true)==null || e.key==-1){
    		return false;
    	}
    	else{
    		AVLTree current=this.getFirst();
            if (current==null) return false;
    		while(current.key<=e.key){
    			if (current.key==e.key && current.address==e.address && current.size==e.size){
    				if (current.left==null && current.right==null){
    					if (current.parent.left==current){
                            current.parent.left=null;
                            updateHeight(current.parent);
                        }else{
                            current.parent.right=null;
                            updateHeight(current.parent);
                        }
    				}
    				else if(current.left==null || current.right==null){
    					if(current.left==null){
    						if (current.parent.left==current){
    							current.parent.left=current.right;
    							current.right.parent=current.parent;
    							updateHeight(current.right);
    						}
    						else{
    							current.parent.right=current.right;
    							current.right.parent=current.parent;
    							updateHeight(current.right);
    						}
    					}
    					else{
    						if(current.parent.left==current){
    							current.parent.left=current.left;
    							current.left.parent=current.parent;
    							updateHeight(current.left);
    						}else{
    							current.parent.right=current.left;
    							current.left.parent=current.parent;
    							updateHeight(current.left);
    						}
    					}
    				}
    				else{
    					AVLTree succ=current.getNext();
        				current.address=succ.address;
    	    			current.size=succ.size;
    	    			current.key=succ.key;
    	    			if (succ.left==null && succ.right==null){
        					if (succ.parent.left==succ){
                                succ.parent.left=null;
                                updateHeight(succ.parent);
                            }else{
                                succ.parent.right=null;
                                updateHeight(succ.parent);
                            }
    	    			}
    	    			else{
    						if (succ.parent.left==succ){
    	    					if (succ.left!=null){
    	    						succ.parent.left=succ.left;
                                    succ.left.parent=succ.parent;
                                    updateHeight(succ.left);
                                }
    	    					else{
    	    						succ.parent.left=succ.right;
                                    succ.right.parent=succ.parent;
                                    updateHeight(succ.right);
                                }
  	    					}else{
   	    						if (succ.left!=null){
   	    							succ.parent.right=succ.left;
                                    succ.left.parent=succ.parent;
                                    updateHeight(succ.left);
                                }
    	    					else{
    	    						succ.parent.right=succ.right;
                                    succ.right.parent=succ.parent;
                                    updateHeight(succ.right);
                                }
                                    
    	    				}
    	    			}
    				}

    				AVLTree d=current.getFirst();
    				while (d.getNext()!=null){
    					int balanceFactor=balfac(d);
   						Balance(balanceFactor, d);
    					d=d.getNext();
    				}

    				return true;
    			}
    			current=current.getNext();
    		}
    	}
    	return false;
    }
    private void Balance(int balanceFactor, AVLTree node){
    	if (balanceFactor > 1) {
      		if (balfac(node.left) >= 0) {
      			rightRotate(node);
			}else {
				leftRotate(node.left);
				rightRotate(node);
			}
		}
		if (balanceFactor < -1) {
			if (balfac(node.right) <= 0) {
				leftRotate(node);
			}else {
				rightRotate(node.right);
				leftRotate(node);
			}
		}
    }
        
    public AVLTree Find(int k, boolean exact)
    {
        if (key==-1)
            return this.Root();
        AVLTree current=this.Root().right;
        if (current==null)
            return null;
        if (exact==true){
            while (current!=null && current.key!=k){
                if(current.key<k) current=current.right;
                else current=current.left;
            }
            return current;
        }else{
            current=this.getFirst();
            while (current!=null && current.key<k){
                current=current.getNext();
            }
            return current;
        }
    }

    public AVLTree getFirst()
    {
        AVLTree current=this.Root();
        if (current.right==null)
            return null;
        else
            current=current.right;
            while (current.left!=null){
                current=current.left;
            }
            return current;
    }

    public AVLTree getNext()
    {
        if (this.parent==null){
            return null;
        }
        if(this.Root().right==null){
            return null;
        }
        if (this.right!=null){
            AVLTree next=this.right;
            while (next.left!=null){
                next=next.left;
            }
            return next;
        }else{
            AVLTree next=this.parent;
            AVLTree curr=this;
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
    private Vector<Integer> inorder(AVLTree root) {
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
    	AVLTree root=this.Root().right;
    	if (root==null) return true;
    	if(balfac(root)>1 || balfac(root)<-1) return false;
    	if (root.height!=getHeight(root)) return false;
        Boolean ans=true;
        inorder(root);
        for(int i = 0 ; i < v.size()- 2; i++ ){
            if(v.get(i) > v.get(i+1)){
               ans=false;
               break;
           }               
        }
        return ans;
    }


    // to test working of delete and insert,working correctly

//     public static void main(String[] args){
//     	AVLTree t=new AVLTree();
//     	AVLTree a, b, c, d, e, f, g, h, i, j, k, l, m;
// 		a = new AVLTree(1,2,10);
// 		b = new AVLTree(2,3,20);
// 		c = new AVLTree(8,3,30);
// 		d = new AVLTree(5,4,43);
// 		e = new AVLTree(6,6,90);
// 		f = new AVLTree(7,7,40);
// 		g = new AVLTree(8,5,50);
// 		h = new AVLTree(13,67,60);
// 		i = new AVLTree(12,56,70);
// 		j = new AVLTree(10,22,80);
// 		k = new AVLTree(9,5,100);
// 		l = new AVLTree(11,23,110);
// 		m = new AVLTree(3,3,30);
// 		t.Insert(6,6,90);
// 		t.Insert(12,56,70);
// 		t.Insert(9,5,100);
// 		t.Insert(2,3,20);
// 		t.Insert(13,67,60);
// 		t.Insert(7,7,40);
// 		t.Insert(11,23,110);
// 		t.Insert(1,2,10);
// 		t.Insert(8,5,50);
// 		t.Insert(3,3,30);
// 		t.Insert(8,3,30);
// 		t.Insert(10,22,80);
// 		t.Insert(5,4,43);
// 		// System.out.println(t.Root().right.key);
// 		// System.out.println(t.Root().right.right.key);
// 		// System.out.println(t.Root().right.left.key);
// 		// System.out.println(t.Root().right.left.address);
// 		// System.out.println(t.Root().right.right.right.key);
// 		// System.out.println(t.Root().right.right.right.right.key);
// 		// System.out.println(t.Root().right.right.left.key);
// 		// System.out.println(t.Root().right.right.left.right.key);
// 		// System.out.println(t.Root().right.left.right.key);
// 		// System.out.println(t.Root().right.left.right.right.key);
// 		// System.out.println(t.Root().right.left.right.right.left.key);
// 		// System.out.println(t.Root().right.left.left.key);
// 		// System.out.println(t.Root().right.left.left.left.key);
// 		// System.out.println(t.Root().right.left.right.left.key);
// 		// System.out.println(t.Root().right.left.right.left.address);
// 		t.Delete(h);
// 		t.Delete(c);
// 		t.Delete(e);
// 		t.Delete(j);
// 		t.Delete(k);
// 		System.out.println(t.Root().right.key);
// 		System.out.println(t.Root().right.right.key);
// 		System.out.println(t.Root().right.left.key);
// 		System.out.println(t.Root().right.right.right.key);
// 		System.out.println(t.Root().right.left.left.key);
// 		System.out.println(t.Root().right.right.left.key);
// 		System.out.println(t.Root().right.right.left.right.key);
// 		System.out.println(t.Root().right.right.left.left.key);
//     }
}