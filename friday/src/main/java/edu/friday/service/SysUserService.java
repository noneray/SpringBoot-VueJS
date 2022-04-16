package edu.friday.service;

import edu.friday.common.result.TableDataInfo;
import edu.friday.model.SysUser;
import edu.friday.model.vo.SysUserVO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;

public interface SysUserService {
    public TableDataInfo selectUserList(SysUserVO user, Pageable page);

    String checkUserNameUnique(String userName);

    String checkPhoneUnique(SysUserVO userInfo);

    String checkEmailUnique(SysUserVO userInfo);

    String count(SysUser sysUser);

    String checkUnique(SysUser user);

    SysUser findOne(Example<SysUser> example);

    boolean insertUser(SysUserVO user);

    int deleteUserByIds(Long[] userIds);

    boolean updateUser(SysUserVO user);

    SysUser selectUserById(Long userId);

    SysUser selectUserByUserName(String userName);
}
