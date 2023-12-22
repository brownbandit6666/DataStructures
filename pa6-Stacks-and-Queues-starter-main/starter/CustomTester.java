/**
 * Name: Michael Brown
 * Email: mibrown@ucsd.edu
 * PID: A17037478
 * Sources Used: JDK 17 Docs
 *
 */

 import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import static org.junit.Assert.*;
 
 /**
  * This class contains Custom test cases for MyDeque, MyStack, and MyQueue
  */
 public class CustomTester {
     /**
      * Helper method to initialize all instance variables of MyDeque
      * @param deque The deque to initialize
      * @param data The data array
      * @param size The value for size
      * @param front The value for front
      * @param rear The value for rear
      */
     static void initDeque(MyDeque<Integer> deque, Object[] data, int size,
                           int front, int rear) {
         deque.data = data;
         deque.size = size;
         deque.front = front;
         deque.rear = rear;
     }
 
     // ------------ Deque ---------------
 
     /** Test the Deque constructor, initialize deque with capacity -1 */
     @Test
     public void testMyDequeConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            MyDeque<Integer> deque = new MyDeque<>(-1);
        });
        
     }
 
     /** Test expandCapacity with array that exemplifies circularity */
     @Test
     public void testExpandCapacity() {
         MyDeque<Integer> deque = new MyDeque<>(5);
         Integer[] orig = {5, 6, null, null, 1};
         Integer[] finalOrdering = {1, 5, 6, null, null, null, null, null,
                 null, null};
         initDeque(deque, orig, 3, 4, 1);
 
         deque.expandCapacity();
 
         assertEquals("Capacity should have doubled", 10, deque.data.length);
         assertEquals("Size should not have changed", 3, deque.size);
         assertEquals("Front should be 0", 0, deque.front);
         assertEquals("Rear should be at size - 1", 2, deque.rear);
         for (int i = 0; i < 10; i++) {
             assertEquals("Deque structure should be maintained",
                     finalOrdering[i], deque.data[i]);
         }
     }
 
     /**
      * Test addFirst to deque first with a null value throws exception,
      next with array at capacity.
      */
     @Test
     public void testAddFirst() {

         MyDeque<Integer> deque = new MyDeque<>(3);
         Integer[] orig = {4, 5, 6};
         initDeque(deque, orig, 3, 0, 2);
 
         deque.addFirst(6);
 
         assertEquals("Capacity should change if deque is full", 6,
                 deque.data.length);
         assertEquals("Should increment size", 4, deque.size);
         assertEquals("Front should be at size-1", 5, deque.front);
         assertEquals("Rear shouldn't change when calling addFirst", 2,
                 deque.rear);
         assertEquals("6 should have been inserted into index 5",
                 6, deque.data[5]);
         assertEquals("Index 0 should not have changed", 4,
                 deque.data[0]);
         assertEquals("Index 1 should not have changed",
                 5, deque.data[1]);
         assertEquals("Index 2 should not have changed", 6,
                 deque.data[2]);
     }
 
     /** Test adding a null element */

     @Test
     public void testAddFirstException(){
        
        MyDeque<Integer> deque2 = new MyDeque<>(5);
        assertThrows(NullPointerException.class, () -> {
            deque2.addFirst(null);
        });

     }

     /**
      * Test addLast to deque with null value to throw exception,
      secondly addLast is called on array at capacity.
      */
     @Test
     public void testAddLast() {

        MyDeque<Integer> deque = new MyDeque<>(3);
        Integer[] orig = {4, 5, 6};
        initDeque(deque, orig, 3, 0, 2);

        deque.addLast(6);

        assertEquals("Capacity should change if deque is full", 6,
                deque.data.length);
        assertEquals("Should increment size", 4, deque.size);
        assertEquals("Front shouldn't change", 0, deque.front);
        assertEquals("Rear should change when calling addLast", 3,
                deque.rear);
        assertEquals("6 should have been inserted into index 3",
                6, deque.data[3]);
        assertEquals("Index 0 should not have changed", 4,
                deque.data[0]);
        assertEquals("Index 1 should not have changed",
                5, deque.data[1]);
        assertEquals("Index 2 should not have changed", 6,
                deque.data[2]);
     }
 
     /** Test adding a null element */

     @Test
     public void testAddLastException(){

        MyDeque<Integer> deque2 = new MyDeque<>(5);
        assertThrows(NullPointerException.class, () -> {
            deque2.addLast(null);
        });

     }

     /**
      * Test removeFirst from deque first with array of capacity 0,
      second highlighting circularity.
      */
     @Test
     public void testRemoveFirst() {

         MyDeque<Integer> deque = new MyDeque<>(5);
         Integer[] orig = {2, 3, 4, null, 1};
         initDeque(deque, orig, 4, 4, 2);
 
         assertEquals("Element removed should be returned", 1,
                 deque.removeFirst().intValue());
 
         assertEquals("Array length shouldn't be changed", 5,
                 deque.data.length);
         assertEquals("Size should decrement", 3, deque.size);
         assertEquals("Front should move one index after removing from " +
                 "non-empty deque", 0, deque.front);
         assertEquals("Rear should not change after calling removeFirst", 2,
                 deque.rear);
         assertEquals("Index 1 should remain unchanged", 3,
                 deque.data[1]);
         assertEquals("Index 2 should remain unchanged", 4,
                 deque.data[2]);
         assertNull("Index 4 should have been set to null", deque.data[4]);
     }
 
     /** Test removing element at capacity 0 */

     @Test
     public void testRemoveFirstNull(){

        MyDeque<Integer> deque2 = new MyDeque<>(0);
        assertEquals(null, deque2.removeFirst());

     }

     /**
      * Test removeLast from deque first with array of capacity 0,
      second highlighting circularity
      */
     @Test
     public void testRemoveLast() {

         MyDeque<Integer> deque = new MyDeque<>(5);
         Integer[] orig = {4, null, 1, 2, 3};
         initDeque(deque, orig, 4, 2, 0);
 
         assertEquals("Element removed should be returned", 4,
                 deque.removeLast().intValue());
 
         assertEquals("Array length shouldn't be changed", 5,
                 deque.data.length);
         assertEquals("Size should decrement", 3, deque.size);
         assertEquals("Rear should move one index after removing from " +
                 "non-empty deque", 4, deque.rear);
         assertEquals("Front should not change after calling removeLast", 2,
                 deque.front);
         assertEquals("Index 3 should remain unchanged", 2,
                 deque.data[3]);
         assertEquals("Index 4 should remain unchanged", 3,
                 deque.data[4]);
         assertNull("Index 0 should have been set to null", deque.data[0]);

         assertEquals("Element removed should be returned", 3,
         deque.removeLast().intValue());

         assertEquals("Element removed should be returned", 2,
         deque.removeLast().intValue());

         assertEquals("Element removed should be returned", 1,
         deque.removeLast().intValue());

         assertEquals("Element removed should be returned", null,
         deque.removeLast());

     }
 
     /** Test removing element at capacity 0 */

     @Test
     public void testRemoveLastNull(){

        MyDeque<Integer> deque2 = new MyDeque<>(0);
        assertEquals(null, deque2.removeLast());

     }

     // ------------ Stack ---------------
 
     /** Test the Stack with most of methods */
     @Test
     public void testStack() {
        assertThrows(IllegalArgumentException.class, () -> {
            MyStack<Integer> stack2 = new MyStack<>(-1);
        });

        MyStack<Integer> stack = new MyStack<>(5);

         assertEquals("Capacity should be initialized to 10", 5,
                 stack.theStack.data.length);
         assertEquals("Size should be initialized to 0", 0,
                 stack.theStack.size);
         assertEquals("Front should be initialized to 0", 0,
                 stack.theStack.front);
         assertEquals("Rear should be initialized to 0", 0,
                 stack.theStack.rear);
        
        assertTrue(stack.empty());

        stack.push(5);
        assertEquals("Element should be at the top of the stack",
                Integer.valueOf(5), stack.theStack.peekFirst());
        assertEquals("Size should be incremented", 1, stack.theStack.size);

        stack.pop();
        assertEquals("Size should be decremented", 0, stack.theStack.size);

     }
 
     @Test
     public void testStack_Peek(){
        MyStack<Integer> stack = new MyStack<>(10);
        Integer[] orig = {null, 2, 3, 28, null, null, null, null, null, null};
        initDeque(stack.theStack, orig, 3, 1, 3);

        Integer res = stack.peek();

        if (res == 2 || res == 28) {
            assertEquals("Size should not have decremented", 3,
                    stack.theStack.size);
            if (stack.theStack.peekFirst() != 2 &&
                    stack.theStack.peekLast() != 28) {
                fail("Elements on either end should not have changed");
            }
        } else {
            fail("An element on one of the ends should have been returned");
        }
        assertEquals("Capacity should not have changed", 10,
                stack.theStack.data.length);
        assertEquals("Front should not have changed", 1, stack.theStack.front);
        assertEquals("Rear should not have changed", 3, stack.theStack.rear);
     }

     @Test
    public void testStackPush() {
        MyStack<Integer> stack = new MyStack<>(5);
        Integer[] orig = {null, null, null, null, null};
        initDeque(stack.theStack, orig, 0, 0, 0);

        stack.push(72);

        // peekLast also works
        assertEquals("Element should be at the top of the stack",
                Integer.valueOf(72), stack.theStack.peekFirst());
        assertEquals("Capacity should not have changed", 5,
                stack.theStack.data.length);
        assertEquals("Size should be incremented", 1, stack.theStack.size);
        assertEquals("Front should not have changed", 0, stack.theStack.front);
        assertEquals("Rear should not have changed", 0, stack.theStack.rear);
    }

    /** Test pop on a stack with several elements */
    @Test
    public void testStackPop() {
        MyStack<Integer> stack = new MyStack<>(10);
        Integer[] orig = {null, 2, 3, 28, null, null, null, null, null, null};
        initDeque(stack.theStack, orig, 3, 1, 3);

        Integer res = stack.pop();

        if (res == 2 || res == 28) {
            assertEquals("Size should have decremented", 2,
                    stack.theStack.size);
            if (stack.theStack.peekFirst() != 2 &&
                    stack.theStack.peekLast() != 28) {
                fail("Next element to remove should be 2");
            }
        } else {
            fail("An element on one of the ends should have been returned");
        }
        assertEquals("Capacity should not have changed", 10,
                stack.theStack.data.length);
    }

     // ------------ Queue ---------------

     /** Test Queue with most of the methods*/
     @Test
     public void testQueue() {
        assertThrows(IllegalArgumentException.class, () -> {
            MyQueue<Integer> queue2 = new MyQueue<>(-1);
        });

        MyQueue<Integer> queue = new MyQueue<>(5);

         assertEquals("Capacity should be initialized to 10", 5,
            queue.theQueue.data.length);
         assertEquals("Size should be initialized to 0", 0,
            queue.theQueue.size);
         assertEquals("Front should be initialized to 0", 0,
            queue.theQueue.front);
         assertEquals("Rear should be initialized to 0", 0,
            queue.theQueue.rear);
        
        assertTrue(queue.empty());

        queue.enqueue(5);
        assertEquals("Element should be at the top of the stack",
                Integer.valueOf(5), queue.peek());
        assertEquals("Size should be incremented", 1, queue.theQueue.size);

        queue.dequeue();
        assertEquals("Size should be decremented", 0, queue.theQueue.size);

     }
 
 }
 
