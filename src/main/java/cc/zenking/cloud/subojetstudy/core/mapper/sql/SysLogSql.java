package cc.zenking.cloud.subojetstudy.core.mapper.sql;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * @Author: wanghongbo
 * @Date: 2019/3/14
 * @Description:
 */
public class SysLogSql {
    public String findSysLog(Map map) {
        String keyword = (String) map.get("keyword");
        StringBuilder sb = new StringBuilder();
        sb.append("select id,description,log_time as logTime,log_operator as operator from sys_log where flag in (0,1) and operating='mhwz' AND log_operator=#{userId}");
        if(!StringUtils.isEmpty(keyword)){
            sb.append(" and description like '%"+keyword+"%'");
        }
        sb.append("  ORDER BY logTime DESC Limit #{page.countOffset},#{page.limit}");
        return sb.toString();
    }
    public String findSysLogCount(Map map) {
        String keyword = (String) map.get("keyword");
        StringBuilder sb = new StringBuilder();
        sb.append("select count(1) from sys_log where flag in (0,1) and operating='ktyj' AND log_operator=#{userId}");
        if(!StringUtils.isEmpty(keyword)){
            sb.append(" and description like '%"+keyword+"%'");
        }
        return sb.toString();
    }
}
