import java.util.ArrayList;

/**
 * SudokuSolver class
 */
public class SudokuSolver {

    private Sudoku solution; 
    private ArrayList<DLXNode> columnObject;
    private DLXNode root; 
    
    /**
     * DLXNode inner class
     * This class defines a dancing links node used in Algorithm X. A node can be 
     * initialised either empty or with a value. A dancing links node represents
     * a node on a grid. The node is doubly linked in each axis, so rows and 
     * columns are represented by doubly linked lists. Each column has a header
     * node whose value indicates a count of the nodes in the column. 
     */
    private class DLXNode {
        public DLXNode L, R, U, D, C; 
        public int value;
        
        public DLXNode(int value) {
            this.L = this; 
            this.R = this; 
            this.U = this; 
            this.D = this; 
            this.C = this; 
            this.value = value;
        }

        public DLXNode() {
            this.L = this; 
            this.R = this; 
            this.U = this; 
            this.D = this; 
            this.C = this; 
            this.value = 0; 
        }

        public void insertRow() {
            this.L.R = this;
            this.R.L = this; 
        }

        public void insertCol() {
            U.D = this;
            D.U = this;
            C.value++; 
        }

        public void removeRow() {
            L.R = R;
            R.L = L;
        }

        public void removeCol() {
            this.U.D = this.D;
            this.D.U = this.U;
            this.C.value--;
        }

        public void insertAbove(DLXNode node) {
            this.C = node.C;
            this.U = node.U; 
            this.D = node;
            this.insertCol();
        }

        public void insertRight(DLXNode node) {
            this.R = node.R; 
            this.L = node; 
            this.insertRow();
        }

        public void cover() {
            this.C.removeRow();
            // Loop column
            for (DLXNode n1 = this.C.D; n1 != this.C; n1 = n1.D) {
                // Loop row
                for (DLXNode n2 = n1.R; n2 != n1; n2 = n2.R) {
                    n2.removeCol();
                }
            }
        }

        public void uncover() {
            this.C.insertRow();
            // Loop column
            for (DLXNode n1 = this.C.D; n1 != this.C; n1 = n1.D) {
                // Loop row
                for (DLXNode n2 = n1.R; n2 != n1; n2 = n2.R) {
                    n2.insertCol();
                }
            }
        }
    }

    public SudokuSolver() {

    }

    private void prepareSolution(Sudoku sudoku) {
        solution = new Sudoku(); 

        columnObject = new ArrayList<>();
        for (int i = 0; i < 4*81; i++) {
            columnObject.add(new DLXNode()); 
        }

        int cRow = 0; 
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int sq = sudoku.getSquare(r, c);
                if (sq == 0) {
                    for (int x = 0; x < 9; x++) {
                        addToMatrix(cRow, r, c, x);
                    }
                } else {
                    addToMatrix(cRow, r, c, sq - 1);
                }
                cRow += 9; 
            }
        }

        root = new DLXNode();
        DLXNode last = root; 
        for (DLXNode header : columnObject) {
            header.insertRight(last);
            last = header; 
        }
    }

    private int[] constraints(int r, int c, int value) {
        int cell = (9*r) + c;
        int row = 81 + (9*r) + value;
        int col = (81*2) + (9*c) + value;
        int box = (81*3) + (9*((3*Math.floorDiv(r, 3)) + Math.floorDiv(c, 3))) + value; 

        return new int[] {cell, row, col, box}; 
    }

    private void addToMatrix(int cRow, int r, int c, int value) {
        DLXNode first = null; 
        for (int con : constraints(r, c, value)) {
            DLXNode node = new DLXNode(cRow + value);
            node.insertAbove(columnObject.get(con));
            if (first == null) {
                first = node; 
            } else {
                node.insertRight(first);
            }
        }
    }

    private void addToSolution(int val) {
        int r = Math.floorDiv(val, 81); 
        val %= 81; 
        int c = Math.floorDiv(val, 9); 
        val %= 9;
        solution.setSquare(r, c, val+1);
    }

    private boolean solve() {
        if (root.R == root) return true;
        int val = 10000; 
        DLXNode colhead = new DLXNode(); 
        for (DLXNode node = root.R; node != root; node = node.R) {
            if (node.value < val) {
                colhead = node; 
                val = node.value;
            }
        }
        if (colhead.value == 0) return false;
        colhead.cover();
        // Loop through column
        for (DLXNode row = colhead.D; row != colhead; row = row.D) {
            for (DLXNode node = row.R; node != row; node = node.R) {
                node.cover();
            }
            if (solve()) {
                addToSolution(row.value);
                return true;
            }
            for (DLXNode node = row.R; node != row; node = node.R) {
                node.uncover();
            }
        }
        colhead.uncover();
        return false;
    }

    public Sudoku getSolution(Sudoku sudoku) {
        prepareSolution(sudoku);
        if (solve()) {
            return solution; 
        } else {
            int[] n = new int[81];
            for (int i = 0; i < 81; i++) {
                n[i] = -1;
            }
            return new Sudoku(n);
        }
    }
}