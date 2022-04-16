package edu.friday.common.exception.user;

public class UserPasswordNotMatchException extends UserException {
    public static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
