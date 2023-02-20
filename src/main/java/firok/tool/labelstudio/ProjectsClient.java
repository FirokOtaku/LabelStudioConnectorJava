package firok.tool.labelstudio;

import firok.tool.labelstudio.bean.LabelConfigBean;
import firok.tool.labelstudio.bean.Page;
import firok.tool.labelstudio.bean.ProjectBean;
import firok.tool.labelstudio.bean.ProjectTaskBean;
import org.jetbrains.annotations.NotNull;

import static firok.tool.labelstudio.util.HttpUtils.mapOf;

public final class ProjectsClient extends InnerClient
{
	ProjectsClient(LabelStudioConnector conn) { super(conn); }

	public Page<ProjectBean> listProjects(
			String ids,
			String ordering,
			int pageIndex,
			int pageSize
	)
	{
		return get("/api/projects", mapOf(
				"page", pageIndex,
				"page_size", pageSize,
				"ordering", ordering,
				"ids", ids
		), ProjectBean.PageType, 200);
	}

	public ProjectBean createProject(ProjectBean project)
	{
		return postJson("/api/projects", project, ProjectBean.Type, 201);
	}

	public void validateLabelConfig(@NotNull String labelConfig)
	{
		postJson("/api/projects/validate", mapOf(
				"label_config", labelConfig
		), 204);
	}

	public ProjectBean getProjectById(long projectId)
	{
		return get("/api/projects/" + projectId, ProjectBean.Type, 200);
	}

	public ProjectBean updateProject(long projectId, ProjectBean project)
	{
		return patchJson200("/api/projects/" + projectId, project, ProjectBean.Type);
	}

	public void deleteProject(long projectId)
	{
		delete204("/api/projects/" + projectId);
	}

	public ProjectTaskBean[] listProjectTasks(long projectId, int pageIndex, int pageSize)
	{
		return get("/api/projects/" + projectId + "/tasks", mapOf(
				"page", pageIndex,
				"page_size", pageSize
		), ProjectTaskBean.ArrayType, 200);
	}

	public void deleteAllTasks(long projectId)
	{
		delete204("/api/projects/" + projectId + "/tasks");
	}

	public void validateProjectLabelConfig(long projectId, LabelConfigBean labelConfig)
	{
		postJson("/api/projects/" + projectId + "/validate", labelConfig, 201);
	}
}
