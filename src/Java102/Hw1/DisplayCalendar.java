package Java102.Hw1;
/*
Name: Rabia Abismail
ID: 140201209
Date: 04 April 2016
*/

import java.util.Scanner;

public class DisplayCalendar {
    public static void main(String[] ar){
        calendar();
    }

    // the calendar display method
    private static void calendar() {
        Scanner sc = new Scanner(System.in);

        final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December"};
        final String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        System.out.print("Enter the year\t");
        int year = sc.nextInt();
        // checks whether year is less than 1
        while (true){
            if (year>1)  break;
            System.err.print("Errorr!! \nEnter a positive non-zero value for year\t");
            year = sc.nextInt();
        }
        System.out.print("Enter the starting day (from 0 to 6)\t");
        int startingDay = sc.nextInt();
        // checks whether starting day is between 0 and 6, or not
        while (true){
            if (startingDay>=0 && startingDay<=6) break;
            System.err.print("Errorr!! \nEnter a valid starting day (from 0 to 6)\t");
            startingDay = sc.nextInt();
        }

        int startingDayEachmonth = 0;
        for (int i=0; i<months.length; i++){
            System.out.println("\n\n\t\t\t\t\t"+months[i] + " " + year);

            for (int j=0; j<52; j++)
                System.out.print("_");
            System.out.println();
            // printing the days
            for (String day : days)
                System.out.print(day + "\t\t");
            System.out.println();

            for (int j=0; j<startingDay; j++)
                System.out.print("\t\t ");
            // printing the days (numbers) for the first line
            for (int j=0; j<7-startingDay; j++)
                System.out.print((j+1)+"\t\t ");
            // printing the days (numbers) for the rest of the lines
            for (int j=7-startingDay, k=0; (i)%2==1? j<30:j<31; j++, k++){
                startingDayEachmonth = (k+1)%7;
                if ((k)%7==0) System.out.println();
                System.out.print((j + 1) + "\t\t ");
                if (i==1){    //if it's February
                    if (isLeapYear(year) && j==28)
                        break;
                    else if (!isLeapYear(year) && j==27)
                        break;
                }
            }
            startingDay = startingDayEachmonth;
        }
    }

    // a method to check whether it's leap or not
    private static boolean isLeapYear(int i){
        return  (i % 4 == 0) && (i % 100 != 0) || (i % 400 == 0);
    }
}
