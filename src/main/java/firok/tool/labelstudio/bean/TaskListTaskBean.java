package firok.tool.labelstudio.bean;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import firok.tool.labelstudio.util.NotTested;
import lombok.Data;

import java.util.*;

@Data
public class TaskListTaskBean
{
	private Long id;
	private ArrayNode drafts;
	private ArrayNode annotators;
	private Long innerId;
	private Long cancelledAnnotations;
	private Long totalAnnotations;
	private Long totalPredictions;
	private String annotationsResults;
	private String predictionsResults;
	@NotTested
	private JsonNode fileUpload;
	@NotTested
	private JsonNode storageFilename;
	private String annotationsIds;
	private List<TaskListUserBean> updatedBy;
	private ObjectNode data;
	private ObjectNode meta;
	private Date createdAt;
	private Date updatedAt;
	private Boolean isLabeled;
	private Long overlap;
	private Long commentCount;
	private Long unresolvedCommentCount;
	private Date lastCommentUpdatedAt;
	private Long project;
	private List<Long> commentAuthors;
}
