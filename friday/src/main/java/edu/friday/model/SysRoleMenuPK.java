package edu.friday.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SysRoleMenuPK implements Serializable {
    private long roleId;
    private long menuId;

    @Column(name = "role_id")
    @Id
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Column(name = "menu_id")
    @Id
    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRoleMenuPK that = (SysRoleMenuPK) o;
        return roleId == that.roleId &&
                menuId == that.menuId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, menuId);
    }
}
