/**  
* <p>Title: Test.java</p>
* <p>Description: </p> 
* @author pxx 
* @date 2019年3月29日  
*/  
package ms.subojetstudy;

/**  
* <p>Title: Test</p>  
* <p>Description: </p>  
* @author pxx  
* @date 2019年3月29日  
*/
public class Test {
	
	public static void main(String[] args) {
		Integer status =0;
		String checkFlowNode = CheckFlowNode(status);
		System.out.println(checkFlowNode);
	}
	
	public static String CheckFlowNode(Integer status) {
		if (status!=null) {
			switch (status) {
				case 0:
					return "草稿";
				case 1:
					return "待上报";
				case 2:
					return "上报未通过";
				case 3:
					return "待审核";
				case 4:
					return "审核未通过";
				case 5:
					return "待提交成果";
				case 6:
					return "待再次审核";
				case 7:
					return "再次审核未通过";
				case 8:
					return "已完成";
				case 9:
					return "待市级审核";
				case 10:
					return "市级审核通过";
				case 11:
					return "市级审核未通";
			}
		}
		return "";
	}

}
