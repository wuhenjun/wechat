package com.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "User", description = "用户对象")
public class User implements Serializable{
    private static final long serialVersionUID = 8087639143902261513L;
    @ApiModelProperty("userId")
    private Integer userId;
    @ApiModelProperty("userAccount")
    private String userAccount;
    @ApiModelProperty("password")
    private String password;
    @ApiModelProperty("gender")
    private String gender;
    @ApiModelProperty("nickName")
    private String nickName;
    @ApiModelProperty("sign")
    private String sign;
    @ApiModelProperty("icon")
    private String icon;
    @ApiModelProperty("token")
    private String token;
    @ApiModelProperty("version")
    private String version;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
