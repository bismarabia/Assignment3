package DataStructure.hw1;

import java.util.Arrays;

public class inversions {
    public static void main(String[] a){
        //An example
        int[] array = {4, 80, 70, 23, 9, 60, 68, 27, 66, 78, 12, 40, 52, 53, 44, 8, 49, 28,
                18, 46, 21, 39, 51, 7, 87, 99, 69, 62, 84, 6, 79, 67, 14, 98, 83, 0, 96, 5, 82, 10, 26, 48, 3, 2, 15, 92,
                11, 55, 63, 97, 43, 45, 81, 42, 95, 20, 25, 74, 24, 72, 91, 35, 86, 19, 75, 58, 71, 47, 76, 59, 64, 93, 17,
                50, 56, 94, 90, 89, 32, 37, 34, 65, 1, 73, 41, 36, 57, 77, 30, 22, 13, 29, 38, 16, 88, 61, 31, 85, 33, 54};
        System.out.println(count(array, array.length));
    }

    public static int count(int[] array, int n) {
        int x, y, z;
        //base case
        if (n < 1)
            return 0;
        else {
            //getting the middle position
            int middle = (n+1) / 2;
            int leftArray[] = Arrays.copyOfRange(array, 0, middle);                 // first half
            int rightArray[] = Arrays.copyOfRange(array, middle, array.length);     // second half

            x = count(leftArray, n/2);
            y = count(rightArray, n/2);
            z = countSplit(array, leftArray, rightArray);
        }

        return x + y + z;
    }

    // if (i ? n / 2 < j)
    public static int countSplit(int[] array, int[] firstHalf, int[] secondHalf) {
        int leftPos = 0, rightPos = 0, countInversions = 0;

        while (leftPos < firstHalf.length || rightPos < secondHalf.length) {
            if (leftPos == firstHalf.length) {
                array[leftPos+rightPos] = secondHalf[rightPos];
                rightPos++;
            } else if (rightPos == secondHalf.length) {
                array[leftPos+rightPos] = firstHalf[leftPos];
                leftPos++;
            } else if (firstHalf[leftPos] <= secondHalf[rightPos]) {
                array[leftPos+rightPos] = firstHalf[leftPos];
                leftPos++;
            } else {
                array[leftPos+rightPos] = secondHalf[rightPos];
                countInversions += firstHalf.length-leftPos;
                rightPos++;
            }
        }
        return countInversions;
    }

}
