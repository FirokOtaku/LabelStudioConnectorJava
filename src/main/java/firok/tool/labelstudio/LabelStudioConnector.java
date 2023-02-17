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
	private final HttpUrl host;
	private final String token;
	private final OkHttpClient client;
	private final ObjectMapper omTemplate;
	private ObjectMapper OM()
	{
		return omTemplate.copy();
	}
	public LabelStudioConnector(URL host, String token)
	{
		this.client = new OkHttpClient.Builder()
				.build();
		this.host = HttpUrl.get(host);
		this.token = token;
		this.omTemplate = new ObjectMapper();
		this.omTemplate.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
		this.omTemplate.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}



	private <TypeBean> TypeBean get(String path, TypeReference<TypeBean> beanClass) throws IOException
	{
		var url = host.resolve(path);
		assert url != null;
		var request = new Request.Builder()
				.get()
				.url(url)
				.addHeader("Authorization", "Token " + token)
				.build();
		try(var response = client.newCall(request).execute())
		{
			return convert(response, beanClass);
		}
	}
	private <TypeBean> TypeBean get(String path, Map<String, Serializable> params, TypeReference<TypeBean> beanType)
	{
		HttpUrl url;
		if(params.isEmpty())
		{
			url = host.resolve(path);
		}
		else
		{
			var urlBuilder = host.resolve(path).newBuilder();
			for(var entry : params.entrySet())
			{
				var key = entry.getKey();
				var value = entry.getValue();
				if(value == null) continue;
				urlBuilder.addQueryParameter(key, String.valueOf(value));
			}
			url = urlBuilder.build();
		}
		assert url != null;
		var requestBuilder = new Request.Builder()
				.get()
				.url(url)
				.addHeader("Authorization", "Token " + token);
		var request = requestBuilder.build();
		try(var response = client.newCall(request).execute())
		{
			return convert(response, beanType);
		}
		catch (IOException any)
		{
			throw new RuntimeException(any);
		}
	}
	private <TypeBean> TypeBean convert(Response response, TypeReference<TypeBean> beanType) throws IOException
	{
		var om = OM();
		return om.readValue(response.body().byteStream(), beanType);
	}

	public AnnotationBean getAnnotationById(@NotNull String id) throws IOException
	{
		return get("/api/annotations/" + id, new TypeReference<>() { });
	}

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
		), new TypeReference<>() { });
	}
}
