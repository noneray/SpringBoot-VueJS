package edu.friday.model;

import edu.friday.common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 菜单权限表 sys_menu
 */
@Entity
@Table(name = "sys_menu", schema = "friday")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysMenu extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;
    private String menuName;
    private Long parentId;
    private String orderNum;
    private String path;
    private String component;
    private String isFrame;
    private String menuType;
    private String visible;
    private String perms;
    private String icon;
    private String createBy;
    private Timestamp createTime;
    private String updateBy;
    private Timestamp updateTime;
    private String remark;

    @Basic
    @Column(name = "menu_id")
    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Basic
    @Column(name = "menu_name")
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Basic
    @Column(name = "parent_id")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "order_num")
    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    @Basic
    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "component")
    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    @Basic
    @Column(name = "is_frame")
    public String getIsFrame() {
        return isFrame;
    }

    public void setIsFrame(String isFrame) {
        this.isFrame = isFrame;
    }

    @Basic
    @Column(name = "menu_type")
    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    @Basic
    @Column(name = "visible")
    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    @Basic
    @Column(name = "perms")
    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    @Basic
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "create_by")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_by")
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysMenu sysMenu = (SysMenu) o;
        return menuId == sysMenu.menuId &&
                Objects.equals(menuName, sysMenu.menuName) &&
                Objects.equals(parentId, sysMenu.parentId) &&
                Objects.equals(orderNum, sysMenu.orderNum) &&
                Objects.equals(path, sysMenu.path) &&
                Objects.equals(component, sysMenu.component) &&
                Objects.equals(isFrame, sysMenu.isFrame) &&
                Objects.equals(menuType, sysMenu.menuType) &&
                Objects.equals(visible, sysMenu.visible) &&
                Objects.equals(perms, sysMenu.perms) &&
                Objects.equals(icon, sysMenu.icon) &&
                Objects.equals(createBy, sysMenu.createBy) &&
                Objects.equals(createTime, sysMenu.createTime) &&
                Objects.equals(updateBy, sysMenu.updateBy) &&
                Objects.equals(updateTime, sysMenu.updateTime) &&
                Objects.equals(remark, sysMenu.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, menuName, parentId, orderNum, path, component, isFrame, menuType, visible, perms, icon, createBy, createTime, updateBy, updateTime, remark);
    }
}
