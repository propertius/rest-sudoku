package ar.com.cuscueta.esteban.sudoku.bo;

import ar.com.cuscueta.esteban.sudoku.beans.Board;
import ar.com.cuscueta.esteban.sudoku.beans.Cell;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class SudokuBOImpl implements SudokuBO {

    public Cell[][] getRandomSudokuBoard() {
        Board board = new Board();
        return board.generatePlayableBoard();
    }

    @Override
    public boolean validateMove(String sudokuJson) throws Exception {
        Cell[][] cells = new ObjectMapper().readValue(sudokuJson, Cell[][].class);
        Board board = new Board(cells);
        return board.isCorrect();
    }

    @Override
    public boolean validateGameEnd(String sudokuJson) throws Exception {
        Cell[][] cells = new ObjectMapper().readValue(sudokuJson, Cell[][].class);
        Board board = new Board(cells);
        return board.isCorrect() && board.allCellsFilled();
    }
}
