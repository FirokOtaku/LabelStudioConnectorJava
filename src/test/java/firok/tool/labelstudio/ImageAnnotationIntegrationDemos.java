package firok.tool.labelstudio;

import com.fasterxml.jackson.databind.ObjectMapper;
import firok.tool.labelstudio.bean.AnnotationBean;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static firok.topaz.general.Collections.sizeOf;

/**
 * 用于测试图片标注相关 API
 * */
@SuppressWarnings({"NewClassNamingConvention"})
public class ImageAnnotationIntegrationDemos
{
    @Test
    void testQueryTaskDetail()
    {
        var conn = RemoteServerInfo.Conn;
        var projectId = 14L;
        var project = conn.Projects.getProjectById(projectId);
        System.out.println("project:");
        System.out.println(project);

        var listTask = conn.Tasks.getTasksList(projectId, null);
        System.out.println("tasks");
        System.out.println(listTask);

        for(var task : listTask.getTasks())
        {
            var taskId = task.getId();
            var listAnno = conn.Annotations.getAllTaskAnnotations(taskId);
            System.out.println("task: " + taskId + ", annotations: " + sizeOf(listAnno));
        }
    }

    @Test
    void testCreateAnnotation() throws Exception
    {
        var conn = RemoteServerInfo.Conn;
        var om = new ObjectMapper();
        var result = om.readTree("""
                [
                  {
                    "original_width": 490,
                    "original_height": 253,
                    "image_rotation": 0,
                    "value": {
                      "points": [
                        [20, 80],
                        [50, 100],
                        [55, 100]
                      ],
                      "closed": true,
                      "polygonlabels": ["干净"]
                    },
                    "id": "1048e39752",
                    "from_name": "label",
                    "to_name": "image",
                    "type": "polygonlabels",
                    "origin": "manual"
                  }
                ]
                """);

        var anno = new AnnotationBean();
        anno.setTask(1617L);
        anno.setProject(14L);
        anno.setGroundTruth(false);
        anno.setWasCancelled(false);
        anno.setResult(result);
        anno.setCompletedBy(4L);
        anno.setUniqueId(UUID.randomUUID().toString());
        conn.Annotations.createAnnotation(1617L, anno);

    }
}
