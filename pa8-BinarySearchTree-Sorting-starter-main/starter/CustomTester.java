/**
 * Name: Michael Brown
 * Email: mibrown@ucsd.edu
 * PID: A17037478
 * Sources Used: JDK 17 Docs
 *
 */

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CustomTester {
    MyBST<Integer, Integer> tree;
    MyBST<Integer, Integer> tree2;
    MyBST<Integer, Integer> tree3;
    MyBST<Integer, Integer> tree4;
    MyBST<Integer, Integer> tree5;
    MyBST<Integer, Integer> tree6;


    @Before
    public void setup() {
        MyBST.MyBSTNode<Integer, Integer> root6 =
                new MyBST.MyBSTNode<>(4, 1, null);

        MyBST.MyBSTNode<Integer, Integer> root =
                new MyBST.MyBSTNode<>(4, 1, null);
        MyBST.MyBSTNode<Integer, Integer> two =
                new MyBST.MyBSTNode<>(2, 1, root);
        MyBST.MyBSTNode<Integer, Integer> six =
                new MyBST.MyBSTNode<>(6, 1, root);
        MyBST.MyBSTNode<Integer, Integer> one =
                new MyBST.MyBSTNode<>(1, 2, two);
        MyBST.MyBSTNode<Integer, Integer> three =
                new MyBST.MyBSTNode<>(3, 30, two);
        MyBST.MyBSTNode<Integer, Integer> five =
                new MyBST.MyBSTNode<>(5, 50, six);

        MyBST.MyBSTNode<Integer, Integer> root3 =
                new MyBST.MyBSTNode<>(2, 1, null);
        MyBST.MyBSTNode<Integer, Integer> one3 =
                new MyBST.MyBSTNode<>(1, 7, root3);

        MyBST.MyBSTNode<Integer, Integer> root4 =
                new MyBST.MyBSTNode<>(2, 1, null);
        MyBST.MyBSTNode<Integer, Integer> three4 =
                new MyBST.MyBSTNode<>(3, 7, root4);

        MyBST.MyBSTNode<Integer, Integer> root5 =
                new MyBST.MyBSTNode<>(10, 1, null);
        MyBST.MyBSTNode<Integer, Integer> eight5 =
                new MyBST.MyBSTNode<>(8, 1, root);
        MyBST.MyBSTNode<Integer, Integer> seven5 =
                new MyBST.MyBSTNode<>(7, 1, eight5);
        MyBST.MyBSTNode<Integer, Integer> six5 =
                new MyBST.MyBSTNode<>(6, 2, seven5);
        MyBST.MyBSTNode<Integer, Integer> nine5 =
                new MyBST.MyBSTNode<>(9, 30, eight5);
        MyBST.MyBSTNode<Integer, Integer> twenty5 =
                new MyBST.MyBSTNode<>(20, 50, root);
        MyBST.MyBSTNode<Integer, Integer> fifteen5 =
                new MyBST.MyBSTNode<>(15, 1, twenty5);
        MyBST.MyBSTNode<Integer, Integer> eleven5 =
                new MyBST.MyBSTNode<>(11, 1, fifteen5);
        MyBST.MyBSTNode<Integer, Integer> thirty5 =
                new MyBST.MyBSTNode<>(30, 1, twenty5);
        MyBST.MyBSTNode<Integer, Integer> twentyfive5 =
                new MyBST.MyBSTNode<>(25, 2, thirty5);
        MyBST.MyBSTNode<Integer, Integer> twentythree5 =
                new MyBST.MyBSTNode<>(23, 30, twentyfive5);
        MyBST.MyBSTNode<Integer, Integer> forty5 =
                new MyBST.MyBSTNode<>(40, 50, thirty5);

        this.tree = new MyBST<>();
        this.tree2 = new MyBST<>();
        this.tree3 = new MyBST<>();
        this.tree4 = new MyBST<>();
        this.tree5 = new MyBST<>();
        this.tree6 = new MyBST<>();


        this.tree.root = root;
        this.tree3.root = root3;
        this.tree4.root = root4;
        this.tree5.root = root5;
        this.tree6.root = root6;
        root5.setLeft(eight5);
        root5.setRight(twenty5);
        eight5.setLeft(seven5);
        eight5.setRight(nine5);
        seven5.setLeft(six5);
        twenty5.setLeft(fifteen5);
        twenty5.setRight(thirty5);
        fifteen5.setLeft(eleven5);
        thirty5.setLeft(twenty5);
        thirty5.setRight(forty5);
        twentyfive5.setLeft(twentythree5);

        root4.setRight(three4);
        root3.setLeft(one3);
        root.setLeft(two);
        root.setRight(six);
        two.setLeft(one);
        two.setRight(three);
        six.setLeft(five);
        tree.size = 6;
        tree2.size = 0;
        tree3.size = 2;
        tree4.size = 2;
    }

    /*This simply tests the successor on different BST scenarios */

    @Test
    public void testSuccessor() {
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root;
        assertSame(treeRoot.getRight().getLeft(), treeRoot.successor());
        assertSame(tree.root.getLeft().getRight(), tree.root.getLeft().successor());

        assertSame(tree3.root, tree3.root.getLeft().successor());

        assertSame(tree4.root.getRight(), tree4.root.successor());
    }

    /* This tests that an exception is thrown if called on an empty node */

    @Test
    public void testSuccessorThrows(){
        assertThrows(NullPointerException.class, () -> {
            assertNull(tree2.root.successor());
        });
    }

    /* Tests insert method with multiple BST scenarios */

    @Test
    public void testInsert() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        tree.insert(7, 18);
        assertEquals(7, root.getRight().getRight().getKey().intValue());
        assertSame(root.getRight(), root.getRight().getRight().getParent());
        assertEquals("size of tree", 7, tree.size);
        
        tree4.insert(4,3);
        assertEquals(4, tree4.root.getRight().getRight().getKey().intValue());
        assertSame(tree4.root.getRight().getRight(), tree4.root.getRight().successor());
        assertEquals("size of tree", 3, tree4.size);

        tree4.insert(1,6);
        assertEquals(1, tree4.root.getLeft().getKey().intValue());
        assertSame(tree4.root, tree4.root.getLeft().successor());
        assertEquals("size of tree", 4, tree4.size);

    }

    /*Test insert on an empty BST */

    @Test
    public void testInsertRootEmpty(){
        tree2.insert(1, 10);
        assertEquals(1, tree2.root.getKey().intValue());
        assertNull(tree2.root.getRight());
        assertSame(tree2.root.getRight(), tree2.root.getLeft());
        assertEquals("size of tree", 1, tree2.size);
    }

    /*Test the insert method under conditions 
    that would result in an exception */

    @Test
    public void testInsertThrows(){
        assertThrows(NullPointerException.class, () -> {
            tree.insert(null, 5);
        });
    }

    /* This checks that null is outputed when necessary */

    @Test
    public void testSearchNull() {
        assertEquals(null, tree.search(null));

        assertEquals(null, tree2.search(1));
    }

    /*This tests search on multiple BST scenarios */

    @Test
    public void testSearch() {
        assertEquals(50, tree.search(5).intValue());
        assertNull(tree.search(8));

        assertEquals(1,tree3.search(2).intValue());
        assertEquals(7, tree3.search(1).intValue());

        assertEquals(7,tree4.search(3).intValue());
    }

    @Test
    public void testRemove() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        MyBST.MyBSTNode<Integer, Integer> root5 = tree5.root;

        assertEquals(30, tree.remove(3).intValue());
        assertNull(root.getLeft().getRight());
        assertEquals(1, tree.remove(6).intValue());
        assertEquals(5, root.getRight().getKey().intValue());
        assertEquals("size of tree", 4, tree.size);

        assertEquals(7, tree3.remove(1).intValue());
        assertNull(tree3.root.getLeft());

        assertEquals(7, tree4.remove(3).intValue());
        assertNull(tree3.root.getRight());

        assertEquals(50, tree5.remove(20).intValue());
        assertEquals(1, root5.getRight().getValue().intValue());

        assertEquals(1,tree6.remove(4).intValue());

        assertEquals(1, tree3.remove(2).intValue());
        assertEquals(0, tree3.size);
    }

    /*This tests the inorder method on four different 
     * trees and their arrays
     */

    @Test
    public void testInorder() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> arr =
                new ArrayList<>();
        arr.add(root.getLeft().getLeft());
        arr.add(root.getLeft());
        arr.add(root.getLeft().getRight());
        arr.add(root);
        arr.add(root.getRight().getLeft());
        arr.add(root.getRight());
        assertEquals(arr, tree.inorder());

        ArrayList<MyBST.MyBSTNode<Integer, Integer>> arr2 =
                new ArrayList<>();
        assertEquals(arr2, tree2.inorder());

        ArrayList<MyBST.MyBSTNode<Integer, Integer>> arr3 =
                new ArrayList<>();
        arr3.add(tree3.root.getLeft());
        arr3.add(tree3.root);
        assertEquals(arr3, tree3.inorder());

        ArrayList<MyBST.MyBSTNode<Integer, Integer>> arr4 =
                new ArrayList<>();
        arr4.add(tree4.root);
        arr4.add(tree4.root.getRight());
        assertEquals(arr4, tree4.inorder());
    }
}

