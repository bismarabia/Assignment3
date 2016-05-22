package DataStructure.Hw4;

/*
* Name: Rabia Abismail
* ID: 140201209
* Project on Github: https://git.io/vr2ww
* */

import java.util.Random;

public class medianHeap {

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
        else
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
        Random random = new Random();
        int index = 0, number;
        double Median=0;
        medianHeap med = new medianHeap();

        for(;index++<10; ) {
            number = random.nextInt(50);
            System.out.println("number == "+number);
            Median = med.findMedian(number);
            System.out.println(Median);
        }

        System.out.println("\n\n===============================\nPrinting the minHeap ");
        med.priorityQueueMin.print();
        System.out.println("\n\nprinting the maxHeap ");
        med.priorityQueueMax.print();
        System.out.println("\n\nMedian == "+Median+"\n===============================");

    }

}
