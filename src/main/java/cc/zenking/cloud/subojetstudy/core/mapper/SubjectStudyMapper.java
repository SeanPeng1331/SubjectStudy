/**  
* <p>Title: SubjectStudyMapper.java</p>
* <p>Description: </p> 
* @author pxx 
* @date 2019年3月28日  
*/  
package cc.zenking.cloud.subojetstudy.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import cc.zenking.cloud.subojetstudy.core.mapper.sql.SubjectStudyQuerySQl;
import cc.zenking.cloud.subojetstudy.core.model.subject.SubjectStudyListVo;
import cc.zenking.cloud.subojetstudy.core.request.PageRequest;

/**  
* <p>Title: SubjectStudyMapper</p>  
* <p>Description: </p>  
* @author pxx  
* @date 2019年3月28日  
*/
@Mapper
public interface SubjectStudyMapper {
	
	/**
	 * <p>Title: getMysubjectCount</p>  
	 * <p>Description:查询课题列表总条数 </p>  
	 * @param tenantId
	 * @return  
	 */  
	@SelectProvider(type=SubjectStudyQuerySQl.class,method="getMysubjectCount")
    Integer getMysubjectCount(@Param("tenantId") Integer tenantId,@Param("keyword") String keyword, @Param("status") Integer status);
	
	/**
	 * <p>Title: getMysubjectList</p>  
	 * <p>Description: 查询课题列表</p>  
	 * @param pageRequest 
	 * @param keyword
	 * @param status
	 * @return  
	 */  
	@SelectProvider(type=SubjectStudyQuerySQl.class,method="SubjectStudyList")
    List<SubjectStudyListVo> getMysubjectList(@Param("page") PageRequest pageRequest, @Param("tenantId") Integer tenantId,@Param("keyword") String keyword, @Param("status") Integer status);

	/**
	 * <p>Title: updateFlowNode</p>  
	 * <p>Description: 修改流程节点</p>  
	 * @param id
	 * @param status
	 * @param tenantId  
	 * @param operator 
	 */ 
	@Update(" UPDATE manage_subject_study SET status=#{status},operator=#{operator} WHERE id=#{id} AND tenant_id=#{tenantId} AND flag=0")
	int updateFlowNode(@Param("id") Integer id, @Param("status") int status,@Param("tenantId") Integer tenantId,@Param("operator") Integer operator);

}
