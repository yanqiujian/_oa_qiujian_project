package com.qiujian.dto;

import java.io.Serializable;
import java.util.Set;

public class BumenDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer bmId;
    private String bmName;
    private Set<UserDto> userDtos;

    public Set<UserDto> getUserDtos() {
        return userDtos;
    }

    public void setUserDtos(Set<UserDto> userDtos) {
        this.userDtos = userDtos;
    }

    public Integer getBmId() {
        return bmId;
    }

    public void setBmId(Integer bmId) {
        this.bmId = bmId;
    }

    public String getBmName() {
        return bmName;
    }

    public void setBmName(String bmName) {
        this.bmName = bmName;
    }
}
