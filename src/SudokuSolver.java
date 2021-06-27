import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SudokuSolver class
 */
public class SudokuSolver {

    private Sudoku solution; 
    private ArrayList<DLXNode> columnObject;
    private DLXNode root; 
    
    private class DLXNode {
        public DLXNode L, R, U, D, C; 
        public int value;
        
        public DLXNode(int value) {
            this.L = this.R = this.U = this.D = this.C = this; 
            this.value = value;
        }

        public DLXNode() {
            this.L = this.R = this.U = this.D = this.C = this; 
            this.value = 0; 
        }

        public void insertRow() {
            this.L.R = this.R.L = this; 
        }

        public void insertCol() {
            U.D = D.U = this;
            C.value++; 
        }

        public void removeRow() {
            L.R = R;
            R.L = L;
        }

        public void removeCol() {
            U.D = D;
            D.U = U;
            C.value--;
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
            for (DLXNode n1 = this.D; n1 != this; n1 = n1.D) {
                // Loop row
                for (DLXNode n2 = n1.R; n2 != n1; n2 = n2.R) {
                    n2.removeCol();
                }
            }
        }

        public void uncover() {
            this.C.insertRow();
            // Loop column
            for (DLXNode n1 = this.D; n1 != this; n1 = n1.D) {
                // Loop row
                for (DLXNode n2 = n1.R; n2 != n1; n2 = n2.R) {
                    n2.insertCol();
                }
            }
        }
    }

    public SudokuSolver(Sudoku sudoku) {
        solution = new Sudoku(); 

        ArrayList<DLXNode> columnObject = new ArrayList<>();
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
        int box = (81*3) + (9*((3*(r / 3)) + (c / 3))) + value; 

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
}