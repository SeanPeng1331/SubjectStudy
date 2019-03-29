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
     * 具体操作名称（功能或模块名）
     */
    private String operating;
    /**
     * 操作类型  OperatingType
     */
    private Integer operatingType;
    /**
     * 结果状态 ResultStatus
     */
    private Integer resultStatus;
    /**
     * 操作员
     */
    private String operator;
    /**
     * 时间
     */
    private Date logTime;
    /**
     * 分类
     */
    private String type;
    /**
     * 日志名(分组）
     */
    private String name;
    /**
     * 内容
     */
    private String description;
    /**
     * 用户类型
     */
    private String userType;
    /**
     * 标记，用于数据恢复
     * 0：普通日志
     * 1：可恢复日志	此属性为‘1’时，name的值为类名加该对象ID，如：cc.zenking.edu.teacher.Teacher:1
     * 2：数据已恢复
     */
    private int flag;
}
