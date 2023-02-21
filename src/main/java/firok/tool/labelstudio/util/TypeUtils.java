package firok.tool.labelstudio.util;

import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.Response;

import java.io.InputStream;

public class TypeUtils
{
	public static final TypeReference<String> StringType = new TypeReference<>() { };
	public static final TypeReference<String[]> StringArrayType = new TypeReference<>() { };

	public static final TypeReference<byte[]> ByteArrayType = new TypeReference<>() { };

	public static final TypeReference<InputStream> InputStreamType = new TypeReference<>() { };

	public static final TypeReference<Response> ResponseType = new TypeReference<>() { };
}
