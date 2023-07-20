package firok.tool.labelstudio;

import firok.tool.labelstudio.bean.ProjectTaskBean;
import firok.tool.labelstudio.bean.TaskListBean;
import firok.tool.labelstudio.util.NotTested;
import firok.topaz.annotation.Indev;

import static firok.tool.labelstudio.util.HttpUtils.mapOf;

public final class TasksClient extends InnerClient
{
	TasksClient(LabelStudioConnector conn) { super(conn); }

	/**
	 * @implNote 这破 API 文档没写分页参数, 默认只返回 100 条数据, 见鬼吧
	 * */
	public TaskListBean getTasksList(Long projectId, Long viewId)
	{
		return getTaskList(projectId, viewId, 1, Integer.MAX_VALUE);
	}

	/**
	 * 带分页的查询
	 * @since 0.7.0
	 * */
	@Indev(usable = false, description = "pageIndex 参数暂时不生效")
	public TaskListBean getTaskList(Long projectId, Long viewId, int pageIndex, int pageSize)
	{
		return get("/api/tasks", mapOf(
				"project", projectId,
				"view", viewId,
				"page_size", pageSize
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
