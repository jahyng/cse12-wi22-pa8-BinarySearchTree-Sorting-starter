/**
 * Name: Josh Yang
 * PID: A16667394
 * Email: jwyag@ucsd.edu
 * Sources: None
 * 
 * This file contains MyBSTIterator class. 
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class creates an iterator for the MyBST. It has instance variables next,
 * the next node in the BST and lastVisited, the node that was previously the 
 * "current" node. 
 */
public class MyBSTIterator<K extends Comparable<K>, V> extends MyBST<K, V> {
    abstract class MyBSTNodeIterator<T> implements Iterator<T> {
        MyBSTNode<K, V> next;
        MyBSTNode<K, V> lastVisited;

        /**
         * Constructor that initializes the node iterator
         *
         * @param first The initial node that next points
         */
        MyBSTNodeIterator(MyBSTNode<K, V> first) {
            next = first;
            lastVisited = null;
        }

        /**
         * This method is used for determining if the next pointer in the
         * iterator points to null.
         *
         * @return If next is null based on the current position of iterator
         */
        public boolean hasNext() {
            return next != null;
        }

        /**
         * 
         */
        MyBSTNode<K, V> nextNode() {
            if (next == null) throw new NoSuchElementException();
            
            if (lastVisited != null) {
            lastVisited = next;
            next = next.successor();
            return next;
            } 
            else{
            lastVisited = next;
            next = next.successor();
            return lastVisited;
            }
            
        }

        /**
         *
         * This method removes the last visited node from the tree.
         */
        public void remove() {
            // lastVisited is already null
            if (lastVisited == null) {
                throw new IllegalStateException();
            }
            // lastVisisted is a leaf node
            if (lastVisited.getRight() != null &&
                    lastVisited.getLeft() != null) {
                next = lastVisited;
            }
            // otherwise remove the lastVisisted node
            MyBSTIterator.this.remove(lastVisited.getKey());
            lastVisited = null;
        }
    }

    /**
     * BST Key iterator class that extends the node iterator.
     */
    class MyBSTKeyIterator extends MyBSTNodeIterator<K> {

        MyBSTKeyIterator(MyBSTNode<K, V> first) {
            super(first);
        }

        /**
         * This method advance the iterator and returns a node key
         *
         * @return K the next key
         */
        public K next() {
            return super.nextNode().getKey();
        }
    }

    /**
     * BST value iterator class that extends the node iterator.
     */
    class MyBSTValueIterator extends MyBSTNodeIterator<V> {

        /**
         * Call the constructor method from node iterator
         *
         * @param first The initial value that next points
         */
        MyBSTValueIterator(MyBSTNode<K, V> first) {
            super(first);
        }

        /**
         * This method advance the iterator and returns a node value
         *
         * @return V the next value
         */
        public V next() {
            return super.nextNode().getValue();
        }
    }

    /**
     * This method is used to obtain an iterator that iterates through the
     * value of BST.
     *
     * @return The value iterator of BST.
     */
    public MyBSTKeyIterator getKeyIterator() {
        MyBSTNode<K, V> curr = root;
        if (curr != null) {
            while (curr.getLeft() != null) {
                curr = curr.getLeft();
            }
        }
        return new MyBSTKeyIterator(curr);
    }

    /**
     * This method is used to obtain an iterator that iterates through the
     * value of BST.
     *
     * @return The value iterator of BST.
     */
    public MyBSTValueIterator getValueIterator() {
        MyBSTNode<K, V> curr = root;
        if (curr != null) {
            while (curr.getLeft() != null) {
                curr = curr.getLeft();
            }
        }
        return new MyBSTValueIterator(curr);
    }
}