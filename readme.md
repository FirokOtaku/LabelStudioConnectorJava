# Label Studio Connector Java

⚠ 这只是一个个人用途的封装, **不是 Label Studio 官方的 SDK**.
不包含官方所有 API 功能, 已实现接口也没有进行测试.

⚠ This is for personal usage and **NOT official SDK of Label Studio**.
NOT all Label Studio APIs are included. Existing interfaces are NOT tested.

* [Label Studio](https://labelstud.io/)
* [Label Studio API docs](https://labelstud.io/api)
* [Label Studio **official** Python SDK](https://github.com/heartexlabs/label-studio-sdk)

----

maven

```xml
<dependencies>
    <dependency>
        <groupId>firok.tool</groupId>
        <artifactId>labelstudio-connector</artifactId>
        <version>{VERSION}</version>
    </dependency>
</dependencies>
```

Java 17

```java
var conn = new firok.tool.labelstudio.LabelStudioConnector("http://localhost:8080", "token123");
var users = conn.Users.listUsers();
System.out.println(users);
```

----

* Annotations
  * --
* Projects
  * --
* Users
  * --
* Import
  * ~~Import tasks~~
* Export
  * ~~Export as File~~
* Tasks
  * --
* Advanced Export
  * export full COCO dataset
