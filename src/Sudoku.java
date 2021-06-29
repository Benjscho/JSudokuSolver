import java.util.Arrays;

public class Sudoku {
    private int[][] grid; 

    public Sudoku() {
        this.grid = new int[9][9];
    }

    public Sudoku(int[] s) {
        this.grid = new int[9][9];
        int c = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = s[c++];
            }
        }
    }

    /**
     * Method to initialise a sudoku that takes as input a string with the 81 digits
     * separated by commas. 
     * @param s     The sudoku to initialise represented as a string. 
     */
    public Sudoku(String s) {
        this.grid = new int[9][9];
        int c = 0;
        for (String i : s.split(",")) {
            grid[Math.floorDiv(c, 9)][c % 9] = Math.round(Float.parseFloat(i));
            c++;
        }
    }

    @Override 
    public boolean equals(Object obj) {
        if (obj instanceof Sudoku) {
            Sudoku temp = (Sudoku) obj; 

            if (Arrays.deepEquals(temp.getSudoku(), this.getSudoku())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public int[][] getSudoku() {
        return grid; 
    }

    public void setSudoku(int[][] sudoku) {
        this.grid = sudoku;
    }

    public void setSquare(int x, int y, int z) {
        this.grid[x][y] = z; 
    }

    public int getSquare(int x, int y) {
        return grid[x][y]; 
    }
}
