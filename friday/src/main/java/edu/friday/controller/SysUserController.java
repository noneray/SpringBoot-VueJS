package edu.friday.controller;


import edu.friday.common.base.BaseController;
import edu.friday.common.constant.UserConstants;
import edu.friday.common.result.RestResult;
import edu.friday.common.result.TableDataInfo;
import edu.friday.model.vo.SysUserVO;
import edu.friday.service.SysRoleService;
import edu.friday.service.SysUserService;
import edu.friday.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
    @Autowired
    SysUserService userService;
    @Autowired
    private SysRoleService roleService;

    @GetMapping("/list")
    public TableDataInfo list(SysUserVO user, Pageable page) {
        int pageNum = page.getPageNumber() - 1;
        pageNum = Math.max(pageNum, 0);
        page = PageRequest.of(pageNum, page.getPageSize());
        return userService.selectUserList(user, page);
    }

    @PostMapping
    public RestResult add(@RequestBody SysUserVO user) {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))) {
            return RestResult.error("新增用户" + user.getUserName() + "失败，登陆账号已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return RestResult.error("新增用户" + user.getUserName() + "失败，手机号码已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return RestResult.error("新增用户" + user.getUserName() + "失败，邮箱账号已存在");
        }
        user.setCreateBy("system");
        boolean flag = userService.insertUser(user);
        return toAjax(flag ? 1 : 0);
    }

    @DeleteMapping("/{userIds}")
    public RestResult remove(@PathVariable Long[] userIds) {
        return toAjax(userService.deleteUserByIds(userIds));
    }

    @PutMapping
    public RestResult edit(@Validated @RequestBody SysUserVO user) {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return RestResult.error("修改用户" + user.getUserName() + "失败，手机号码已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return RestResult.error("修改用户" + user.getUserName() + "失败，邮箱账号已存在");
        }
        user.setUpdateBy("system");
        boolean flag = userService.updateUser(user);
        return toAjax(flag ? 1 : 0);
    }

    @GetMapping(value = {"/", "/{userId}"})
    public RestResult getInfo(@PathVariable(value = "userId", required = false) Long userId) {
        RestResult ajax = RestResult.success();
        ajax.put("roles", roleService.selectRoleAll());
        if (StringUtils.isNotNull(userId)) {
            ajax.put(RestResult.DATA_TAG, userService.selectUserById(userId));
            ajax.put("roleIds", roleService.selectRoleListByUserId(userId));
        }
        return ajax;
    }
}
