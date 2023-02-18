# Label Studio Connector Java

**⚠ 这不是 Label Studio 官方的 SDK, 也不包含官方所有 API 功能**  
**⚠ This is NOT official SDK of Label Studio and NOT all Label Studio APIs are included**

* [Label Studio](https://labelstud.io/)
* [Label Studio API docs](https://labelstud.io/api)
* [Label Studio official Python SDK](https://github.com/heartexlabs/label-studio-sdk)

## 使用 Usage

maven

```xml
<dependencies>
    <dependency>
        <groupId>firok.tool</groupId>
        <artifactId>labelstudio-sdk</artifactId>
        <version>{VERSION}</version>
    </dependency>
</dependencies>
```

Java 17

```java
var conn = new firok.tool.labelstudio.LabelStudioConnector("http://localhost:8080", "token123");
var projects = conn.listProjects("", "", 1, 10);
System.out.println(projects);
```
