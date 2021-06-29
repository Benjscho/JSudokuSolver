import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SudokuSolverTest {

    

    @Test
    void testSudokus() {
        System.out.println("setup running");

        ArrayList<String> difficulties = new ArrayList<>(Arrays.asList(
            "very_easy", 
            "easy", 
            "medium",
            "hard"
        ));
        String puzzleLine = ""; 
        String solutionLine = ""; 
        Sudoku puzzle; 
        Sudoku solution;
        SudokuSolver solver = new SudokuSolver();  
        for (String diff : difficulties) {
            try {
                //Scanner sc = new Scanner(new File("data/" + diff + "_puzzle.csv"));
                System.out.println(new File(".").getAbsoluteFile());
                BufferedReader brPuzzle = new BufferedReader(new FileReader("/Users/crow/Desktop/bath-msc-main/cm10228-POP2/JSudokuSolver/src/data/" + diff + "_puzzle.csv"));
                BufferedReader brSolution = new BufferedReader(new FileReader("/Users/crow/Desktop/bath-msc-main/cm10228-POP2/JSudokuSolver/src/data/" + diff + "_solution.csv"));
                System.out.println("Files found.");
                while ((puzzleLine = brPuzzle.readLine()) != null && (solutionLine = brSolution.readLine()) != null) {
                    puzzle = new Sudoku(puzzleLine); 
                    solution = new Sudoku(solutionLine); 
                    assertEquals(solution, solver.getSolution(puzzle));
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    
    @Test
    void sudokuCreationTest() {
        Sudoku s = new Sudoku(); 
    }

    @Test
    void sudokuStringCreationTest() {
        Sudoku s = new Sudoku("8,5,2,9,7,6,2,4,3,6,7,9,1,4,3,2,8,5,0,3,1,2,5,8,7,6,9,3,1,4,5,2,7,8,9,6,7,6,8,3,9,1,4,5,0,9,2,5,6,0,0,3,7,1,5,4,3,8,6,2,9,1,7,1,9,7,4,3,5,0,2,8,2,8,6,7,1,9,5,3,4"); 
    }
}
