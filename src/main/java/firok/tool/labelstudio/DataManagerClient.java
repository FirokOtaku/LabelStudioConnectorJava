package firok.tool.labelstudio;

import firok.tool.labelstudio.bean.ActionBean;

import java.util.*;

/**
 * @deprecated 官方文档不全, 这个不做了
 * */
@SuppressWarnings("DeprecatedIsStillUsed")
@Deprecated
final class DataManagerClient extends InnerClient
{
	DataManagerClient(LabelStudioConnector conn) { super(conn); }

	public List<ActionBean> getActions()
	{
		return get("/api/dm/actions", null, 200);
	}

	public void performAction()
	{
		// todo
	}
}
