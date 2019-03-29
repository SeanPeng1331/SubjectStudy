package cc.zenking.cloud.subojetstudy.core.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cc.zenking.cloud.subojetstudy.core.constant.ErrorCode;

/**
 * @author chengjunchao
 * 成功 status 是 0 ，失败 status 是 小于0
 */
public class JsonResponse {

    /**
     * 成功 message默认OK
     *
     * @param data
     * @return
     */
    public static <T> ResponseEntity<Object> success(T data) {
        return response(data, "成功", JsonData.CODE_SUCCESS);
    }

    /**
     * 成功
     *
     * @param data
     * @param message
     * @return
     */
    public static <T> ResponseEntity<Object> success(T data, String message) {
        return response(data, message, JsonData.CODE_SUCCESS);
    }

    /**
     * 成功 message默认OK
     *
     * @return
     */
    public static <T> ResponseEntity<Object> success() {
        return response(null, "成功", JsonData.CODE_SUCCESS);
    }

    /**
     * 失败
     *
     * @param code 错误码
     * @return
     */
    public static ResponseEntity<Object> fail(Integer code) {
        return response(null, ErrorCode.getMsg(code), -code);
    }
    
    private static <T> ResponseEntity<Object> response(T data, String msg, int code) {
        JsonData<T> jd = new JsonData();
        jd.setData(data);
        jd.setStatus(code);
        jd.setReason(msg);
        return new ResponseEntity<Object>(jd, HttpStatus.OK);
    }

    /**
     * 分页 返回成功
     *
     * @param data
     * @param amount 总条数
     * @param pages 总页数
     * @param <T>
     * @return
     */
    public static <T> ResponseEntity<Object> success(List<T> data, Integer amount, Integer pages) {
        return pageResponse(data, amount, pages, JsonData.CODE_SUCCESS, "成功");
    }

    /**
     * 分页返回失败
     *
     * @param data
     * @param amount
     * @param pages
     * @param status 错误码
     * @param <T>
     * @return
     */
    public static <T> ResponseEntity<Object> fail(List<T> data, Integer amount, Integer pages, Integer status) {
        return pageResponse(data, amount, pages, -status, ErrorCode.getMsg(status));
    }

    public static <T> ResponseEntity<Object> pageResponse(List<T> data, Integer amount, Integer pages, int status, String msg) {
        PageData<T> pageData = new PageData<>();
        pageData.setData(data);
        pageData.setAmount(amount);
        pageData.setPages(pages);
        pageData.setStatus(status);
        pageData.setReason(msg);
        return new ResponseEntity<Object>(pageData, HttpStatus.OK);
    }
}
