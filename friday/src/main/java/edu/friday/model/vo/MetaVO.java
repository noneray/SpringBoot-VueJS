package edu.friday.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 路由显示信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetaVO {
    /**
     * 设置该路由的名字
     */
    private String title;
    /**
     * 设置该路由的图标
     */
    private String icon;
}
