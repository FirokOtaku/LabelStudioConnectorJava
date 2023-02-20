package firok.tool.labelstudio.bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class ProjectBean
{
	public static final TypeReference<ProjectBean> Type = new TypeReference<>() { };
	public static final TypeReference<ProjectBean[]> ArrayType = new TypeReference<>() { };
	public static final TypeReference<Page<ProjectBean>> PageType = new TypeReference<>() { };

	private Long id;
	private String title;
	private String description;
	private String labelConfig;
	private String expertInstruction;
	private Boolean showInstruction;
	private Boolean showSkipButton;
	private Boolean enableEmptyAnnotation;
	private Boolean showAnnotationHistory;
	private Long organization;
	private String color;
	private Long maximumAnnotations;
	private Boolean isPublished;
	private String modelVersion;
	private Boolean isDraft;
	private UserSimpleBean createdBy;
	private Long minAnnotationsToStartTraining;
	private Boolean showCollabPredictions;
	private String sampling;
	private Boolean showGroundTruthFirst;
	private Boolean showOverlapFirst;
	private Long overlapCohortPercentage;
	private String taskDataLogin;
	private String taskDataPassword;
	private JsonNode controlWeights;
	private Boolean evaluatePredictionsAutomatically;
	private String skipQueue;
	private Boolean revealPreannotationsInteractively;
	private String pinnedAt;
	private Long finishedTaskNumber;
}
