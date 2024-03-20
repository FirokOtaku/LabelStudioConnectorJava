package firok.tool.labelstudio;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.net.URI;
import java.net.URL;

import static java.lang.StringTemplate.STR;

public class RemoteServerInfo
{
    public static final String ServerUrl;
    public static final String ServerToken;
    public static final long ServerExportProjectId;

    public static final LabelStudioConnector Conn;
    private record ConnInfo(String url, String token, long exportProjectId) { }

    static
    {
        var file = new File("./test-conn.json");
        try
        {
            file = file.getCanonicalFile();
            var om = new ObjectMapper();
            var info = om.readValue(file, ConnInfo.class);
            ServerUrl = info.url();
            ServerToken = info.token();
            ServerExportProjectId = info.exportProjectId();
            System.out.println("读取连接信息完成");
            System.out.println("ServerUrl: " + ServerUrl);
            System.out.println("ServerToken: " + ServerToken);
            var uri = URI.create(ServerUrl);
            Conn = new LabelStudioConnector(uri.toURL(), ServerToken);
        }
        catch (Exception any)
        {
            System.err.println(STR."""
                    Label Studio 连接配置文件错误: \{any.getLocalizedMessage()}
                    如需正常启动测试, 请在 \{file.getAbsolutePath()} 位置创建一个 JSON 文件, 内容如下:
                      {
                        "url": "{(字符串) 需要测试连接的 Label Studio 访问地址}",
                        "token": "{(字符串) 访问 Label Studio 所需的 token}",
                        "exportProjectId": {(整型) 需要测试导出的项目 ID}
                      }
                    """);
            throw new IllegalStateException();
        }
    }
}
