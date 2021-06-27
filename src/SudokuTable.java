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

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            int x = Integer.parseInt((String) aValue); 
            if (x >= 0 && x <= 9) {
                s.setSquare(rowIndex, columnIndex, x); 
            }
        } catch (NumberFormatException e) {
            System.out.println("No number in entry");
        }
    }
    
}