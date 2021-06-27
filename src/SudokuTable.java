import javax.swing.table.AbstractTableModel;

public class SudokuTable extends AbstractTableModel {
    private Sudoku s;

    public SudokuTable(Sudoku unfilledSudoku) {
        this.s = unfilledSudoku; 
    } 

    public SudokuTable() {
        this.s = new Sudoku(); 
    }

    @Override
    public int getRowCount() {
        return 9;
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return s.getSquare(rowIndex, columnIndex);
    } 

    
}