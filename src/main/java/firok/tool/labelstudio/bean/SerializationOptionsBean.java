package firok.tool.labelstudio.bean;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class SerializationOptionsBean
{
	private JsonNode drafts;
	private JsonNode predictions;
	private JsonNode annotationsCompletedBy;
	private JsonNode interpolateKeyFrames;
}
