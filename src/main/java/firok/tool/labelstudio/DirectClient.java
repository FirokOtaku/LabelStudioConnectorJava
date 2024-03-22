package firok.tool.labelstudio;

import okhttp3.Response;

import java.io.InputStream;

import static firok.tool.labelstudio.util.TypeUtils.*;

public final class DirectClient extends InnerClient
{
	DirectClient(LabelStudioConnector conn) { super(conn); }

	public InputStream requestStream(String path)
	{
		return get(path, InputStreamType, 200);
	}
	public byte[] requestBytes(String path)
	{
		return get(path, ByteArrayType, 200);
	}
	public Response requestResponse(String path)
	{
		return get(path, ResponseType, Integer.MIN_VALUE);
	}
}
