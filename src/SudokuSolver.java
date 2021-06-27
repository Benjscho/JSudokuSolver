import java.util.Iterator;
import java.util.NoSuchElementException;

public class SudokuSolver {

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

    }
}