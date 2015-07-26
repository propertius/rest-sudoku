package ar.com.cuscueta.esteban.sudoku.utils;

import ar.com.cuscueta.esteban.sudoku.beans.Cell;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CellJsonSerializer extends JsonSerializer<Cell> {
    @Override
    public void serialize(Cell cell, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeNumber(cell.getValue());
    }
}
