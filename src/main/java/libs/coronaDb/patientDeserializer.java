package libs.coronaDb;

import DataClass.Patient;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;

public class patientDeserializer extends StdDeserializer<ArrayList<Patient>> {
    public patientDeserializer() {
        this((JavaType) null);
    }
    protected patientDeserializer(Class<?> vc) {
        super(vc);
    }

    protected patientDeserializer(JavaType valueType) {
        super(valueType);
    }

    @Override
    public ArrayList<Patient> deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        System.out.println("here");
        ArrayList<Patient> result = new ArrayList<>();
        ObjectCodec oc = jsonParser.getCodec();

        JsonNode node = oc.readTree(jsonParser);

        ObjectMapper mapper = new ObjectMapper();
        for (JsonNode n : node) {
            result.add(mapper.treeToValue(n, Patient.class));
        }
        return result;
    }
}
