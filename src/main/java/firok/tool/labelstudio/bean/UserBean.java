package firok.tool.labelstudio.bean;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;

import java.util.*;

@Data
public class UserBean
{
	public static final TypeReference<UserBean> Type = new TypeReference<>() { };
	public static final TypeReference<UserBean[]> ArrayType = new TypeReference<>() { };

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private Date lastActivity;
	private String avatar;
	private String initials;
	private String phone;
	private Long activeOrganization;
	private Boolean allowNewsletters;
}
