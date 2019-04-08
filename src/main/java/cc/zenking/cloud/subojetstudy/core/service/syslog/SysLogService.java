package cc.zenking.cloud.subojetstudy.core.service.syslog;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.zenking.cloud.subojetstudy.config.CurrentUser;
import cc.zenking.cloud.subojetstudy.core.constant.Const;
import cc.zenking.cloud.subojetstudy.core.entity.SysLog;
import cc.zenking.cloud.subojetstudy.core.entity.enumerate.OperatingType;
import cc.zenking.cloud.subojetstudy.core.entity.enumerate.ResultStatus;
import cc.zenking.cloud.subojetstudy.core.mapper.SysLogMapper;
import cc.zenking.cloud.subojetstudy.core.model.syslog.SysLogVO;
import cc.zenking.cloud.subojetstudy.core.request.PageRequest;

/**
 * Created with IntelliJ IDEA.
 * User: wanghongbo
 * Date: 2018/6/1
 * Time: 11:11
 * Description:
 */
@Service
public class SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

//    @Autowired
//    private UserMapper userMapper;

    /**
     * 添加日志
     *
     * @param operator
     * @param type
     * @param name
     * @param description
     * @param userType
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int addLog(Integer operator, String type, String name, String description, Integer tenantId) {
        SysLog sysLog = new SysLog();
        sysLog.setOperator(operator);
        sysLog.setType(type);
        sysLog.setName(name);
        sysLog.setDescription(description);
        sysLog.setPlatform(Const.SYSTEM_NAME);
        sysLog.setTenantId(tenantId);
        sysLog.setLogTime(new Date());
        if (sysLog.getResultStatus() == null) {
            sysLog.setResultStatus(ResultStatus.RESULT_STATUS.ordinal());
        }
        int n = sysLogMapper.add(sysLog);
        return n;
    }

    /**
     * 看看日志
     *
     * @param keyword
     * @return
     */
    public List<SysLogVO> findSysLogList(String keyword, PageRequest pageRequest) {
        Integer userId = CurrentUser.getCurrentUser().getUser();
        Integer tenantId = CurrentUser.getCurrentUser().getTenantId();
        String realName = CurrentUser.getCurrentUser().getRealName();
        List<SysLogVO> list = sysLogMapper.findSysLogList(userId, keyword, pageRequest, tenantId);
        if(null != realName){
            for (SysLogVO v : list) {
                v.setOperator(realName);
            }
        }
        return list;
    }

    /**
     * 分页总数量
     *
     * @param keyword
     * @return
     */
    public Integer findSysLogCount(String keyword) {
        Integer userId = CurrentUser.getCurrentUser().getUser();
        Integer tenantId = CurrentUser.getCurrentUser().getTenantId();
        return sysLogMapper.findSysLogCount(userId, keyword, tenantId);
    }
}
