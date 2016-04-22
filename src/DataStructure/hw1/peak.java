package DataStructure.hw1;

public class peak {
    public static void main(String[] ar){
        //An example
        int[] array = {-13, -4, 1, 2, 4, 7, 20, 30, 31, 33, 140, 144, 150, 222, 30, 20, 11};
        System.out.println(findThePickEntry(array, 0, array.length - 1));
    }

    public static int findThePickEntry(int[] array, int start, int end){
        //get the middle position
        int middle = (start+end)/2;

        //if the order is increasing
        if (array[middle]<array[middle+1] && array[middle]>array[middle-1]){
            return findThePickEntry(array, middle+1, end);
        }
        //if the order is decreasing
        else if (array[middle]>array[middle+1] && array[middle]<array[middle-1]){
            return findThePickEntry(array, start, middle-1);
        }
        //if the middle is the peak itself
        return middle;
    }
}
