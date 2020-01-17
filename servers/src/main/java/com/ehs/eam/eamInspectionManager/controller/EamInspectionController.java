package com.ehs.eam.eamInspectionManager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.eam.eamInspectionManager.bean.InspectionTaskBean;
import com.ehs.eam.eamInspectionManager.service.EamInspectionTaskService;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;

@RestController
@RequestMapping(value = "/eam/eamInspection")
public class EamInspectionController {
	
	@Resource
	private EamInspectionTaskService inspectionTaskService;
	
	@RequestAuth(menuKeys = {"eamInspectionTask"})
	@RequestMapping(value = "/getAllTask")
	public String getAll(@RequestBody(required = false) QueryBean queryBean, HttpServletRequest request) {
		PageInfoBean pb = inspectionTaskService.findAllTask(queryBean);
		return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	
	@RequestAuth(menuKeys = {"eamInspectionEdit"})
	@RequestMapping(value = "/saveTask")
	public String saveTask(@RequestBody InspectionTaskBean taskBean) {
		System.out.println("=====进入巡检任务保存=======");
		ResultBean resultBean = new ResultBean();
		try {
			inspectionTaskService.saveTask(taskBean);
			return JsonUtils.toJsonString(resultBean.ok("保存成功"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JsonUtils.toJsonString(resultBean.error("保存失败"));
	}

}
