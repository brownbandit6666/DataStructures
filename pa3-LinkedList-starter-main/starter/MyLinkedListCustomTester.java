
/**
 * IMPORTANT: Do not change the method headers. Your tests will be run against
 * good and bad implementations of MyLinkedList. You will only receive points
 * if your test passes when run on a good implementation and fails for the
 * corresponding bad implementation.
 */

import static org.junit.Assert.*;
import org.junit.*;

public class MyLinkedListCustomTester {

	// Optional: add test variables here
	private MyLinkedList<Integer> emptyIntegerList;
    private MyLinkedList<String> threeStringList;
    private String[] strData = {"Start", "Middle", "End"};
	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
	@Before
	public void setUp() throws Exception {
		// Optional: add setup here
		emptyIntegerList = new MyLinkedList<Integer>();
        threeStringList = new MyLinkedList<>();
	}

	private void populateLinkedList() {
        MyLinkedList<String>.Node node0 =  
            this.threeStringList.new Node(this.strData[0]);
        MyLinkedList<String>.Node node1 =  
            this.threeStringList.new Node(this.strData[1]);
        MyLinkedList<String>.Node node2 =  
            this.threeStringList.new Node(this.strData[2]);

        this.threeStringList.head.next = node0;
        node0.prev = this.threeStringList.head;
        node0.next = node1;
        node1.prev = node0;
        node1.next = node2;
        node2.prev = node1;
        node2.next = this.threeStringList.tail;
        this.threeStringList.tail.prev = node2;
        this.threeStringList.size = 3;
    }

	/**
	 * Aims to test the add(E data) method with a valid argument.
	 */
	@Test
	public void testCustomAdd() {
		// TODO: add your test here
		assertEquals(true, this.emptyIntegerList.add(10));
        assertEquals("New node should be accessible from head", 
            Integer.valueOf(10), this.emptyIntegerList.head.next.data);
        assertEquals("New node should be accessible from tail", 
            Integer.valueOf(10), this.emptyIntegerList.tail.prev.data);
        assertEquals("Size of the LinkedList should be updated", 
            1, this.emptyIntegerList.size);
        assertSame("Make sure the referece from head and tail are the same", 
            this.emptyIntegerList.head.next, this.emptyIntegerList.tail.prev);
        assertSame("Added node should have correct previous pointer",
            this.emptyIntegerList.head.next.prev, this.emptyIntegerList.head);
        assertSame("Added node should have the correct next pointer",
            this.emptyIntegerList.head.next.next, this.emptyIntegerList.tail);
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the beginning of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToStart() {
		// TODO: add your test here
		this.threeStringList.add(0, "NewStart");
        assertEquals("Check head reference is updated",
            String.valueOf("NewStart"), this.threeStringList.head.next.data);
        assertEquals("Check tail reference is updated", 
            String.valueOf("NewStart"), this.threeStringList.tail.prev.data);
        assertEquals("Check size is updated", 1, this.threeStringList.size);
        assertSame("Added node should have correct previous pointer",
            this.threeStringList.head.next.prev, this.threeStringList.head);
        assertSame("Added node should have the correct next pointer",
            this.threeStringList.head.next.next, this.threeStringList.tail);
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the middle of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToMiddle() {
		// TODO: add your test here
		this.populateLinkedList();
		this.threeStringList.add(1, "NewMiddle");
        assertEquals("Check head reference is updated",
            String.valueOf("NewMiddle"), this.threeStringList.head.next.next.data);
        assertEquals("Check tail reference is updated", 
            String.valueOf("NewMiddle"), this.threeStringList.tail.prev.prev.prev.data);
        assertEquals("Check size is updated", 4, this.threeStringList.size);
        assertSame("Added node should have correct previous pointer",
            this.threeStringList.head.next.next.prev, this.threeStringList.head.next);
        assertSame("Added node should have the correct next pointer",
            this.threeStringList.head.next.next.next, this.threeStringList.tail.prev.prev);
	}

	/**
	 * Aims to test the remove(int index) method. Remove from an empty list.
	 */
	@Test
	public void testCustomRemoveFromEmpty() {
		// TODO: add your test here
		this.populateLinkedList();

        assertThrows(IndexOutOfBoundsException.class, () -> {
            emptyIntegerList.remove(1);
        });
	}

	/**
	 * Aims to test the remove(int index) method.
	 * Remove a valid argument from the middle of MyLinkedList.
	 */
	@Test
	public void testCustomRemoveFromMiddle() {
		// TODO: add your test here
		this.populateLinkedList();
        MyLinkedList<String>.Node node1 = this.threeStringList.tail.prev.prev.prev;
        this.threeStringList.remove(1);
        // We can also check whether we update the prev and next correctly.
        assertSame("Tail.prev should point to the new last element", 
            node1, this.threeStringList.tail.prev.prev);
        assertSame("The new last node should point to tail", 
            node1.next, this.threeStringList.tail.prev);
        assertSame("The tail should point to the new last node", 
            node1, this.threeStringList.tail.prev.prev);
        assertEquals("Size of linked list",
            2, this.threeStringList.size());
	}

	/**
	 * Aims to test the set(int index, E data) method.
	 * Set an out-of-bounds index with a valid data argument.
	 */
	@Test
	public void testCustomSetIdxOutOfBounds() {
		this.populateLinkedList();

        assertThrows(IndexOutOfBoundsException.class, () -> {
            threeStringList.set(5, "2nd End");
        });
	}
}
