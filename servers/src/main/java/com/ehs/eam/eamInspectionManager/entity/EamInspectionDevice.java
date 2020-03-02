package com.ehs.eam.eamInspectionManager.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamInspectionDevice.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年1月19日 上午10:28:39 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月19日     zhaol           v1.0.0               修改原因
*/
@Entity
@Table(name = "EAM_INSPECTION_DEVICE",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class EamInspectionDevice extends com.ehs.eam.eamInspectionManager.entity.entitySuper.EamInspectionDevice {

	private static final long serialVersionUID = 1L;

}
