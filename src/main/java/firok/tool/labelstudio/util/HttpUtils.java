package firok.tool.labelstudio.util;

import java.util.*;

public class HttpUtils
{
	public static Map<String, Object> mapOf(Object... kvps)
	{
		if(kvps == null || kvps.length % 2 != 0)
			throw new IllegalArgumentException();
		var ret = new HashMap<String, Object>();
		var stepMax = kvps.length / 2;
		for(var step = 0; step < stepMax; step++)
		{
			var key = kvps[step * 2];
			var value = kvps[step * 2 + 1];
			if(value == null) continue;
			ret.put(String.valueOf(key), value);
		}
		return ret;
	}
}
