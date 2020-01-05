import GlobalVars from '@components/global/globalVars.js'
const Mock = require('mockjs')
Mock.mock(GlobalVars.globalServiceServlet + '/auth/menu/menuDatas', 'get', require('./json/menuDatas'))
