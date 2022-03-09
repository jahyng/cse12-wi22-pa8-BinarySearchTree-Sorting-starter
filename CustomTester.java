import java.util.*;

import javax.accessibility.AccessibleAttributeSequence;
import javax.swing.text.DefaultStyledDocument.ElementSpec;

import static org.junit.Assert.*;
import org.junit.*;


public class CustomTester {
    MyBST<Integer, Integer> completeTree;
    MyBST<Integer, Integer> leftTree;


    /**
     * The setup method creates a complete tree with height 4
     * The tree has following structure and will be used as testing purpose
     *           4
     *         /  \
     *       2     6
     *     /  |   /  \
     *    1   3  5    7
     *                 \
     *                  8
     * 
     * The setup method also creates a left tree with height 4
     * The tree has following structure and will be used as testing purpose
     *         6
     *        /  
     *       5     
     *      /   
     *     2    
     *    /             
     *   1               
     */
    @Before
    public void setup(){
        // create complete tree
        MyBST.MyBSTNode<Integer, Integer> root = 
            new MyBST.MyBSTNode(4, 1, null);
        MyBST.MyBSTNode<Integer, Integer> two = 
            new MyBST.MyBSTNode(2, 1, root);
        MyBST.MyBSTNode<Integer, Integer> six = 
            new MyBST.MyBSTNode(6, 1, root);
        MyBST.MyBSTNode<Integer, Integer> one = 
            new MyBST.MyBSTNode(1, 2, two);
        MyBST.MyBSTNode<Integer, Integer> three = 
            new MyBST.MyBSTNode(3, 30, two);
        MyBST.MyBSTNode<Integer, Integer> five = 
            new MyBST.MyBSTNode(5, 50, six);
        MyBST.MyBSTNode<Integer, Integer> seven =
            new MyBST.MyBSTNode(7, 70, six);
        MyBST.MyBSTNode<Integer, Integer> eight =
            new MyBST.MyBSTNode(8,80,three);


        this.completeTree = new MyBST<>();
        this.completeTree.root = root;
        root.setLeft(two);
        root.setRight(six);
        two.setLeft(one);
        two.setRight(three);
        six.setLeft(five);
        six.setRight(seven);
        seven.setRight(eight);
        this.completeTree.size = 8;

        // create leftTree
        MyBST.MyBSTNode<Integer, Integer> root2 = 
            new MyBST.MyBSTNode(6,60,null);
        MyBST.MyBSTNode<Integer, Integer> firstChild = 
            new MyBST.MyBSTNode(5,50,root2);
        MyBST.MyBSTNode<Integer, Integer> secondChild = 
            new MyBST.MyBSTNode(2,20,firstChild);
        MyBST.MyBSTNode<Integer, Integer> thirdChild = 
            new MyBST.MyBSTNode(1,10,secondChild);
        
            this.leftTree = new MyBST<>();
            this.leftTree.root = root2;
            root2.setLeft(firstChild);
            firstChild.setLeft(secondChild);
            secondChild.setLeft(thirdChild);
            this.leftTree.size = 4;
    }

    /**
     * Tester for insert method when replacing already existing key and 
     * inserting in middle of tree
     */
    @Test
    public void testInsertCompleteTree(){
        // complete tree
        MyBST.MyBSTNode<Integer, Integer> root = completeTree.root; 
        completeTree.insert(3, 55);
        assertEquals((Integer)55, root.getLeft().getRight().getValue());
        assertEquals(8, completeTree.size());

    }

    /**
     * Test insert new node in middle of list
     */
    @Test
    public void testInsertLeftTree(){
        // left tree
        MyBST.MyBSTNode<Integer, Integer> root2 = leftTree.root;
        leftTree.insert(4,40);
        assertEquals((Integer)40, root2.getLeft().getLeft().getRight().getValue());
        assertEquals(5, leftTree.size());
    }

    /**
     * Test search method in complete tree
     */
    @Test
    public void testSearchCompleteTree(){
        assertEquals((Integer)80, completeTree.search(8));
        assertEquals(null, completeTree.search(9));
    }

    /**
     * Test search method in left tree
     */
    @Test
    public void testSearchLeftTree(){
        assertEquals((Integer)20, leftTree.search(2));
        assertEquals(null, leftTree.search(4));
    }

    /**
     * Test remove method in complete tree when removing the root node
     */
    @Test
    public void testRemoveCompleteTree(){
        MyBST.MyBSTNode<Integer, Integer> root = completeTree.root; 
        assertEquals((Integer)1, completeTree.remove(4));
        assertEquals(7, completeTree.size());
        assertEquals((Integer)5, root.getKey());
        assertEquals((Integer)50, root.getValue());
    }

    /**
     * Test remove method in left tree
     */
    @Test
    public void testRemoveLeftTree(){
        MyBST.MyBSTNode<Integer, Integer> root = leftTree.root;
        assertEquals((Integer)60, leftTree.remove(6));
        assertEquals(3, leftTree.size());
        assertEquals((Integer)5, root.getKey());
        assertEquals((Integer)50, root.getValue());
    }

    /**
     * Test inorder method for complete tree
     */
    @Test
    public void testInOrderCompleteTree(){
        MyBST.MyBSTNode<Integer, Integer> root = completeTree.root; 
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expectedRes 
            = new ArrayList<>();
        expectedRes.add(root.getLeft().getLeft());
        expectedRes.add(root.getLeft());
        expectedRes.add(root.getLeft().getRight());
        expectedRes.add(root);
        expectedRes.add(root.getRight().getLeft());
        expectedRes.add(root.getRight());
        expectedRes.add(root.getRight().getRight());
        expectedRes.add(root.getRight().getRight().getRight());
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> actualRes 
            = completeTree.inorder();
        for (int i = 0; i < 8; i++){
            assertEquals(expectedRes.get(i), actualRes.get(i));
        }
    }

    /**
     * Test InOrder method for left tree
     */
    @Test
    public void testInOrderLeftTree(){
        MyBST.MyBSTNode<Integer, Integer> root = leftTree.root; 
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expectedRes 
            = new ArrayList<>();
        expectedRes.add(root.getLeft().getLeft().getLeft());
        expectedRes.add(root.getLeft().getLeft());
        expectedRes.add(root.getLeft());
        expectedRes.add(root);
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> actualRes 
            = leftTree.inorder();
        for (int i = 0; i < 4; i++){
            assertEquals(expectedRes.get(i), actualRes.get(i));
        }
    }
}


