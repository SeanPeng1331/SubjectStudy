package cc.zenking.cloud.subojetstudy.core.entity.enumerate;

/**
 * sys_log 结果状态
 */
public enum ResultStatus {
    RESULT_STATUS("状态"),
    SUCCESS("成功"),
    FAILURE("失败");

    private String name;

    private ResultStatus(String name) {
        this.name = name;
    }

    public String getCnName() {
        return this.name;
    }
}
