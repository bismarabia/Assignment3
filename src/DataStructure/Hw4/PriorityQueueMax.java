package DataStructure.Hw4;


/** Max-heap implementation */
public class PriorityQueueMax {

    private int[] array;         // to store elements
    private int currentSize;    // the number of elements
    private int maxsize;

    private static final int FIRST_INDEX = 1;

    public PriorityQueueMax (int maxsize) {
        this.maxsize = maxsize;
        this.currentSize = 0;
        array = new int[this.maxsize + 1];
        array[0] = Integer.MAX_VALUE;
    }

    private int parent(int pos)
    {
        return pos / 2;
    }

    private int leftChild(int pos)
    {
        return (2 * pos);
    }

    private int rightChild(int pos)
    {
        return (2 * pos) + 1;
    }

    private boolean isLeaf(int pos) {
        if (pos >=  (currentSize / 2)  &&  pos <= currentSize)
        {
            return true;
        }
        return false;
    }

    private void swap(int fpos,int spos) {
        int tmp;
        tmp = array[fpos];
        array[fpos] = array[spos];
        array[spos] = tmp;
    }

    private void percolateDown(int pos)
    {
        if (!isLeaf(pos))
        {
            if ( array[pos] < array[leftChild(pos)]  || array[pos] < array[rightChild(pos)])
            {
                if (array[leftChild(pos)] > array[rightChild(pos)])
                {
                    swap(pos, leftChild(pos));
                    percolateDown(leftChild(pos));
                }else
                {
                    swap(pos, rightChild(pos));
                    percolateDown(rightChild(pos));
                }
            }
        }
    }

    public void add(int element) {
        if (currentSize + 1 == array.length)
            doubleArray();

        array[++currentSize] = element;
        int current = currentSize;

        while(array[current] > array[parent(current)])
        {
            swap(current,parent(current));
            current = parent(current);
        }
    }

    public int element() {

        return array[ 1 ];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void print() {
        System.out.println("the size of the max-heap == "+ currentSize);
        for (int i = 1; i<= currentSize; i++)
            System.out.print(array[i]+" ");
    }

    public void buildHeap() {
        for (int pos = (currentSize / 2); pos >= 1; pos--)
        {
            percolateDown(pos);
        }
    }

    public void clear() {
        currentSize = 0;
    }

    public int remove() {
        int popped = array[FIRST_INDEX];
        array[FIRST_INDEX] = array[currentSize--];
        percolateDown(FIRST_INDEX);
        return popped;
    }

    public int size() {
        return currentSize;
    }

    private void doubleArray(){
        int[] newArray = new int[array.length*2];
        for (int i = 0; i < array.length; i++ )
            newArray[i] = array[i];
        array = newArray;
    }
}
