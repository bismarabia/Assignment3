package DataStructure.Hw4;

/*
* Name: Rabia Abismail
* ID: 140201209
* Project on Github:
* */

public class medianHeap {

    private MinHeap minHeap = new MinHeap(4);
    private PriorityQueue    priorityQueueMin  = new PriorityQueue();           // min-heap
    private PriorityQueueMax priorityQueueMax  = new PriorityQueueMax();        // max-heap

    public double findMedian (int number) {
        // this is in case of the first element.
        if (priorityQueueMin.size() == 0 && priorityQueueMax.size()==0)
            priorityQueueMin.add(number);
        // if number is greater than root of min-heap, we will insert it in max-heap
        else if (number >= priorityQueueMin.element())
            priorityQueueMin.add(number);
        // if number is less than root of max-heap, we will insert it in min-heap
        else if (number < priorityQueueMax.element())
            priorityQueueMax.add(number);

        // returning the median
        System.out.print("Median  == ");
        if (priorityQueueMax.size() - priorityQueueMin.size() == 1)         // the sizes differ by 1
            return priorityQueueMax.element();
        else if (priorityQueueMin.size() - priorityQueueMax.size() == 1)    // the sizes differ by 1
            return priorityQueueMin.element();
        //  if the size of max-heap is greater by 2 than min heap,
        //    we will pop the root of the max and inset it in min-heap.
        //    and return the root of min-heap as the median.
        //    and Vice versa.
        else if (priorityQueueMax.size() - priorityQueueMin.size() == 2 && priorityQueueMax.size()!=0)
            priorityQueueMin.add(priorityQueueMax.remove());
        else if (priorityQueueMin.size() - priorityQueueMax.size() == 2 && priorityQueueMin.size()!=0)
            priorityQueueMax.add(priorityQueueMin.remove());
        // otherwise, if both heaps have the same size, we will return the median as the average between the roots.
        return  (priorityQueueMin.element() + priorityQueueMax.element()) / 2.0;
    }

    public static void main (String[] args) {
        int index = 0;
        medianHeap med = new medianHeap();
        int[] array = {3, 5, 2, 9, 7, 1, 10, 0, 8, 11, 45, 100};

        for(;index<array.length ; ) {
            System.out.println(med.findMedian(array[index++]));
        }

        System.out.println("printing the minHeap ");
        med.priorityQueueMin.print();
        System.out.println("\nprinting the maxHeap ");
        med.priorityQueueMax.print();

    }

}
