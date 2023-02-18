package firok.tool.labelstudio;

import firok.tool.labelstudio.bean.LabelConfig;
import firok.tool.labelstudio.bean.Page;
import firok.tool.labelstudio.bean.ProjectBean;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static firok.tool.labelstudio.util.HttpUtils.mapOf;

public final class ProjectsClient extends InnerClient
{
	ProjectsClient(LabelStudioConnector conn) { super(conn); }

	public Page<List<ProjectBean>> listProjects(
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
		), anyway(), 200);
	}

	public ProjectBean createProject(ProjectBean project)
	{
		return postJson("/api/projects", project, anyway(), 201);
	}

	public void validateLabelConfig(@NotNull String labelConfig)
	{
		postJson("/api/projects/validate", mapOf(
				"label_config", labelConfig
		), 204);
	}

	public ProjectBean getProjectById(long projectId)
	{
		return get("/api/projects/" + projectId, anyway(), 200);
	}

	public ProjectBean updateProject(long projectId, ProjectBean project)
	{
		return patchJson200("/api/projects/" + projectId, project, anyway());
	}

	public void deleteProject(long projectId)
	{
		delete204("/api/projects/" + projectId);
	}

	public Page<Void> listProjectTasks(long projectId, int pageIndex, int pageSize)
	{
		return get("/api/projects/" + projectId + "/tasks", mapOf(
				"page", pageIndex,
				"page_size", pageSize
		), anyway(), 200);
	}

	public void deleteAllTasks(long projectId)
	{
		delete204("/api/projects/" + projectId + "/tasks");
	}

	public void validateProjectLabelConfig(long projectId, LabelConfig labelConfig)
	{
		postJson("/api/projects/" + projectId + "/validate", labelConfig, 201);
	}
}
