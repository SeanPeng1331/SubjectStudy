package cc.zenking.cloud.subojetstudy.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.zenking.cloud.subojetstudy.core.constant.ErrorCode;
import cc.zenking.cloud.subojetstudy.core.model.syslog.SysLogVO;
import cc.zenking.cloud.subojetstudy.core.request.PageRequest;
import cc.zenking.cloud.subojetstudy.core.response.JsonResponse;
import cc.zenking.cloud.subojetstudy.core.service.syslog.SysLogService;
import lombok.extern.log4j.Log4j;

/**
 * @Author: wanghongbo
 * @Date: 2019/3/14
 * @Description:
 */
@Log4j
@RestController
@RequestMapping(value="/syslog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 日志列表
     *
     * @return
     */
    @RequestMapping(value="/findSysLogList")
    public ResponseEntity<Object> findPicturePlayList(PageRequest pageRequest, String keyword) {
        List<SysLogVO> list = null;
        Integer count = null;
        try {
            count = sysLogService.findSysLogCount(keyword);
            list = sysLogService.findSysLogList(keyword, pageRequest);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询日志列表失败！");
            return JsonResponse.fail(ErrorCode.ERROR_CODE_1005);
        }
        return JsonResponse.success(list, count, pageRequest.countTatalPage(count));
    }
}
