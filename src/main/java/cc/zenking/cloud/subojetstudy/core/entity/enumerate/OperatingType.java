package cc.zenking.cloud.subojetstudy.core.entity.enumerate;

/**
 * sys_log使用 枚举
 */
public enum OperatingType {
    OPERATING("操作"),
    INSERT("添加"),
    UPDATE("修改"),
    INSERT_UPDATE("添加或修改"),
    DELETE("删除"),
    IMPORT("导入"),
    IMPORT_INSERT("导入添加"),
    IMPORT_UPDATE("导入修改"),
    LOGIN("登录"),
    LOGOUT("登出");

    private String name;

    private OperatingType(String name) {
        this.name = name;
    }

    public String getCnName() {
        return this.name;
    }
}
