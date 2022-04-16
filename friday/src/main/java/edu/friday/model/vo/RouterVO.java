package edu.friday.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 路由配置信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterVO {
    /**
     * 路由名字
     */
    private String name;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 是否隐藏路由，当设置true的时候该路由不会在侧边栏出现
     */
    private String hidden;
    /**
     * 重定向地址，当设置noRedirect时，该路由在面包屑导航中不可被单击
     */
    private String redirect;
    /**
     * 组件地址
     */
    private String component;
    /**
     * 当该路由下的子路由大于1个时，变为嵌套的模式
     */
    private Boolean alwaysShow;
    /**
     * 其它元素
     */
    private MetaVO meta;
    /**
     * 子路由
     */
    private List<RouterVO> children;
}
