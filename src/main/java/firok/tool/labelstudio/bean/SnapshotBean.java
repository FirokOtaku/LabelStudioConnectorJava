package firok.tool.labelstudio.bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.*;

@Data
public class SnapshotBean
{
	public static final TypeReference<SnapshotBean> Type = new TypeReference<>() { };
	public static final TypeReference<SnapshotBean[]> ArrayType = new TypeReference<>() { };

	private String title;
	private Long id;
	private UserSimpleBean createdBy;
	private Date createdAt;
	private Date finishedAt;
	private String status;
	private String md5;
	private JsonNode counters;
	private TaskFilterOptionsBean taskFilterOptions;
	private AnnotationFilterOptionsBean annotationFilterOptions;
	private SerializationOptionsBean serializationOptions;
}
