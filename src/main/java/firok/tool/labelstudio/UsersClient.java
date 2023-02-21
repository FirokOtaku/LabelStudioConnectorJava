package firok.tool.labelstudio;

import firok.tool.labelstudio.bean.UserBean;

import static firok.tool.labelstudio.util.TypeUtils.StringType;

public final class UsersClient extends InnerClient
{
	UsersClient(LabelStudioConnector conn) { super(conn); }

	/**
	 * 调用此方法之后
	 * */
	public String resetUserToken()
	{
		var token = postJson201("/api/current-user/reset-token", null, StringType);
		return conn.token = "Token " + token;
	}

	public String getUserToken()
	{
		return get("/api/current-user/token", StringType, 200);
	}

	public UserBean retrieveMyUser()
	{
		return get("/api/current-user/whoami", UserBean.Type, 200);
	}

	public UserBean[] listUsers()
	{
		return get("/api/users", UserBean.ArrayType, 200);
	}

	public UserBean createUser(UserBean user)
	{
		return postJson201("/api/users", user, UserBean.Type);
	}

	public UserBean getUserInfo(long userId)
	{
		return get("/api/users/" + userId, UserBean.Type, 200);
	}

	public UserBean updateUserDetail(long userId, UserBean user)
	{
		return patchJson200("/api/users/" + userId, user, UserBean.Type);
	}

	public void deleteUser(long userId)
	{
		delete204("/api/users/" + userId);
	}
}
