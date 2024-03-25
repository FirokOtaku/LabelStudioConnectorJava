package firok.tool.labelstudio;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import firok.topaz.general.ProgramMeta;
import firok.topaz.general.Version;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.net.URL;
import java.util.List;

public class LabelStudioConnector
{
	public static final ProgramMeta META = new ProgramMeta(
			"firok.tool.labelstudioconnector",
			"Label Studio Connector Java",
			new Version(0, 12, 0),
			"",
			List.of("Firok"),
			List.of("https://github.com/FirokOtaku/LabelStudioConnectorJava"),
			List.of("https://github.com/FirokOtaku/LabelStudioConnectorJava"),
			"Mulan PSL v2"
	);
	@Deprecated(forRemoval = true)
	public static final String Name = META.name;
	@Deprecated(forRemoval = true)
	public static final Version Version = META.version;

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
	/**
	 * Give u direct access to the Label Studio API to get image file or something.
	 * @since 0.11.0
	 * */
	public final DirectClient Direct;

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
