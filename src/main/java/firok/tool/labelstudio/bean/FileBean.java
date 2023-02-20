package firok.tool.labelstudio.bean;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;

@Data
public class FileBean
{
	public static final TypeReference<FileBean> Type = new TypeReference<>() { };
	public static final TypeReference<FileBean[]> ArrayType = new TypeReference<>() { };

	Long id;
	String file;
}
