package firok.tool.labelstudio;

import org.junit.jupiter.api.Test;

import java.io.File;

public class FullExportTests
{
    @Test
    void testExport() throws Exception
    {
//        var projects = conn.Projects.listProjects(null, null, 1, 100);
//        System.out.println("project 数量: ");
//        System.out.println(projects.getCount());
//        for(var project : projects.getResults())
//        {
//            System.out.println(project.getTitle());
//            System.out.println(project.getId());
//        }

        System.out.println("ServerExportProjectId: " + RemoteServerInfo.ServerExportProjectId);
        var folderTestFullExport = new File("./test-full-export");
        RemoteServerInfo.Conn.AdvancedExport.exportFullCocoDataset(RemoteServerInfo.ServerExportProjectId, folderTestFullExport);
    }

    @Test
    void testTaskList() throws Exception
    {
        System.out.println("ServerExportProjectId: " + RemoteServerInfo.ServerExportProjectId);
        var tasks = RemoteServerInfo.Conn.Tasks.getTasksList(RemoteServerInfo.ServerExportProjectId, null).getTasks();
        System.out.println(tasks.size());
    }
}
