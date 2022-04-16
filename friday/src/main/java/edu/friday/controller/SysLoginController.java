package edu.friday.controller;

import edu.friday.common.constant.Constants;
import edu.friday.common.result.RestResult;
import edu.friday.common.security.LoginUser;
import edu.friday.common.security.service.SysLoginService;
import edu.friday.common.security.service.SysPermissionService;
import edu.friday.common.security.service.TokenService;
import edu.friday.model.vo.SysMenuVO;
import edu.friday.model.vo.SysUserVO;
import edu.friday.service.SysMenuService;
import edu.friday.utils.http.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * 登录验证
 */
@RestController
public class SysLoginController {
    @Autowired
    private SysLoginService loginService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysPermissionService permissionService;
    @Autowired
    private SysMenuService menuService;

    /**
     * 登录方法
     *
     * @param username 用户名
     * @param password 密码
     * @return 结果
     */
    @PostMapping({"/login", "/"})
    public RestResult login(String username, String password, String uuid) {
        RestResult ajax = RestResult.success();
        //生成令牌
        String token = loginService.login(username, password, "123", uuid);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @GetMapping("getInfo")
    public RestResult getInfo() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUserVO user = loginUser.getUser();
        Set<String> roles = permissionService.getMenuPermission(user);
        Set<String> permissions = permissionService.getMenuPermission(user);

        RestResult ajax = RestResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public RestResult getRouters() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        //用户信息
        SysUserVO user = loginUser.getUser();
        List<SysMenuVO> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return RestResult.success(menuService.buildMenus(menus));
    }
}
