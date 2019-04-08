package cc.zenking.cloud.subojetstudy.core.auth;

import java.util.List;

import lombok.Data;

/**
 * @Author: chengjunchao
 * @Date: 2019/4/4 11:36
 * @Description: 网关CurrentUser
 */
@Data
public class GateCurrentUser {
    /**
     * 用户id
     */
    private Integer user;
    /**
     * 用户姓名
     */
    private String realName;
    /**
     * 当前选择的 组织机构id
     */
    private Integer tenantId;
    /**
     * 当前选择的 应用权限
     */
    private List<ApplicationFunction> applicationFunctionList;
}
