package cc.zenking.cloud.subojetstudy.core.model.syslog;

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
public class SysLogVO {
    private Integer id;

    /**
     * 操作员
     */
    private String operator;
    /**
     * 时间
     */
    private Date logTime;

    /**
     * 内容
     */
    private String description;

}
