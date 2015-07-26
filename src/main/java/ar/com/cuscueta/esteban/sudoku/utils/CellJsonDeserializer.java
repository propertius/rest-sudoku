package ar.com.cuscueta.esteban.sudoku.utils;

import ar.com.cuscueta.esteban.sudoku.beans.Cell;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class CellJsonDeserializer extends JsonDeserializer<Cell> {

    @Override
    public Cell deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        Cell cell = new Cell();
        cell.setValue(node.asInt());
        if (node.asInt() == 0) {
            cell.hide();
        }
        return cell;
    }
}
