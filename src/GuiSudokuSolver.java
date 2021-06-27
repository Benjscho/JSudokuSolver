import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTable;


public class GuiSudokuSolver{
    
    public GuiSudokuSolver() {

    }

    public void draw() {
        JFrame mFrame = new JFrame();
        mFrame.setTitle("Sudoku Solver");
        mFrame.setSize(640, 480);

        SudokuTable st = new SudokuTable();
        JTable table = new JTable(st);
        mFrame.add(new JScrollPane(table));

        mFrame.pack();
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mFrame.setVisible(true);
    }

    public static void main(String[] args) {
        GuiSudokuSolver m = new GuiSudokuSolver();
        m.draw();
    }
}