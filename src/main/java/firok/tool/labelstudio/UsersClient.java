package firok.tool.labelstudio;

import firok.tool.labelstudio.bean.UserBean;

import java.util.List;

public final class UsersClient extends InnerClient
{
	UsersClient(LabelStudioConnector conn) { super(conn); }

	/**
	 * 调用此方法之后
	 * */
	public String resetUserToken()
	{
		var token = postJson("/api/current-user/reset-token", null, anyway(), 201);
		return conn.token = "Token " + token;
	}

	public String getUserToken()
	{
		return get("/api/current-user/token", anyway(), 200);
	}

	public UserBean retrieveMyUser()
	{
		return get("/api/current-user/whoami", anyway(), 200);
	}

	public List<UserBean> listUsers()
	{
		return get("/api/users", anyway(), 200);
	}

	public UserBean createUser(UserBean user)
	{
		return postJson("/api/users", user, anyway(), 201);
	}

	public UserBean getUserInfo(long userId)
	{
		return get("/api/users/" + userId, anyway(), 200);
	}

	public UserBean updateUserDetail(long userId, UserBean user)
	{
		return patchJson200("/api/users/" + userId, user, anyway());
	}

	public void deleteUser(long userId)
	{
		delete204("/api/users/" + userId);
	}
}
