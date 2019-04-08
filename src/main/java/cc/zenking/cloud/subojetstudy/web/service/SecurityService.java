/**  
* <p>Title: SecurityService.java</p>
* <p>Description: </p> 
* @author pxx 
* @date 2019年4月4日  
*/  
package cc.zenking.cloud.subojetstudy.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cc.zenking.cloud.subojetstudy.config.CurrentUser;
import cc.zenking.cloud.subojetstudy.core.auth.ApplicationFunction;
import cc.zenking.cloud.subojetstudy.core.constant.Const;
import cc.zenking.cloud.subojetstudy.core.entity.Function;
import cc.zenking.cloud.subojetstudy.core.model.function.FunctionVo;

/**  
* <p>Title: SecurityService</p>  
* <p>Description: </p>  
* @author pxx  
* @date 2019年4月4日  
*/
@Service
public class SecurityService {

	/**
	 * <p>Title: haveMenus</p>  
	 * <p>Description: 系统菜单查询</p>  
	 * @return  
	 */  
	public List<FunctionVo> haveMenus() {
		CurrentUser currentUser = CurrentUser.getCurrentUser();
		List<ApplicationFunction> ApplicationFunctionList = currentUser.getApplicationFunctionList();
		List<FunctionVo> allMenu = new ArrayList<>();
		List<FunctionVo> rootMenu = new ArrayList<>();
		if (ApplicationFunctionList.size() > 0) {
			for (ApplicationFunction applicationFunction : ApplicationFunctionList) {
				if (Const.SYSTEM_NAME.equals(applicationFunction.getApplicationKey())) {
					if (applicationFunction.getApplicationFunctionList().size() > 0) {
						for (Function function : applicationFunction.getApplicationFunctionList()) {
							if (function.getIsMenu()==true) {
								FunctionVo functionVo = new FunctionVo();
								functionVo.loadData(function);
								allMenu.add(functionVo);
							}
						}
					}
				}
				// 根节点
				for (FunctionVo nav : allMenu) {
					if (nav.getParentId().equals(applicationFunction.getApplicationId())) {// 父节点是应用id，为根节点。
						rootMenu.add(nav);
					}
				}
				// 为根菜单设置子菜单，getClild是递归调用的
				for (FunctionVo nav : rootMenu) {
					/* 获取根节点下的所有子节点 使用getChild方法 */
					List<FunctionVo> childList = getChild(nav.getId(), allMenu);
					nav.setChilds(childList);// 给根节点设置子节点
				}
			}

		}
		return rootMenu;
	}
	
	  /**
     * 递归查找子菜单
     *
     * @param id       当前菜单id
     * @param rootMenu 要查找的列表
     * @return
     */
	private List<FunctionVo> getChild(Integer id, List<FunctionVo> rootMenu) {
		// 子菜单
		List<FunctionVo> childList = new ArrayList<>();
		for (FunctionVo menu : rootMenu) {
			// 遍历所有节点，将父菜单id与传过来的id比较
			if (menu.getParentId() != null) {
				if (menu.getParentId().equals(id)) {
					childList.add(menu);
				}
			}
		}
		// 把子菜单的子菜单再循环一遍
		for (FunctionVo menu : childList) {// 没有url子菜单还有子菜单
			// 递归
			menu.setChilds(getChild(menu.getId(), rootMenu));
		} // 递归退出条件
		if (childList.size() == 0) {
			return new ArrayList<>();
		}
		return childList;
	}

	/**
	 * <p>Title: haveButtons</p>  
	 * <p>Description:系统按钮查询 </p>  
	 * @param menuId 
	 * @return  
	 */  
	public List<String> haveButtons(Integer menuId) {
		CurrentUser currentUser = CurrentUser.getCurrentUser();
		List<String> allButtons = new ArrayList<>();
		if (currentUser.getApplicationFunctionList().size() > 0) {
			List<ApplicationFunction> ApplicationFunctionList = currentUser.getApplicationFunctionList();
			if (ApplicationFunctionList.size() > 0) {
				for (ApplicationFunction applicationFunction : ApplicationFunctionList) {
					if (Const.SYSTEM_NAME.equals(applicationFunction.getApplicationKey())) {
						if (applicationFunction.getApplicationFunctionList().size() > 0) {
							for (Function function : applicationFunction.getApplicationFunctionList()) {
								if (function.getIsMenu()==false && function.getParentId().equals(menuId)) {
									allButtons.add(function.getFunctionKey());
								}
							}
						}
					}
				}
			}
			return allButtons;
		}
		return allButtons;
	}

}
