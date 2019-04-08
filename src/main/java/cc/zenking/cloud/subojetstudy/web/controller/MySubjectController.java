/**  
* <p>Title: Test.java</p>
* <p>Description: </p> 
* @author pxx 
* @date 2019年3月25日  
*/  
package cc.zenking.cloud.subojetstudy.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.zenking.cloud.subojetstudy.config.CurrentUser;
import cc.zenking.cloud.subojetstudy.core.constant.ErrorCode;
import cc.zenking.cloud.subojetstudy.core.entity.AchievementOrReport;
import cc.zenking.cloud.subojetstudy.core.model.subject.AchievementRequest;
import cc.zenking.cloud.subojetstudy.core.model.subject.SubjectStudyDetailVo;
import cc.zenking.cloud.subojetstudy.core.model.subject.SubjectStudyListVo;
import cc.zenking.cloud.subojetstudy.core.model.subject.SubjectStudyRequest;
import cc.zenking.cloud.subojetstudy.core.request.PageRequest;
import cc.zenking.cloud.subojetstudy.core.response.JsonResponse;
import cc.zenking.cloud.subojetstudy.core.service.syslog.SysLogService;
import cc.zenking.cloud.subojetstudy.web.service.MySubjectService;
import lombok.extern.log4j.Log4j;
/**  
* <p>Title: Test</p>  
* <p>Description: </p>  
* @author pxx  
* @date 2019年3月26日  
*/
@Log4j
@RestController
@RequestMapping(value="/mysubject")
public class MySubjectController {
	
	@Autowired
	private MySubjectService mySubjectService;
	
	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 
	 * <p>Title: applySubojet</p>  
	 * <p>Description:申请课题 </p>  
	 * @param subjectStudy
	 * @return
	 */
	@RequestMapping(value="/applySubject")
	public ResponseEntity<Object> applySubojet(SubjectStudyRequest subjectStudy) {
		try {
			mySubjectService.applySubojet(subjectStudy);
			Integer userId = CurrentUser.getCurrentUser().getUser();
			Integer tenantId = CurrentUser.getCurrentUser().getTenantId();
			//添加日志
			sysLogService.addLog(userId, "我的课题", "申请课题", subjectStudy.getSubjectName(), tenantId);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("申请课题失败！");
			return JsonResponse.fail(ErrorCode.ERROR_CODE_1006);
		}
		return JsonResponse.success();
	}
	
	/**
	 * 
	 * <p>Title: mysubjectList</p>  
	 * <p>Description: 我的课题列表</p>  
	 * @param tenantId
	 * @param keyword
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/mysubjectList")
	public ResponseEntity<Object> mysubjectList(String keyword,Integer status,PageRequest pageRequest){
		List<SubjectStudyListVo> subjectStudyList=null;
		Integer count=0;
		try {
			count=mySubjectService.getMysubjectCount(keyword,status);
			if (count > 0) {
				subjectStudyList=mySubjectService.getMysubjectList(pageRequest,keyword,status);
			}
       } catch (Exception e) {
           e.printStackTrace();
           log.error("查询我的课题列表失败！");
           return JsonResponse.fail(ErrorCode.ERROR_CODE_1001);
       }
       return JsonResponse.success(subjectStudyList,subjectStudyList==null?0:subjectStudyList.size(),pageRequest.countTatalPage(count));
	}
	
	/**
	 * 
	 * <p>Title: applySubojet</p>  
	 * <p>Description:修改课题 </p>  
	 * @param  id
	 * @return
	 */
	@RequestMapping(value = "/updateMysubject")
	public ResponseEntity<Object> updateMysubject(SubjectStudyRequest subjectStudy) {
		try {
			mySubjectService.updateMysubject(subjectStudy);
			Integer userId = CurrentUser.getCurrentUser().getUser();
			Integer tenantId = CurrentUser.getCurrentUser().getTenantId();
			//添加日志
	        sysLogService.addLog(userId, "我的课题", "修改课题 ", subjectStudy.getSubjectName(), tenantId);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("修改课题失败！");
			return JsonResponse.fail(ErrorCode.ERROR_CODE_1007);
		}
		return JsonResponse.success();
	}
	
	/**
	 * 
	 * <p>Title: applySubojet</p>  
	 * <p>Description:删除课题 </p>  
	 * @param  id
	 * @param  subjectName
	 * @return
	 */
	@RequestMapping(value = "/deleteMysubject")
	public ResponseEntity<Object> deleteMysubject(Integer id,String subjectName) {
		try {
			mySubjectService.deleteMysubject(id);
			Integer userId = CurrentUser.getCurrentUser().getUser();
			Integer tenantId = CurrentUser.getCurrentUser().getTenantId();
			//添加日志
	        sysLogService.addLog(userId, "我的课题", "删除课题 ", subjectName, tenantId);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除课题失败！");
			return JsonResponse.fail(ErrorCode.ERROR_CODE_1008);
		}
		return JsonResponse.success();
	}

	
	/**
	 * 
	 * <p>Title: applySubojet</p>  
	 * <p>Description:提交成果或报告 </p>  
	 * @param subjectStudy
	 * @return
	 */
	@RequestMapping(value="/submitAchievement")
	public ResponseEntity<Object> submitAchievement(AchievementRequest achievementRequest) {
		try {
			mySubjectService.submitAchievement(achievementRequest);
			Integer userId = CurrentUser.getCurrentUser().getUser();
			Integer tenantId = CurrentUser.getCurrentUser().getTenantId();
			//添加日志
			//成果
			List<AchievementOrReport> subjectAchievementList = achievementRequest.getSubjectAchievementList();
			//报告
			List<AchievementOrReport> subjectReportList = achievementRequest.getSubjectReportList();
			if (subjectAchievementList.size() > 0) {
				for (AchievementOrReport achievementOrReport : subjectAchievementList) {
					sysLogService.addLog(userId, "我的课题", "提交成果 ", achievementOrReport.getTitle(), tenantId);
				}
			}
			if (subjectReportList.size() > 0) {
				for (AchievementOrReport achievementOrReport : subjectReportList) {
					sysLogService.addLog(userId, "我的课题", "提交报告 ", achievementOrReport.getTitle(), tenantId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("提交成果或报告失败！");
			return JsonResponse.fail(ErrorCode.ERROR_CODE_1009);
		}
		return JsonResponse.success();
	}
	
	/**
	 * 
	 * <p>Title: applySubojet</p>  
	 * <p>Description:修改成果获报告 </p>  
	 * @param  id
	 * @return
	 */
	@RequestMapping(value = "/updateAchievement")
	public ResponseEntity<Object> updateAchievement(AchievementRequest achievementRequest) {
		try {
			mySubjectService.updateAchievement(achievementRequest);
			Integer userId = CurrentUser.getCurrentUser().getUser();
			Integer tenantId = CurrentUser.getCurrentUser().getTenantId();
			//添加日志
			//成果
			List<AchievementOrReport> subjectAchievementList = achievementRequest.getSubjectAchievementList();
			//报告
			List<AchievementOrReport> subjectReportList = achievementRequest.getSubjectReportList();
			if (subjectAchievementList.size() > 0) {
				for (AchievementOrReport achievementOrReport : subjectAchievementList) {
					sysLogService.addLog(userId, "我的课题", "修改成果 ", achievementOrReport.getTitle(), tenantId);
				}
			}
			if (subjectReportList.size() > 0) {
				for (AchievementOrReport achievementOrReport : subjectReportList) {
					sysLogService.addLog(userId, "我的课题", "修改报告 ", achievementOrReport.getTitle(), tenantId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("修改成果获报告失败！");
			return JsonResponse.fail(ErrorCode.ERROR_CODE_1010);
		}
		return JsonResponse.success();
	}
	
	/**
	 * 
	 * <p>Title: completeDetail</p>  
	 * <p>Description: 详情页面</p>  
	 * @param tenantId
	 * @param keyword
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/completeDetail")
	public ResponseEntity<Object> completeDetail(Integer subjectId,Integer status){
		SubjectStudyDetailVo subjectStudyDetailVo=null;
		try {
			subjectStudyDetailVo=mySubjectService.getCompleteDetail(subjectId,status);
       } catch (Exception e) {
           e.printStackTrace();
           log.error("查看详情页面失败！");
           return JsonResponse.fail(ErrorCode.ERROR_CODE_1011);
       }
       return JsonResponse.success(subjectStudyDetailVo);
	}
}
