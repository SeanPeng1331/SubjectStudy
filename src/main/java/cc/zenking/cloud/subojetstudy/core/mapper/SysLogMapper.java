package cc.zenking.cloud.subojetstudy.core.mapper;

import cc.zenking.cloud.subojetstudy.core.entity.SysLog;
import cc.zenking.cloud.subojetstudy.core.mapper.sql.SysLogSql;
import cc.zenking.cloud.subojetstudy.core.model.syslog.SysLogVO;
import cc.zenking.cloud.subojetstudy.core.request.PageRequest;

import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface SysLogMapper {

    /**
     * 插入sys_log表
     *
     * @param sysLog
     * @return
     */
    @Insert("insert into sys_log (description,log_time,name,log_operator,log_type,tenant_id,flag,platform,result_status) values(#{entity.description},#{entity.logTime},#{entity.name},#{entity.operator},#{entity.type},#{entity.tenantId},0,#{entity.platform},#{entity.resultStatus})")
    int add(@Param("entity") SysLog sysLog);

    /**
     * 日志列表
     * @param userId
     * @param keyword
     * @param pageRequest
     * @param tenantId
     * @return
     */
    @SelectProvider(type = SysLogSql.class, method = "findSysLog")
    List<SysLogVO> findSysLogList(@Param("userId") Integer userId, @Param("keyword") String keyword, @Param("page") PageRequest pageRequest, @Param("tenantId") Integer tenantId);

    @SelectProvider(type = SysLogSql.class, method = "findSysLogCount")
    Integer findSysLogCount(@Param("userId") Integer userId,@Param("keyword") String keyword,@Param("tenantId") Integer tenantId);


}