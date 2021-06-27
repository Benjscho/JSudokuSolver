public class Sudoku {
    private int[][] grid; 

    public Sudoku() {
        this.grid = new int[9][9];
    }

    public Sudoku(int[] s) {
        int c = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = s[c++];
            }
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
