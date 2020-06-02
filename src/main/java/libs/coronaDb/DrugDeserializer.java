package libs.coronaDb;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import model.usedDrug;

import java.io.IOException;
import java.util.ArrayList;

public class DrugDeserializer extends StdDeserializer<ArrayList<usedDrug>> {
     public DrugDeserializer() {
         this((JavaType) null);
     }
    protected DrugDeserializer(Class<?> vc) {
        super(vc);
    }

    protected DrugDeserializer(JavaType valueType) {
        super(valueType);
    }

    @Override
    public ArrayList<usedDrug> deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        ArrayList<usedDrug> result = new ArrayList<>();
        ObjectCodec oc = jsonParser.getCodec();

        JsonNode node = oc.readTree(jsonParser);

        ObjectMapper mapper = new ObjectMapper();
        for (JsonNode n : node) {
            result.add(mapper.treeToValue(n, usedDrug.class));
        }
        return result;
    }
}
