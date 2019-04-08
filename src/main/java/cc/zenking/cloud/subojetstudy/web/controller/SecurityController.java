package cc.zenking.cloud.subojetstudy.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cc.zenking.cloud.subojetstudy.core.constant.ErrorCode;
import cc.zenking.cloud.subojetstudy.core.model.function.FunctionVo;
import cc.zenking.cloud.subojetstudy.core.response.JsonResponse;
import cc.zenking.cloud.subojetstudy.web.service.SecurityService;
import lombok.extern.log4j.Log4j;

/**
 * 权限控制层
 *
 * @author gxguo
 * 2017年11月2日 上午11:45:22
 */
@Controller
@RequestMapping(value="/security")
@Log4j
public class SecurityController {
    @Resource
    private SecurityService securityService;

   /**
    * 
    * <p>Title: haveMenus</p>  
    * <p>Description: 系统菜单查询</p>  
    * @return
    */
    @RequestMapping(value="/haveMenus")
	public ResponseEntity<Object> haveMenus(){
		List<FunctionVo> FunctionVoList=null;
		try {
			FunctionVoList=securityService.haveMenus();
       } catch (Exception e) {
           e.printStackTrace();
           log.error("查询课题研究菜单失败！");
           return JsonResponse.fail(ErrorCode.ERROR_CODE_1002);
       }
       return JsonResponse.success(FunctionVoList);
	}
    
    /**
     * 
     * <p>Title: haveButtons</p>  
     * <p>Description:系统按钮查询 </p>  
     * @param menuId
     * @return
     */
    @RequestMapping(value="/haveButtons")
    public ResponseEntity<Object> haveButtons(Integer menuId){
    	List<String> FunctionVoList=null;
    	try {
    		FunctionVoList=securityService.haveButtons(menuId);
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("查询系统按钮失败！");
    		return JsonResponse.fail(ErrorCode.ERROR_CODE_1003);
    	}
    	return JsonResponse.success(FunctionVoList);
    }
}
