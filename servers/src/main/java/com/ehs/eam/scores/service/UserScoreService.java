package com.ehs.eam.scores.service;

import com.ehs.common.oper.bean.PageBody;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.scores.bean.UserScoreBean;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: UserScoreService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2020年4月13日 上午10:50:21 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年4月13日     zhaol           v1.0.0               修改原因
*/
public interface UserScoreService {

	/**
	 * 
	* @Function: UserScoreService.java
	* @Description: 分数相加
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午10:50:26 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	public void addScores();
	
	/**
	 * 
	* @Function: UserScoreService.java
	* @Description: 查询用户总分
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午10:50:37 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	public UserScoreBean getUserScoresTotal(String userKey);
	
	/**
	 * 
	* @Function: UserScoreService.java
	* @Description: 通过年、月查询用户分数
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午10:50:53 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	public UserScoreBean getUserScoresByYearAndMonth(String userKey,int[] years,int[] months);
	
	/**
	 * 
	* @Function: UserScoreService.java
	* @Description: 分页查询所有用户的分数
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2020年4月13日 上午10:51:20 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年4月13日     zhaol           v1.0.0               修改原因
	 */
	public PageInfoBean getAllUserScores(int[] years, int[] months,PageBody pageBody);
}
