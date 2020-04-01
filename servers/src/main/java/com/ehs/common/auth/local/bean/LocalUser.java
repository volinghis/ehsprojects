package com.ehs.common.auth.local.bean;

import org.apache.commons.lang.StringUtils;

import com.ehs.common.auth.entity.SysUser;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.SpringUtils;
import com.ehs.common.organization.entity.OrgUser;
import com.ehs.common.organization.entity.OrganizationInfo;

public class LocalUser {

	private String sysUserKey;
	private String userKey;
	private String orgKey;
	private String account;
	private String username;
	private String orgName;
	private String avatar;
	// 员工编号
	private String dataCode;
	private String roleKeys;
	/**
	 * 组织编号
	 */
	private String orgDataCode;
	
	/**
	 * 职务
	 */
	private String positionName;

	public LocalUser initBySysUser(String sysUserKey) {
		SysUser su = (SysUser) SpringUtils.getBean(BaseCommonService.class).findByKey(SysUser.class, sysUserKey);
		this.setSysUserKey(su.getKey());
		this.setAccount(su.getAccount());
		this.setRoleKeys(su.getRoleKeys());
		this.setUserKey(su.getUserKey());
		String refUser = su.getUserKey();
		if (StringUtils.isNotBlank(refUser)) {
			OrgUser uu = (OrgUser) SpringUtils.getBean(BaseCommonService.class).findByKey(OrgUser.class, refUser);
			this.setOrgKey(uu.getOrgKey());
			this.setUsername(uu.getName());
			this.setDataCode(uu.getDataCode());
			this.setAvatar(uu.getAvatar());
			this.setPositionName(uu.getPositionName());
			if (StringUtils.isNotBlank(uu.getOrgKey())) {
				OrganizationInfo oi = (OrganizationInfo) SpringUtils.getBean(BaseCommonService.class)
						.findByKey(OrganizationInfo.class, uu.getOrgKey());
				this.setOrgName(oi.getName());
				this.setOrgDataCode(oi.getDataCode());
			}
		}

		return this;
	}

	public String getOrgDataCode() {
		return orgDataCode;
	}

	public void setOrgDataCode(String orgDataCode) {
		this.orgDataCode = orgDataCode;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	public String getRoleKeys() {
		return roleKeys;
	}

	public void setRoleKeys(String roleKeys) {
		this.roleKeys = roleKeys;
	}

	public String getOrgKey() {
		return orgKey;
	}

	public void setOrgKey(String orgKey) {
		this.orgKey = orgKey;
	}

	public String getSysUserKey() {
		return sysUserKey;
	}

	public void setSysUserKey(String sysUserKey) {
		this.sysUserKey = sysUserKey;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * @param avatar the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

}
