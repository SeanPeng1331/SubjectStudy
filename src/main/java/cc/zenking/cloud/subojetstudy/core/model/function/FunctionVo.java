/**  
* <p>Title: FunctionVo.java</p>
* <p>Description: </p> 
* @author pxx 
* @date 2019年4月4日  
*/  
package cc.zenking.cloud.subojetstudy.core.model.function;

import java.util.ArrayList;
import java.util.List;

import cc.zenking.cloud.subojetstudy.core.entity.Function;
import lombok.Data;

/**  
* <p>Title: FunctionVo</p>  
* <p>Description: </p>  
* @author pxx  
* @date 2019年4月4日  
*/
@Data
public class FunctionVo extends Function{
	
	/**
     * 主键
     */
    private Integer id;
    /**
     * key 菜单key
     */
    private String functionKey;
    /**
     * parent_id 父ID
     */
    private Integer parentId;
    /**
     * is_menu 是否菜单
     */
    private boolean menu;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * uri地址
     */
    private String url;


    private List<FunctionVo> childs = new ArrayList<>();
    
    
    @Override
    public void loadData(Function function) {
        this.id = function.getId();
        this.functionKey = function.getFunctionKey();
        this.parentId = function.getParentId();
        this.menu = function.getIsMenu();
        this.name = function.getName();
        this.url = function.getUrl();
    }
    
}
