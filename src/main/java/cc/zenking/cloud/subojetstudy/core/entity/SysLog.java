package cc.zenking.cloud.subojetstudy.core.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: chengjunchao
 * Date: 2018/5/31
 * Time: 20:25
 * Description:日志
 */
@Data
public class SysLog {

    private Integer id;
    /**
     * 平台类型
     */
    private String platform;
    /**
     * 结果状态 ResultStatus
     */
    private Integer resultStatus;
    /**
     * 操作员
     */
    private Integer operator;
    /**
     * 时间
     */
    private Date logTime;
    /**
     * 分类
     */
    private String type;
    /**
     * 操作名称
     */
    private String name;
    /**
     * 内容
     */
    private String description;
    /**
     * 机构id
     */
    private Integer tenantId;
    /**
     * 是否删除 0正常-1删除
     */
    private int flag;
}
