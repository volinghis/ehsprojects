package com.ehs.jobs.util;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseUtil {

	private static final Logger logger=LoggerFactory.getLogger(ResponseUtil.class);
	
	/**
	 * 
	* @Function: ResponseUtil.java
	* @Description: 校验数据是否存在
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2020年5月14日 下午3:08:57 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2020年5月14日     chentm           v1.0.0               修改原因
	 */
	public static boolean validateData(String url){
		try {
			 String result = "";

		        // 创建httpclient对象
		        CloseableHttpClient httpClient = HttpClients.createDefault();

		        // 创建get方式请求对象
		        HttpGet httpGet = new HttpGet(url);
		        // 通过请求对象获取响应对象
		        CloseableHttpResponse response = httpClient.execute(httpGet);

		        // 获取结果实体
		        // 判断网络连接状态码是否正常(0--200都数正常)
		        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
		            result = EntityUtils.toString(response.getEntity(), "utf-8");
		        }
		        // 释放链接
		        response.close();
		        return Boolean.valueOf(result);
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return false;
	}

}
