package cc.zenking.cloud.subojetstudy.core.constant;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

/**
 * 错误码
 *
 * @author cjc
 * @date 2016-08-01
 */
public class ErrorCode {
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("errorcode");
    // 系统登录有关
    // 参数有误
    public static Integer ERROR_CODE_1000 = 1000;
    // 系统繁忙，请稍后尝试
    public static Integer ERROR_CODE_1001 = 1001;
    // 申请课题失败
    public static Integer ERROR_CODE_1002 = 1002;
    // 只能修改自己申报的课题
    public static Integer ERROR_CODE_1003 = 1003;
    //只能删除自己申报的课题
    public static Integer ERROR_CODE_1004 = 1004;
    //查询日志列表失败!
    public static Integer ERROR_CODE_1005 = 1005;
    //查询课题列表失败！
    public static Integer ERROR_CODE_1006 = 1006;
    //修改课题失败！
    public static Integer ERROR_CODE_1007 = 1007;
    //删除课题失败！
    public static Integer ERROR_CODE_1008 = 1008;
    //提交成果或报告失败！
    public static Integer ERROR_CODE_1009 = 1009;
    //修改成果获报告失败!
    public static Integer ERROR_CODE_1010 = 1010;
    //查看详情页面失败!
    public static Integer ERROR_CODE_1011 = 1011;
    //查询课题研究菜单失败!
    public static Integer ERROR_CODE_1012 = 1012;
    //查询系统按钮失败！
    public static Integer ERROR_CODE_1013 = 1013;

    /**
     * 根据错误码返回错误信息 错误码在errordoe.preperties中，错误码code不包含前缀erroe_code_。
     * 如：errordoe中的error_code_11001，在调用该方法直接传11001即可。
     *
     * @param code 错误码
     * @return String
     */
    public static String getMsg(Integer code) {

        try {
            return new String(RESOURCE_BUNDLE.getString("error_code_" + code).getBytes("ISO8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
