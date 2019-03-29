/**  
* <p>Title: SubjectStudy.java</p>
* <p>Description: </p> 
* @author pxx 
* @date 2019年3月26日  
*/  
package cc.zenking.cloud.subojetstudy.core.model.subject;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**  
* <p>Title: SubjectStudy</p>  
* <p>Description:课题研究表 manage_subject_study </p>  
* @author pxx  
* @date 2019年3月26日  
*/
@Data
public class SubjectStudyRequest {
	/**
	 * 课题id
	 */
	private Integer id;
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
	 * 完成时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
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
	 * 提交方式
	 */
	private Integer submitStatus;
	
	/**
	 * 学校id
	 */
	private Integer tenantId;
	
	/**
	 * 操作者
	 */
	private Integer operator;
}
