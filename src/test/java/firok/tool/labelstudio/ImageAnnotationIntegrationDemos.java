package firok.tool.labelstudio;

import com.fasterxml.jackson.databind.ObjectMapper;
import firok.tool.labelstudio.bean.AnnotationBean;
import org.junit.jupiter.api.Test;

import static firok.topaz.general.Collections.sizeOf;

/**
 * 用于测试图片标注相关 API
 * */
@SuppressWarnings({"NewClassNamingConvention"})
public class ImageAnnotationIntegrationDemos
{
//    @Test
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

//    @Test
    void testCreateAnnotation() throws Exception
    {
        var conn = RemoteServerInfo.Conn;
        var om = new ObjectMapper();
        var result = om.readTree("""
                [
                  {
                    "original_width": 440,
                    "original_height": 237,
                    "image_rotation": 0,
                    "value": {
                      "points": [
                        [25.643507972665148, 87.01594533029613],
                        [48.5876993166287, 67.88154897494306],
                        [53.372851522054255, 91.79954441913439]
                      ],
                      "closed": true,
                      "polygonlabels": ["干净"]
                    },
                    "id": "LrdPBd3RiH",
                    "from_name": "label",
                    "to_name": "image",
                    "type": "polygonlabels",
                    "origin": "manual"
                  }
                ]
                """);

        var anno = new AnnotationBean();
        anno.setProject(1617L);
        anno.setGroundTruth(false);
        anno.setWasCancelled(false);
        anno.setResult(result);
        conn.Annotations.createAnnotation(1617L, anno);

    }
}
