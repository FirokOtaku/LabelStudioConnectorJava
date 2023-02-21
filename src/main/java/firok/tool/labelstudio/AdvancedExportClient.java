package firok.tool.labelstudio;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipFile;

public final class AdvancedExportClient extends InnerClient
{
	AdvancedExportClient(LabelStudioConnector conn) { super(conn); }

	/**
	 * 导出完整 COCO 数据集
	 * */
	@SuppressWarnings({"ResultOfMethodCallIgnored", "TryFinallyCanBeTryWithResources"})
	public void exportFullCocoDataset(long projectId, File pathOutputFolder)
	{
		try
		{
			System.out.println("创建目录");
			pathOutputFolder.mkdirs();
			var fileZip = new File(pathOutputFolder, "export.zip");
			var fileCocoJson = new File(pathOutputFolder, "coco.json");
			var folderImages = new File(pathOutputFolder, "images");
			folderImages.mkdirs();

			System.out.println("导出并读取 COCO 数据");
			try(
					var is = conn.Export.exportTaskAndAnnotationsStream(
							projectId,
							"COCO",
							null,
							false,
							null
					);
					var ofs = new FileOutputStream(fileZip)
			)
			{
				is.transferTo(ofs);
				ofs.flush();
			}
			var om = new ObjectMapper();

			JsonNode cocoJson;
			try(
					var zip = new ZipFile(fileZip);
					var is = zip.getInputStream(zip.getEntry("result.json"))
			){
				cocoJson = om.readTree(is);
				System.out.println("获取 COCO 数据成功");
			}
			finally
			{
				fileZip.delete();
			}

//			var mapImageFilename = new HashMap<ObjectNode, String>();
			var cocoImages = cocoJson.get("images");
			var sizeImages = cocoImages.size();
			for(var step = 0; step < sizeImages; step++)
			{
				var cocoImage = (ObjectNode) cocoImages.get(step);
				var cocoFilenameUrl = cocoImage.get("file_name").textValue();
				var cocoFilenameReal = URLDecoder.decode(cocoFilenameUrl, StandardCharsets.UTF_8);
//				mapImageFilename.put(cocoImage, cocoFilenameReal);
				var imageFilename = new File(cocoFilenameReal).getName();
				cocoImage.put("file_name", imageFilename);
			}

			System.out.println("获取任务信息 读取真实图片路径");
			var projectTasks = conn.Tasks.getTasksList(projectId, null).getTasks();

			for(var task : projectTasks)
			{
				var imageUrlUrl = task.getData().get("image").textValue();
				var imageUrlReal = URLDecoder.decode(imageUrlUrl, StandardCharsets.UTF_8);
				var imageFilename = new File(imageUrlReal).getName();
				var imageFile = new File(folderImages, imageFilename);
				try(
						var isImage = conn.Direct.requestStream(imageUrlReal);
						var osImage = new FileOutputStream(imageFile)
				) { isImage.transferTo(osImage); }
				System.out.println("下载图片成功: " + imageFilename);
			}

			om.writeValue(fileCocoJson, cocoJson);
			System.out.println("保存 COCO 数据成功");
		}
		catch (Exception any)
		{
			System.out.println("导出发生错误");
			throw new RuntimeException(any);
		}
		finally
		{
			System.out.println("导出完成");
		}
	}
}
