package cc.zenking.cloud.subojetstudy.core.constant;

/**
 * 需要用到的枚举值 固定的  不需要在properties中配置的
 * Created by cjc on 2016/12/29.
 */
public class Const {

    public static String ADD = "add";
    public static String UPDATE = "update";

    /**
     * 轮播图
     */
    public static class PicturePlay {
        public static final Integer ON_LINE = 1;
        public static final Integer OFF_LINE = 0;
        //允许上线数量
        public static final Integer ON_LINE_NUM = 5;
    }

    /**
     * 应用
     */
    public static class Application {
        public static final Integer RECOMMENDED = 1;
        public static final Integer NO_RECOMMENDED = 0;
    }

    /**
     * 友情链接
     */
    public static class FriendshipLink {
        public static final Integer ON_LINE = 1;
        public static final Integer OFF_LINE = 0;
    }
}
