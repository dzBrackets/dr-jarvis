package libs.coronaDb;

import DataClass.Drug;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;

public class deserializeType extends StdDeserializer<ArrayList<Drug>> {
     public deserializeType() {
         this((JavaType) null);
     }
    protected deserializeType(Class<?> vc) {
        super(vc);
    }

    protected deserializeType(JavaType valueType) {
        super(valueType);
    }

    @Override
    public ArrayList<Drug> deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        System.out.println("here");
        ArrayList<Drug> result = new ArrayList<>();
        ObjectCodec oc = jsonParser.getCodec();

        JsonNode node = oc.readTree(jsonParser);

        ObjectMapper mapper = new ObjectMapper();
        for (JsonNode n : node) {
            result.add(mapper.treeToValue(n, Drug.class));
        }
        return result;
    }
}
