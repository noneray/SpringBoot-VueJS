package edu.friday.service.impl;

import edu.friday.common.constant.UserConstants;
import edu.friday.common.result.TableDataInfo;
import edu.friday.model.SysRole;
import edu.friday.model.vo.SysRoleVO;
import edu.friday.repository.SysRoleRepository;
import edu.friday.service.SysRoleService;
import edu.friday.utils.BeanUtils;
import edu.friday.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    SysRoleRepository sysRoleRepository;

    @Override
    public List<SysRole> selectRoleAll() {
        return sysRoleRepository.findAll();
    }

    @Override
    public List<Long> selectRoleListByUserId(Long userId) {
        return sysRoleRepository.selectRoleIdsByUserId(userId);
    }

    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        List<SysRole> perms = sysRoleRepository.selectRoleByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms) {
            if (StringUtils.isNotNull(perm)) {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public TableDataInfo selectRoleList(SysRoleVO role, Pageable page) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(role, sysRole);
        sysRole.setDelFlag("0");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("roleName", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("roleKey", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<SysRole> example = Example.of(sysRole, exampleMatcher);
        Page<SysRole> rs = sysRoleRepository.findAll(example, page);
        return TableDataInfo.success(rs.toList(), rs.getTotalElements());
    }

    @Override
    public String checkRoleNameUnique(SysRoleVO role) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(role, sysRole);
        return checkUnique(sysRole);
    }

    @Override
    public String checkRoleKeyUnique(SysRoleVO role) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(role, sysRole);
        return checkUnique(sysRole);
    }

    @Override
    @Transactional
    public boolean insertRole(SysRoleVO role) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(role, sysRole);
        sysRole.setDelFlag("0");
        sysRoleRepository.save(sysRole);
        role.setRoleId(sysRole.getRoleId());
        insertRoleMenu(role);
        return null != sysRole.getRoleId();
    }

    @Transactional
    public int insertRoleMenu(SysRoleVO role) {
        Long[] menus = role.getMenuIds();
        if (StringUtils.isNull(menus) || menus.length == 0) {
            return 0;
        }
        Long[] roleIds = new Long[menus.length];
        Arrays.fill(roleIds, role.getRoleId());
        return sysRoleRepository.batchInsertRoleMenu(roleIds, menus);
    }

    public String checkUnique(SysRole role) {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1l : role.getRoleId();
        Example<SysRole> example = Example.of(role);
        SysRole info = findOne(example);
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    public SysRole findOne(Example<SysRole> example) {
        List<SysRole> list = sysRoleRepository.findAll(example, PageRequest.of(0, 1)).toList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    @Transactional
    public int deleteRoleByIds(Long[] roleIds) {
        return sysRoleRepository.deleteRoleByIds(roleIds);
    }

    @Override
    @Transactional
    public boolean updateRole(SysRoleVO role) {
        Long roleId = role.getRoleId();
        Optional<SysRole> op = sysRoleRepository.findById(roleId);
        if (!op.isPresent()) {
            return false;
        }
        SysRole sysRole = op.get();
        BeanUtils.copyPropertiesIgnoreNull(role, sysRole);
        sysRoleRepository.save(sysRole);

        sysRoleRepository.deleteRoleMenuByRoleId(role.getRoleId());

        insertRoleMenu(role);
        return null != sysRole.getRoleId();
    }
}
