package cc.zenking.cloud.subojetstudy.core.entity;

import java.util.Date;

import lombok.Data;

/**
 * @Author: chengjunchao
 * @Date: 2019/3/22 13:39
 * @Description: 权限表  sys_function
 */
@Data
public class Function {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 权限key  function_key
     */
    private String functionKey;
    /**
     * 权限名
     */
    private String name;
    /**
     * 权限描述
     */
    private String description;
    /**
     * 是否是菜单  is_menu
     */
    private Boolean isMenu;
    /**
     * 是否是应用 is_app
     */
    private Boolean isApp;
    /**
     * uri资源
     */
    private String url;
    /**
     * 父id parent_id
     */
    private Integer parentId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 删除字段  0 正常 ，-1 删除
     */
    private Integer flag;
    /**
     * 创建人
     */
    private Integer creator;
    /**
     * 创建时间 create_time
     */
    private Date created;
    /**
     * 最后一次更新时间 lastop_time
     */
    private Date updated;
    
    public void loadData(Function function) {
        this.id = function.getId();
        this.functionKey = function.getFunctionKey();
        this.parentId = function.getParentId();
        this.isMenu = function.getIsMenu();
        this.name = function.getName();
        this.url = function.getUrl();
    }
}
