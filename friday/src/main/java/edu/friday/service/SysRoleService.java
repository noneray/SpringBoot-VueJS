package edu.friday.service;

import edu.friday.common.result.TableDataInfo;
import edu.friday.model.SysRole;
import edu.friday.model.vo.SysRoleVO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface SysRoleService {
    List<SysRole> selectRoleAll();

    public TableDataInfo selectRoleList(SysRoleVO role, Pageable page);

    List<Long> selectRoleListByUserId(Long userId);

    Set<String> selectRolePermissionByUserId(Long userId);

    String checkRoleNameUnique(SysRoleVO role);

    String checkRoleKeyUnique(SysRoleVO role);

    boolean insertRole(SysRoleVO role);

    int deleteRoleByIds(Long[] roleIds);

    boolean updateRole(SysRoleVO role);
}
