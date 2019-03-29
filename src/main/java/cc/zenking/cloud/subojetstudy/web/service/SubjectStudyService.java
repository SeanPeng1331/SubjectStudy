/**  
* <p>Title: SubjectStudyService.java</p>
* <p>Description: </p> 
* @author pxx 
* @date 2019年3月28日  
*/  
package cc.zenking.cloud.subojetstudy.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.zenking.cloud.subojetstudy.config.CurrentUser;
import cc.zenking.cloud.subojetstudy.core.mapper.SubjectStudyMapper;
import cc.zenking.cloud.subojetstudy.core.model.subject.SubjectStudyListVo;
import cc.zenking.cloud.subojetstudy.core.request.PageRequest;

/**  
* <p>Title: SubjectStudyService</p>  
* <p>Description: </p>  
* @author pxx  
* @date 2019年3月28日  
*/
@Service
public class SubjectStudyService {
	
	@Autowired
	private SubjectStudyMapper subjectStudyMapper;

	/**
	 * <p>Title: getSubjectmanageCount</p>  
	 * <p>Description: 查询课题总数</p>  
	 * @param keyword
	 * @param status
	 * @return  
	 */  
	public Integer getSubjectmanageCount(String keyword, Integer status) {
		Integer  tenantId= CurrentUser.getCurrentUser().getTenantId();
		return subjectStudyMapper.getMysubjectCount(tenantId, keyword, status);
	}

	/**
	 * <p>Title: getSubjectmanageList</p>  
	 * <p>Description: 查询课题列表</p>  
	 * @param pageRequest
	 * @param keyword
	 * @param status
	 * @return  
	 */  
	public List<SubjectStudyListVo> getSubjectmanageList(PageRequest pageRequest, String keyword, Integer status) {
		Integer  tenantId= CurrentUser.getCurrentUser().getTenantId();
		List<SubjectStudyListVo>  getSubjectmanageList= subjectStudyMapper.getMysubjectList(pageRequest, tenantId, keyword, status);
		return getSubjectmanageList;
	}

	/**
	 * <p>Title: updateFlowNode</p>  
	 * <p>Description: 修改流程节点</p>  
	 * @param id
	 * @param status  
	 */  
	@Transactional
	public void updateFlowNode(Integer id, int status) {
		Integer  tenantId= CurrentUser.getCurrentUser().getTenantId();
		Integer  operator= CurrentUser.getCurrentUser().getUser();
		subjectStudyMapper.updateFlowNode(id,status,tenantId,operator);
	}

}
