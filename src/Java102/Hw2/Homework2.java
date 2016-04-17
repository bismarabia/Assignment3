package Java102.Hw2;
/*
*  Name : Rabia Abismail
*  ID : 140201209
*
**/


public class Homework2 {
    private static boolean flag;
    private static int tmp;

    public static void main(String[] ar){

        int[][] grid = new int[][]{{2, 0, 1, 1, 0, 8}, {2, 1, 0, 5, 4, 0}, {1, 2, 1, 2, 1, 3}, {2, 5, 2,
                0, 1, 0}, {0, 0, 2, 2, 7, 2}, {2, 0, 1, 1, 0, 0}};
        for (int[] aGrid : grid) {
            for (int j = 0; j < grid.length; j++)
                System.out.print(aGrid[j] + " ");
            System.out.println();
        }

        System.out.println();
        mergeNumbers(grid, 3, 3, 1);

        for (int[] aGrid : grid) {
            for (int j = 0; j < grid.length; j++)
                System.out.print(aGrid[j] + " ");
            System.out.println();
        }
    }

    private static void mergeNumbers(int[][] grid, int row, int column, int nextNumber) {

        grid[row][column] = grid[row][column] == 0 ? nextNumber : grid[row][column];

        tmp = grid[row][column];
        while (checkNeighbors(grid, row, column)){
            checkUps    (grid, row-1, column, row, column);
            checkDowns  (grid, row+1, column, row, column);
            checkRights (grid, row, column+1, row, column);
            checkLefts  (grid, row, column-1, row, column);

            grid[row][column]++;
        }
    }

    private static boolean checkNeighbors(int[][] grid, int row, int column) {
        return grid[row][column] == grid[row-1][column]
                || grid[row+1][column] == grid[row][column]
                || grid[row][column-1] == grid[row][column]
                || grid[row][column+1] == grid[row][column];
    }
    private static boolean checkUpNeighbors(int[][] grid, int row, int column) {
        return grid[row-1][column] == tmp;
    }
    private static boolean checkDownNeighbors(int[][] grid, int row, int column) {
        return grid[row+1][column] == tmp;
    }
    private static boolean checkRightNeighbors(int[][] grid, int row, int column) {
        return grid[row][column+1] == grid[row][column+1];
    }
    private static boolean checkLeftNeighbors(int[][] grid, int row, int column) {
        return grid[row][column-1] == tmp;
    }

    private static void checkUps(int[][] grid, int cr, int cc, int r, int c){
        int counter = 0, element = grid[r][c];
        flag = false;
        while (grid[cr][cc] == element && counter<3 && r-counter>0){
            if (grid[cr][cc-1] == element){
                grid[cr][cc-1] = 0;
                flag = !flag;
            }
            if (grid[cr][cc+1] == element){
                grid[cr][cc+1] = 0;
                flag = !flag;
            }
            if (counter>=1) {
                grid[cr][cc] = 0;
                grid[cr+1][cc] = 0;
            } else {
                grid[cr][cc] = 0;
                if (!checkLeftNeighbors(grid, r, c) && !checkDownNeighbors(grid, r, c)
                        && !checkRightNeighbors(grid, r, c))
                    grid[cr][cc] = tmp;
            }
            counter++;
            if (cr>0)   cr--;
            else
                break;
        }
    }
    private static void checkDowns(int[][] grid, int cr, int cc, int r, int c){
        int counter = 0, element = grid[r][c];
        flag = false;
        while (grid[cr][cc] == element && counter<2 && r+counter<6){
            if (grid[cr][cc-1] == element){
                grid[cr][cc-1] = 0;
                flag = true;
            }
            if (grid[cr][cc+1] == element){
                grid[cr][cc+1] = 0;
                flag = true;
            }
            if (flag) {
                grid[cr][cc] = 0;
                if (counter>=1 )
                    grid[cr-1][cc] = 0;
            } else {
                grid[cr][cc] = 0;
                if (!checkUpNeighbors(grid, r, c) && !checkLeftNeighbors(grid, r, c)
                        && !checkRightNeighbors(grid, r, c))
                    grid[cr][cc] = tmp;
            }
            counter++;
            if (cr < grid.length-1) cr++;
            else
                break;
        }
    }
    private static void checkRights(int[][] grid, int cr, int cc, int r, int c){
        int counter = 0, element = grid[r][c];
        flag = false;
        while (grid[cr][cc] == element && counter < 2 && c+counter <6){
            if (grid[cr-1][cc] == element){
                grid[cr-1][cc] = 0;
                flag = true;
            }
            if (grid[cr+1][cc] == element){
                grid[cr+1][cc] = 0;
                flag = true;
            }
            if (flag) {
                grid[cr][cc] = 0;
                if (counter>=1)
                    grid[cr][cc-1] = 0;
            }else {
                grid[cr][cc] = 0;
                if (!checkUpNeighbors(grid, r, c) && !checkDownNeighbors(grid, r, c)
                        && !checkLeftNeighbors(grid, r, c))
                    grid[cr][cc] = tmp;
            }
            counter++;
            if (cc < grid.length-1) cc++;
            else
                break;
        }
    }
    private static void checkLefts(int[][] grid, int cr, int cc, int r, int c){
        int counter = 0, element = grid[r][c];
        flag = false;
        while (grid[cr][cc] == element && counter<3 && c-counter>0){
            if (grid[cr-1][cc] == element) {
                grid[cr-1][cc] = 0;
                flag = true;
            }
            if (grid[cr+1][cc] == element){
                grid[cr+1][cc] = 0;
                flag =true;
            }
            if (counter>=1) {
                grid[cr][cc] = 0;
                grid[cr][cc+1] = 0;
            }
            else {
                grid[cr][cc] = 0;
                if (!checkUpNeighbors(grid, r, c) && !checkDownNeighbors(grid, r, c)
                        && !checkRightNeighbors(grid, r, c))
                    grid[cr][cc] = tmp;
            }
            counter++;
            if (cc>0) cc--;
            else
                break;
        }
    }
}
