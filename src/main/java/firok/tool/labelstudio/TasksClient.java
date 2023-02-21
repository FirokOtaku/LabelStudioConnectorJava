package firok.tool.labelstudio;

import firok.tool.labelstudio.bean.ProjectTaskBean;
import firok.tool.labelstudio.bean.TaskListBean;
import firok.tool.labelstudio.util.NotTested;

import static firok.tool.labelstudio.util.HttpUtils.mapOf;

public final class TasksClient extends InnerClient
{
	TasksClient(LabelStudioConnector conn) { super(conn); }

	public TaskListBean getTasksList(Long projectId, Long viewId)
	{
		return get("/api/tasks", mapOf(
				"project", projectId,
				"view", viewId
		), TaskListBean.Type, 200);
	}

	@NotTested
	public ProjectTaskBean createTask(ProjectTaskBean task)
	{
		return postJson201("/api/tasks", task, ProjectTaskBean.Type);
	}

	@NotTested
	public ProjectTaskBean getTask(long taskId)
	{
		return get("/api/tasks/" + taskId, ProjectTaskBean.Type, 200);
	}

	@NotTested
	public ProjectTaskBean updateTask(long taskId, ProjectTaskBean task)
	{
		return patchJson200("/api/tasks/" + taskId, task, ProjectTaskBean.Type);
	}

	@NotTested
	public void deleteTask(long taskId)
	{
		delete204("/api/tasks/" + taskId);
	}
}
