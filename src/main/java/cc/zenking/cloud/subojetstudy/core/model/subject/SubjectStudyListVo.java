/**  
* <p>Title: SubjectStudy.java</p>
* <p>Description: </p> 
* @author pxx 
* @date 2019年3月26日  
*/  
package cc.zenking.cloud.subojetstudy.core.model.subject;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**  
* <p>Title: SubjectStudy</p>  
* <p>Description:课题研究表 manage_subject_study </p>  
* @author pxx  
* @date 2019年3月26日  
*/
@Data
public class SubjectStudyListVo {
	/**
	 * 课题Id
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
	 * 发布时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date publishTime;
	/**
	 * 流程状态
	 */
	private Integer status;
}
