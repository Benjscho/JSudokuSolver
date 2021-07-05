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
import javax.swing.border.Border;
import javax.swing.JTable;


public class GuiSudokuSolver implements ActionListener{

    private JButton solve, reset;
    private SudokuTable st;
    
    public GuiSudokuSolver() {

    }

    public void draw() {
        JFrame mFrame = new JFrame();
        mFrame.setTitle("Sudoku Solver");
        mFrame.setSize(640, 480);

        st = new SudokuTable();
        JTable table = new JTable(st);
        JPanel sudokuPanel = new JPanel();
        sudokuPanel.setLayout(new FlowLayout());
        sudokuPanel.add(table);

        solve = new JButton("Solve sudoku");
        solve.addActionListener(this);
        reset = new JButton("Reset sudoku");
        reset.addActionListener(this);

        JPanel buttonPanel = new JPanel(); 
        buttonPanel.add(solve);
        buttonPanel.add(reset);

        mFrame.setLayout(new BorderLayout());
        mFrame.add(sudokuPanel, BorderLayout.NORTH);
        mFrame.add(buttonPanel, BorderLayout.SOUTH);

        mFrame.pack();
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == solve) {
            SudokuSolver solver = new SudokuSolver(); 
            st.setSudoku(solver.getSolution(st.getSudoku()));
            System.out.println("Sudoku solved");
        } else if (e.getSource() == reset) {
            st.setSudoku(new Sudoku());
        }
    }

    public static void main(String[] args) {
        GuiSudokuSolver m = new GuiSudokuSolver();
        m.draw();
    }
}