package cc.zenking.cloud.subojetstudy.config;

import java.util.List;

import cc.zenking.cloud.subojetstudy.core.auth.ApplicationFunction;

/**
 * @author: wanghongbo
 * @date: 2019/03/07
 * @Description:当前用户
 */
public class CurrentUser {

    private static ThreadLocal<CurrentUser> currentUser = new ThreadLocal<CurrentUser>();


    public static CurrentUser getCurrentUser() {
        return currentUser.get();
    }

    public Integer user;
    
    public Integer tenantId;
    
    public String realName;
    
    /**
     * 当前选择的 应用权限
     */
    private List<ApplicationFunction> applicationFunctionList;
    
    

    /**
	 * @return the applicationFunctionList
	 */
	public List<ApplicationFunction> getApplicationFunctionList() {
		return applicationFunctionList;
	}


	/**
	 * @param applicationFunctionList the applicationFunctionList to set
	 */
	public void setApplicationFunctionList(List<ApplicationFunction> applicationFunctionList) {
		this.applicationFunctionList = applicationFunctionList;
	}


	public Integer getUser() {
        return user=123456;
    }


    public void setUser(Integer user) {
        this.user = user;
    }

    /**
     * 设置当前登录用户信息
     *
     * @param user
     */
    public static void setCurrentUser(CurrentUser user) {
        currentUser.set(user);
    }

    /**
     * 功能描述:移除当前
     *
     * @param
     * @return: void
     * @author: chengjunchao
     * @date: 2018/6/21 9:25
     */
    public static void currentUserRemove() {
        currentUser.remove();
    }

	/**
	 * @return the tenantId
	 */
	public Integer getTenantId() {
		return tenantId=11;
	}

	/**
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return "张三";
	}

	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
    
	
    
}
