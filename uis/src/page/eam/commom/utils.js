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
  }
}
