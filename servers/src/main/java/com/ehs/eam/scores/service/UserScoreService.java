package com.ehs.eam.scores.service;

import com.ehs.common.oper.bean.PageBody;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.scores.bean.UserScoreBean;

public interface UserScoreService {

	public void addScores();
	
	public UserScoreBean getUserScoresTotal(String userKey);
	
	public UserScoreBean getUserScoresByYearAndMonth(String userKey,int[] years,int[] months);
	
	public PageInfoBean getAllUserScores(int[] years, int[] months,PageBody pageBody);
}
