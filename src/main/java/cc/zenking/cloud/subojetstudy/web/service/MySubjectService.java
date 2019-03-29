/**  
* <p>Title: MySubjectService.java</p>
* <p>Description: </p> 
* @author pxx 
* @date 2019年3月26日  
*/  
package cc.zenking.cloud.subojetstudy.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.zenking.cloud.subojetstudy.config.CurrentUser;
import cc.zenking.cloud.subojetstudy.core.entity.SubjectStudy;
import cc.zenking.cloud.subojetstudy.core.mapper.MySbujectMapper;
import cc.zenking.cloud.subojetstudy.core.model.subject.AchievementOrReportVo;
import cc.zenking.cloud.subojetstudy.core.model.subject.AchievementRequest;
import cc.zenking.cloud.subojetstudy.core.model.subject.SubjectStudyDetailVo;
import cc.zenking.cloud.subojetstudy.core.model.subject.SubjectStudyListVo;
import cc.zenking.cloud.subojetstudy.core.model.subject.SubjectStudyRequest;
import cc.zenking.cloud.subojetstudy.core.request.PageRequest;

/**  
* <p>Title: MySubjectService</p>  
* <p>Description: </p>  
* @author pxx  
* @date 2019年3月26日  
*/
@Service
public class MySubjectService {

	@Autowired
	private MySbujectMapper mySbujectMapper;
	/**
	 * <p>Title: applySubojet</p>  
	 * <p>Description: 申请课题</p>  
	 * @param subjectStudy  
	 */  
	@Transactional
	public void applySubojet(SubjectStudyRequest subjectStudy) {
		Integer operator = CurrentUser.getCurrentUser().getUser();
		Integer tenantId = CurrentUser.getCurrentUser().getTenantId();
		subjectStudy.setTenantId(tenantId);
		subjectStudy.setOperator(operator);
		mySbujectMapper.applySubojet(subjectStudy);
	}
	/**
	 * <p>Title: getMysubjectList</p>  
	 * <p>Description: 我的课题列表</p>  
	 * @param pageRequest 
	 * @param keyword
	 * @param status
	 * @param tenantId
	 * @return  
	 */  
	public List<SubjectStudyListVo> getMysubjectList(PageRequest pageRequest, String keyword, Integer status) {
		//获取学校机构id
		Integer tenantId = CurrentUser.getCurrentUser().getTenantId();
		List<SubjectStudyListVo> mysubjectList=mySbujectMapper.getMysubjectList(pageRequest,tenantId,keyword, status);
		return mysubjectList;
	}
	/**
	 * <p>Title: updateMysubject</p>  
	 * <p>Description: 课题修改</p>  
	 * @param id  
	 */  
	@Transactional
	public void updateMysubject(SubjectStudyRequest subjectStudy) {
		Integer operator = CurrentUser.getCurrentUser().getUser();
		Integer tenantId = CurrentUser.getCurrentUser().getTenantId();
		subjectStudy.setOperator(operator);
		subjectStudy.setTenantId(tenantId);
		//是否是自己申报的课题
		Integer myselfapply = mySbujectMapper.getMyselfapply(operator,tenantId,subjectStudy.getId());
		if (myselfapply > 0) {
			mySbujectMapper.updateMysubject(subjectStudy);
		}
	}
	
	/**
	 * <p>Title: deleteMysubject</p>  
	 * <p>Description:删除课题 </p>  
	 * @param id  
	 */  
	@Transactional
	public void deleteMysubject(Integer id) {
		Integer operator = CurrentUser.getCurrentUser().getUser();
		Integer tenantId = CurrentUser.getCurrentUser().getTenantId();
		//是否是自己申报的课题
		int myselfapply = mySbujectMapper.getMyselfapply(operator,tenantId,id);
		if (myselfapply > 0) {
			mySbujectMapper.deleteMysubject(tenantId,id);
		}
	}
	/**
	 * <p>Title: getMysubjectCount</p>  
	 * <p>Description: 我的课题列表总条数</p>  
	 * @return  
	 */  
	public Integer getMysubjectCount(String keyword, Integer status) {
		//获取学校机构id
		Integer tenantId = CurrentUser.getCurrentUser().getTenantId();
		return mySbujectMapper.getMysubjectCount(tenantId,keyword,status);
	}
	/**
	 * <p>Title: submitAchievement</p>  
	 * <p>Description:提交成果或报告 </p>  
	 * @param achievementRequest  
	 */  
	@Transactional
	public void submitAchievement(AchievementRequest achievementRequest) {
		mySbujectMapper.submitAchievement(achievementRequest);
	}
	/**
	 * <p>Title: updateAchievement</p>  
	 * <p>Description:修改成果获报告 </p>  
	 * @param achievementRequest  
	 */  
	@Transactional
	public void updateAchievement(AchievementRequest achievementRequest) {
		mySbujectMapper.updateAchievement(achievementRequest);
	}
	/**
	 * <p>Title: getCompleteDetail</p>  
	 * <p>Description:详情页面 </p>  
	 * @param subjectId
	 * @param status
	 * @return  
	 */  
	public SubjectStudyDetailVo getCompleteDetail(Integer subjectId, Integer status) {
		Integer tenantId = CurrentUser.getCurrentUser().getTenantId();
		//先查询课题
		SubjectStudyDetailVo subjectStudyDetailVo=new SubjectStudyDetailVo();
		subjectStudyDetailVo=mySbujectMapper.getSubjectStudyDetail(tenantId,subjectId,status);
		if (subjectStudyDetailVo !=null && !subjectStudyDetailVo.equals("")) {
			//查询成果或报告
			List<AchievementOrReportVo> AchievementOrReportList=mySbujectMapper.getCompleteDetail(tenantId,subjectId);
			List<AchievementOrReportVo> subjectAchievementList=new ArrayList<>();
			List<AchievementOrReportVo> subjectReportList=new ArrayList<>();
			for (AchievementOrReportVo achievementOrReportVo : AchievementOrReportList) {
				if (achievementOrReportVo.getType()==0) {
					subjectAchievementList.add(achievementOrReportVo);
				}else if (achievementOrReportVo.getType()==1) {
					subjectReportList.add(achievementOrReportVo);
				}
			}
			subjectStudyDetailVo.setSubjectAchievementList(subjectAchievementList);
			subjectStudyDetailVo.setSubjectReportList(subjectReportList);
		}
		return subjectStudyDetailVo;
	}

}
