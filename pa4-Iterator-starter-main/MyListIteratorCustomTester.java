/**
 * Name: Michael Brown
 * Email: mibrown@ucsd.edu
 * PID: A17037478
 * Sources Used: JDK 17 Docs
 *
 * This file contains all the hidden tests as mentioned on Git.
 */
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;

/**
 * This class contains hidden test cases for MyListIterator. listLen1 is a
 * linkedlist of length 1 and listLen2 is a linkedlist of length 2.
 */

public class MyListIteratorCustomTester {

    /**
     * Testing LinkedLists instantiated below
     */

     private MyLinkedList listLen1, listLen2;
     private MyLinkedList.MyListIterator listLen1Iter, listLen2Iter;

    @Before
    public void setUp() throws Exception {
        listLen1 = new MyLinkedList();
        listLen1.add("Start");
        listLen1Iter = listLen1.new MyListIterator();

        listLen2 = new MyLinkedList();
        listLen2.add("Start");
        listLen2.add("End");
        listLen2Iter = listLen2.new MyListIterator();
    }

    /**
     * Aims to test the next() method when iterator is at end of the list 
     */
    @Test
    public void testNextEnd() {
        listLen1Iter.left = listLen1.head.getNext();
        listLen1Iter.right = listLen1.head.getNext().getNext();
        listLen1Iter.idx = 1;
        listLen1Iter.forward = true;
        listLen1Iter.canRemoveOrSet = true;
        assertThrows(NoSuchElementException.class, () -> {
            listLen1Iter.next();
        });
        
    }

    /**
     * Aims to test the previous() method when iterator is at the start of the 
     * list 
     */
    @Test
    public void testPreviousStart() {
        assertThrows(NoSuchElementException.class, () -> {
            listLen1Iter.previous();
        });
    }

    /**
     * Aims to test the add(E e) method when an invalid element is added
     */
    @Test
    public void testAddInvalid() {
        assertThrows(NullPointerException.class, () -> {
            listLen2Iter.add(null);
        });
    }

    /**
     * Aims to test the set(E e) method when canRemoveOrSet is false
     */
    @Test
    public void testCantSet() {
        assertThrows(IllegalStateException.class, () -> {
            listLen1Iter.set("End");
        });
    }


    /**
     * Aims to test the set(E e) method when an invalid element is set
     */
    @Test
    public void testSetInvalid() {
        assertThrows(NullPointerException.class, () -> {
            listLen1Iter.set(null);
        });
    }

    /**
     * Aims to test the remove() method when canRemoveOrSet is false
     */
    @Test
    public void testCantRemove() {
        assertThrows(IllegalStateException.class, () -> {
            listLen1Iter.remove();
        });
    }

    /**
     * Aims to tests the hasNext() method at the end of a list
     */
    @Test
    public void testHasNextEnd() {
        listLen1Iter.left = listLen1.head.getNext();
        listLen1Iter.right = listLen1.head.getNext().getNext();
        listLen1Iter.idx = 1;
        listLen1Iter.forward = true;
        listLen1Iter.canRemoveOrSet = true;
        assertEquals(false, listLen1Iter.hasNext());
    }

    /**
     * Aims to test the hasPrevious() method at the start of a list
     */
    @Test
    public void testHasPreviousStart() {
        assertEquals(false, listLen1Iter.hasPrevious());
    }

    /**
     * Aims to test the previousIndex() method at the start of a list
     */
    @Test
    public void testPreviousIndexStart() {
        assertEquals(-1, listLen1Iter.previousIndex());
    }

    /**
     * Aims to test the nextIndex() method at the end of a list
     */
    @Test
    public void testNextIndexEnd() {
        listLen1Iter.left = listLen1.head.getNext();
        listLen1Iter.right = listLen1.head.getNext().getNext();
        listLen1Iter.idx = 1;
        listLen1Iter.forward = true;
        listLen1Iter.canRemoveOrSet = true;
        assertEquals(1, listLen1Iter.nextIndex());
    }
}
