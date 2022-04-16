package edu.friday.controller;

import edu.friday.common.base.BaseController;
import edu.friday.common.constant.UserConstants;
import edu.friday.common.result.RestResult;
import edu.friday.common.result.TableDataInfo;
import edu.friday.model.vo.SysRoleVO;
import edu.friday.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {
    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("/list")
    public TableDataInfo list(SysRoleVO role, Pageable page) {
        int pageNum = page.getPageNumber() - 1;
        pageNum = Math.max(0, pageNum);
        page = PageRequest.of(pageNum, page.getPageSize());
        return sysRoleService.selectRoleList(role, page);
    }

    @PostMapping
    public RestResult add(@Validated @RequestBody SysRoleVO role) {
        if (UserConstants.NOT_UNIQUE.equals(sysRoleService.checkRoleNameUnique(role))) {
            return RestResult.error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(sysRoleService.checkRoleKeyUnique(role))) {
            return RestResult.error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setCreateBy("system");
        boolean flag = sysRoleService.insertRole(role);
        return toAjax(flag ? 1 : 0);
    }

    @DeleteMapping("/{roleIds}")
    public RestResult remove(@PathVariable Long[] roleIds) {
        return toAjax(sysRoleService.deleteRoleByIds(roleIds));
    }

    @PutMapping
    public RestResult edit(@Validated @RequestBody SysRoleVO role) {
        if (UserConstants.NOT_UNIQUE.equals(sysRoleService.checkRoleNameUnique(role))) {
            return RestResult.error("修改角色" + role.getRoleName() + "失败，角色名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(sysRoleService.checkRoleKeyUnique(role))) {
            return RestResult.error("修改角色" + role.getRoleName() + "失败，角色权限已存在");
        }
        role.setUpdateBy("system");
        boolean flag = sysRoleService.updateRole(role);
        return toAjax(flag ? 1 : 0);
    }
}
