package Java102.Hw2;
/*
*  Name : Rabia Abismail
*  ID : 140201209
*  Project on GitHub :  https://git.io/vwtVm
*/


public class Homework2 {
    private static int tmp;       // to store the value of grid[row][column]
    private static int c;

    public static void main(String[] arg){

        int[][] grid = new int[][]{
                {2, 0, 1, 1, 0, 8},
                {2, 1, 0, 2, 4, 0},
                {1, 2, 3, 1, 1, 3},
                {2, 3, 2, 0, 0, 0},
                {0, 0, 5, 8, 7, 2},
                {2, 0, 1, 1, 0, 0}};
        // before calling mergeNumbers method
        for (int[] aGrid : grid) {
            for (int j = 0; j < grid.length; j++)
                System.out.print(aGrid[j] + " ");
            System.out.println();
        }

        System.out.println();
        mergeNumbers(grid, 3, 3, 1);

        // After calling mergeNumbers method
        for (int[] aGrid : grid) {
            for (int j = 0; j < grid.length; j++)
                System.out.print(aGrid[j] + " ");
            System.out.println();
        }
    }
    // the merge method
    private static void mergeNumbers(int[][] grid, int row, int column, int nextNumber) {

        if (grid[row][column] == 0)
            grid[row][column] = nextNumber;
        else
            return;

        tmp = grid[row][column];
        while (checkNeighbor(grid, row, column)){
            checkUps    (grid, row-1, column, row, column);
            checkDowns  (grid, row+1, column, row, column);
            checkRights (grid, row, column+1, row, column);
            checkLefts  (grid, row, column-1, row, column);

            if (c>2)
                grid[row][column]++;
            else
                break;
        }
    }

    // this method checks the first upper, down, right and left neighbor
    private static boolean checkNeighbor(int[][] grid, int row, int column) {
        return grid[row][column] == grid[row-1][column]
                || grid[row+1][column] == grid[row][column]
                || grid[row][column-1] == grid[row][column]
                || grid[row][column+1] == grid[row][column];
    }

    // this method checks the first upper neighbor
    private static boolean checkUpNeighbors(int[][] grid, int row, int column) {
        return grid[row-1][column] == tmp;
    }

    // this method checks the first down neighbor
    private static boolean checkDownNeighbors(int[][] grid, int row, int column) {
        return grid[row+1][column] == tmp;
    }

    // this method checks the first right neighbor
    private static boolean checkRightNeighbors(int[][] grid, int row, int column) {
        return grid[row][column+1] == tmp;
    }

    // this method checks the first left neighbor
    private static boolean checkLeftNeighbors(int[][] grid, int row, int column) {
        return grid[row][column-1] == tmp;
    }


    //       - checkUps(int[][] grid, int currentRow, int currentColumn, int row, int column)
//             ==> travels along the upside of grid[row][column] and merges the identical adjacent number
//
//       - checkDowns(int[][] grid, int currentRow, int currentColumn, int row, int column)
//             ==> travels along the downside of grid[row][column] and merges the identical adjacent number
//
//       - checkRights(int[][] grid, int currentRow, int currentColumn, int row, int column)
//             ==> travels along the upside of grid[row][column] and merges the identical adjacent number
//
//       - checkLefts(int[][] grid, int currentRow, int currentColumn, int row, int column)
//             ==> travels along the upside of grid[row][column] and merges the identical adjacent number

    private static void checkUps(int[][] grid, int currentRow, int currentColumn, int row, int column){
        int counter = 0, element = grid[row][column];
        c=0;
        while (grid[currentRow][currentColumn] == element && row-counter>0){
            if (grid[currentRow][currentColumn-1] == element){
                grid[currentRow][currentColumn-1] = 0;
                c++;
            }
            if (grid[currentRow][currentColumn+1] == element){
                grid[currentRow][currentColumn+1] = 0;
                c++;
            }
            if (c>0) {
                grid[currentRow][currentColumn] = 0;
                c++;
//                if (counter>=1 )
//                    grid[currentRow+1][currentColumn] = 0;
//            } else {
//                //grid[currentRow][currentColumn] = 0;
//                System.out.println(checkLeftNeighbors(grid, row, column));
//                if (!checkLeftNeighbors(grid, row, column) || !checkDownNeighbors(grid, row, column)
//                        || !checkRightNeighbors(grid, row, column)){
//                    grid[currentRow][currentColumn] = tmp;
//
//                }
            }
            counter++;
            // in case we exceed the boundaries
            if (currentRow>0)   currentRow--;
            else
                break;
        }

    }
    private static void checkDowns(int[][] grid, int currentRow, int currentColumn, int row, int column){
        int counter = 0, element = grid[row][column];
        c=0;
        while (grid[currentRow][currentColumn] == element && row+counter<grid.length){
            if (grid[currentRow][currentColumn-1] == element){
                grid[currentRow][currentColumn-1] = 0;
                c++;
            }
            if (grid[currentRow][currentColumn+1] == element){
                grid[currentRow][currentColumn+1] = 0;
                c++;
            }
            if (c>0) {
                grid[currentRow][currentColumn] = 0;
                c++;
//                if (counter>=1 )
//                    grid[currentRow-1][currentColumn] = 0;
//            } else {
//                grid[currentRow][currentColumn] = 0;
//                if (!checkUpNeighbors(grid, row, column) || !checkLeftNeighbors(grid, row, column)
//                        || !checkRightNeighbors(grid, row, column)) {
//                    grid[currentRow][currentColumn] = tmp;
//                }
            }
            counter++;
            // in case we exceed the boundaries
            if (currentRow < grid.length-1) currentRow++;
            else
                break;
        }
    }
    private static void checkRights(int[][] grid, int currentRow, int currentColumn, int row, int column){
        int counter = 0, element = grid[row][column];
        c=0;
        while (grid[currentRow][currentColumn] == element && column+counter <6){
            if (grid[currentRow-1][currentColumn] == element){
                grid[currentRow-1][currentColumn] = 0;
                c++;
            }
            if (grid[currentRow+1][currentColumn] == element){
                grid[currentRow+1][currentColumn] = 0;
                c++;
            }
            if (c>0) {
                grid[currentRow][currentColumn] = 0;
                c++;
//                if (counter>=1)
//                    grid[currentRow][currentColumn-1] = 0;
//            }else {
//                grid[currentRow][currentColumn] = 0;
//                if (!checkUpNeighbors(grid, row, column) || !checkDownNeighbors(grid, row, column)
//                        || !checkLeftNeighbors(grid, row, column)){
//                    grid[currentRow][currentColumn] = tmp;
//                }
            }
            counter++;
            // in case we exceed the boundaries
            if (currentColumn < grid.length-1) currentColumn++;
            else
                break;
        }

    }
    private static void checkLefts(int[][] grid, int currentRow, int currentColumn, int row, int column){
        int counter = 0, element = grid[row][column];
        c=0;
        while (grid[currentRow][currentColumn] == element && column-counter>0){
            if (grid[currentRow-1][currentColumn] == element) {
                grid[currentRow-1][currentColumn] = 0;
                c++;
            }
            if (grid[currentRow+1][currentColumn] == element){
                grid[currentRow+1][currentColumn] = 0;
                c++;
            }
            if (c>0) {
                grid[currentRow][currentColumn] = 0;
                c++;
//                if (counter>=1 )
//                    grid[currentRow][currentColumn+1] = 0;
            }
//            else {
//                grid[currentRow][currentColumn] = 0;
//                if (!checkUpNeighbors(grid, row, column) || !checkDownNeighbors(grid, row, column)
//                        || !checkRightNeighbors(grid, row, column)){
//                    grid[currentRow][currentColumn] = tmp;
//                }
//            }
            counter++;
            // in case we exceed the boundaries
            if (currentColumn>0) currentColumn--;
            else
                break;
        }
    }
}