import java.util.AbstractList;

public class MyLinkedList<E> extends AbstractList<E> {

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null	
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the parameter prev as the previous node
         * @param prev new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;		
        }

        /** 
         * Set the parameter next as the next node
         * @param next new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /** 
         * Set the parameter element as the node's data
         * @param element new element 
         */
        public void setElement(E element) {
            this.data = element;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }
        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        } 
        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        } 
    }

    private static final int ZERO = 0;

    //  Implementation of the MyLinkedList Class
    public MyLinkedList() {

        // the next two following lines implement both dummy nodes into MyLinkedList
        // as in setting the head to a new Node with contents null and similarly
        // to the tail.

        this.head = new Node(null);
        this.tail = new Node(null);
        head.next = tail;
        tail.prev = head;
        this.size = ZERO;       
    }

    @Override
    public int size() {
        // need to implement the size method
        return this.size; // TODO
    }

    // A preliminary for most folling methods are the exceptions thrown
    // which looks at whether or not the index given does not lie
    // within the bounds of MyLinkedList, and thus will throw 
    // an exception when presented with one.
    
    // The other thrown exception is whether the inputed data
    // is not valid as in set to null or otherwise, and thus 
    // will throw an exception.

    @Override
    public E get(int index) {
        if(index < ZERO || index >= size()){
            throw new IndexOutOfBoundsException();
        }   
        Node getNode = this.head;
        for(int i = ZERO; i <= index; i++){
            getNode = getNode.next;
        }
        return getNode.getElement();  // TODO
    }

    @Override
    public void add(int index, E data) {
       if(data == null){
        throw new NullPointerException();
       }
       else if(index < ZERO || index > size()){
        throw new IndexOutOfBoundsException();
       }
       Node addNode = this.head;
       Node newNode = new Node(data);
        for(int i = ZERO; i <= index; i++){
            addNode = addNode.next;
        }

        // The following code pretty much sets up the new pathways
        // for the nodes lying in both previous and next positions.

        // This sets both the pathways for the newNode and also redirects
        // the before and after node pathways.

        // This is the same for both add() methods.

        newNode.next = addNode;
        newNode.prev = addNode.prev;
        addNode.prev.next = newNode;
        addNode.prev = newNode;
        size++;
    }

    @Override
    public boolean add(E data) {
        if(data == null){
            throw new NullPointerException();
        }
        Node addNode = this.head;
        Node newNode = new Node(data);
        for(int i = ZERO; i <= size; i++){
            addNode = addNode.next;
        }
        newNode.next = addNode;
        newNode.prev = addNode.prev;
        addNode.prev.next = newNode;
        addNode.prev = newNode;
        size++;
        return true; // TODO
    }

    @Override
    public E set(int index, E data) {
        if(data == null){
            throw new NullPointerException();
        }    
        else if(index < ZERO || index >= size()){
            throw new IndexOutOfBoundsException();
        } 
        Node setNode = this.head;
        for(int i = ZERO; i <= index; i++){
            setNode = setNode.next;
        }

        // Creating an object variable to 'remember' the data
        // in order to return it, after the code runs.

        E sData = setNode.data;
        setNode.data  = data;
        return sData;
    }

    @Override
    public E remove(int index) {
        if(index < ZERO || index >= size()){
            throw new IndexOutOfBoundsException();
        } 
        Node removeNode = this.head;
        for(int i = ZERO; i <= index; i++){
            removeNode = removeNode.next;
        }

        // This removes the node by redirecting the pathways
        // of the the two surrounding nodes, either side of the 
        // node that was removed.

        removeNode.prev.next = removeNode.next;
        removeNode.next.prev = removeNode.prev;
        E rData = removeNode.data;
        size--;
        return rData;
    }

    @Override
    public void clear() {
        this.size = ZERO;
        this.head.setNext(tail);
        this.tail.setPrev(head);
    }

    @Override
    public boolean isEmpty() {
        return size == ZERO;  // TODO
    }

    protected Node getNth(int index) {
        if(index < ZERO || index >= size()){
            throw new IndexOutOfBoundsException();
        } 
        Node getNthNode = this.head;
        for(int i = ZERO; i <= index; i++){
            getNthNode = getNthNode.next;
        }
        return getNthNode;  // TODO
    }
}
