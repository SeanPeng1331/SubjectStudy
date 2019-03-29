/**  
* <p>Title: SubjectStudy.java</p>
* <p>Description: </p> 
* @author pxx 
* @date 2019年3月26日  
*/  
package cc.zenking.cloud.subojetstudy.core.model.subject;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import cc.zenking.cloud.subojetstudy.core.entity.AchievementOrReport;
import lombok.Data;

/**  
* <p>Title: SubjectStudy</p>  
* <p>Description:课题研究表 manage_subject_study </p>  
* @author pxx  
* @date 2019年3月26日  
*/
@Data
public class AchievementRequest {
	
	/**
	 * 课题Id
	 */
	private Integer subjectId;
	
	/**
	 * 课题成果
	 */
	private List<AchievementOrReport> subjectAchievementList;
	
	/**
	 * 课题报告
	 */
	private List<AchievementOrReport> subjectReportList;
}
