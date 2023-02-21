package firok.tool.labelstudio.bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Data;

import java.util.*;

@Data
public class ProjectTaskBean
{
	public static final TypeReference<ProjectTaskBean> Type = new TypeReference<>() { };
	public static final TypeReference<Page<ProjectTaskBean>> PageType = new TypeReference<>() { };
	public static final TypeReference<ProjectTaskBean[]> ArrayType = new TypeReference<>() { };

	private Long id;
	private JsonNode data;
	private JsonNode meta;
	private Date createdAt;
	private Date updatedAt;
	private Boolean isLabeled;
	private Long overlap;
	private Long innerId;
	private Long totalAnnotations;
	private Long cancelledAnnotations;
	private Long totalPredictions;
	private Long commentCount;
	private Long unresolvedCommentCount;
	private Date lastCommentUpdatedAt;
	private Long project;
	private Long updatedBy;
	private Long fileUpload;
	private List<Long> commentAuthors;
	private List<AnnotationBean> annotations;
	private ArrayNode predictions;
}
