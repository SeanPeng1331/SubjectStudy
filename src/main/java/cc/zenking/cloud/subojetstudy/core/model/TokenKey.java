package cc.zenking.cloud.subojetstudy.core.model;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: chengjunchao
 * Date: 2018/6/2
 * Time: 15:12
 * Description:token的 加密解密
 */
@Data
public class TokenKey {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 是否退出  -1是退出  0是正常
     */
    private Integer isLogOut;
}
