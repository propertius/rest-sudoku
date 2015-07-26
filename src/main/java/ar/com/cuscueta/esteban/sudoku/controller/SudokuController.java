package ar.com.cuscueta.esteban.sudoku.controller;

import ar.com.cuscueta.esteban.sudoku.bo.SudokuBO;
import ar.com.cuscueta.esteban.sudoku.beans.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SudokuController {

    @Autowired
    SudokuBO sudokuBO;

    @RequestMapping("/sudoku")
    public Cell[][] getRandomSudokuBoard() {
        return sudokuBO.getRandomSudokuBoard();
    }

    @RequestMapping("/validate")
    public boolean validateMove(@RequestBody String sudokuJson) throws Exception {
        return sudokuBO.validateMove(sudokuJson);
    }

    @RequestMapping("/finished")
    public boolean validateGameEnd(@RequestBody String sudokuJson) throws Exception {
        return sudokuBO.validateGameEnd(sudokuJson);
    }

    public SudokuBO getSudokuBO() {
        return sudokuBO;
    }

    public void setSudokuBO(SudokuBO sudokuBO) {
        this.sudokuBO = sudokuBO;
    }
}
