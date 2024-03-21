package firok.tool.labelstudio;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

@SuppressWarnings({"unchecked", "DataFlowIssue"})
abstract sealed class InnerClient
		permits AdvancedExportClient, AnnotationsClient, DataManagerClient, DirectClient, ExportClient, ImportClient, ProjectsClient, TasksClient, UsersClient
{
	protected final LabelStudioConnector conn;
	protected InnerClient(LabelStudioConnector conn)
	{
		this.conn = conn;
	}

	private static final String AuthHeader = "Authorization";
	@FunctionalInterface
	private interface RequestFunction { Request.Builder apply(Request.Builder request, ObjectMapper om) throws Exception; };
	@FunctionalInterface
	private interface ResponseFunction<TypeBean> { TypeBean apply(Response response, ObjectMapper om) throws Exception; }

	private static <TypeBean> TypeBean handleResponse(Response response, ObjectMapper om, TypeReference<TypeBean> beanType) throws IOException
	{
		return switch (beanType.getType().getTypeName())
		{
			case "java.io.InputStream" -> (TypeBean) response.body().byteStream();
			case "byte[]" -> (TypeBean) response.body().bytes();
			case "okhttp3.Response" -> (TypeBean) response;
			default -> {
				if(om == null) throw new IOException("No json mapper provided");
				yield om.readValue(response.body().byteStream(), beanType);
//				var bodyContent = response.body().string();
//				System.out.println("原始数据: \n" + bodyContent);
//				yield om.readValue(bodyContent, beanType);
			}
		};
	}

	private <TypeBean> TypeBean handle(HttpUrl url, boolean json, int code,
	                           RequestFunction functionRequest,
	                           ResponseFunction<TypeBean> functionResponse
	) throws RequestException
	{
		Request request = null;
		Response response = null;
		try
		{
			var om = json ? conn.OM() : null;
			request = functionRequest.apply(
					new Request.Builder()
							.url(url)
							.addHeader(AuthHeader, conn.token),
					om
			).build();
			response = conn.client.newCall(request).execute();
			if(code != Integer.MIN_VALUE && code != response.code())
			{
				String str = null;
				try(var is = response.body().byteStream())
				{
					str = new String(is.readAllBytes());
				}
				catch (Exception _){ }
				throw new IllegalStateException(str);
			}
			return functionResponse.apply(response, om);
		}
		catch (Exception any)
		{
			throw new RequestException(request, response, any);
		}
	}
	private <TypeBean> TypeBean handle(String path, boolean json, int code,
	                       RequestFunction functionRequest,
                           ResponseFunction<TypeBean> functionResponse
	) throws RequestException
	{
		var url = conn.host.resolve(path);
		return handle(url, json, code, functionRequest, functionResponse);
	}

	protected <TypeBean> TypeBean get(String path, TypeReference<TypeBean> beanType, int code)
	{
		return handle(
				path, true, code,
				(request, om) -> request.get(),
				(response, om) -> handleResponse(response, om, beanType)
		);
	}
	protected <TypeBean> TypeBean get(String path, Map<String, Object> params, TypeReference<TypeBean> beanType, int code)
	{
		var url = switch (params.isEmpty() ? 1 : 0) {
			case 1 -> conn.host.resolve(path);
			case 0 -> {
				var urlBuilder = conn.host.resolve(path).newBuilder();
				for(var entry : params.entrySet())
				{
					var key = entry.getKey();
					var value = entry.getValue();
					if(value instanceof Object[] arr)
					{
						for(var obj : arr)
							urlBuilder.addQueryParameter(key, String.valueOf(obj));
					}
					else if(value != null)
					{
						urlBuilder.addQueryParameter(key, String.valueOf(value));
					}
				}
				yield urlBuilder.build();
			}
			default -> null;
		};
		return handle(
				url, true, code,
				(request, om) -> request.get(),
				(response, om) -> handleResponse(response, om, beanType)
		);
	}

	private static final MediaType TypeJson = MediaType.parse("application/json");
	private static final MediaType TypeEmpty = MediaType.parse("");

	protected <TypeBean> TypeBean postJson201(String path, Object body, TypeReference<TypeBean> beanType)
	{
		return handle(
				path, true, 201,
				(request, om) -> {
					RequestBody requestBody;
					if(body == null)
					{
						requestBody = RequestBody.create(new byte[0], TypeEmpty);
					}
					else
					{
						var bodyJson = om.writeValueAsString(body);
						requestBody = RequestBody.create(bodyJson, TypeJson);
					}
					return request.post(requestBody);
				},
				(response, om) -> handleResponse(response, om, beanType)
		);
	}
	protected void postJson(String path, Object body, int code)
	{
		handle(
				path, true, code,
				(request, om) -> {
					RequestBody requestBody;
					if(body == null)
					{
						requestBody = RequestBody.create(new byte[0], TypeEmpty);
					}
					else
					{
						var bodyJson = om.writeValueAsString(body);
						requestBody = RequestBody.create(bodyJson, TypeJson);
					}
					return request.post(requestBody);
				},
				(response, om) -> response
		);
	}
	protected <TypeBean> TypeBean postFiles(String path, Map<String, File> files, TypeReference<TypeBean> beanType, int code)
	{
		return handle(
				path, true, code,
				(request, om) -> {
					var requestBody = new MultipartBody.Builder()
							// todo
							.build();
					return request.post(requestBody);
				},
				(response, om) -> handleResponse(response, om, beanType)
		);
	}

	protected <TypeBean> TypeBean patchJson200(String path, Object body, TypeReference<TypeBean> beanType)
	{
		return handle(
				path, true, Integer.MIN_VALUE,
				(request, om) -> {
					var bodyJson = om.writeValueAsString(body);
					var requestBody = RequestBody.create(bodyJson, TypeJson);
					return request.patch(requestBody);
				},
				(response, om) -> handleResponse(response, om, beanType)
		);
	}

	protected void delete204(String path)
	{
		handle(
				path, false, Integer.MIN_VALUE,
				(request, om) -> request.delete(),
				(response, om) -> {
					response.close();
					return null;
				}
		);
	}
}
