package firok.tool.labelstudio;

import com.fasterxml.jackson.databind.JsonNode;
import firok.tool.labelstudio.bean.FileBean;

import java.io.File;
import java.net.URL;
import java.util.*;

public final class ImportClient extends InnerClient
{
	ImportClient(LabelStudioConnector conn) { super(conn); }

	public FileBean getFile(long fileId)
	{
		return get("/api/import/file-upload/" + fileId, FileBean.Type, 200);
	}

	public FileBean updateFile(long fileId)
	{
		return patchJson200("/api/import/file-upload/" + fileId, new HashMap<>(), FileBean.Type);
	}

	public void deleteFile(long fileId)
	{
		delete204("/api/import/file-upload/" + fileId);
	}

	public FileBean[] getProjectFiles(long projectId)
	{
		return get("/api/projects/" + projectId + "/file-uploads", FileBean.ArrayType, 200);
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
