package firok.tool.labelstudio;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import firok.tool.labelstudio.bean.AnnotationBean;
import firok.tool.labelstudio.bean.Page;
import firok.tool.labelstudio.bean.ProjectBean;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.*;

import static firok.tool.labelstudio.util.HttpUtils.mapOf;

public class LabelStudioConnector
{
	final HttpUrl host;
	String token;
	final OkHttpClient client;
	final ObjectMapper omTemplate;
	ObjectMapper OM()
	{
		return omTemplate.copy();
	}

	public final ProjectsClient Projects;
	public final AnnotationsClient Annotations;


	public LabelStudioConnector(URL host, String token)
	{
		this.client = new OkHttpClient.Builder()
				.build();
		this.host = HttpUrl.get(host);
		this.token = "Token " + token;
		this.omTemplate = new ObjectMapper();
		this.omTemplate.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
		this.omTemplate.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		this.Projects = new ProjectsClient(this);
		this.Annotations = new AnnotationsClient(this);
	}
}
