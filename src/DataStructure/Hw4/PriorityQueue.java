package DataStructure.Hw4;


import java.util.*;

/**
 * PriorityQueue class implemented via the binary heap.
 */
public class PriorityQueue<Integer> extends AbstractCollection<Integer>
{
    private static class DefaultComparator implements Comparator
    {
        public int compare (Object o1, Object o2)
        {
            return ((Comparable) o1).compareTo(o2);
        }
    }
    private Comparator myComp = new DefaultComparator();

    private int         currentSize;
    private ArrayList   array;

    /**
     * This is a trivial iterator class that returns
     * elements in the PriorityQueue ArrayList field
     * one-at-a-time
     */
    private class iterator implements Iterator{
        public Object next()
        {
            return array.get(myCursor);
        }

        public boolean hasNext()
        {
            return myCursor <= currentSize;
        }

        public void remove()
        {
            throw new UnsupportedOperationException("remove not implemented");
        }

        private int myCursor = 1;
    }

    /**
     * constructs an empty priority queue, when new elements
     * are added their natural order will be used to determine
     * which is minimal. This means elements that are added must
     * implement the <code>Comparable</code> interface and must
     * be <em>mutually comparable</em>, i.e.,
     * <code>e1.compareTo(e2)</code> must not throw a
     * <code>CastCastException</code> for any two elements <code>e1</code>
     * and <code>e2</code> in the priority queue.
     */
    public PriorityQueue(){
        array = new ArrayList(32);
        array.add(null);             // first slot has index 1
        currentSize = 0;
    }

    /**
     * constructs an empty priority queue, when new elements
     * are added the <code>Comparator comp</code> determines which is
     * smaller.
     *
     * @param cmp is the <code>Comparator</code> used in determining order
     */
    public PriorityQueue(Comparator cmp){
        this();
        myComp = cmp;
    }

    /**
     * all elements in coll are added to the priority queue. The
     * complexity is O(n) where <code>coll.size() == n</code>
     *
     * @param coll is a collection of mutually comparable elements
     */
    public PriorityQueue(Collection coll) {
        this();
        array.addAll(coll);
        currentSize = coll.size();

        buildHeap();
    }

    /**
     * A new element <code>o</code> is added to the priority queue
     * in O(log n) time where n is the size of the priority queue.
     * <P>
     * The return value should be ignored, a boolean value must be
     * returned because of the requirements of the
     * <code>Collection</code> interface.
     *
     * @param x is the (Comparable) object added to the priority queue
     * @return true
     */
    public boolean add(Object x) {
        if (currentSize +1 == array.size())
            doubleArray();

        array.add(x);        // stored, but not correct location
        currentSize++;             // added element, update count
        int k = currentSize;       // location of new element

        while (k > 1 && myComp.compare(array.get(k/2), x) > 0)
        {
            array.set(k, array.get(k/2));
            k /= 2;
        }
        array.set(k,x);
        return true;
    }

    private void doubleArray(){
        ArrayList newArray = new ArrayList(array.size()*2);
        for (int i = 0; i < array.size(); i++ )
            newArray.add(i, array.get(i));
        array = (ArrayList) newArray.clone();
    }

    /**
     * @return the number of elements in the priority queue
     */
    public int size()
    {
        return currentSize;
    }

    public void clear( )
    {
        currentSize = 0;
    }

    /**
     * @return true if and only if the priority queue is empty
     */
    public boolean isEmpty()
    {
        return currentSize == 0;
    }

    /**
     * The smallest/minimal element is removed and returned
     * in O(log n) time where n is the size of the priority queue.
     *
     * @return the smallest element (and removes it)
     */
    public int remove() {
        if (! isEmpty())
        {
            int minItem = element();

            array.set(1, array.get(currentSize));  // move last to top
            array.remove(currentSize);              // pop last off
            currentSize--;
            if (currentSize > 1)
            {
                percolateDown(1);
            }
            return minItem;
        }
        return java.lang.Integer.parseInt(null);
    }

    /**
     * Executes in O(1) time, returns smallest element
     * @return the minimal element in the priority queue
     */
    public int element()
    {
        return (int) array.get(1);
    }

    /**
     * The order of the elements returned by the iterator is not specified
     * @return an iterator of all elements in priority queue
     */
    public Iterator iterator()
    {
        return new iterator();
    }

    /**
     * works in O(log(size()-vroot)) time
     * @param hole is the index at which re-heaping occurs
     * @precondition: subheaps of index vroot are heaps
     * @postcondition: heap rooted at index vroot is a heap
     */
    private void percolateDown(int hole) {
        int child;
        Object tmp = array.get(hole);
        while (2*hole <= currentSize)
        {
            child = 2*hole;
            if (child < currentSize &&
                    myComp.compare(array.get(child),
                            array.get(child+1)) > 0)
            {
                child++;
            }
            if (myComp.compare(tmp, array.get(child)) <= 0)
            {
                break;
            }
            else
            {
                array.set(hole, array.get(child));
                hole = child;
            }
        }
        array.set(hole, tmp);
    }

    private void buildHeap(){
        for (int i = currentSize /2; i>0; i-- )
            percolateDown(i);
    }

    public void print(){
        System.out.println("the size of the min-heap == "+ currentSize);
        for (int i = 1; i<= currentSize; i++)
            System.out.print(array.get(i)+" ");

    }
}
