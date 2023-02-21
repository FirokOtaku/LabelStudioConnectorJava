package firok.tool.labelstudio.bean;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;

import java.util.*;

@Data
public class TaskListBean
{
	public static final TypeReference<TaskListBean> Type = new TypeReference<>() { };

	private Long totalAnnotations;
	private Long totalPredictions;
	private Long total;
	private List<TaskListTaskBean> tasks;
}
