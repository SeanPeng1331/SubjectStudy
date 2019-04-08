/**  
* <p>Title: SubjectStudyController.java</p>
* <p>Description: </p> 
* @author pxx 
* @date 2019年3月28日  
*/  
package cc.zenking.cloud.subojetstudy.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.zenking.cloud.subojetstudy.config.CurrentUser;
import cc.zenking.cloud.subojetstudy.core.constant.ErrorCode;
import cc.zenking.cloud.subojetstudy.core.model.subject.SubjectStudyListVo;
import cc.zenking.cloud.subojetstudy.core.request.PageRequest;
import cc.zenking.cloud.subojetstudy.core.response.JsonResponse;
import cc.zenking.cloud.subojetstudy.core.service.syslog.SysLogService;
import cc.zenking.cloud.subojetstudy.web.service.SubjectStudyService;
import lombok.extern.log4j.Log4j;

/**  
* <p>Title: SubjectStudyController</p>  
* <p>Description: 课题研究控制成</p>  
* @author pxx  
* @date 2019年3月28日  
*/
@Log4j
@RestController
@RequestMapping(value="/subjectmanage")
public class SubjectStudyController {
	
	@Autowired
	private SubjectStudyService subjectStudyService;
	
	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 
	 * <p>Title: mysubjectList</p>  
	 * <p>Description: 课题列表</p>  
	 * @param tenantId
	 * @param keyword
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/subjectmanageList")
	public ResponseEntity<Object> subjectmanageList(String keyword,Integer status,PageRequest pageRequest){
		List<SubjectStudyListVo> subjectStudyList=null;
		Integer count=0;
		try {
			count=subjectStudyService.getSubjectmanageCount(keyword,status);
			if (count > 0) {
				subjectStudyList=subjectStudyService.getSubjectmanageList(pageRequest,keyword,status);
			}
       } catch (Exception e) {
           e.printStackTrace();
           log.error("查询课题列表失败！");
           return JsonResponse.fail(ErrorCode.ERROR_CODE_1006);
       }
       return JsonResponse.success(subjectStudyList,subjectStudyList==null?0:subjectStudyList.size(),pageRequest.countTatalPage(count));
	}
	
	/**
	 * 
	 * <p>Title: updateFlowNode</p>  
	 * <p>Description: 修改流程节点</p>  
	 * @param id 课题id
	 * @param subjectName 课题名称
	 * @param status 流程节点状态
	 * @return
	 */
	@RequestMapping(value = "/updateFlowNode")
	public ResponseEntity<Object> updateFlowNode(Integer id,String subjectName, Integer status) {
		//检验流程节点
		String flowNodeName = CheckFlowNode(status);
		Integer userId = CurrentUser.getCurrentUser().getUser();
        Integer tenantId = CurrentUser.getCurrentUser().getTenantId();
        //添加日志
        sysLogService.addLog(userId, "课题管理", flowNodeName, subjectName, tenantId);
		try {
			subjectStudyService.updateFlowNode(id,status);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("修改流程节点失败！");
			return JsonResponse.fail(ErrorCode.ERROR_CODE_1002);
		}
		return JsonResponse.success();
	}

	/**
	 * <p>Title: CheckFlowNode</p>  
	 * <p>Description: 判断流程节点值</p>  
	 * @param status  
	 */  
	public String CheckFlowNode(Integer status) {
		if (status!=null) {
			switch (status) {
				case 0:
					return "草稿";
				case 1:
					return "上报";
				case 2:
					return "上报未通过";
				case 3:
					return "审核";
				case 4:
					return "审核未通过";
				case 5:
					return "提交成果";
				case 6:
					return "待再次审核";
				case 7:
					return "再次审核未通过";
				case 8:
					return "已完成";
				case 9:
					return "市级审核";
				case 10:
					return "市级审核通过";
				case 11:
					return "市级审核未通";
			}
		}
		return "";
	}

}
