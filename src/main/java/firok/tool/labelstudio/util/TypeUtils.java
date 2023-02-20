package firok.tool.labelstudio.util;

import com.fasterxml.jackson.core.type.TypeReference;

public class TypeUtils
{
	public static final TypeReference<String> StringType = new TypeReference<>() { };
	public static final TypeReference<String[]> StringArrayType = new TypeReference<>() { };

	public static final TypeReference<byte[]> ByteArrayType = new TypeReference<>() { };
}
