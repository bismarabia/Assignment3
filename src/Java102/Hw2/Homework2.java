package Java102.Hw2;

import java.util.Scanner;

public class Homework2 {
    private static boolean flag = false;
    private static int size;
    private static int[][] grid;

    private Homework2(int size, int[][] grid){
        Homework2.grid = grid;
        Homework2.size = size;
    }

    public static void main(String[] ar){
        Scanner s = new Scanner(System.in);
        System.out.print("enter a size = ");
        int size = s.nextInt();
        Homework2 hw = new Homework2(size, grid);


        int[][] grid = {{2, 0, 1, 1, 0, 8}, {2, 1, 0, 2, 4, 0}, {1, 2, 1, 2, 1, 3}, {2, 3, 2, 0, 1, 0},
                {0, 0, 5, 8, 7, 2}, {2, 0, 1, 1, 0, 0}};
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++)
                System.out.print(grid[i][j]+" ");
            System.out.println();
        }
        System.out.println();
        mergeNumbers(grid, 3, 3, 1);
        for (int i=0; i<6; i++){
            for (int j=0; j<6; j++)
                System.out.print(grid[i][j]+" ");
            System.out.println();
        }

    }

    private static void mergeNumbers(int[][] grid, int row, int column, int nextNumber) {

        grid[row][column] = grid[row][column] ==0? nextNumber :
                grid[row][column];

        while (checkNeighbors(grid, row, column) && !flag){
            checkUps    (grid, row-1, column, row, column);
            checkDowns  (grid, row+1, column, row, column);
            checkRights (grid, row, column+1, row, column);
            checkLefts  (grid, row, column-1, row, column);

            // make an if statements .... there is no need maybe
            // use GitHub
            grid[row][column]++;
        }
    }

    private static boolean checkNeighbors(int[][] grid, int row, int column) {
        return grid[row][column] == grid[row-1][column]
                || grid[row+1][column] == grid[row][column]
                || grid[row][column-1] == grid[row][column]
                || grid[row][column+1] == grid[row][column];
    }

    private static void checkUps(int[][] grid, int cr, int cc, int r, int c){
        int counter = 0, element = grid[r][c];
        boolean f = false;
        while (grid[cr][cc] == element && counter<3 && r-counter>0){
            if (grid[cr][cc-1] == element){
                grid[cr][cc-1] = 0;
                f = !f;
                flag = true;
            }
            if (grid[cr][cc+1] == element){
                grid[cr][cc+1] = 0;
                f = !f;
                flag = true;
            }
            if (f) {
                grid[cr][cc] = 0;
                if (counter>=1)
                    grid[cr+1][cc] = 0;
            }
            counter++;
            if (cr>0)   cr--;
            else
                break;
        }
        if (counter == 1)
            flag = true;
    }

    private static void checkDowns(int[][] grid, int cr, int cc, int r, int c){
        int counter = 0, element = grid[r][c];
        boolean f = false;
        while (grid[cr][cc] == element && counter<2 && r+counter<6){
            if (grid[cr][cc-1] == element){
                grid[cr][cc-1] = 0;
                f = true;
                flag = true;
            }
            if (grid[cr][cc+1] == element){
                grid[cr][cc-1] = 0;
                f = true;
                flag = true;
            }
            if (f) {
                grid[cr][cc] = 0;
                if (counter>=1 )
                    grid[cr-1][cc] = 0;
            }
            counter++;
            if (cr < size-1) cr++;
            else {
                break;
            }
        }
        if (counter == 1)
            flag = true;
    }

    private static void checkRights(int[][] grid, int cr, int cc, int r, int c){
        int counter = 0, element = grid[r][c];
        boolean f = false;
        while (grid[cr][cc] == element && counter<2 && c+counter<6){
            if (grid[cr-1][cc] == element){
                grid[cr-1][cc] = 0;
                f = true;
                flag = true;
            }
            if (grid[cr+1][cc] == element){
                grid[cr+1][cc] = 0;
                f = true;
                flag = true;
            }
            if (f) {
                grid[cr][cc] = 0;
                if (counter>=1)
                    grid[cr][cc-1] = 0;
            }
            counter++;
            if (cc < size-1) cc++;
            else {
                break;
            }
        }
        if (counter == 1)
            flag = true;
    }

    private static void checkLefts(int[][] grid, int cr, int cc, int r, int c){
        int counter = 0, element = grid[r][c];
        boolean f = false;
        while (grid[cr][cc] == element && counter<3 && c-counter>0){
            if (grid[cr-1][cc] == element)
            {
                grid[cr-1][cc] = 0;
                f = true;
                flag = true;
            }
            if (grid[cr+1][cc] == element){
                grid[cr+1][cc] = 0;
                f =true;
                flag = true;
            }
            if (f) {
                grid[cr][cc] = 0;
                if (counter>=1)
                    grid[cr][cc+1] = 0;
            }
            counter++;
            if (cc>0) cc--;
            else
                break;
        }
        if (counter == 1)
            flag = true;
    }
}
