public class Sudoku {
    private int[][] grid; 

    public Sudoku() {
        this.grid = new int[9][9];
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
