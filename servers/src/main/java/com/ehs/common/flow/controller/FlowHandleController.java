package com.ehs.common.flow.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.flow.bean.FlowSampleBean;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.service.FlowBaseService;
import com.ehs.common.oper.bean.ResultBean;

@RestController
public class FlowHandleController {

	@Resource
	private FlowBaseService flowBaseService;
	
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/flow/handle/cancelProcess")
	public String cancelProcess(HttpServletRequest request, @RequestBody FlowProcessInfo processInfo) {
		ResultBean resultBean = new ResultBean();
		flowBaseService.processCancel(processInfo);
		return JsonUtils.toJsonString(resultBean.ok("撤销流程成功！"));
	}
	
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/flow/handle/rejectProcess")
	public String rejectProcess(HttpServletRequest request, @RequestBody FlowProcessInfo processInfo) {
		ResultBean resultBean = new ResultBean();
		flowBaseService.processReject(processInfo);
		return JsonUtils.toJsonString(resultBean.ok("驳回流程成功！"));
	}
	
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/flow/handle/sendProcess")
	public String sendProcess(HttpServletRequest request, @RequestBody FlowProcessInfo processInfo) {
		ResultBean resultBean = new ResultBean();
		flowBaseService.processSend(processInfo);
		return JsonUtils.toJsonString(resultBean.ok("提交流程成功！"));
	}
	
	
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/flow/handle/endProcess")
	public String endProcess(HttpServletRequest request, @RequestBody FlowProcessInfo processInfo) {
		ResultBean resultBean = new ResultBean();
		flowBaseService.processEnd(processInfo);
		return JsonUtils.toJsonString(resultBean.ok("提交成功！"));
	}
	
}
