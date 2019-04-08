/**  
* <p>Title: SubjectStudyListSql.java</p>
* <p>Description: </p> 
* @author pxx 
* @date 2019年3月27日  
*/  
package cc.zenking.cloud.subojetstudy.core.mapper.sql;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import cc.zenking.cloud.subojetstudy.core.entity.AchievementOrReport;
import cc.zenking.cloud.subojetstudy.core.model.subject.AchievementRequest;
import cc.zenking.cloud.subojetstudy.core.request.PageRequest;

/**  
* <p>Title: SubjectStudyListSql</p>  
* <p>Description: 动态SQL</p>  
* @author pxx  
* @date 2019年3月27日  
*/
public class SubjectStudyQuerySQl {
	
	/**
	 * <p>Title: getMysubjectList</p>  
	 * <p>Description: 查询我的课题列表</p>  
	 * @param pageRequest 
	 * @param keyword
	 * @param status
	 * @return  
	 */ 
	@SuppressWarnings("unused")
	public String SubjectStudyListSQL(Map map) {
		Integer tenantId = (Integer) map.get("tenantId");
        String keyword = (String) map.get("keyword");
        Integer status = (Integer) map.get("status");
        PageRequest page = (PageRequest) map.get("page");
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id,subject_name as subjectName,subject_year as subjectYear,publish_time as publishTime,status from manage_subject_study WHERE tenant_id=#{tenantId} ");
        if (status!=null && !status.equals("")) {
        	sql.append("  AND status = '"+status+"'  ");
        }
        if (keyword!=null && !keyword.equals("")) {
        	sql.append(" AND subject_name LIKE '"+keyword+"%' ");
        }
        sql.append(" ORDER BY publish_time DESC limit  #{page.countOffset},#{page.limit}");
        return sql.toString();
    }
	
	
	/**
	 * <p>Title: getMysubjectCount</p>  
	 * <p>Description:我的课题列表总条数 </p>  
	 * @param tenantId
	 * @return  
	 */  
	@SuppressWarnings("unused")
	public String getMysubjectCountSQL(Map map) {
		Integer tenantId = (Integer) map.get("tenantId");
		String keyword = (String) map.get("keyword");
		Integer status = (Integer) map.get("status");
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT count(1) from manage_subject_study WHERE tenant_id=#{tenantId} AND flag=0 ");
		if (status!=null && !status.equals("")) {
        	sql.append("  AND status = '"+status+"'  ");
        }
        if (keyword!=null && !keyword.equals("")) {
        	sql.append(" AND subject_name LIKE '"+keyword+"%' ");
        }
		sql.append(" ORDER BY publish_time DESC");
		return sql.toString();
	}
	
	
	/**
	 * <p>Title: submitAchievement</p>  
	 * <p>Description: 提交成果或报告</p>  
	 * @param achievementRequest  
	 */  
	@SuppressWarnings("unused")
	public String submitAchievement(Map map) {
		AchievementRequest achievementRequest = (AchievementRequest) map.get("a");
		Integer subjectId = achievementRequest.getSubjectId();
		List<AchievementOrReport> subjectAchievementList = achievementRequest.getSubjectAchievementList();
		List<AchievementOrReport> subjectReportList = achievementRequest.getSubjectReportList();
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO subject_achievement_report");
		sql.append(" (subject_id, title, subject_report, type, file_name, file_path, tenant_id, operator,status) VALUES ");
		MessageFormat messageFormat = new MessageFormat("('"+subjectId+"', " +
				"#'{'a.subjectAchievementList[{0}].title}, #'{'a.subjectAchievementList[{0}].subjectReport}, " +
				"#'{'a.subjectAchievementList[{0}].type}, #'{'a.subjectAchievementList[{0}].fileName}, " +
				"#'{'a.subjectAchievementList[{0}].filePath}, #'{'a.subjectAchievementList[{0}].tenantId},#'{'a.subjectAchievementList[{0}].operator},#'{'a.subjectAchievementList[{0}].status})");
		for (int i = 0; i < subjectAchievementList.size(); i++) {
			sql.append(messageFormat.format(new Integer[]{i}));
			sql.append(",");
		}
		MessageFormat messageFormat1 = new MessageFormat("('"+subjectId+"', " +
				"#'{'a.subjectReportList[{0}].title}, #'{'a.subjectReportList[{0}].subjectReport}, " +
				"#'{'a.subjectReportList[{0}].type}, #'{'a.subjectReportList[{0}].fileName}, " +
				"#'{'a.subjectReportList[{0}].filePath}, #'{'a.subjectReportList[{0}].tenantId},#'{'a.subjectReportList[{0}].operator,#'{'a.subjectReportList[{0}].status}})");
		for (int i = 0; i < subjectReportList.size(); i++) {
			sql.append(messageFormat1.format(new Integer[]{i}));
			sql.append(",");
		}
		sql.setLength(sql.length() - 1);
		return sql.toString();
	}
	
	/**
	 * <p>Title: updateAchievement</p>  
	 * <p>Description: :修改成果获报告</p>  
	 * @param achievementRequest  
	 */ 
	@SuppressWarnings("unused")
	public String updateAchievement(Map map) {
		AchievementRequest achievementRequest = (AchievementRequest) map.get("a");
		Integer subjectId = achievementRequest.getSubjectId();
		List<AchievementOrReport> subjectAchievementList = achievementRequest.getSubjectAchievementList();
		List<AchievementOrReport> subjectReportList = achievementRequest.getSubjectReportList();
		StringBuilder sql = new StringBuilder();
		for (AchievementOrReport subjectAchievement : subjectAchievementList) {
			sql.append(" UPDATE subject_achievement_report SET title='" + subjectAchievement.getTitle() + "',");
			sql.append(" subject_report='" + subjectAchievement.getSubjectReport() + "',");
			sql.append(" file_name='" + subjectAchievement.getFileName() + "',");
			sql.append(" file_path='" + subjectAchievement.getFilePath() + "' ");
			sql.append(" WHERE id='" + subjectAchievement.getId() + "' and subject_id='"+ subjectId + "' and tenant_id='"
					+ subjectAchievement.getTenantId() + "' and  operator='" + subjectAchievement.getOperator() + "';");
		}
		for (AchievementOrReport subjectAchievement : subjectReportList) {
			sql.append(" UPDATE subject_achievement_report SET title='" + subjectAchievement.getTitle() + "',");
			sql.append(" subject_report='" + subjectAchievement.getSubjectReport() + "',");
			sql.append(" file_name='" + subjectAchievement.getFileName() + "',");
			sql.append(" file_path='" + subjectAchievement.getFilePath() + "' ");
			sql.append(" WHERE id='" + subjectAchievement.getId() + "' and subject_id='"+ subjectId + "' and tenant_id='"
					+ subjectAchievement.getTenantId() + "' and  operator='" + subjectAchievement.getOperator() + "';");
		}
		return sql.toString();
	}
	
	/**
	 * <p>Title: getMysubjectList</p>  
	 * <p>Description: 查询课题</p>  
	 * @param pageRequest 
	 * @param keyword
	 * @param status
	 * @return  
	 */ 
	@SuppressWarnings("unused")
	public String SubjectStudyList(Map map) {
		Integer tenantId = (Integer) map.get("tenantId");
        String keyword = (String) map.get("keyword");
        Integer status = (Integer) map.get("status");
        PageRequest page = (PageRequest) map.get("page");
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id,subject_name as subjectName,subject_year as subjectYear,publish_time as publishTime,status from manage_subject_study WHERE tenant_id=#{tenantId} ");
        if (status!=null && !status.equals("")) {
        	sql.append("  AND status = '"+status+"'  ");
        }
        if (keyword!=null && !keyword.equals("")) {
        	sql.append(" AND subject_name LIKE '"+keyword+"%' ");
        }
        sql.append(" ORDER BY publish_time DESC limit  #{page.countOffset},#{page.limit}");
        return sql.toString();
    }
	

	/**
	 * <p>Title: getMysubjectCount</p>  
	 * <p>Description:课题列表总条数 </p>  
	 * @param tenantId
	 * @return  
	 */  
	@SuppressWarnings("unused")
	public String getMysubjectCount(Map map) {
		Integer tenantId = (Integer) map.get("tenantId");
		String keyword = (String) map.get("keyword");
		Integer status = (Integer) map.get("status");
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT count(1) from manage_subject_study WHERE tenant_id=#{tenantId} AND flag=0 ");
		if (status!=null && !status.equals("")) {
        	sql.append("  AND status = '"+status+"'  ");
        }
        if (keyword!=null && !keyword.equals("")) {
        	sql.append(" AND subject_name LIKE '"+keyword+"%' ");
        }
		sql.append(" ORDER BY publish_time DESC");
		return sql.toString();
	}
}
