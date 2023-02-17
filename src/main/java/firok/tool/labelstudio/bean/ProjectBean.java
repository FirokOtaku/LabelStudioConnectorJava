package firok.tool.labelstudio.bean;

import lombok.Data;

@Data
public class ProjectBean
{
	Long id;
	String title;
	String description;
	String labelConfig;
	String expertInstruction;
	Boolean showInstruction;
	Boolean showSkipButton;
	Boolean enableEmptyAnnotation;
	Boolean showAnnotationHistory;
	Long organization;
	String color;
	Long maximumAnnotations;
	Boolean isPublished;
	String modelVersion;
	Boolean isDraft;
}
