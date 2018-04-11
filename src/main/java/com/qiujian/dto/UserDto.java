package com.qiujian.dto;

import java.io.Serializable;
import java.util.Set;

public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private int userId;
    private String userName;
    private String userGanwei;
    private String userPassword;
    private Set<RolesDto> rolesDtos;

    public Set<RolesDto> getRolesDtos() {
        return rolesDtos;
    }

    public void setRolesDtos(Set<RolesDto> rolesDtos) {
        this.rolesDtos = rolesDtos;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGanwei() {
        return userGanwei;
    }

    public void setUserGanwei(String userGanwei) {
        this.userGanwei = userGanwei;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        if (userId != userDto.userId) return false;
        if (userName != null ? !userName.equals(userDto.userName) : userDto.userName != null) return false;
        if (userGanwei != null ? !userGanwei.equals(userDto.userGanwei) : userDto.userGanwei != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userGanwei != null ? userGanwei.hashCode() : 0);
        return result;
    }
}
