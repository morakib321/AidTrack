
package AidTrack;

public abstract class AbstractTree<Incident> implements Tree<Incident> {
    
 @Override /** Reverse Inorder traversal from the root*/
 public void reverseInorder() {
 }

 @Override /** Postorder traversal from the root */
 public void postorder() {
 }

 @Override /** Preorder traversal from the root */
 public void preorder() {
 }

 @Override /** Return true if the tree is empty */
 public boolean isEmpty() {
   return getSize() == 0;
 }
}

