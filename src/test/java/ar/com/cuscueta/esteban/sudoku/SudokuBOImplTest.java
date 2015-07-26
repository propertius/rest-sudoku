package ar.com.cuscueta.esteban.sudoku;

import ar.com.cuscueta.esteban.sudoku.bo.SudokuBO;
import ar.com.cuscueta.esteban.sudoku.bo.SudokuBOImpl;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SudokuBOImplTest {

    private SudokuBO sudokuBO = new SudokuBOImpl();

    @Test
    public void testGetRandomSudokuBoardShouldReturnABoard() throws Exception {
        assertThat(sudokuBO.getRandomSudokuBoard().length, is(equalTo(9)));
    }

    @Test
    public void testValidateMoveIsValid() throws Exception {
        String randomBoard = "[" +
                "[0,0,0,7,0,0,0,6,5]," +
                "[0,0,0,0,4,0,2,0,8]," +
                "[0,0,6,0,0,8,9,1,7]," +
                "[0,0,8,0,0,0,0,0,9]," +
                "[0,2,0,0,8,0,5,0,0]," +
                "[0,0,0,0,0,0,0,8,2]," +
                "[6,5,0,4,9,7,0,2,1]," +
                "[0,4,7,5,0,0,0,0,0]," +
                "[0,0,9,0,6,3,0,0,0]]";
        assertThat(sudokuBO.validateMove(randomBoard), is(true));
    }

    @Test
    public void testValidateMoveIsNotValid() throws Exception {
        String randomBoard = "[" +
                "[0,0,7,7,0,0,0,6,5]," +
                "[0,0,0,0,4,0,2,0,8]," +
                "[0,0,6,0,0,8,9,1,7]," +
                "[0,0,8,0,0,0,0,0,9]," +
                "[0,2,0,0,8,0,5,0,0]," +
                "[0,0,0,0,0,0,0,8,2]," +
                "[6,5,0,4,9,7,0,2,1]," +
                "[0,4,7,5,0,0,0,0,0]," +
                "[0,0,9,0,6,3,0,0,0]]";
        assertThat(sudokuBO.validateMove(randomBoard), is(not(true)));
    }

    @Test
    public void testValidateGameEndIsNotFinished() throws Exception {
        String randomBoard = "[" +
                "[0,0,0,7,0,0,0,6,5]," +
                "[0,0,0,0,4,0,2,0,8]," +
                "[0,0,6,0,0,8,9,1,7]," +
                "[0,0,8,0,0,0,0,0,9]," +
                "[0,2,0,0,8,0,5,0,0]," +
                "[0,0,0,0,0,0,0,8,2]," +
                "[6,5,0,4,9,7,0,2,1]," +
                "[0,4,7,5,0,0,0,0,0]," +
                "[0,0,9,0,6,3,0,0,0]]";
        assertThat(sudokuBO.validateGameEnd(randomBoard), is(not(true)));
    }

    @Test
    public void testValidateGameEndIsFinished() throws Exception {
        String randomBoard = "[" +
                "[1,6,3,5,9,2,8,4,7]," +
                "[2,5,7,3,4,8,9,6,1]," +
                "[4,9,8,7,1,6,2,5,3]," +
                "[5,3,1,9,6,4,7,2,8]," +
                "[9,7,6,2,8,5,1,3,4]," +
                "[8,2,4,1,7,3,6,9,5]," +
                "[7,1,5,4,2,9,3,8,6]," +
                "[6,4,2,8,3,1,5,7,9]," +
                "[3,8,9,6,5,7,4,1,2]]";
        assertThat(sudokuBO.validateGameEnd(randomBoard), is(true));
    }

    @Test
    public void testValidateGameEndIsNotFinishedBecauseOfErrors() throws Exception {
        String randomBoard = "[" +
                "[1,1,3,5,9,2,8,4,7]," +
                "[2,5,7,3,4,8,9,6,1]," +
                "[4,9,8,7,1,6,2,5,3]," +
                "[5,3,1,9,6,4,7,2,8]," +
                "[9,7,6,2,8,5,1,3,4]," +
                "[8,2,4,1,7,3,6,9,5]," +
                "[7,1,5,4,2,9,3,8,6]," +
                "[6,4,2,8,3,1,5,7,9]," +
                "[3,8,9,6,5,7,4,1,2]]";
        assertThat(sudokuBO.validateGameEnd(randomBoard), is(not(true)));
    }

} 
