package firok.tool.labelstudio.bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.*;

@Data
public class AnnotationBean
{
	public static final TypeReference<AnnotationBean> Type = new TypeReference<>() { };
	public static final TypeReference<AnnotationBean[]> ArrayType = new TypeReference<>() { };

	private Long id;
	private String createdUsername;
	private Long completedBy;
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
}
