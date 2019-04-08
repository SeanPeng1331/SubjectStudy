package cc.zenking.cloud.subojetstudy.core.auth;

import java.util.List;

import cc.zenking.cloud.subojetstudy.core.entity.Function;
import lombok.Data;

/**
 * @Author: chengjunchao
 * @Date: 2019/4/4 11:47
 * @Description: 应用权限
 */
@Data
public class ApplicationFunction {
	/**
     * 应用id
     */
    private Integer applicationId;
	 /**
     * 应用别名
     */
    private String applicationKey;
	
    /**
     * 应用
     */
    private List<Function> applicationFunctionList;
}
