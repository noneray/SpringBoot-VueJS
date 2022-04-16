package edu.friday.common.exception.user;

import edu.friday.common.exception.BaseException;

/**
 * 用户信息异常类
 */
public class UserException extends BaseException {
    public static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
