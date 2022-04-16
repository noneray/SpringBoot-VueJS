package edu.friday.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long roleId;

    @NotBlank(message = "角色名称不能为空")
    @Size(max = 30, message = "角色名称长度不能超过30个字符")
    private String roleName;

    @NotBlank(message = "权限字符不能为空")
    @Size(max = 180, message = "权限字符长度不能超过180个字符")
    private String roleKey;

    @NotBlank(message = "显示顺序不能为空")
    private String roleSort;

    private String dataScope;
    private String status;
    private String delFlag;
    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;
    private String updateBy;
    private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;
    private boolean flag;

    private Long[] menuIds;

    public SysRoleVO(Long roleId) {
        this.roleId = roleId;

    }

    public static boolean isAdmin(Long roleId) {
        return roleId != null && 1L == roleId;
    }

    public boolean isAdmin() {
        return isAdmin(this.roleId);
    }

}
