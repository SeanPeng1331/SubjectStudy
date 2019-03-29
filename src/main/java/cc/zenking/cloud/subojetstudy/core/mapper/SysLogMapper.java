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
    @Insert("insert into sys_log (description,log_time,name,log_operator,log_type,user_type,flag,platform,operating,operating_type,result_status) values(#{entity.description},#{entity.logTime},#{entity.name},#{entity.operator},#{entity.type},#{entity.userType},0,#{entity.platform},#{entity.operating},#{entity.operatingType},#{entity.resultStatus})")
    int add(@Param("entity") SysLog sysLog);
    
    @SelectProvider(type = SysLogSql.class, method = "findSysLog")
    List<SysLogVO> findSysLogList(@Param("userId") Integer userId,@Param("keyword") String keyword,@Param("page") PageRequest pageRequest);

    @SelectProvider(type = SysLogSql.class, method = "findSysLogCount")
    Integer findSysLogCount(@Param("userId") Integer userId,@Param("keyword") String keyword);
}