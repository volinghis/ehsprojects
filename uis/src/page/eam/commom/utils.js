export default {
  /**
   * 取出集合所有元素中key的值，组成以逗号隔开的字符串
   */
  handlerArrayDatas (datas) {
    let keys = ''
    if (datas.length === 0) {
      return keys
    }
    for (let i = 0; i < datas.length; i++) {
      keys += datas[i].key + ','
    }
    if (keys.length > 0) {
      keys = keys.substr(0, keys.length - 1)
    }
    return keys
  },

  /**
   * 计算输入的时间和当前时间差
   * @param {*} curTime
   * @param {*} oldTime
   */
  getDiffDays (oldTime, curTime) {
    var s1 = new Date(oldTime.replace(/-/g, '/'))
    var days = curTime - s1.getTime()
    var time = parseInt(days / (1000 * 60 * 60 * 24))
    return time
  }
}
