/**
 * Name: Michael Brown
 * Email: mibrown@ucsd.edu
 * PID: A17037478
 * Sources Used: JDK 17 Docs
 *
 */

import java.util.ArrayList;


public class MyBST<K extends Comparable<K>, V> {
    MyBSTNode<K, V> root = null;
    int size = 0;

    public int size() {
        return size;
    }

    /* This insert method works without using recursion and goes through
     * all cases for the majority of the method by utilzing a while loop
     */

    public V insert(K key, V value) {
        if(key == null){
            throw new NullPointerException();
        }
        MyBSTNode<K,V> currentNode = root;
        if(root == null){
            root = new MyBSTNode<K,V>(key, value,null);
            size++;
            return null;
        }
        while(true){
            if(key.compareTo(currentNode.getKey()) < 0){
                if(currentNode.getLeft() != null){
                    currentNode = currentNode.getLeft();
                }
                else{
                    currentNode.setLeft(new MyBSTNode<K,V>(key, value, currentNode));
                    size++;
                    return null;
                }
            }
            else if(key.compareTo(currentNode.getKey()) > 0){
                if(currentNode.getRight() != null){
                    currentNode = currentNode.getRight();
                }
                else{
                    currentNode.setRight(new MyBSTNode<K,V>(key, value, currentNode));
                    size++;
                    return null;
                }
            }
            else{
                V prevNode = currentNode.getValue();
                currentNode.setValue(value);
                return prevNode;
            }
        }
    }

    /* My search method utilizes the searchHelper method that I have
     * created below. This can be seen to simply utilize recursion and 
     * keep parsing until the search can complete
     */

    public V search(K key) {
        MyBSTNode<K,V> currentNode = root;
        if(key == null){
            return null;
        }
        return searchHelper(currentNode, key);
    }

    private V searchHelper(MyBSTNode<K,V> currentNode, K key){
        if(currentNode == null){
            return null;
        }
        if(currentNode.getKey() == key){
            return currentNode.getValue();
        }
        if(key.compareTo(currentNode.getKey()) > 0){
            return searchHelper(currentNode.getRight(), key);
        }
        else{
            return searchHelper(currentNode.getLeft(), key);
        }
    }

    /* My remove method utilizes the removeHelper method that
     * I created which utilizes recursion. It functions by mainly 
     * making checks until the while loop can be utilized and adjusts the
     * data, key, and nodes accordingly after removal.
     */

    public V remove(K key) {
        MyBSTNode<K,V> currentNode = root;
        if(key == null){
            return null;
        }

        MyBSTNode<K,V> removeNode = removeHelper(currentNode,key);
        V ndata = removeNode.getValue();

        MyBSTNode<K,V> pNode = removeNode.getParent();
        MyBSTNode<K,V> succNode = removeNode.successor();

        if(pNode == null){
            if(removeNode.getLeft() != null && removeNode.getRight() == null){
                removeNode.setKey(removeNode.getLeft().getKey());
                removeNode.setValue(removeNode.getLeft().getValue());
                size--;
            }
            else if(removeNode.getLeft() == null && removeNode.getRight() != null){
                removeNode.setKey(removeNode.getRight().getKey());
                removeNode.setValue(removeNode.getRight().getValue());
                size--;
            }
            else if(removeNode.getRight() == null && removeNode.getLeft() == null){
                size--;
                return ndata;
            }
            else {
                remove(succNode.getKey());
                removeNode.setKey(succNode.getKey());
                removeNode.setValue(succNode.getValue());
            }
        }

        else if(removeNode.getKey().compareTo(pNode.getKey()) < 0){
            if(removeNode.getLeft() != null && removeNode.getRight() == null){
                pNode.setLeft(removeNode.getLeft());
                removeNode.getLeft().setParent(pNode);
                size--;
            }
            else if(removeNode.getLeft() == null && removeNode.getRight() != null){
                pNode.setLeft(removeNode.getRight());
                removeNode.getRight().setParent(pNode);
                size--;
            }
            else if(removeNode.getRight() == null && removeNode.getLeft() == null){
                pNode.setLeft(null);
                removeNode.setParent(null);
                size--;
                return ndata;
            }
            else{
                remove(succNode.getKey());
                removeNode.setKey(succNode.getKey());
                removeNode.setValue(succNode.getValue());
            }
        }
        else if(removeNode.getKey().compareTo(pNode.getKey()) > 0){
            if(removeNode.getLeft() != null && removeNode.getRight() == null){
                pNode.setRight(removeNode.getLeft());
                removeNode.getLeft().setParent(pNode);
                size--;
            }
            else if(removeNode.getLeft() == null && removeNode.getRight() != null){
                pNode.setRight(removeNode.getRight());
                removeNode.getRight().setParent(pNode);
                size--;
            }
            else if(removeNode.getRight() == null && removeNode.getLeft() == null){
                pNode.setRight(null);
                removeNode.setParent(null);
                size--;
                return ndata;
            }
            else{
                remove(succNode.getKey());
                removeNode.setKey(succNode.getKey());
                removeNode.setValue(succNode.getValue());
            }
        }
        return ndata;
    }

    private MyBSTNode<K,V> removeHelper(MyBSTNode<K,V> currentNode, K key){
        if(currentNode == null){
            return currentNode;
        }
        if(currentNode.getKey().compareTo(key) == 0){
            return currentNode;
        }
        if(key.compareTo(currentNode.getKey()) > 0){
            return removeHelper(currentNode.getRight(), key);
        }
        else{
            return removeHelper(currentNode.getLeft(), key);
        }
    }

    /* My inorder method functions through the helper method I have 
     * created below. This helper method uses recursion in order to place
     * the BST nodes in order in an array.
     */

    public ArrayList<MyBSTNode<K, V>> inorder() {
        MyBSTNode<K,V> currentNode = root;
        ArrayList<MyBSTNode<K, V>> arrayList = new ArrayList<>();
        inorderHelper(currentNode, arrayList);
        return arrayList;
    }

    private void inorderHelper(MyBSTNode<K,V> currentNode, ArrayList<MyBSTNode<K, V>> arrayList){
        if(currentNode == null){
            return;
        }
        inorderHelper(currentNode.getLeft(), arrayList);
        arrayList.add(currentNode);
        inorderHelper(currentNode.getRight(), arrayList);
    }

    static class MyBSTNode<K, V> {
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K, V> parent;
        private MyBSTNode<K, V> left = null;
        private MyBSTNode<K, V> right = null;

        /**
         * Creates a MyBSTNode storing specified data
         *
         * @param key    the key the MyBSTNode will store
         * @param value  the data the MyBSTNode will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Return the key stored in the the MyBSTNode
         *
         * @return the key stored in the MyBSTNode
         */
        public K getKey() {
            return key;
        }

        /**
         * Set the key stored in the MyBSTNode
         *
         * @param newKey the key to be stored
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Return data stored in the MyBSTNode
         *
         * @return the data stored in the MyBSTNode
         */
        public V getValue() {
            return value;
        }

        /**
         * Set the data stored in the MyBSTNode
         *
         * @param newValue the data to be stored
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        /**
         * Return the parent
         *
         * @return the parent
         */
        public MyBSTNode<K, V> getParent() {
            return parent;
        }

        /**
         * Set the parent
         *
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K, V> newParent) {
            this.parent = newParent;
        }

        /**
         * Return the left child
         *
         * @return left child
         */
        public MyBSTNode<K, V> getLeft() {
            return left;
        }

        /**
         * Set the left child
         *
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K, V> newLeft) {
            this.left = newLeft;
        }

        /**
         * Return the right child
         *
         * @return right child
         */
        public MyBSTNode<K, V> getRight() {
            return right;
        }

        /**
         * Set the right child
         *
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K, V> newRight) {
            this.right = newRight;
        }

        public MyBSTNode<K, V> successor() {
            if(this.getRight() != null){
                MyBSTNode<K,V> currentNode = this.getRight();
                while(currentNode.getLeft() != null){
                    currentNode = currentNode.getLeft();
                }
                return currentNode;
            }
            else{
                MyBSTNode<K,V> currentNode = this;
                MyBSTNode<K,V> parentNode = getParent();
                while(parentNode != null && currentNode == parentNode.getRight()){
                    currentNode = parentNode;
                    parentNode = parentNode.getParent();
                }
                return parentNode;
            }
        }

        /**
         * This method compares if two node objects are equal.
         *
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj) {
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K, V> comp = (MyBSTNode<K, V>) obj;

            return ((this.getKey() == null ? comp.getKey() == null :
                    this.getKey().equals(comp.getKey()))
                    && (this.getValue() == null ? comp.getValue() == null :
                    this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         *
         * @return "Key:Value" that represents the node object
         */
        public String toString() {
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}
