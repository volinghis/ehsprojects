import GlobalVars from '@components/global/globalVars.js'
const Mock = require('mockjs')
Mock.mock(GlobalVars.globalServiceServlet + '/auth/menu/menuDatas', 'get', require('./json/menuDatas'))
Mock.mock(GlobalVars.globalServiceServlet + '/eam/eamLedger/getList', 'get', require('./json/eam/eamLedger/eamTableData'))
Mock.mock(GlobalVars.globalServiceServlet + '/eam/eamLedger/getSuggestions', 'get', require('./json/eam/eamLedger/searchSuggestions'))
Mock.mock(GlobalVars.globalServiceServlet + '/eam/eamLedger/getRepairRecord', 'get', require('./json/eam/eamLedger/repairRecord'))
// 备件管理
Mock.mock(GlobalVars.globalServiceServlet + '/eam/eamPartsAccount/getList', 'get', require('./json/eam/eamPartsAccount/partTable'))
Mock.mock(GlobalVars.globalServiceServlet + '/eam/eamPartsAccount/getSuggestions', 'get', require('./json/eam/eamLedger/searchSuggestions'))
// 领用申请
Mock.mock(GlobalVars.globalServiceServlet + '/eam/eamApplyForUse/getList', 'get', require('./json/eam/eamApplyForUse/indexTable'))
Mock.mock(GlobalVars.globalServiceServlet + '/eam/eamApplyForUse/getSuggestions', 'get', require('./json/eam/eamLedger/searchSuggestions'))
// 入库管理
Mock.mock(GlobalVars.globalServiceServlet + '/eam/eamEnterWareHouse/getList', 'get', require('./json/eam/eamEnterWareHouse/indexTable'))
Mock.mock(GlobalVars.globalServiceServlet + '/eam/eamEnterWareHouse/getSuggestions', 'get', require('./json/eam/eamLedger/searchSuggestions'))
// 出库管理
Mock.mock(GlobalVars.globalServiceServlet + '/eam/eamOutWarehouse/getList', 'get', require('./json/eam/eamOutWarehouse/indexTable'))
Mock.mock(GlobalVars.globalServiceServlet + '/eam/eamOutWarehouse/getSuggestions', 'get', require('./json/eam/eamLedger/searchSuggestions'))
