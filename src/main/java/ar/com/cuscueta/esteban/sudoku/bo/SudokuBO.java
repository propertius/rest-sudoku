package ar.com.cuscueta.esteban.sudoku.bo;

import ar.com.cuscueta.esteban.sudoku.beans.Cell;

public interface SudokuBO {

    Cell[][] getRandomSudokuBoard();

    boolean validateMove(String sudokuJson) throws Exception;

    boolean validateGameEnd(String sudokuJson) throws Exception;
}
