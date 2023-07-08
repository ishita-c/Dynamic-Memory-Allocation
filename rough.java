// if (current.key==e.key && current.address==e.size && current.size==e.size){
//                     if(current.left == null) {
//                         replace(current, current.right);
//                         if(current.right != null){
//                             deleteBalance(current.right);
//                         }
//                         else{
//                             replace(current, current.left);
//                             if(current.left != null)
//                                 deleteBalance(current.left);
//                         }
//                     }
//                     else {
//                         AVLTree y = current.right; 
//                         while(y.left!=null){
//                             y=y.left;
//                         }
//                         if(y.parent != current) {
//                             replace(y, y.right);
//                             y.right = current.right;
//                             y.right.parent = y;
//                         }
//                         replace(current, y);
//                         y.left = current.left;
//                         y.left.parent = y;
//                         if(y != null)
//                             deleteBalance(y);
//                     }
//                     return true;
//                 }

//  private void deleteBalance(AVLTree node){
//         while(node != null) {
//             node.height = 1 + max(getHeight(node.left), getHeight(node.right));
//             if(balfac(node) <= -2 || balfac(node) >= 2) {
//                 AVLTree x, y, z;
//                 x=node;
//                 if(x.left.height > x.right.height)
//                     y = x.left;
//                 else
//                     y = x.right;
//                 if(y.left.height > y.right.height) {
//                     z = y.left;
//                 }
//                 else if(y.left.height < y.right.height) {
//                     z = y.right;
//                 }
//                 else { 
//                     if(y == x.left)
//                         z = y.left;
//                     else
//                         z = y.right;
//                 }

//                 if(y == x.left){
//                     if(z == x.left.left)
//                         rightRotate(x);

//                     else{
//                         leftRotate(y);
//                         rightRotate(x);
//                     }
//                 }
//                 else{
//                     if(z == x.right.right)
//                         leftRotate(x);

//                     else{
//                         rightRotate(y);
//                         leftRotate(x);
//                     }
//                  }
//             }
//             node=node.parent;
//         }
//     }

// private void replace(AVLTree node1, AVLTree node2){
//         if(node1.parent == null) this.Root().right = node2;
//         else if(node1.parent.left == node1) node1.parent.left = node2;
//         else node1.parent.right = node2;
//         if(node2!= null) node2.parent = node1.parent;
//     }


    
//   