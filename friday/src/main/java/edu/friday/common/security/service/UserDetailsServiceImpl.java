package edu.friday.common.security.service;

import edu.friday.common.enums.UserStatus;
import edu.friday.common.security.LoginUser;
import edu.friday.model.SysUser;
import edu.friday.model.vo.SysUserVO;
import edu.friday.service.SysUserService;
import edu.friday.utils.BeanUtils;
import edu.friday.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.selectUserByUserName(username);
        if (StringUtils.isNull(user)) {
            log.info("登录用户：{}不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + "不存在");
        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            log.info("登录用户：{}已被删除.", username);
            throw new UsernameNotFoundException("对不起，您的账号：" + username + "已被删除");
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{}已被停用.", username);
            throw new UsernameNotFoundException("对不起，您的账号：" + username + "已被停用");
        }
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user) {
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(user, sysUserVO);
        return new LoginUser(permissionService.getMenuPermission(sysUserVO), sysUserVO);
    }
}
