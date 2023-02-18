package firok.tool.labelstudio;

import firok.tool.labelstudio.bean.AnnotationBean;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class AnnotationsClient extends InnerClient
{
	AnnotationsClient(LabelStudioConnector conn) { super(conn); }

	public AnnotationBean getAnnotationById(@NotNull String id)
	{
		return get("/api/annotations/" + id, anyway(), 200);
	}

	public AnnotationBean updateAnnotation(long annotationId, AnnotationBean annotation)
	{
		return patchJson200("/api/annotations/" + annotationId, annotation, anyway());
	}

	public void deleteAnnotation(long annotationId)
	{
		delete204("/api/annotations/" + annotationId);
	}

	public List<AnnotationBean> getAllTaskAnnotations(long taskId)
	{
		return get("/api/tasks/" + taskId + "/annotations", anyway(), 200);
	}

	public AnnotationBean createAnnotation(long taskId, AnnotationBean annotation)
	{
		return postJson("/api/tasks/" + taskId + "/annotations", annotation, anyway(), 201);
	}
}
