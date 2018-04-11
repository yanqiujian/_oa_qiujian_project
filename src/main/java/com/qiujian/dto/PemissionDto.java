package com.qiujian.dto;

import java.io.Serializable;

public class PemissionDto implements Serializable{
    private static final long serialVersionUID = 1L;
    private int pmId;
    private String pmName;

    public int getPmId() {
        return pmId;
    }

    public void setPmId(int pmId) {
        this.pmId = pmId;
    }

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PemissionDto that = (PemissionDto) o;

        if (pmId != that.pmId) return false;
        if (pmName != null ? !pmName.equals(that.pmName) : that.pmName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pmId;
        result = 31 * result + (pmName != null ? pmName.hashCode() : 0);
        return result;
    }
}
