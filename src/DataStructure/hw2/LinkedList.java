package DataStructure.hw2;
/*
* Name : Rabia Abismail
* ID   : 140201209
* Project on github: /
* */

public class LinkedList<AnyType>
{
    public LinkedList( )
    {
        header = new ListNode<AnyType>( null );
    }

    public boolean isEmpty( )
    {
        return header.next == null;
    }

    public void makeEmpty( )
    {
        header.next = null;
    }

    public LinkedListIterator<AnyType> zeroth( )
    {
        return new LinkedListIterator<AnyType>( header );
    }

    public LinkedListIterator<AnyType> first( )
    {
        return new LinkedListIterator<AnyType>( header.next );
    }

    public void insert( AnyType x, LinkedListIterator<AnyType> p )
    {
        if( p != null && p.current != null )
            p.current.next = new ListNode<AnyType>( x, p.current.next );
    }

    public LinkedListIterator<AnyType> find( AnyType x )
    {
        ListNode<AnyType> itr = header.next;

        while( itr != null && !itr.element.equals( x ) )
            itr = itr.next;

        return new LinkedListIterator<AnyType>( itr );
    }

    public LinkedListIterator<AnyType> findPrevious( AnyType x )
    {
        ListNode<AnyType> itr = header;

        while( itr.next != null && !itr.next.element.equals( x ) )
            itr = itr.next;

        return new LinkedListIterator<AnyType>( itr );
    }

    public void remove( AnyType x )
    {
        LinkedListIterator<AnyType> p = findPrevious( x );

        if( p.current.next != null )
            p.current.next = p.current.next.next;  // Bypass deleted node
    }

    public static <AnyType> void printList( LinkedList<AnyType> theList )
    {
        if( theList.isEmpty( ) )
            System.out.print( "Empty list" );
        else
        {
            LinkedListIterator<AnyType> itr = theList.first( );
            for( ; itr.isValid( ); itr.advance( ) )
                System.out.print( itr.retrieve( ) + " " );
        }

        System.out.println( );
    }

    private ListNode<AnyType> header;

    public static <AnyType> int listSize( LinkedList<AnyType> theList )
    {
        LinkedListIterator<AnyType> itr;
        int size = 0;
        
        for( itr = theList.first(); itr.isValid(); itr.advance() )
            size++;
            
        return size;    
    }

    public static void main( String [ ] args )
    {
        LinkedList<Integer> theList = new LinkedList<Integer>( );
        LinkedListIterator<Integer> theItr;
        theItr = theList.zeroth();
        theList.insert(4, theItr);
        theList.insert(17, theItr);
        theList.insert(29, theItr);
        theList.insert(3, theItr);
        theList.insert(8, theItr);
        theList.insert(5, theItr);
        theList.insert(11, theItr);
        theList.insert(14, theItr);
        theList.insert(20, theItr);
        theList.insert(55, theItr);
        theList.insert(66, theItr);
        theList.insert(88, theItr);

        printList(theList);
        theList.shift();
    }

    private void shift(){
        LinkedListIterator<AnyType> itr = this.first();   // to iterate over odd position
        LinkedListIterator<AnyType> it;                   // for the last item in the linkedList
        AnyType tmpElement;                               // to store the element that will be removed and inserted
        int size = listSize(this);
        int pos = 0;

        while (itr.current.next != null && pos<size/2){
            // will return if the linkedList contains exactly one or two elements
            if (size<=2)
                return;

            it = this.first();              // it points to the first node
            itr.advance();                  // advance itr to the odd position
            tmpElement = itr.retrieve();    // get the element
            itr.advance();                  // advance one more

            // this for loop will make it points to the last element
            for(int i=0; i<size-1; i++)
                it.advance();

            this.remove(tmpElement);        // remove tmpElement
            this.insert(tmpElement, it);    // insert tmpElement at the end of the list
            pos++;                          // increment pos
        }
        printList(this);
    }


}
