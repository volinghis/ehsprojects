/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.bean 
 * @author: qjj   
 * @date: 2020年1月14日 下午7:53:42 
 */
package com.ehs.eam.eamPartLibraryManager.bean;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EnterWareHouseFlowBean.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年2月9日 下午5:50:31 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年2月9日     Administrator           v1.0.0               修改原因
*/
public class WareHouseFlowBean {
	
	private String currentStep;
	
	private String instanceId;
	
	private String editPage;
	
	private String viewPage;
	
	private String currentUser;
	
	private String startActivityId;
	
	private String processPage;

	/**
	 * @return the currentStep
	 */
	public String getCurrentStep() {
		return currentStep;
	}

	/**
	 * @param currentStep the currentStep to set
	 */
	public void setCurrentStep(String currentStep) {
		this.currentStep = currentStep;
	}

	/**
	 * @return the instanceId
	 */
	public String getInstanceId() {
		return instanceId;
	}

	/**
	 * @param instanceId the instanceId to set
	 */
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * @return the editPage
	 */
	public String getEditPage() {
		return editPage;
	}

	/**
	 * @param editPage the editPage to set
	 */
	public void setEditPage(String editPage) {
		this.editPage = editPage;
	}

	/**
	 * @return the viewPage
	 */
	public String getViewPage() {
		return viewPage;
	}

	/**
	 * @param viewPage the viewPage to set
	 */
	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}

	/**
	 * @return the currentUser
	 */
	public String getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * @return the startActivityId
	 */
	public String getStartActivityId() {
		return startActivityId;
	}

	/**
	 * @param startActivityId the startActivityId to set
	 */
	public void setStartActivityId(String startActivityId) {
		this.startActivityId = startActivityId;
	}

	public String getProcessPage() {
		return processPage;
	}

	public void setProcessPage(String processPage) {
		this.processPage = processPage;
	}
	
}
