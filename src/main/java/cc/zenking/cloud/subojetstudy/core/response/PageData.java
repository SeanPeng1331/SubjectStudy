package cc.zenking.cloud.subojetstudy.core.response;

import lombok.Data;

import java.util.List;

/**
 * Created by chengjunchao on 2018/3/2 9:54.
 */
@Data
public class PageData<T> {
    /**
     * 总条数
     */
    private Integer amount;
    /**
     * 总页数
     */
    private Integer pages;
    /**
     * 状态
     */
    private int status = 0;
    /**
     * 返回结果
     */
    private String reason;
    private List<T> data;
}
