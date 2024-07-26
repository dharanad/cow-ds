package ai.kunato;

import org.junit.Test;

import static org.junit.Assert.*;


public class BinarySearchTreeTest {

    @Test
    public void testNewTreeNode() {
        final TreeNode node = new TreeNode(-1, null, null);
        assertNull(node.getLeft());
        assertNull(node.getRight());
        assertEquals(-1, node.getValue());
    }

    @Test
    public void testBstInsert() {
        final BinarySearchTree SUT = new BinarySearchTree();
        SUT.insert(5);
        SUT.insert(3);
        SUT.insert(4);
        SUT.insert(1);
        SUT.insert(8);
        SUT.insert(7);
        SUT.insert(9);
        assertEquals(7, SUT.getCurrentVersion());
    }

    @Test
    public void testBstSearch() {
        final BinarySearchTree SUT = new BinarySearchTree();
        SUT.insert(5); // 1
        SUT.insert(3); // 2
        SUT.insert(4); // 3
        SUT.insert(1); // 4
        SUT.insert(8); // 5
        SUT.insert(7); // 6
        SUT.insert(9); // 7

        TreeNode node = SUT.search(1, 5);
        assertNotNull(node);
        assertEquals(node.getValue(), 5);

        node = SUT.search(3, 1);
        assertNull(node);
    }
}