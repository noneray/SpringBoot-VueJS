package edu.friday.model;

import edu.friday.common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "sys_user", schema = "friday")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Basic
    @Column(name = "user_name")
    private String userName;

    @Basic
    @Column(name = "nick_name")
    private String nickName;

    @Basic
    @Column(name = "user_type")
    private String userType;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "phonenumber")
    private String phonenumber;

    @Basic
    @Column(name = "sex")
    private String sex;

    @Basic
    @Column(name = "avatar")
    private String avatar;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "status")
    private String status;

    @Basic
    @Column(name = "del_flag")
    private String delFlag;

    @Basic
    @Column(name = "login_ip")
    private String loginip;

    @Basic
    @Column(name = "login_date")
    private Timestamp loginDate;

    @Basic
    @Column(name = "create_by")
    private String createBy;

    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;

    @Basic
    @Column(name = "update_by")
    private String updateBy;

    @Basic
    @Column(name = "update_time")
    private Timestamp updateTime;

    @Basic
    @Column(name = "remark")
    private String remark;
}

//package edu.friday.model;
//
//import javax.persistence.*;
//import java.sql.Timestamp;
//import java.util.Objects;
//
//@Entity
//@Table(name = "sys_user", schema = "friday", catalog = "")
//public class SysUser {
//    private long userId;
//    private String userName;
//    private String nickName;
//    private String userType;
//    private String email;
//    private String phonenumber;
//    private String sex;
//    private String avatar;
//    private String password;
//    private String status;
//    private String delFlag;
//    private String loginIp;
//    private Timestamp loginDate;
//    private String createBy;
//    private Timestamp createTime;
//    private String updateBy;
//    private Timestamp updateTime;
//    private String remark;
//
//    @Id
//    @Column(name = "user_id")
//    public long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(long userId) {
//        this.userId = userId;
//    }
//
//    @Basic
//    @Column(name = "user_name")
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    @Basic
//    @Column(name = "nick_name")
//    public String getNickName() {
//        return nickName;
//    }
//
//    public void setNickName(String nickName) {
//        this.nickName = nickName;
//    }
//
//    @Basic
//    @Column(name = "user_type")
//    public String getUserType() {
//        return userType;
//    }
//
//    public void setUserType(String userType) {
//        this.userType = userType;
//    }
//
//    @Basic
//    @Column(name = "email")
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    @Basic
//    @Column(name = "phonenumber")
//    public String getPhonenumber() {
//        return phonenumber;
//    }
//
//    public void setPhonenumber(String phonenumber) {
//        this.phonenumber = phonenumber;
//    }
//
//    @Basic
//    @Column(name = "sex")
//    public String getSex() {
//        return sex;
//    }
//
//    public void setSex(String sex) {
//        this.sex = sex;
//    }
//
//    @Basic
//    @Column(name = "avatar")
//    public String getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(String avatar) {
//        this.avatar = avatar;
//    }
//
//    @Basic
//    @Column(name = "password")
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    @Basic
//    @Column(name = "status")
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    @Basic
//    @Column(name = "del_flag")
//    public String getDelFlag() {
//        return delFlag;
//    }
//
//    public void setDelFlag(String delFlag) {
//        this.delFlag = delFlag;
//    }
//
//    @Basic
//    @Column(name = "login_ip")
//    public String getLoginIp() {
//        return loginIp;
//    }
//
//    public void setLoginIp(String loginIp) {
//        this.loginIp = loginIp;
//    }
//
//    @Basic
//    @Column(name = "login_date")
//    public Timestamp getLoginDate() {
//        return loginDate;
//    }
//
//    public void setLoginDate(Timestamp loginDate) {
//        this.loginDate = loginDate;
//    }
//
//    @Basic
//    @Column(name = "create_by")
//    public String getCreateBy() {
//        return createBy;
//    }
//
//    public void setCreateBy(String createBy) {
//        this.createBy = createBy;
//    }
//
//    @Basic
//    @Column(name = "create_time")
//    public Timestamp getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Timestamp createTime) {
//        this.createTime = createTime;
//    }
//
//    @Basic
//    @Column(name = "update_by")
//    public String getUpdateBy() {
//        return updateBy;
//    }
//
//    public void setUpdateBy(String updateBy) {
//        this.updateBy = updateBy;
//    }
//
//    @Basic
//    @Column(name = "update_time")
//    public Timestamp getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(Timestamp updateTime) {
//        this.updateTime = updateTime;
//    }
//
//    @Basic
//    @Column(name = "remark")
//    public String getRemark() {
//        return remark;
//    }
//
//    public void setRemark(String remark) {
//        this.remark = remark;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        SysUser sysUser = (SysUser) o;
//        return userId == sysUser.userId &&
//                Objects.equals(userName, sysUser.userName) &&
//                Objects.equals(nickName, sysUser.nickName) &&
//                Objects.equals(userType, sysUser.userType) &&
//                Objects.equals(email, sysUser.email) &&
//                Objects.equals(phonenumber, sysUser.phonenumber) &&
//                Objects.equals(sex, sysUser.sex) &&
//                Objects.equals(avatar, sysUser.avatar) &&
//                Objects.equals(password, sysUser.password) &&
//                Objects.equals(status, sysUser.status) &&
//                Objects.equals(delFlag, sysUser.delFlag) &&
//                Objects.equals(loginIp, sysUser.loginIp) &&
//                Objects.equals(loginDate, sysUser.loginDate) &&
//                Objects.equals(createBy, sysUser.createBy) &&
//                Objects.equals(createTime, sysUser.createTime) &&
//                Objects.equals(updateBy, sysUser.updateBy) &&
//                Objects.equals(updateTime, sysUser.updateTime) &&
//                Objects.equals(remark, sysUser.remark);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, userName, nickName, userType, email, phonenumber, sex, avatar, password, status, delFlag, loginIp, loginDate, createBy, createTime, updateBy, updateTime, remark);
//    }
//}
