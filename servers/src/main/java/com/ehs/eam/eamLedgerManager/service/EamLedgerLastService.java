package com.ehs.eam.eamLedgerManager.service;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamLedgerManager.bean.EamLedgerQueryBean;

public interface EamLedgerLastService {
	
	public PageInfoBean findEamLedgerLastList(EamLedgerQueryBean querybean);

	public PageInfoBean findLeftEamLedgerList(EamLedgerQueryBean querybean);
}
