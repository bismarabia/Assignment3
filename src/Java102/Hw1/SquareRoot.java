package Java102.Hw1;
/*
Name: Rabia Abismail
ID: 140201209
Date: 04 April 2016
*/

public class SquareRoot {
    public static void main(String[] ar){
        System.out.println("the square root of 650 is: " + findSquareRoot(650, 10, 5));
        System.out.println("the square root of 650 is: " + findSquareRootRec(650, 10, 5));
    }


    // the method using usual way to find square
    private static double findSquareRoot(double n, int guess, int error){
        if (n<0)
            return -1;
        else if (n==0)
            return 0;
        double x0 = guess;
        double x1= guess-((Math.pow(guess, 2)-n)/(2*guess));
        while (Math.abs(x1-x0)>= Math.pow(10, -1*error)){
            x0 = x1;
            x1 = x1-((Math.pow(x1, 2)-n)/(2*x1));
        }
        return x1;
    }

    // the method using the recursion to find square
    private static double findSquareRootRec(double n, double guess, int error){
        if (n<0)
            return -1;
        else if (n==0)
            return 0;
        double x0 = guess;
        double x1= guess-((Math.pow(guess, 2)-n)/(2*guess));
        return Math.abs(x1-x0) < Math.pow(10, -1*error) ? x1 : findSquareRootRec(n, x1, error);
    }
}
