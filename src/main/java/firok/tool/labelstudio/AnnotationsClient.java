package firok.tool.labelstudio;

import firok.tool.labelstudio.bean.AnnotationBean;
import org.jetbrains.annotations.NotNull;

public final class AnnotationsClient extends InnerClient
{
	AnnotationsClient(LabelStudioConnector conn) { super(conn); }

	public AnnotationBean getAnnotationById(@NotNull String id)
	{
		return get("/api/annotations/" + id, AnnotationBean.Type, 200);
	}

	public AnnotationBean updateAnnotation(long annotationId, AnnotationBean annotation)
	{
		return patchJson200("/api/annotations/" + annotationId, annotation, AnnotationBean.Type);
	}

	public void deleteAnnotation(long annotationId)
	{
		delete204("/api/annotations/" + annotationId);
	}

	public AnnotationBean[] getAllTaskAnnotations(long taskId)
	{
		return get("/api/tasks/" + taskId + "/annotations", AnnotationBean.ArrayType, 200);
	}

	public AnnotationBean createAnnotation(long taskId, AnnotationBean annotation)
	{
		return postJson201("/api/tasks/" + taskId + "/annotations", annotation, AnnotationBean.Type);
	}
}
