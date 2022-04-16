package edu.friday.model.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 菜单权限表 sys_menu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysMenuVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 菜单ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menuId")
    private Long menuId;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    @Size(max = 50, message = "菜单名称长度不能超过69个字符")
    private String menuName;

    /**
     * 父菜单名称
     */
    private String parentName;
    /**
     * 父菜单ID
     */
    private Long parentId;
    /**
     * 显示顺序
     */
    @NotBlank(message = "显示顺序不能为空")
    private String orderNum;
    /**
     * 路由地址
     */
    @Size(max = 200, message = "路由地址不能超过200个字符")
    private String path;
    /**
     * 组件路径
     */
    @Size(max = 255, message = "组件路径不能超过255个字符")
    private String component;
    /**
     * 是否为外链（0是1否）
     */
    private String isFrame;
    /**
     * 类型（M目录C菜单F按钮）
     */
    @NotBlank(message = "菜单类型不能为空")
    private String menuType;
    /**
     * 菜单状态：0显示，1隐藏
     */
    private String visible;
    /**
     * 权限字符串
     */
    @Size(max = 100, message = "权限标识长度不能超过100个字符")
    private String perms;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 子菜单
     */
    private List<SysMenuVO> children = new ArrayList<>();
}
