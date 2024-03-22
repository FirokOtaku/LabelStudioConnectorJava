package firok.tool.labelstudio;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import com.fasterxml.jackson.databind.jsontype.*;
import lombok.*;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.Test;

import java.util.Collection;

public class JsonUnionTypeFieldTests
{
    @Data
    @ToString
    @JsonSubTypes({
            @JsonSubTypes.Type(value = DetailedCompletedByUserBean.class),
    })
    public static class CompletedByUserBean
    {
        Long id;
    }

    @Data
    @ToString
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, visible = true, property = "firstName")
    public static class DetailedCompletedByUserBean extends CompletedByUserBean
    {
        String firstName;
        String lastName;
        String avatar;
        String email;
        String initails;
    }

    @Data
    @ToString
    public static class UnionTypeClass
    {
        CompletedByUserBean completedBy;
    }

    @Language("JSON")
    static final String Json1 = """
            {
              "value": { "id": 123 }
            }
            """;

    @Language("JSON")
    static final String Json2 = """
            {
              "value": { "id": 456 }
            }
            """;

    @Language("JSON")
    static final String Json3 = """
            {
              "value": { "id": 789, "firstName": "password" }
            }
            """;

    @Test
    void test() throws Exception
    {
        var om = new ObjectMapper();
        var obj1 = om.readValue(Json1, UnionTypeClass.class);
        System.out.println(obj1);
        var obj2 = om.readValue(Json2, UnionTypeClass.class);
        System.out.println(obj2);
        var obj3 = om.readValue(Json3, UnionTypeClass.class);
        System.out.println(obj3);
    }
}
