package cc.zenking.cloud.subojetstudy.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import cc.zenking.cloud.subojetstudy.core.mapper.sql.SubjectStudyQuerySQl;
import cc.zenking.cloud.subojetstudy.core.model.subject.AchievementOrReportVo;
import cc.zenking.cloud.subojetstudy.core.model.subject.AchievementRequest;
import cc.zenking.cloud.subojetstudy.core.model.subject.SubjectStudyDetailVo;
import cc.zenking.cloud.subojetstudy.core.model.subject.SubjectStudyListVo;
import cc.zenking.cloud.subojetstudy.core.model.subject.SubjectStudyRequest;
import cc.zenking.cloud.subojetstudy.core.request.PageRequest;

@Mapper
public interface MySbujectMapper {
    /**
     * 插入manage_subject_study表
     *
     * @param sysLog
     * @return
     */
    @Insert("INSERT INTO manage_subject_study (subject_year,subject_name, principal, member, study_unit, completion_time, subject_scheme, file_name, file_path, tenant_id, operator,status) VALUES"
    		+ " (#{s.subjectYear},#{s.subjectName},#{s.principal},#{s.member},#{s.studyUnit},#{s.completionTime},#{s.subjectScheme},#{s.fileName},#{s.filePath},#{s.tenantId},#{s.operator},#{s.submitStatus})")
    int applySubojet(@Param("s") SubjectStudyRequest subjectStudy);

	/**
	 * <p>Title: getMysubjectList</p>  
	 * <p>Description: 查询我的课题列表</p>  
	 * @param pageRequest 
	 * @param keyword
	 * @param status
	 * @return  
	 */  
	@SelectProvider(type=SubjectStudyQuerySQl.class,method="SubjectStudyListSQL")
    List<SubjectStudyListVo> getMysubjectList(@Param("page") PageRequest pageRequest, @Param("tenantId") Integer tenantId,@Param("keyword") String keyword, @Param("status") Integer status);

	/**
	 * <p>Title: updateMysubject</p>  
	 * <p>Description: 修改课题</p>  
	 * @param id  
	 */
    @Update("UPDATE manage_subject_study SET subject_year=#{s.subjectYear}, subject_name=#{s.subjectName}, principal=#{s.principal}, member=#{s.member}, study_unit=#{s.studyUnit}, completion_time=#{s.completionTime}, subject_scheme=#{s.subjectScheme}, file_name=#{s.fileName}, file_path=#{s.filePath},status=#{s.submitStatus} WHERE id=#{s.id} AND tenant_id=#{s.tenantId} AND operator=#{s.operator}")
	int updateMysubject(@Param("s") SubjectStudyRequest subjectStudy);

	/**
	 * <p>Title: getMyselfapply</p>  
	 * <p>Description:是否是自己申报的课题 </p>  
	 * @param user
	 * @param id
	 * @param tenantId 
	 * @return  
	 */  
    @Select("SELECT COUNT(1) FROM  manage_subject_study WHERE flag=0 AND operator=#{operator} AND id=#{id} AND tenant_id=#{tenantId}")
	Integer getMyselfapply(@Param("operator") Integer operator, @Param("tenantId") Integer tenantId, @Param("id")Integer id);

	/**
	 * <p>Title: deleteMysubject</p>  
	 * <p>Description: 删除课题</p>  
	 * @param tenantId  
	 * @param id
	 */  
    @Update("UPDATE manage_subject_study SET flag=-1 WHERE tenant_id=#{tenantId} AND id=#{id}")
	int deleteMysubject(@Param("tenantId") Integer tenantId, @Param("id") Integer id);

	/**
	 * <p>Title: getMysubjectCount</p>  
	 * <p>Description:我的课题列表总条数 </p>  
	 * @param tenantId
	 * @return  
	 */  
	@SelectProvider(type=SubjectStudyQuerySQl.class,method="getMysubjectCountSQL")
    Integer getMysubjectCount(@Param("tenantId") Integer tenantId,@Param("keyword") String keyword, @Param("status") Integer status);

	/**
	 * <p>Title: submitAchievement</p>  
	 * <p>Description: 提交成果或报告</p>  
	 * @param achievementRequest  
	 */  
	@InsertProvider(type=SubjectStudyQuerySQl.class,method="submitAchievement")
	int submitAchievement(@Param("a") AchievementRequest achievementRequest);

	/**
	 * <p>Title: updateAchievement</p>  
	 * <p>Description: :修改成果获报告</p>  
	 * @param achievementRequest  
	 */  
	@UpdateProvider(type=SubjectStudyQuerySQl.class,method="updateAchievement")
	int updateAchievement(@Param("a") AchievementRequest achievementRequest);

	/**
	 * <p>Title: getSubjectStudyDetail</p>  
	 * <p>Description:查询课题 </p>  
	 * @param tenantId
	 * @param subjectId
	 * @param status
	 * @return  
	 */  
	@Select("SELECT id as subjectId, subject_year as subjectYear, subject_name as subjectName, principal, member, study_unit as studyUnit, completion_time as completionTime, subject_scheme as subjectScheme, file_name as fileName, file_path as filePath FROM manage_subject_study WHERE id=#{subjectId} AND tenant_id=#{tenantId} AND status=#{status}")
	SubjectStudyDetailVo getSubjectStudyDetail(@Param("tenantId") Integer tenantId, @Param("subjectId") Integer subjectId,@Param("status") Integer status);

	/**
	 * <p>Title: getCompleteDetail</p>  
	 * <p>Description:查询详情页面 </p>  
	 * @param tenantId
	 * @param subjectId
	 * @return  
	 */  
	@Select("SELECT id,title,subject_report as subjectReport,type,file_name as fileName,file_path as filePath FROM subject_achievement_report WHERE subject_id=#{subjectId} AND tenant_id=#{tenantId}")
	List<AchievementOrReportVo> getCompleteDetail(@Param("tenantId") Integer tenantId,@Param("subjectId") Integer subjectId);
}