package firok.tool.labelstudio.bean;

import lombok.Data;

@Data
public class TaskFilterOptionsBean
{
	private Long view;
	private AnyMemberEnum skipped;
	private AnyMemberEnum finished;
	private AnyMemberEnum annotated;
	private Boolean onlyWithAnnotations;
}
