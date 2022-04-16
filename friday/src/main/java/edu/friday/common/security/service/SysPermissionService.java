package edu.friday.common.security.service;

import edu.friday.model.vo.SysUserVO;
import edu.friday.service.SysMenuService;
import edu.friday.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SysPermissionService {
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysMenuService menuService;

    public Set<String> getRolePermission(SysUserVO user) {
        Set<String> roles = new HashSet<String>();

        if (user.isAdmin()) {
            roles.add("admin");
        } else {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getUserId()));
        }
        return roles;
    }

    public Set<String> getMenuPermission(SysUserVO user) {
        Set<String> roles = new HashSet<String>();
        if (user.isAdmin()) {
            roles.add("*:*:*");
        } else {
            roles.addAll(menuService.selectMenuPermsByUserId(user.getUserId()));
        }
        return roles;
    }
}
