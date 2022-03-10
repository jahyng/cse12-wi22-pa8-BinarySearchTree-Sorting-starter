import java.util.ArrayList;



public class MyBST<K extends Comparable<K>,V>{
    MyBSTNode<K,V> root = null;
    int size = 0;

    public int size(){
        return size;
    }

    /**
     * This method inserts a node containing the key and value given into BST.
     * @param key Key of new node
     * @param value value of new node
     * @return the value of the node that was replaced or null if a new node is
     * created
     */
    public V insert(K key, V value){
        // key is null
        if (key.equals(null)) throw new NullPointerException();
        if (this.root == null) {
            this.root = new MyBSTNode<K,V>(key, value, null);
        }
        MyBSTNode<K,V> curr = this.root;
        K currKey = this.root.getKey();
        V currValue = this.root.getValue();
        while (curr != null) {
            if (currKey.compareTo(key) == 0){
                V temp = currValue;
                curr.setValue(value);
                return temp;
            }
            else if(key.compareTo(currKey) > 0) {
                if (curr.getRight() != null) {
                    curr = curr.getRight();
                    currValue = curr.getValue();
                    currKey = curr.getKey();
                }
                else {
                    curr.setRight(new MyBSTNode<K,V>(key, value, curr));
                    this.size++;
                    return null;
                }
            }
            else {
                if (curr.getLeft() != null) {
                    curr = curr.getLeft();
                    currValue = curr.getValue();
                    currKey = curr.getKey();
                }
                else {
                    curr.setLeft(new MyBSTNode<K,V>(key, value, curr));
                    this.size++;
                    return null;
                }
            }

        }  
        return null;
    }

    /**
     * This method looks for the node with the given key
     * @param key key to look for
     * @return the value of the node with the given key or null if there is no
     * node. 
     */
    public V search(K key){
        // always return null if key is null
        if (key == null) return null;

        MyBSTNode<K,V> curr = this.root;
        V currValue = this.root.getValue();
        K currKey= this.root.getKey();
        
        while(curr != null){
            if (key.compareTo(currKey) == 0){
                return currValue;   // node found!
            }
            // key is smaller, go to left child
            else if (key.compareTo(currKey) < 0){
                curr = curr.getLeft(); 
                if (curr != null) {
                    currValue = curr.getValue();
                    currKey = curr.getKey();
                }
            }
            // key is greater, go to right child
            else{
                curr = curr.getRight();
                if (curr != null) {
                    currValue = curr.getValue();
                    currKey = curr.getKey();
                }
            }
        }
        return null; // node not found
    }

    /**
     * This method removes the node with given key.
     * @param key key that determines which node will be removed
     * @return value of removed node
     */
    public V remove(K key){
        // if key is null, always return null 
        if (key == null) return null;

        MyBSTNode<K,V> curr = this.root;
        K currKey = this.root.getKey();
        // V currValue = this.root.getValue();

        // find the node with key and value
        while (curr != null) {
            if (currKey.compareTo(key) == 0){
                break; // curr is the node we need to delete
            }
            else if(key.compareTo(currKey) > 0) { 
                if (curr.getRight() != null) {
                    curr = curr.getRight();
                    currKey = curr.getKey();
                }
                else return null;
            }
            else {
                if (curr.getLeft() != null) {
                    curr = curr.getLeft();
                    currKey = curr.getKey();
                }
                else return null;
            }

        }

        if(curr == this.root) {
            V val = this.root.getValue();
            if (curr.getRight() == null && curr.getLeft() != null){
                curr.getLeft().setParent(null);
                this.root = curr.getLeft();
            }
            else if (curr.getRight() != null && curr.getLeft() == null){
                curr.getRight().setParent(this.root.getParent());
                this.root = curr.getRight();
            } else if (curr.getRight() != null && curr.getLeft() != null){
                K successorKey = curr.successor().getKey();
                curr.setKey(curr.successor().getKey());
                curr.setValue(curr.successor().getValue());
                this.remove(successorKey);
            }
            this.size--;
            return val;

        }
        // has no children
        if (curr.getRight() == null && curr.getLeft() == null){
            V val = curr.getValue();
            if (curr.getParent().getLeft() == curr) {
                curr.getParent().setLeft(null);
            }
            else{
                curr.getParent().setRight(null);
            }
            curr.setParent(null);
            curr = null;
            this.size--;
            return val;
        } 

        // only left child
        else if (curr.getRight() == null && curr.getLeft() != null){
            V val = curr.getValue();
            curr.getLeft().setParent(curr.getParent());
            // node is not the root
                if (curr == curr.getParent().getLeft()) {
                    curr.getParent().setLeft(curr.getLeft());
                }
                else{
                    curr.getParent().setRight(curr.getLeft());
                }
                
            curr.setParent(null);
            curr.setLeft(null);
            this.size--;
            return val;            
        }

        // only right child
        else if (curr.getRight() != null && curr.getLeft() == null){
            V val = curr.getValue();
            curr.getRight().setParent(curr.getParent());

            // node is not the root
            if (curr.getParent() != null){
                if (curr == curr.getParent().getLeft()){
                    curr.getParent().setLeft(curr.getRight());
                }
                else{
                    curr.getParent().setRight(curr.getRight());
                }
            }

            // base case when node is the root
            curr.setParent(null);
            curr.setRight(null);
            this.size--;
            return val;
        }

        // has two children
        else{
            V val = curr.getValue();
            K successorKey = curr.successor().getKey();
            curr.setKey(curr.successor().getKey());
            curr.setValue(curr.successor().getValue());
            this.remove(successorKey);
            this.size--;
            return val;
        }
    }
    
    /**
     * 
     * @return
     */
    public ArrayList<MyBSTNode<K, V>> inorder(){
        MyBSTNode<K,V> curr = this.root;
        ArrayList<MyBSTNode<K,V>> list = new ArrayList<MyBSTNode<K,V>>();

        // go to the left most node
        if (this.root.getLeft() != null){
            while (curr.getLeft() != null) {
                curr = curr.getLeft();
            }
        }
        // starting at left most node, add the successor
        for (int i = 0; i < this.size(); i++){
            list.add(curr);            
            curr = curr.successor();
        }

        return list;
    }

    static class MyBSTNode<K,V>{
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K,V> parent;
        private MyBSTNode<K,V> left = null;
        private MyBSTNode<K,V> right = null;

        /**
         * Creates a MyBSTNode<K,V> storing specified data
         * @param key the key the MyBSTNode<K,V> will
         * @param value the data the MyBSTNode<K,V> will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent){
            this.key = key;
            this.value = value;
            this.parent = parent; 
        }

        /**
         * Return the key stored in the the MyBSTNode<K,V>
         * @return the key stored in the MyBSTNode<K,V>
         */
        public K getKey(){
            return key;
        }

        /**
         * Return data stored in the MyBSTNode<K,V>
         * @return the data stored in the MyBSTNode<K,V>
         */
        public V getValue(){
            return value;
        }

        /**
         * Return the parent
         * @return the parent
         */
        public MyBSTNode<K,V> getParent(){
            return parent;
        }

        /**
         * Return the left child 
         * @return left child
         */
        public MyBSTNode<K,V> getLeft(){
            return left;
        }

        /**
         * Return the right child 
         * @return right child
         */
        public MyBSTNode<K,V> getRight(){
            return right;
        }

        /**
         * Set the key stored in the MyBSTNode<K,V>
         * @param newKey the key to be stored
         */
        public void setKey(K newKey){
            this.key = newKey;
        }

        /**
         * Set the data stored in the MyBSTNode<K,V>
         * @param newValue the data to be stored
         */
        public void setValue(V newValue){
            this.value = newValue;
        }

        /**
         * Set the parent
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K,V> newParent){
            this.parent = newParent;
        }

        /**
         * Set the left child
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K,V> newLeft){
            this.left = newLeft;
        }

        /**
         * Set the right child
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K,V> newRight){
            this.right = newRight;
        }

        /**
         *
         * This method returns the in order successor of current node object.
         * It can be served as a helper method when implementing inorder().
         * @return the successor of current node object
         */
        public MyBSTNode<K, V> successor(){
            if(this.getRight() != null){
                // set curr to right child
                MyBSTNode<K,V> curr = this.getRight();
                // go to left most node
                while(curr.getLeft() != null){
                    curr = curr.getLeft();
                }
                return curr;
            }
            // there is no right child
            else{
                // get the parent node and current node
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                // get the parent node as long as this node is a right child
                // this will give the node with the smallest key
                while(parent != null && curr == parent.getRight()){
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        /**
         * This method returns the in order predecessor of currnet node object.
         * It can be served as a helpeer method when implementing inorder().
         * @return the predecessor of current node object
         */
        public MyBSTNode<K, V> predecessor(){
            if (this.getLeft() != null) {
                MyBSTNode<K,V> curr = this.getLeft();
                // go to the right most node
                while (curr.getRight() != null) {
                    curr = curr.getRight();
                }
                return curr;
            }
            // no left child
            else {
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                // get the parent node as long as this node is a left child
                // this will give the node with the largest node
                while(parent != null && curr == parent.getLeft()) {
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        /** This method compares if two node objects are equal.
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj){
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K,V> comp = (MyBSTNode<K,V>)obj;
            
            return( (this.getKey() == null ? comp.getKey() == null : 
                this.getKey().equals(comp.getKey())) 
                && (this.getValue() == null ? comp.getValue() == null : 
                this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         * @return "Key:Value" that represents the node object
         */
        public String toString(){
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}