package cc.zenking.cloud.subojetstudy.core.model;

import lombok.Data;

/**
 * @Author: chengjunchao
 * @Date: 2018/11/15 16:32
 * @Description: 接口返回参数
 */
@Data
public class Result {
    /**
     * 状态
     */
    private int status;
    /**
     * 原因
     */
    private String reason;
}