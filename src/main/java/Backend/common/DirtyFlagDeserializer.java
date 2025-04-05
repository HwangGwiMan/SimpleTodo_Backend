package Backend.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class DirtyFlagDeserializer extends JsonDeserializer<DirtyFlag> {
    @Override
    public DirtyFlag deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();
        DirtyFlag dirtyFlag = new DirtyFlag();
        if ("I".equals(value)) {
            dirtyFlag.markAsInsert();
        } else if ("U".equals(value)) {
            dirtyFlag.markAsUpdate();
        } else if ("D".equals(value)) {
            dirtyFlag.markAsDelete();
        }
        return dirtyFlag;
    }
} 