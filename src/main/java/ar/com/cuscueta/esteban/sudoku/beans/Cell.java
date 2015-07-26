package ar.com.cuscueta.esteban.sudoku.beans;


import ar.com.cuscueta.esteban.sudoku.utils.CellJsonDeserializer;
import ar.com.cuscueta.esteban.sudoku.utils.CellJsonSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.HashSet;

@JsonDeserialize(using = CellJsonDeserializer.class)
@JsonSerialize(using = CellJsonSerializer.class)
public class Cell implements Serializable {

    private int value;
    @JsonIgnore
    private boolean filled;
    @JsonIgnore
    private HashSet<Integer> attempts;

    public Cell() {
        filled = false;
        attempts = new HashSet();
    }

    public boolean isFilled() {
        return filled;
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int number) {
        filled = true;
        value = number;
        attempts.add(number);
    }

    public void clear() {
        value = 0;
        filled = false;
    }

    public void reset() {
        clear();
        attempts.clear();
    }

    public void show() {
        filled = true;
    }

    public void hide() {
        filled = false;
    }

    public boolean attempted(final int number) {
        return attempts.contains(number);
    }

    public void tryNumber(final int number) {
        attempts.add(number);
    }

    public int numberOfAttempts() {
        return attempts.size();
    }

}
