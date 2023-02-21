package firok.tool.labelstudio;

import firok.tool.labelstudio.bean.SnapshotBean;
import firok.tool.labelstudio.util.TypeUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;

import static firok.tool.labelstudio.util.HttpUtils.mapOf;
import static firok.tool.labelstudio.util.TypeUtils.*;

public final class ExportClient extends InnerClient
{
	ExportClient(LabelStudioConnector conn) { super(conn); }

	public byte[] exportTaskAndAnnotations(
			long projectId,
			@Nullable String exportType,
			@Nullable Boolean downloadAllTasks,
			@Nullable Boolean downloadResources,
			@Nullable String[] taskIds
	)
	{
		return get("/api/projects/" + projectId + "/export", mapOf(
				"export_type", exportType,
				"download_all_tasks", downloadAllTasks,
				"download_resources", downloadResources,
				"ids[]", taskIds
		), ByteArrayType, 200);
	}
	public InputStream exportTaskAndAnnotationsStream(
			long projectId,
			@Nullable String exportType,
			@Nullable Boolean downloadAllTasks,
			@Nullable Boolean downloadResources,
			@Nullable String[] taskIds
	)
	{
		return get("/api/projects/" + projectId + "/export", mapOf(
				"export_type", exportType,
				"download_all_tasks", downloadAllTasks,
				"download_resources", downloadResources,
				"ids[]", taskIds
		), InputStreamType, 200);
	}

	public String[] getExportFormats(long projectId)
	{
		return get("/api/projects/" + projectId + "/export/formats", StringArrayType, 200);
	}

	public SnapshotBean[] listAllExportSnapshots(long projectId)
	{
		return get("/api/projects/" + projectId + "/exports", SnapshotBean.ArrayType, 200);
	}

	public SnapshotBean createNewExportSnapshot(long projectId, SnapshotBean snapshot)
	{
		return postJson201("/api/projects/" + projectId + "/exports", snapshot, SnapshotBean.Type);
	}

	public SnapshotBean getExportSnapshot(long projectId, @NotNull String exportPrimaryKey)
	{
		return get("/api/projects/" + projectId + "/exports/" + exportPrimaryKey, SnapshotBean.Type, 200);
	}

	public void deleteExportSnapshot(long projectId, @NotNull String exportPrimaryKey)
	{
		delete204("/api/projects/" + projectId + "/exports/" + exportPrimaryKey);
	}

	public byte[] downloadExportSnapshotAsFile(long projectId, @NotNull String exportPrimaryKey, @Nullable String exportType)
	{
		return get("/api/projects/" + projectId + "/exports/" + exportPrimaryKey + "/download", mapOf(
				"exportType", exportType
		), TypeUtils.ByteArrayType, 200);
	}
}
