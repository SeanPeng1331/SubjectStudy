package cc.zenking.cloud.subojetstudy.core.request;

import lombok.Data;

/**
 * Created by chengjunchao on 2018/3/2 10:10.
 * 分页请求参数
 */
@Data
public class PageRequest {
    /**
     * 当前页面
     */
    private Integer page;
    /**
     * 页大小
     */
    private Integer limit;


    @SuppressWarnings("unused")
	private Integer countOffset;

    /**
     * 计算当前页开始的记录
     *
     * @return 当前页开始记录号
     */
    public Integer getCountOffset() {
        final int offset = limit * (page - 1);
        return offset;
    }

    /**
     * 计算总页数
     *
     * @return 总页数
     */
    public int countTatalPage(final int allRow) {
        int toalPage = allRow % limit == 0 ? allRow / limit : allRow / limit + 1;
        return toalPage;
    }
}
