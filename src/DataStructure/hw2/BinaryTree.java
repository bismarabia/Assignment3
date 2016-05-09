package DataStructure.hw2;
 /*
*  Name : Rabia Abismail
*  ID   : 140201209
*  Project on GitHub : I'll upload it later under this link https://git.io/vwBMQ
*/

public class BinaryTree<AnyType>
{
    BinaryTree()
    {
        root = null;
    }

    BinaryTree(AnyType rootItem)
    {
        root = new BinaryNode<AnyType>( rootItem, null, null );
    }

    public void printPreOrder( )
    {
        if( root != null )
            root.printPreOrder( );
    }

    private void printInOrder()
    {
        if( root != null )
           root.printInOrder( );
    }

    public void printPostOrder( )
    {
        if( root != null )
           root.printPostOrder( );
    }

    public void makeEmpty( )
    {
        root = null;
    }

    public boolean isEmpty( )
    {
        return root == null;
    }
    
    void merge(AnyType rootItem, BinaryTree<AnyType> t1, BinaryTree<AnyType> t2) {
        if( t1.root == t2.root && t1.root != null )
        {
            System.err.println( "leftTree==rightTree; merge aborted" );
            return;
        }

            // Allocate new node
        root = new BinaryNode<AnyType>( rootItem, t1.root, t2.root );

            // Ensure that every node is in one tree
        if( this != t1 )
            t1.root = null;
        if( this != t2 )
            t2.root = null;
    }

    public int size( )
    {
        return BinaryNode.size( root );
    }

    public int height( )
    {
        return BinaryNode.height( root );
    }

    BinaryNode<AnyType> getRoot()
    {
        return root;
    }
    
    private BinaryNode<AnyType> root;

    static public void main( String [ ] args ) {
        BinaryTree<Integer> t1  = new BinaryTree<Integer>( 1 );
        BinaryTree<Integer> t3  = new BinaryTree<Integer>( 3 );
        BinaryTree<Integer> t5  = new BinaryTree<Integer>( 5 );
        BinaryTree<Integer> t7  = new BinaryTree<Integer>( 7 );
        BinaryTree<Integer> t2  = new BinaryTree<Integer>( );
        BinaryTree<Integer> t4  = new BinaryTree<Integer>( );
        BinaryTree<Integer> t6  = new BinaryTree<Integer>( );
        BinaryTree<Integer> t8  = new BinaryTree<Integer>(8);
        BinaryTree<Integer> t9  = new BinaryTree<Integer>(9);
        BinaryTree<Integer> t10 = new BinaryTree<Integer>(10);
        BinaryTree<Integer> t11 = new BinaryTree<Integer>(11);
        BinaryTree<Integer> t12 = new BinaryTree<Integer>(12);
        BinaryTree<Integer> t13 = new BinaryTree<Integer>(13);
        BinaryTree<Integer> t14 = new BinaryTree<Integer>(14);
        BinaryTree<Integer> t15 = new BinaryTree<Integer>(15);

        t1.merge(1, t8, t9);
        t3.merge(3, t10, t11);
        t5.merge(5, t12, t13);
        t7.merge(7, t14, t15);
        t2.merge( 2, t1, t3 );
        t6.merge( 6, t5, t7 );
        t4.merge( 4, t2, t6 );

        System.out.println(countLeavesAtDepth(t4.getRoot(), 0, 3));
        System.out.println(isFull(t4.getRoot()));

        System.out.println("Before mirroring the tree ");
        t4.printPreOrder();
        mirror(t4.getRoot());
        System.out.println("After mirroring the tree ");
        t4.printPreOrder();
    }

    private static int countLeavesAtDepth(BinaryNode binaryNode, int current, int depth) {
        /* current is equal to 0 initially, then it'll increment until it reaches depth
        *  we have three case:
        *   1- if binaryNode is null (i.e it's empty, no node)              ==> 0
        *   2- if we reach the case where current is equal to depth         ==> 1
        *   3- otherwise, we recurse over the left child with current+1
        *           and right child with current+1
        * */
        if (binaryNode == null )
            return 0;
        if (current == depth)
            return 1;
        return countLeavesAtDepth(binaryNode.getLeft(), current+1, depth)
                + countLeavesAtDepth(binaryNode.getRight(), current+1, depth);
    }
    private static boolean isFull(BinaryNode<Integer> binaryNode) {
        /* we have three case:
        *   1- if binaryNode is null (i.e it's empty)                       ==> true
        *   2- if BinaryTree has one root with no right and left child      ==> true
        *   3- if the root has the right and left child then we recurse over
        *           the left child and right child
        *   4- otherwise                                                    ==> false
        * */

        return binaryNode == null
                || binaryNode.getLeft() == null && binaryNode.getRight() == null
                || binaryNode.getLeft() != null && binaryNode.getRight() != null
                && isFull(binaryNode.getLeft()) && isFull(binaryNode.getRight());

    }
    private static void mirror(BinaryNode binaryNode){
        if (binaryNode.getLeft() != null && binaryNode.getRight() != null){
            // duplicate the left child
            BinaryNode bn = binaryNode.getLeft().duplicate();
            // and assign it to the right child
            binaryNode.setRight(bn);

            // recurse over the right side
            mirror(binaryNode.getRight().duplicate());

        }




    }

}