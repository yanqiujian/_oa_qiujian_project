package com.qiujian.dto;

import java.io.Serializable;
import java.util.Set;

public class RolesDto implements Serializable{
    private static final long serialVersionUID = 1L;
    private int rolesId;
    private String rolesName;
    private Set<PemissionDto> pemissionDtos;

    public Set<PemissionDto> getPemissionDtos() {
        return pemissionDtos;
    }

    public void setPemissionDtos(Set<PemissionDto> pemissionDtos) {
        this.pemissionDtos = pemissionDtos;
    }

    public int getRolesId() {
        return rolesId;
    }

    public void setRolesId(int rolesId) {
        this.rolesId = rolesId;
    }

    public String getRolesName() {
        return rolesName;
    }

    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolesDto rolesDto = (RolesDto) o;

        if (rolesId != rolesDto.rolesId) return false;
        if (rolesName != null ? !rolesName.equals(rolesDto.rolesName) : rolesDto.rolesName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rolesId;
        result = 31 * result + (rolesName != null ? rolesName.hashCode() : 0);
        return result;
    }
}
