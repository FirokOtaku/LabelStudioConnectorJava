package firok.tool.labelstudio.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.NumericNode;
import lombok.Data;
import lombok.ToString;

import java.io.IOException;
import java.util.*;

@Data
public class AnnotationBean
{
	public static final TypeReference<AnnotationBean> Type = new TypeReference<>() { };
	public static final TypeReference<AnnotationBean[]> ArrayType = new TypeReference<>() { };

	private Long id;
	private String createdUsername;
	/**
	 * @see <a href="https://github.com/HumanSignal/label-studio/issues/5618">Issue 5618</a>
	 * */
	@JsonSerialize(using = CompletedByUserBeanSerializer.class)
	@JsonDeserialize(using = CompletedByUserBeanDeserializer.class)
	private FieldUserBean completedBy;

	public void setCompletedBy(Object value)
	{
		this.completedBy = switch (value)
        {
            case Long num ->
            {
                var bean = new FieldUserBean();
                bean.id = num;
				yield bean;
            }
			case FieldDetailedUserBean bean -> bean;
			case FieldUserBean bean -> bean;
            case null, default -> null;
        };
	}

	private String uniqueId;
	private JsonNode result;
	private Boolean wasCancelled;
	private Boolean GroundTruth;
	private Date createdAt;
	private Date updatedAt;
	private Double leadTime;
	private AnnotationActionEnum lastAction;
	private Long task;
	private Long project;
	private Long updatedBy;
	private Long parentPrediction;
	private Long parentAnnotation;
	private Long lastCreatedBy;

	@Data
	@ToString
	public static class FieldUserBean
	{
		Long id;

		@JsonIgnore
		public boolean isDetailed()
		{
			return false;
		}
	}

	@Data
	@ToString
	public static class FieldDetailedUserBean extends FieldUserBean
	{
		String firstName;
		String lastName;
		String avatar;
		String email;
		String initails;

		@Override
		@JsonIgnore
		public boolean isDetailed()
		{
			return true;
		}
	}

	public static class CompletedByUserBeanSerializer extends JsonSerializer<FieldUserBean>
	{
		@Override
		public void serialize(FieldUserBean value, JsonGenerator gen, SerializerProvider serializers) throws IOException
		{
			if(value.isDetailed())
			{
				gen.writeObject(value);
			}
			else
			{
				gen.writeNumber(value.id);
			}
		}
	}

	public static class CompletedByUserBeanDeserializer extends JsonDeserializer<FieldUserBean>
	{
		@Override
		public FieldUserBean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException
		{
			var node = p.getCodec().readTree(p);
			if(node.isObject())
			{
				return p.getCodec().treeToValue(node, FieldDetailedUserBean.class);
			}
			else if(node instanceof NumericNode num)
			{
				var bean = new FieldUserBean();
				bean.id = num.asLong();
				return bean;
			}
			else return null;
		}
	}
}
