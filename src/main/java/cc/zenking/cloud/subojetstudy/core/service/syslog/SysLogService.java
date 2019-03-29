package cc.zenking.cloud.subojetstudy.core.service.syslog;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.zenking.cloud.subojetstudy.config.CurrentUser;
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

    /**
     * 添加日志
     * @param operator
     * @param type
     * @param name
     * @param description
     * @param userType
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int addLog(String operator, String type, String name, String description, String userType) {
        SysLog sysLog = new SysLog();
        sysLog.setOperator(operator);
        sysLog.setType(type);
        sysLog.setName(name);
        sysLog.setDescription(description);
        sysLog.setPlatform("课题研究后台管理");
        sysLog.setOperating("ktyj");
        sysLog.setUserType(userType);
        sysLog.setLogTime(new Date());
        if (sysLog.getOperatingType() == null) {
            sysLog.setOperatingType(OperatingType.OPERATING.ordinal());
        }
        if (sysLog.getResultStatus() == null) {
            sysLog.setResultStatus(ResultStatus.RESULT_STATUS.ordinal());
        }
        int n = sysLogMapper.add(sysLog);
        return n;
    }
    
    /**
     * 看看日志
     * @param keyword
     * @return
     */
    public List<SysLogVO> findSysLogList(String keyword, PageRequest pageRequest){
        Integer userId = CurrentUser.getCurrentUser().getUser();
        return  sysLogMapper.findSysLogList(userId,keyword,pageRequest);
    }

    /**
     * 日志数量
     * @param keyword
     * @return
     */
    public Integer findSysLogCount(String keyword){
        Integer userId = CurrentUser.getCurrentUser().getUser();
        return sysLogMapper.findSysLogCount(userId,keyword);
    }
}
