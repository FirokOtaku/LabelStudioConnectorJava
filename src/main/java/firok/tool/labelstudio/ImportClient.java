package firok.tool.labelstudio;

import com.fasterxml.jackson.databind.JsonNode;
import firok.tool.labelstudio.bean.FileBean;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ImportClient extends InnerClient
{
	ImportClient(LabelStudioConnector conn) { super(conn); }

	public FileBean getFile(long fileId)
	{
		return get("/api/import/file-upload/" + fileId, anyway(), 200);
	}

	public FileBean updateFile(long fileId)
	{
		return patchJson200("/api/import/file-upload/" + fileId, new HashMap<>(), anyway());
	}

	public void deleteFile(long fileId)
	{
		delete204("/api/import/file-upload/" + fileId);
	}

	public List<FileBean> getProjectFiles(long projectId)
	{
		return get("/api/projects/" + projectId + "/file-uploads", anyway(), 200);
	}

	public void deleteProjectFiles(long projectId)
	{
		delete204("/api/projects/" + projectId + "/file-uploads");
	}

	public void importTasks(long projectId, JsonNode json)
	{
		postJson("/api/projects/" + projectId + "/import", json, 201);
	}
	public void importTasks(long projectId, Map<String, File> files)
	{
		// todo
		// postJson();
	}
	public void importTasks(long projectId, URL url)
	{
		// todo
	}
}
