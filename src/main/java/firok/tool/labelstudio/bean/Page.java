package firok.tool.labelstudio.bean;

import lombok.Data;

@Data
public class Page<TypeBean>
{
	private Long count;
	private String next;
	private String previous;
	private TypeBean results;
}
