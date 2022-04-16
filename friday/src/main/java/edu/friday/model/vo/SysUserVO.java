package edu.friday.model.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户对象 sys_user
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private Long userId;


    @NotBlank(message = "用户账号不能为空")
    @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
    private String userName;

    @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
    private String nickName;

    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    private String email;

    @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
    private String phonenumber;

    private String sex, userType, avatar;

    @JsonProperty
    private String password;

    private String salt, status, delFlag, loginIp;

    private Date loginDate;

    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String remark;

    private List<SysRoleVO> roles;

    private Long[] roleIds;

    public SysUserVO(Long userId) {
        this.userId = userId;
    }

    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    public boolean isAdmin() {
        return isAdmin(this.userId);
    }
}
