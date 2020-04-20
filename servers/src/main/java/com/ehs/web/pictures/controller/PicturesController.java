package com.ehs.web.pictures.controller;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.web.pictures.bean.PicturesMove;
import com.ehs.web.pictures.bean.PicturesQuery;
import com.ehs.web.pictures.service.PicturesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping({"/portal/web/pictures","/web/pictures"})
public class PicturesController {

    private static final Logger logger=LoggerFactory.getLogger(PicturesController.class);

    @Resource
    private PicturesService picService;

    /*
      * @Function: getPicturesList
      * @Description:
      * @param:[picturesQuery]
      * @return:java.lang.String
      * @throws:
      * @version: v1.0.0
      * @author: qjj
      * @date: 2020/4/15 14:41
      * Modification History:
      * Date          Author       Version          Description
      *---------------------------------------------------------*
      * 2020/4/15         qjj        v1.0.0           修改原因
      **/
    @RequestAuth(menuKeys = {AuthConstants.GLOBAL_MENU_KEY})
    @RequestMapping("/getPicturesList")
    public String getPicturesList(@RequestBody PicturesQuery picturesQuery){
        PageInfoBean pageBean=picService.findPicturesList(picturesQuery);
        return pageBean!=null?JsonUtils.toJsonString(pageBean):"[]";
    }

    /*
      * @Function: savePictureInfo
      * @Description: 保存图片信息
      * @param:[fileId, fileName] 
      * @return:java.lang.String 
      * @throws: 
      * @version: v1.0.0 
      * @author: qjj
      * @date: 2020/4/16 10:26
      * Modification History:
      * Date          Author       Version          Description
      *---------------------------------------------------------*
      * 2020/4/16         qjj        v1.0.0           修改原因 
      **/
    @RequestAuth(menuKeys = {"picturesManeger"})
    @RequestMapping("/savePictureInfo")
    public String savePictureInfo(@RequestParam String fileId,@RequestParam String fileName,@RequestParam int order){
        ResultBean resultBean=new ResultBean();
        try {
            picService.savePictureInfo(fileId,fileName,order);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return JsonUtils.toJsonString(resultBean.ok("图片上传成功"));
    }


    /*
      * @Function: updateMovedRow
      * @Description: 图片的上下移动
      * @param:[picturesMove]
      * @return:java.lang.String
      * @throws:
      * @version: v1.0.0
      * @author: qjj
      * @date: 2020/4/16 15:16
      * Modification History:
      * Date          Author       Version          Description
      *---------------------------------------------------------*
      * 2020/4/16         qjj        v1.0.0           修改原因
      **/
    @RequestAuth(menuKeys = {"picturesManeger"})
    @RequestMapping(value = "/updateMovedRow")
    public String updateMovedRow(@RequestBody PicturesMove picturesMove) {
        ResultBean resultBean=new ResultBean();
        try {
            picService.saveSortChangedEntity(picturesMove);
        } catch (Exception e) {
            return JsonUtils.toJsonString(resultBean.error("操作失败"));
        }
        return JsonUtils.toJsonString(resultBean.ok("操作成功！"));
    }

    /*
      * @Function: deletePictureInfo
      * @Description: 删除图片
      * @param: key 图片对象唯一标识
      * @return:java.lang.String
      * @throws:
      * @version: v1.0.0
      * @author: qjj
      * @date: 2020/4/16 18:17
      * Modification History:navigate
      * Date          Author       Version          Description
      *---------------------------------------------------------*
      * 2020/4/16         qjj        v1.0.0           修改原因
      **/
    @RequestAuth(menuKeys = {"picturesManeger"})
    @RequestMapping(value = "/deletePictureInfo")
    public String deletePictureInfo(@RequestParam String key) {
        ResultBean resultBean=new ResultBean();
        try {
            picService.deletePictureInfo(key);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonUtils.toJsonString(resultBean.error("删除失败"));
        }
        return JsonUtils.toJsonString(resultBean.ok("删除成功！"));
    }
}
