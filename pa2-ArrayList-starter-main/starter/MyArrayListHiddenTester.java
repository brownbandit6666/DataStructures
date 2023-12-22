import static org.junit.Assert.*;

import org.junit.*;

public class MyArrayListHiddenTester {
    // Do not change the method signatures!
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test 
     */
    static final int DEFAULT_CAPACITY = 5;
    static final int MY_CAPACITY = 4;
    Object[] newArr = new Object[8];
    Integer[] newArrInts = {4, 5, 6};
    Integer[] newArrNonEmptyInts = {null, 2, null};

    private MyArrayList listEmpty, listNonEmpty, listDefaultCap, 
            listCustomCapacity, listWithNull, listWithNullParam, listWithInt;
    
    @Before
    public void setUp() throws Exception {
        listEmpty = new MyArrayList();
        listNonEmpty = new MyArrayList<>(newArrNonEmptyInts);
        listNonEmpty.size = 3;
        listDefaultCap = new MyArrayList(DEFAULT_CAPACITY);
        listCustomCapacity = new MyArrayList(MY_CAPACITY);
        listWithNull = new MyArrayList(newArr);
        listWithNullParam = new MyArrayList<>(null);
        listWithInt = new MyArrayList<Integer>(newArrInts);
        
    }

    /**
     * Aims to test the constructor when the input argument
     * is not valid
     */
    @Test
    public void testConstructorInvalidArg(){
        boolean caught = false;
        try{
            listEmpty = new MyArrayList<>(-1);
        }
        catch(IllegalArgumentException e){
            caught = true;
        }
        assertEquals(caught, true);
    }

    /**
     * Aims to test the constructor when the input argument is null
     */
    @Test
    public void testConstructorNullArg(){
        assertArrayEquals(listDefaultCap.data, listWithNullParam.data);
    }

    /**
     * Aims to test the constructor when the input array has null elements
     */
    @Test
    public void testConstructorArrayWithNull(){
       assertArrayEquals(new Object[8], listWithNull.data);
       assertEquals(8, listWithNull.size);
    }

    /**
     * Aims to test the append method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendAtCapacity(){
        listWithInt.append(3);
        assertArrayEquals("Check for successful append", 
                new Integer[]{4, 5, 6, 3, null, null}, listWithInt.data);
        assertEquals("Check list size after the append", 4, listWithInt.size);
    }

    /**
     * Aims to test the append method when null is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendNull(){
        listWithInt.append(null);
        assertArrayEquals("Check for successful append", 
                new Integer[]{4, 5, 6, null, null, null}, listWithInt.data);
        assertEquals("Check list size after the append", 4, listWithInt.size);
    }

    /**
     * Aims to test the prepend method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testPrependAtCapacity(){
        listWithInt.prepend(3);
        assertArrayEquals("Check for successful append", 
                new Integer[]{3, 4, 5, 6, null, null}, listWithInt.data);
        assertEquals("Check list size after the append", 4, listWithInt.size);
    }
    
    /**
     * Aims to test the prepend method when a null element is added
     * Checks reflection on size and capacity
     * Checks whether null was added successfully
     */
    @Test
    public void testPrependNull(){
        listWithInt.prepend(null);
        assertArrayEquals("Check for successful append", 
                new Integer[]{null, 4, 5, 6, null, null}, listWithInt.data);
        assertEquals("Check list size after the append", 4, listWithInt.size);
    }
    
    /**
     * Aims to test the insert method when input index is out of bounds
     */
    @Test
    public void testInsertOutOfBounds(){
        boolean caught = false;
        try{
            listWithInt.insert(5, 4);
        }
        catch(IndexOutOfBoundsException e){
            caught = true;
        }
        assertEquals(caught, true);
    }

    /**
     * Insert multiple (eg. 1000) elements sequentially beyond capacity -
     * Check reflection on size and capacity
     * Hint: for loop could come in handy
     */
    @Test
    public void testInsertMultiple(){
        listWithInt.insert(0, 7);
        listWithInt.insert(1, 2);
        assertArrayEquals(new Integer[]{7, 2, 4, 5, 6, null}, listWithInt.data);
        assertEquals(5, listWithInt.size);
    }

    /**
     * Aims to test the get method when input index is out of bound
     */
    @Test
    public void testGetOutOfBound(){
        boolean caught = false;
        try{
            listWithInt.get(5);
        }
        catch(IndexOutOfBoundsException e){
            caught = true;
        }
        assertEquals(caught, true);
    }

    /**
     * Aims to test the set method when input index is out of bound
     */
    @Test
    public void testSetOutOfBound(){
        boolean caught = false;
        try{
            listWithInt.set(5, 8);
        }
        catch(IndexOutOfBoundsException e){
            caught = true;
        }
        assertEquals(caught, true);
    }

    /**
     * Aims to test the remove method when removing multiple items from a list
     */
    @Test
    public void testRemoveMultiple(){
        listWithInt.remove(0);
        listWithInt.remove(1);
        assertArrayEquals(new Integer[]{5, null, null}, listWithInt.data);
        assertEquals(1, listWithInt.size);
    }

    /**
     * Aims to test the remove method when input index is out of bound
     */
    @Test
    public void testRemoveOutOfBound(){
        boolean caught = false;
        try{
            listWithInt.remove(6);
        }
        catch(IndexOutOfBoundsException e){
            caught = true;
        }
        assertEquals(caught, true);
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is strictly less than the current capacity
     */
    @Test
    public void testExpandCapacitySmaller(){
        boolean caught = false;
        try{
            listWithInt.expandCapacity(1);;
        }
        catch(IllegalArgumentException e){
            caught = true;
        }
        assertEquals(caught, true);
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is greater than current capacity+3 and default capacity
     */
    @Test
    public void testExpandCapacityLarge(){
        listWithInt.expandCapacity(7);
        assertArrayEquals(new Integer[]{4, 5, 6, null, null, null, null}, listWithInt.data);
        assertEquals(3, listWithInt.size);
    }
    

}
