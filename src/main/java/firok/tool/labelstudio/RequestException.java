package firok.tool.labelstudio;

import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Firok
 * */
public class RequestException extends RuntimeException
{
	public final Request request;
	public final Response response;
	public RequestException(Request request, Response response, Throwable exception)
	{
		super(exception);
		this.request = request;
		this.response = response;
	}
	public RequestException(Request request, Response response, String message, Throwable exception)
	{
		super(message, exception);
		this.request = request;
		this.response = response;
	}
}
