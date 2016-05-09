package Java102.Hw2;
 /*
*  Name : Rabia Abismail
*  ID   : 140201209
*  Project on GitHub : I'll upload it later under this link https://git.io/vwBMQ
*/

public class Assignment2 {
    // this variable will count the adjacent similarities
    private static int counterEquality;

    public static void main(String[] arg){

        int[][] grid = new int[][]{
                {0,2,2,2,2,2},
                {1,1,2,2,2,2},
                {2,2,2,1,0,2},
                {2,2,2,0,1,2},
                {2,2,1,2,1,2},
                {2,2,2,2,2,2}};
        System.out.println("before calling the merge method ");
        printArray(grid);

        System.out.println();
        mergeNumbers(grid, 0, 0, 1);

        System.out.println("After calling the merge method ");
        printArray(grid);
    }

    // the merge method
    private static void mergeNumbers(int[][] grid, int row, int column, int nextNumber) {
        // if the field is zero (i.e is empty), we will assign nextNumber to it.
        if (grid[row][column] == 0)
            grid[row][column] = nextNumber;
        // otherwise, if it is a number already, then we should return
        else
            return;

        // this array will be the test array
        int[][] tmpArray = new int[grid.length][grid.length];

        while (true){
            // get a copy of grid
            copyArray(grid, tmpArray);
            // initially is zero
            counterEquality = 0;
            // check all directions
            checkUps    (tmpArray, row, column, nextNumber);
            checkDowns  (tmpArray, row, column, nextNumber);
            checkRights (tmpArray, row, column, nextNumber);
            checkLefts  (tmpArray, row, column, nextNumber);

            // if counterEquality >=2 it means that we at least three same adjacent numbers
            if (counterEquality >=2){
                // replace tmpArray[row][column] value by its next value
                tmpArray[row][column] = ++nextNumber;
                // get back to grid with updated values
                copyArray(tmpArray, grid);
            }
            // otherwise, we will finish the loop
            else{
                break;
            }
        }
    }

    // this method checks the upper neighbors
    private static void checkUps(int[][] grid, int row, int column, int nextNumber){
        // currentRow ==> previous row
        int currentRow = row-1;
        if (currentRow >= 0 && grid[currentRow][column] == nextNumber){
            grid[currentRow][column] = 0;
            // check right side of the right field
            if (column < grid.length-1)
                checkRights(grid, currentRow, column, nextNumber);

            // check right side of the left field
            if (column > 0)
                checkLefts(grid, currentRow, column, nextNumber);

            // check right side of the upper field
            if (currentRow > 0)
                checkUps(grid, currentRow, column, nextNumber);

            // increment counterEquality
            counterEquality++;
        }
    }
    // this method checks the down neighbors
    private static void checkDowns(int[][] grid, int row, int column, int nextNumber){
        // currentRow ==> next row
        int currentRow = row+1;
        if (currentRow <= grid.length-1 && grid[currentRow][column] == nextNumber){
            grid[currentRow][column] = 0;

            // check right side of the down field
            if (currentRow<grid.length-1)
                checkDowns(grid, currentRow, column, nextNumber);

            // check right side of the left field
            if (column>0)
                checkLefts(grid,currentRow, column, nextNumber);

            // check right side of the right field
            if (column < grid.length-1)
                checkRights(grid, currentRow, column, nextNumber);

            // increment currentEquality
            counterEquality++;
        }
    }
    // this method checks the right neighbors
    private static void checkRights(int[][] grid, int row, int column, int nextNumber){
        // currentColumn ==> next column
        int currentColumn = column+1;
        if (currentColumn <= grid.length-1 && grid[row][currentColumn] == nextNumber ){
            grid[row][currentColumn] = 0;
            // check right side of the down field
            if (row<grid.length-1)
                checkDowns(grid, row, currentColumn, nextNumber);

            // check right side of the ups field
            if (row>0)
                checkUps(grid, row, currentColumn, nextNumber);

            // check right side of the right field
            if (currentColumn < grid.length-1)
                checkRights(grid, row, currentColumn, nextNumber);

            // increment counterEquality
            counterEquality++;
    }
}
    // this method checks the left neighbors
    private static void checkLefts(int[][] grid, int row, int column, int nextNumber){
        // currentColumn ==> previous column
        int currentColumn = column-1;
        if (currentColumn >= 0 && grid[row][currentColumn] == nextNumber){
            grid[row][currentColumn] = 0;

            // check right side of the down field
            if (row < grid.length-1)
                checkDowns(grid, row, currentColumn, nextNumber);

            // check right side of the left field
            if (currentColumn > 0)
                checkLefts(grid, row, currentColumn, nextNumber);

            // check right side of the upper field
            if (row > 0)
                checkUps(grid, row, currentColumn, nextNumber);

            counterEquality++;
        }
    }

    /*   There is a memory problem of using oldArray = newArray, since they
    *    will be pointing to same memory location. That is, if you change
    *    one of them the other will change accordingly
    *    For that reason, I implemented copyArray method
    */
    private static void copyArray(int[][] oldArray, int[][] newArray){
        for (int i=0; i<oldArray.length; i++)
            newArray[i] = oldArray[i].clone();
    }

    // A simple method to print array out.
    private static void printArray(int[][] array){
        for (int[] arr : array) {
            for (int j = 0; j < array.length; j++)
                System.out.print(arr[j] + " ");
            System.out.println();
        }
    }
}
