/**  
* <p>Title: SubjectStudy.java</p>
* <p>Description: </p> 
* @author pxx 
* @date 2019年3月26日  
*/  
package cc.zenking.cloud.subojetstudy.core.model.subject;

import java.util.Date;
import java.util.List;

import cc.zenking.cloud.subojetstudy.core.entity.AchievementOrReport;
import lombok.Data;

/**  
* <p>Title: SubjectStudy</p>  
* <p>Description:课题研究表 manage_subject_study </p>  
* @author pxx  
* @date 2019年3月26日  
*/
@Data
public class SubjectStudyDetailVo {
	/**
	 * 课题Id
	 */
	private Integer subjectId;
	
	/**
	 * 课题年度
	 */
	private String subjectYear;
	
	/**
	 * 课题名称
	 */
	private String subjectName;
	
	/**
	 * 负责人
	 */
	private String principal;
	
	/**
	 * 成员
	 */
	private String member;
	
	/**
	 * 研究单位
	 */
	private String studyUnit;
	
	/**
	 * 发布时间
	 */
	private Date publishTime;
	
	/**
	 * 完成时间
	 */
	private Date completionTime;

	/**
	 * 课题方案
	 */
	private String subjectScheme;
	
	/**
	 * 附件名称
	 */
	private String fileName;
	
	/**
	 * 附件地址
	 */
	private String filePath;
	
	/**
	 * 课题成果
	 */
	private List<AchievementOrReportVo> subjectAchievementList;
	
	/**
	 * 课题报告
	 */
	private List<AchievementOrReportVo> subjectReportList;
}
