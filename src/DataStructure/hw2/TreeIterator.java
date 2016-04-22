package DataStructure.hw2;

import java.util.NoSuchElementException;

public abstract class TreeIterator<AnyType>
{

    public TreeIterator( BinaryTree<AnyType> theTree )
    {
        t = theTree;
        current = null;
    }

    abstract public void first( );

    final public boolean isValid( )
    {
        return current != null;
    }

    final public Object retrieve( )
    {
        if( current == null )
            throw new NoSuchElementException( "TreeIterator retrieve" );
        return current.getElement( );
    }

    abstract public void advance( );

        /** The tree. */
    protected BinaryTree<AnyType> t;        // Tree
        /** Current position. */
    protected BinaryNode current;  // Current position
}

class PostOrder<AnyType> extends TreeIterator<AnyType>
{

    public PostOrder( BinaryTree<AnyType> theTree )
    {
        super( theTree );
        s = new ArrayStack<StNode<AnyType>>( );
        s.push( new StNode<AnyType>( t.getRoot( ) ) );
    }

    public void first( )
    {
        s.makeEmpty();
        if( t.getRoot( ) != null )
        {
            s.push( new StNode<AnyType>( t.getRoot( ) ) );
            advance( );
        }
    }


    public void advance( )
    {
        if( s.isEmpty( ) )
        {
            if( current == null )
                throw new NoSuchElementException( "PostOrder Advance" );
            current = null;
            return;
        }

        StNode<AnyType> cnode;

        for( ; ; )
        {
            cnode = s.topAndPop( );

            if( ++cnode.timesPopped == 3 )
            {
                current = cnode.node;
                return;
            }

            s.push( cnode );
            if( cnode.timesPopped == 1 )
            {
                if( cnode.node.getLeft( ) != null )
                    s.push( new StNode<AnyType>( cnode.node.getLeft( ) ) );
            }
            else  // cnode.timesPopped == 2
            {
                if( cnode.node.getRight( ) != null )
                    s.push( new StNode<AnyType>( cnode.node.getRight( ) ) );
            }
        }
    }

      // An internal class for tree iterators
    protected static class StNode<AnyType>
    {
        BinaryNode<AnyType> node;
        int timesPopped;

        StNode( BinaryNode<AnyType> n )
        {
           node = n;
           timesPopped = 0;
        }
    }
        /** An internal stack if visited nodes. */
    protected ArrayStack<StNode<AnyType>> s;    // The stack of StNode objects
}


class InOrder<AnyType> extends PostOrder<AnyType>
{
    public InOrder( BinaryTree<AnyType> theTree )
    {
        super( theTree );
    }

    public void advance( )
    {
        if( s.isEmpty( ) )
        {
            if( current == null )
                throw new NoSuchElementException( "InOrder advance" );
            current = null;
            return;
        }

        StNode<AnyType> cnode;

        for( ; ; )
        {
            cnode = s.topAndPop( );

            if( ++cnode.timesPopped == 2 )
            {
                current = cnode.node;
                if( cnode.node.getRight( ) != null )
                    s.push( new StNode<AnyType>( cnode.node.getRight( ) ) );
                return;
            }

                // First time through
            s.push( cnode );
            if( cnode.node.getLeft( ) != null )
                s.push( new StNode<AnyType>( cnode.node.getLeft( ) ) );
        }
    }
}

class PreOrder<AnyType> extends TreeIterator<AnyType>
{

    public PreOrder( BinaryTree<AnyType> theTree )
    {
        super( theTree );
        s = new ArrayStack<BinaryNode<AnyType>>( );
        s.push( t.getRoot( ) );
    }

    public void first( )
    {
        s.makeEmpty( );
        if( t.getRoot( ) != null )
        {
            s.push( t.getRoot( ) );
            advance( );
        }
    }


    public void advance( )
    {
        if( s.isEmpty( ) )
        {
            if( current == null )
                throw new NoSuchElementException( "PreOrder Advance" );
            current = null;
            return;
        }

        current = s.topAndPop( );

        if( current.getRight( ) != null )
            s.push( current.getRight( ) );
        if( current.getLeft( ) != null )
            s.push( current.getLeft( ) );
    }

        /** An internal stack of visited nodes */
    private ArrayStack<BinaryNode<AnyType>> s;    // Stack of BinaryNode objects
}


class LevelOrder<AnyType> extends TreeIterator<AnyType>
{
    public static final int DEFAULT_CAPACITY = 10;
    LevelOrder(BinaryTree<AnyType> theTree)
    {
        super( theTree );
        q = new ListQueue<BinaryNode<AnyType>>();
        q.enqueue( t.getRoot( ) );
    }


    public void first( )
    {
        q.makeEmpty( );
        if( t.getRoot( ) != null )
        {
            q.enqueue( t.getRoot( ) );
            advance( );
        }
    }

    public void advance( )
    {
        if( q.isEmpty( ) )
        {
            if( current == null )
                throw new NoSuchElementException( "LevelOrder advance" );
            current = null;
            return;
        }

        current = (BinaryNode) q.dequeue( );

        if( current.getLeft( ) != null )
            q.enqueue( current.getLeft( ) );
        if( current.getRight( ) != null )
            q.enqueue( current.getRight( ) );
    }

        /** An internal queue of visited nodes */
    private ListQueue q;  // Queue of BinaryNode objects
}

abstract class TestTreeIterators
{
        // Test program
    public static void main( String[ ] args )
    {
        BinaryTree<Integer> t = new BinaryTree<Integer>( );

        testItr( "PreOrder", new PreOrder<Integer>( t ) ); // Test empty tree

        BinaryTree<Integer> t1 = new BinaryTree<Integer>( 1 );
        BinaryTree<Integer> t3 = new BinaryTree<Integer>( 3 );
        BinaryTree<Integer> t5 = new BinaryTree<Integer>( 5 );
        BinaryTree<Integer> t7 = new BinaryTree<Integer>( 7 );
        BinaryTree<Integer> t2 = new BinaryTree<Integer>( );
        BinaryTree<Integer> t4 = new BinaryTree<Integer>( );
        BinaryTree<Integer> t6 = new BinaryTree<Integer>( );

        t2.merge( 2, t1, t3 );
        t6.merge( 6, t5, t7 );
        t4.merge( 4, t2, t6 );
        
        t = t4;

        testItr( "Preorder", new PreOrder<Integer>( t ) );
        testItr( "Postorder", new PostOrder<Integer>( t ) );
        testItr( "Inorder", new InOrder<Integer>( t ) );
        testItr( "Level order", new LevelOrder<Integer>( t ) );
    }

    public static <AnyType> void testItr( String type, TreeIterator<AnyType> itr )
    {
        try
        {
            System.out.print( type + ":" );
            for( itr.first( ); itr.isValid( ); itr.advance( ) )
                System.out.print( " " + itr.retrieve( ) );
            System.out.println( );
            itr.advance( );
        }
        catch( NoSuchElementException e )
          { System.out.println( e + " (as expected)" ); }
    }

}
