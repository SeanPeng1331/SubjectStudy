package cc.zenking.cloud.subojetstudy.core.mapper.sql;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cc.zenking.cloud.subojetstudy.core.constant.Const;

/**
 * @Author: wanghongbo
 * @Date: 2019/3/14
 * @Description:
 */
public class SysLogSql {
    public String findSysLog(Map map) {
        String keyword = (String) map.get("keyword");
        StringBuilder sb = new StringBuilder();
        sb.append("select id,concat(name,' ',description) as description,log_time as logTime,log_operator as creator from sys_log where flag <>-1 AND platform IN('"+ Const.SYSTEM_NAME+"','"+Const.SYSTEM_LOGIN+"') AND log_operator=#{userId} AND tenant_id=#{tenantId} ");
        if(!StringUtils.isEmpty(keyword)){
            sb.append(" and description like '"+keyword+"%'");
        }
        sb.append("  ORDER BY logTime DESC Limit #{page.countOffset},#{page.limit}");
        return sb.toString();
    }
    public String findSysLogCount(Map map) {
        String keyword = (String) map.get("keyword");
        StringBuilder sb = new StringBuilder();
        sb.append("select count(1) from sys_log where flag <>-1 AND platform IN('"+ Const.SYSTEM_NAME+"','"+ Const.SYSTEM_LOGIN +"') AND log_operator=#{userId}  AND tenant_id=#{tenantId} ");
        if(!StringUtils.isEmpty(keyword)){
            sb.append(" and description like '"+keyword+"%'");
        }
        return sb.toString();
    }

}
