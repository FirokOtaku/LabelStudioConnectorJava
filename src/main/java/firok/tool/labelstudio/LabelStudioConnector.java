package firok.tool.labelstudio;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import firok.topaz.general.Version;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.net.URL;

public class LabelStudioConnector
{
	public static final String Name = "Label Studio Connector Java";
	public static final Version Version = new Version(0, 8, 0);

	final HttpUrl host;
	String token;
	final OkHttpClient client;
	final ObjectMapper omTemplate;
	ObjectMapper OM()
	{
		return omTemplate.copy();
	}

	public final UsersClient Users;
	public final ProjectsClient Projects;
	public final AnnotationsClient Annotations;
	public final ImportClient Import;
	public final ExportClient Export;
	public final TasksClient Tasks;
	final DirectClient Direct;

	public final AdvancedExportClient AdvancedExport;

	public LabelStudioConnector(URL host, String token)
	{
		this.client = new OkHttpClient.Builder()
				.build();
		this.host = HttpUrl.get(host);
		this.token = "Token " + token;
		this.omTemplate = new ObjectMapper();
		this.omTemplate.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
		this.omTemplate.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		this.Projects = new ProjectsClient(this);
		this.Annotations = new AnnotationsClient(this);
		this.Import = new ImportClient(this);
		this.Export = new ExportClient(this);
		this.Users = new UsersClient(this);
		this.Tasks = new TasksClient(this);

		this.Direct = new DirectClient(this);

		this.AdvancedExport = new AdvancedExportClient(this);
	}
}
