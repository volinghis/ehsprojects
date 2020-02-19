package com.ehs.common.data.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.BaseUtils;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.data.entity.DataFileInfo;
import com.ehs.common.data.service.DataFileInfoService;
import com.ehs.common.data.utils.OfficeToPDF;
import com.ehs.common.oper.bean.ResultBean;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;

@Controller
public class DataFileInfoController {

	private static final Logger logger=LoggerFactory.getLogger(DataFileInfoController.class);
	
	@Resource
	private BaseCommonService baseCommonService;

	
	@Resource
	private GridFsTemplate gridFsTemplate;
	@Resource
 	private MongoDbFactory mongoDbFactory;
	
	@Resource
	private DataFileInfoService dataFileInfoService;
	
	/**
	 * 
	* @Function:upload 
	* @Description: 文件上传
	* @param file 文件对象
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午2:42:46 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping("/data/file/fileUpload")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file,HttpServletRequest req) {
		ResultBean rb=new ResultBean();
		String paramData=req.getParameter("categories");
		if (file.isEmpty()) {
			return JsonUtils.toJsonString(rb.error("上传失败！"));

		}

		ObjectId objectId = ObjectId.get();
		try {
			objectId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename());
			logger.info("上传成功！");
			DataFileInfo dfi=new DataFileInfo();
			dfi.setFileId(objectId.toString());
			dfi.setFileSize(BaseUtils.formatFileSize(file.getSize()));
			dfi.setName(file.getOriginalFilename());
			dfi.setType(StringUtils.substringAfterLast(file.getOriginalFilename(), "."));
			if(StringUtils.isNotBlank(paramData)) { //文件类别（说明书，检修标准，操作手册）
				dfi.setCategories(paramData);
			}
			baseCommonService.saveOrUpdate(dfi);
			
			System.out.println(JsonUtils.toJsonString(dfi));
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		System.out.println(JsonUtils.toJsonString(rb.ok("上传成功！",objectId.toString())));
		return JsonUtils.toJsonString(rb.ok("上传成功！",objectId.toString()));
	}
	
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping("/data/file/fileList")
	@ResponseBody
	public String fileList(@RequestParam("fileIds") String fileIds) {
		String[] files=StringUtils.split(fileIds, ",");
		List<DataFileInfo> lists= dataFileInfoService.findDataFilesByFileIds(files);
		return (lists==null||lists.isEmpty())?"[]":JsonUtils.toJsonString(lists);
	}
	

	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping("/data/file/downloadFile")
	public void downloadFile(@RequestParam("fileId") String fileId,HttpServletRequest request,
            HttpServletResponse response) {
		GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(fileId)));
		if(gridFSFile==null) {
			return ;
		}
		GridFsResource gridFsResource=new GridFsResource(gridFSFile,GridFSBuckets.create(mongoDbFactory.getDb()).openDownloadStream(gridFSFile.getObjectId()));
        String fileName = gridFSFile.getFilename().replace(",", "");
        //处理中文文件名乱码
        if (request.getHeader("User-Agent").toUpperCase().contains("MSIE") ||
                request.getHeader("User-Agent").toUpperCase().contains("TRIDENT")
                || request.getHeader("User-Agent").toUpperCase().contains("EDGE")) {
            try {
				fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
        } else {
            //非IE浏览器的处理：
            try {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
        }
		response.reset();
		response.setContentType("charset=UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
        try {
			IOUtils.copy(gridFsResource.getInputStream(),response.getOutputStream());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 * 
	* @Function:createPDF 
	* @Description: PDF预览
	* @param fileId 文件的唯一标识
	* @param response
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年9月5日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping("/data/file/viewFile")
	public void viewByPDF(@RequestParam("fileId") String fileId,HttpServletResponse response) {
	  	  GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(fileId)));
	  	  if(gridFSFile==null) {
	  		  return ;
	  	  }
	  	  String fileName = gridFSFile.getFilename().replace(",", "");
	  	  String subFileName=fileName.substring(fileName.indexOf(".")+1);
	  	  String outFileName=fileName.substring(0,fileName.indexOf("."))+".pdf";
	  	  try {
	  		  OutputStream out = response.getOutputStream();
				if(gridFSFile!=null) {
					GridFsResource gridFsResource=new GridFsResource(gridFSFile,GridFSBuckets.create(mongoDbFactory.getDb()).openDownloadStream(gridFSFile.getObjectId()));
					 InputStream in=gridFsResource.getInputStream();
				  	  if("docx".equals(subFileName)||"doc".equals(subFileName)) {//判断文件类型
				  		  OfficeToPDF.docTopdf(in, out);
				  	  }else if("xls".equals(subFileName)||"xlsx".equals(subFileName)){
				  		  OfficeToPDF.excelTopdf(in, out);
				  	  }else{
				  		  IOUtils.copy(in,out);
				  	  }
				  	  response.setHeader("Content-Disposition", "inline;filename=\"" + outFileName + "\"");
				}
				out.close();
	  	  	} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}  
	}
}
