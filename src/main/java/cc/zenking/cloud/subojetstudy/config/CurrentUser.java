package cc.zenking.cloud.subojetstudy.config;


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

    public Integer bureau;
    
    public Integer tenantId;

    public Integer getBureau() {
        return bureau;
    }

    public void setBureau(Integer bureau) {
        this.bureau = bureau;
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
    
    
}
