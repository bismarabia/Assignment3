package DataStructure.hw2;
/*
* Name : Rabia Abismail
* ID : 140201209
* Project on github: /
* */


public class compress {

    public static void main(String[] arr){
        ArrayStack<Integer> s = new ArrayStack<Integer>();
        int[] array = {10, 20, 10, 20, 20, 10};
        for (int element : array)
            s.push(element);
        compressDuplicates(s);
    }

    private static void compressDuplicates(ArrayStack<Integer> theStack){
        ListQueue<Integer> q = new ListQueue<Integer>();
        int counter = 1;        // to count the number of occurrences


        // get the first element
        Integer number = theStack.topAndPop();
        /*
            the program will @throw an exception if the stack is empty
        */

        // enqueue number to q
        q.enqueue(number);

        /*
        *  This while will pop all elements of stack
        *  and count all the occurrences of the same number
        *  then, enqueue the count in q
        * */
        while (!theStack.isEmpty()){
            // pop all the same number
            // count all the occurrences
            if (theStack.top().equals(number) ){
                counter++;
                theStack.pop();
            }
            // if we found a new different number
            else{
                // enqueue the counter
                q.enqueue(counter);

                // get a new number
                number = theStack.topAndPop();

                // enqueue the number
                q.enqueue(number);

                // set counter to default (to 1)
                counter = 1;
            }
        }
        // enqueue the last counter
        q.enqueue(counter);

        // push all the elements in q into theStack
        while (!q.isEmpty())
            theStack.push(q.dequeue());

        // print the stack
        printStack(theStack);
    }

    // a method to print a stack
    private static void printStack(ArrayStack theStack){
        while (!theStack.isEmpty())
            System.out.print(theStack.topAndPop()+" ");
    }

}
