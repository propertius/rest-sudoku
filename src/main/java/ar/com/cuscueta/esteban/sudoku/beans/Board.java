package ar.com.cuscueta.esteban.sudoku.beans;

import java.io.Serializable;

public class Board implements Serializable {

    private static final int BLOCK_SIZE = 3;
    private final int boardSize = BLOCK_SIZE * BLOCK_SIZE;
    private Cell[][] cells;

    public Board() {
        cells = new Cell[boardSize][boardSize];
        for (int i = 0; i < boardSize; ++i) {
            for (int j = 0; j < boardSize; ++j) {
                cells[i][j] = new Cell();
            }
        }
    }

    public Board(Cell[][] cells) {
        this.cells = cells;
    }

    public int boardSize() {
        return boardSize;
    }

    public void reset(final int row, final int column) {
        cells[row - 1][column - 1].reset();
    }

    public boolean isFilled(final int row, final int column) {
        return cells[row - 1][column - 1].isFilled();
    }

    public boolean allCellsFilled() {
        for (int i = 0; i < boardSize; ++i) {
            for (int j = 0; j < boardSize; ++j) {
                if (!cells[i][j].isFilled() || cells[i][j].getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void set(final int number, final int row, final int column) {
        cells[row - 1][column - 1].setValue(number);
    }

    public void tryNumber(final int number, final int row, final int column) {
        cells[row - 1][column - 1].tryNumber(number);
    }

    public boolean numberAttempted(final int number, final int row, final int column) {
        return cells[row - 1][column - 1].attempted(number);
    }

    public int numberOfAttempts(final int row, final int column) {
        return cells[row - 1][column - 1].numberOfAttempts();
    }

    public boolean checkBox(final int number, final int row, final int column) {
        int r = row, c = column;
        if (r % BLOCK_SIZE == 0) {
            r -= BLOCK_SIZE - 1;
        } else {
            r = (r / BLOCK_SIZE) * BLOCK_SIZE + 1;
        }
        if (c % BLOCK_SIZE == 0) {
            c -= BLOCK_SIZE - 1;
        } else {
            c = (c / BLOCK_SIZE) * BLOCK_SIZE + 1;
        }
        for (int i = r; i < r + BLOCK_SIZE; ++i) {
            for (int j = c; j < c + BLOCK_SIZE; ++j) {
                if (cells[i - 1][j - 1].isFilled() && (cells[i - 1][j - 1].getValue() == number)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkRow(final int number, final int row) {
        for (int i = 0; i < boardSize; ++i) {
            if (cells[row - 1][i].isFilled() && cells[row - 1][i].getValue() == number) {
                return false;
            }
        }
        return true;
    }

    public boolean checkColumn(final int number, final int column) {
        for (int i = 0; i < boardSize; ++i) {
            if (cells[i][column - 1].isFilled() && cells[i][column - 1].getValue() == number) {
                return false;
            }
        }
        return true;
    }

    public boolean checkNumber(final int number, final int row, final int column) {
        return (checkBox(number, row, column)
                && checkRow(number, row)
                && checkColumn(number, column));
    }

    public boolean isCorrect() {
        for (int i = 0; i < boardSize; ++i) {
            for (int j = 0; j < boardSize; ++j) {
                if (cells[i][j].isFilled()) {
                    int value = cells[i][j].getValue();
                    cells[i][j].hide();
                    boolean correct = checkNumber(value, i + 1, j + 1);
                    cells[i][j].show();
                    if (!correct) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public Index nextCell(final int row, final int column) {
        int r = row;
        int c = column;
        if (c < boardSize) {
            ++c;
        } else {
            c = 1;
            ++r;
        }
        return new Index(r, c);
    }

    public int getRandomIndex() {
        return (int) (Math.random() * 10) % boardSize + 1;
    }

    public void generateCompleteBoard(final int row, final int column) {
        while (numberOfAttempts(row, column) < boardSize()) {
            int candidate;
            do {
                candidate = getRandomIndex();
            } while (numberAttempted(candidate, row, column));
            if (checkNumber(candidate, row, column)) {
                set(candidate, row, column);
                Index nextCell = nextCell(row, column);
                if (nextCell.getI() <= boardSize()
                        && nextCell.getJ() <= boardSize()) {
                    generateCompleteBoard(nextCell.getI(), nextCell.getJ());
                }
            } else {
                tryNumber(candidate, row, column);
            }
        }
        if (!isFilled(boardSize(), boardSize())) {
            reset(row, column);
        }
    }

    public Cell[][] generatePlayableBoard() {
        generateCompleteBoard(1, 1);
        int cleared = 0;
        do {
            int i = getRandomIndex() - 1, j = getRandomIndex() - 1;
            if (cells[i][j].isFilled()) {
                cells[i][j].clear();
                cleared++;
            }
        } while (cleared < 50);

        return cells;
    }

}