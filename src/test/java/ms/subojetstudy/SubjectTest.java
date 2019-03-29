/**  
* <p>Title: Test.java</p>
* <p>Description: </p> 
* @author pxx 
* @date 2019年3月27日  
*/  
package ms.subojetstudy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cc.zenking.cloud.subojetstudy.core.entity.AchievementOrReport;
import cc.zenking.cloud.subojetstudy.core.model.subject.AchievementRequest;
import cc.zenking.cloud.subojetstudy.web.service.MySubjectService;

/**  
* <p>Title: Test</p>  
* <p>Description: </p>  
* @author pxx  
* @date 2019年3月27日  
*/
public class SubjectTest {
	
	@Autowired
	private MySubjectService mySubjectService;
	
	@Test
	public void submitAchievement(){
		AchievementRequest achievementRequest = new AchievementRequest();
		achievementRequest.setSubjectId(1);
		List<AchievementOrReport> subjectAchievementList=new ArrayList<>();
		
		AchievementOrReport achievementOrReport = new AchievementOrReport();
		achievementOrReport.setTitle("成果0");
		achievementOrReport.setSubjectReport("achievement0");
		achievementOrReport.setFileName("");
		achievementOrReport.setFilePath("");
		achievementOrReport.setOperator(12345);
		achievementOrReport.setTenantId(11);
		achievementOrReport.setType(0);
		achievementOrReport.setSubjectId(1);
		subjectAchievementList.add(achievementOrReport);
		
		AchievementOrReport achievementOrReport1 = new AchievementOrReport();
		achievementOrReport1.setTitle("成果1");
		achievementOrReport1.setSubjectReport("achievementOrReport0");
		achievementOrReport1.setFileName("");
		achievementOrReport1.setFilePath("");
		achievementOrReport1.setOperator(12345);
		achievementOrReport1.setTenantId(11);
		achievementOrReport1.setType(0);
		achievementOrReport1.setSubjectId(1);
		subjectAchievementList.add(achievementOrReport1);
		
		achievementRequest.setSubjectAchievementList(subjectAchievementList);
		
		
		
		
		List<AchievementOrReport> subjectReportList=new ArrayList<>();
		AchievementOrReport achievementOrReport11 = new AchievementOrReport();
		achievementOrReport11.setTitle("报告0");
		achievementOrReport11.setSubjectReport("Report0");
		achievementOrReport11.setFileName("");
		achievementOrReport11.setFilePath("");
		achievementOrReport11.setOperator(12345);
		achievementOrReport11.setTenantId(11);
		achievementOrReport11.setType(1);
		achievementOrReport11.setSubjectId(1);
		subjectReportList.add(achievementOrReport11);
		
		AchievementOrReport achievementOrReport111 = new AchievementOrReport();
		achievementOrReport111.setTitle("报告1");
		achievementOrReport111.setSubjectReport("Report1");
		achievementOrReport111.setFileName("");
		achievementOrReport111.setFilePath("");
		achievementOrReport111.setOperator(12345);
		achievementOrReport111.setTenantId(11);
		achievementOrReport111.setType(1);
		achievementOrReport111.setSubjectId(1);
		subjectReportList.add(achievementOrReport111);
		
		achievementRequest.setSubjectReportList(subjectReportList);
		
	}
	
	@Test
	public void updataAchievement(){
		AchievementRequest achievementRequest = new AchievementRequest();
		achievementRequest.setSubjectId(1);
		List<AchievementOrReport> subjectAchievementList=new ArrayList<>();
		
		AchievementOrReport achievementOrReport = new AchievementOrReport();
		achievementOrReport.setId(10);
		achievementOrReport.setTitle("成果0---彭西西");
		achievementOrReport.setSubjectReport("achievement0");
		achievementOrReport.setFileName("");
		achievementOrReport.setFilePath("");
		achievementOrReport.setOperator(12345);
		achievementOrReport.setTenantId(11);
		achievementOrReport.setType(0);
		achievementOrReport.setSubjectId(1);
		subjectAchievementList.add(achievementOrReport);
		
		AchievementOrReport achievementOrReport1 = new AchievementOrReport();
		achievementOrReport1.setId(11);
		achievementOrReport1.setTitle("成果1");
		achievementOrReport1.setSubjectReport("achievementOrReport0");
		achievementOrReport1.setFileName("");
		achievementOrReport1.setFilePath("");
		achievementOrReport1.setOperator(12345);
		achievementOrReport1.setTenantId(11);
		achievementOrReport1.setType(0);
		achievementOrReport1.setSubjectId(1);
		subjectAchievementList.add(achievementOrReport1);
		
		achievementRequest.setSubjectAchievementList(subjectAchievementList);
		
		
		
		
		List<AchievementOrReport> subjectReportList=new ArrayList<>();
		AchievementOrReport achievementOrReport11 = new AchievementOrReport();
		achievementOrReport11.setId(12);
		achievementOrReport11.setTitle("报告0");
		achievementOrReport11.setSubjectReport("Report0");
		achievementOrReport11.setFileName("");
		achievementOrReport11.setFilePath("");
		achievementOrReport11.setOperator(12345);
		achievementOrReport11.setTenantId(11);
		achievementOrReport11.setType(1);
		achievementOrReport11.setSubjectId(1);
		subjectReportList.add(achievementOrReport11);
		
		AchievementOrReport achievementOrReport111 = new AchievementOrReport();
		achievementOrReport111.setId(13);
		achievementOrReport111.setTitle("报告1");
		achievementOrReport111.setSubjectReport("Report1");
		achievementOrReport111.setFileName("");
		achievementOrReport111.setFilePath("");
		achievementOrReport111.setOperator(12345);
		achievementOrReport111.setTenantId(11);
		achievementOrReport111.setType(1);
		achievementOrReport111.setSubjectId(1);
		subjectReportList.add(achievementOrReport111);
		
		achievementRequest.setSubjectReportList(subjectReportList);
	}

}
