package firok.tool.labelstudio.bean;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class AnyStringDeserialize extends JsonDeserializer<String>
{
    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException
    {
        return p.getCodec().readTree(p).toString();
    }
}
